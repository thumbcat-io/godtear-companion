//
//  CohortsExplorerViewController.swift
//  GTCompanion
//
//  Copyright © 2020 Thumbcat Software Solutions, LLC. All rights reserved.
//

import UIKit
import MultiPlatformLibrary

fileprivate let cohortCellIdentifier = "CohortsExplorerCell"

class CohortsExplorerViewController: UIViewController {
    let tableView: UITableView = .init()
    let stateController: StateController
    let settingsViewController: SettingsViewController
    
    init(
        stateController: StateController,
        settingsViewController: SettingsViewController
    ) {
        self.stateController = stateController
        self.settingsViewController = settingsViewController
        super.init(nibName: nil, bundle: nil)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        view.addSubview(tableView)
        tableView.anchor(
            top: view.safeAreaLayoutGuide.topAnchor,
            left: view.safeAreaLayoutGuide.leftAnchor,
            bottom: view.safeAreaLayoutGuide.bottomAnchor,
            right: view.safeAreaLayoutGuide.rightAnchor,
            paddingTop: 0,
            paddingLeft: 0,
            paddingBottom: 0,
            paddingRight: 0,
            width: 0,
            height: 0,
            enableInsets: false
        )
//        tableView.allowsMultipleSelection = true
        setBackgroundColors()
        let eventsDispatcher = EventsDispatcher<CohortsExplorerViewModelCohortRowSelectionListener>(listener: self)
        let dataSource = CohortsExplorerViewModel(eventsDispatcher: eventsDispatcher) { data in
            self.tableView.reloadData()
        }
        tableView.dataSource = dataSource
        tableView.delegate = self
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: cohortCellIdentifier)
        self.navigationItem.leftBarButtonItem = UIBarButtonItem(
            barButtonSystemItem: .bookmarks,
            target: self,
            action: #selector(showSettingsView)
        )
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        setBackgroundColors()
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        self.tableView.deselectAllRows(animated: false)
    }
    
    override func traitCollectionDidChange(_ previousTraitCollection: UITraitCollection?) {
        super.traitCollectionDidChange(previousTraitCollection)
        if #available(iOS 13.0, *) {
            if previousTraitCollection?.hasDifferentColorAppearance(comparedTo: self.traitCollection) ?? false {
                setBackgroundColors()
            }
        } else {
            // Fallback on earlier versions
        }
    }
    
    @objc
    func showSettingsView() {
        navigationController?.pushViewController(settingsViewController, animated: true)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

extension CohortsExplorerViewModel {
    public func data() -> [Int: [Int: CohortsExplorerRowData]] {
        self.cohorts.value as? [Int: [Int: CohortsExplorerRowData]] ?? [:]
    }
}

extension CohortsExplorerViewModel : UITableViewDataSource {
    public func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        data()[section]!.keys.count
    }

    public func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        data()[section]![0]!.categoryName
    }
    
    public func numberOfSections(in tableView: UITableView) -> Int {
        data().keys.count
    }
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cohort: CohortsExplorerRowData = data()[indexPath.section]![indexPath.row]!
        let cell = tableView.dequeueReusableCell(withIdentifier: cohortCellIdentifier, for: indexPath)
        cell.textLabel?.text = cohort.championName
        return cell
    }
}

extension CohortsExplorerViewController : UITableViewDelegate {
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        guard let dataSource = tableView.dataSource as? CohortsExplorerViewModel else {
            fatalError("failed to cast dataSource to CohortsExplorerViewModel")
        }
        let cohort: CohortsExplorerRowData = dataSource.data()[indexPath.section]![indexPath.row]!
        dataSource.onCohortRowSelected(cohort: cohort)
    }
    
    func tableView(_ tableView: UITableView, didDeselectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: false)
    }
}

extension CohortsExplorerViewController {
    func setBackgroundColors() {
        if traitCollection.userInterfaceStyle == .dark {
            view.backgroundColor = .black
            navigationController?.navigationBar.backgroundColor = .black
            tabBarController?.view.backgroundColor = .black
        } else {
            view.backgroundColor = .white
            navigationController?.navigationBar.backgroundColor = .lightGray
            tabBarController?.view.backgroundColor = .lightGray
        }
    }
}

extension CohortsExplorerViewController : CohortsExplorerViewModelCohortRowSelectionListener {
    public func routeToWarbandView(cohort: CohortsExplorerRowData) {
        let cohortRosterViewController = CohortRosterViewController(
            stateController: stateController,
            cohort: cohort
        )
        cohortRosterViewController.title = cohort.championName
        navigationController!.pushViewController(cohortRosterViewController, animated: true)
    }
}
