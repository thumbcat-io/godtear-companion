//
//  WarbandExplorerViewController.swift
//  GTCompanion
//
//  Copyright © 2020 Thumbcat Software Solutions, LLC. All rights reserved.
//

import UIKit
import MultiPlatformLibrary

fileprivate let cohortCellIdentifier = "WarbandExplorerCell"

class WarbandExplorerViewController: UIViewController {
    let tableView: UITableView = .init()
    let stateController: StateController
    let settingsViewController: SettingsViewController
    let exploreWarbandButton: UIBarButtonItem
    private var viewModel: WarbandExplorerViewModel!
    
    init(
        stateController: StateController,
        settingsViewController: SettingsViewController
    ) {
        self.stateController = stateController
        self.settingsViewController = settingsViewController
        self.exploreWarbandButton = UIBarButtonItem(
            title: "View",
            style: .plain,
            target: nil,
            action: nil
        )
        super.init(nibName: nil, bundle: nil)
        updateExploreWarbandButtonState()
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        
        let eventsDispatcher = EventsDispatcher<WarbandExplorerViewModelWarbandSelectionListener>(listener: self)
        viewModel = WarbandExplorerViewModel(eventsDispatcher: eventsDispatcher)
        viewModel.cohorts.addObserver { c in
            guard let cohorts = c else {
                print("nil cohorts")
                return
            }
            if cohorts.count > 0 {
                print("\(cohorts.count) cohorts")
                self.tableView.reloadData()
            } else {
                print("zero cohorts")
            }
        }
        
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
        tableView.allowsMultipleSelection = true
        setBackgroundColors()
        tableView.dataSource = viewModel
        tableView.delegate = self
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: cohortCellIdentifier)
        self.navigationItem.leftBarButtonItem = UIBarButtonItem(
            barButtonSystemItem: .bookmarks,
            target: self,
            action: #selector(showSettingsView)
        )
        self.exploreWarbandButton.target = self
        self.exploreWarbandButton.action = #selector(routeToWarbandExplorer)
        self.navigationItem.rightBarButtonItem = exploreWarbandButton
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
    
    @objc
    func routeToWarbandExplorer() {
        viewModel.onWarbandSelected(cohorts: stateController.warbandExplorerWarband)
    }
    
    override func didMove(toParent parent: UIViewController?) {
        if parent == nil {
            viewModel.onCleared()
        }
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

extension WarbandExplorerViewModel {
    public func data() -> [Int: [Int: WarbandExplorerCohort]] {
        self.cohorts.value as? [Int: [Int: WarbandExplorerCohort]] ?? [:]
    }
}

extension WarbandExplorerViewModel : UITableViewDataSource {
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
        let cohort: WarbandExplorerCohort = data()[indexPath.section]![indexPath.row]!
        let cell = tableView.dequeueReusableCell(withIdentifier: cohortCellIdentifier, for: indexPath)
        cell.textLabel?.text = cohort.championName
        return cell
    }
}

extension WarbandExplorerViewController : UITableViewDelegate {
    func tableView(_ tableView: UITableView, willSelectRowAt indexPath: IndexPath) -> IndexPath? {
        if isMaxRowsSelected() {
            return nil
        }
        return indexPath
    }
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let cohort: WarbandExplorerCohort = viewModel.data()[indexPath.section]![indexPath.row]!
        stateController.selectWarbandExplorerCohort(cohort: cohort)
        updateExploreWarbandButtonState()
    }
    
    func tableView(_ tableView: UITableView, didDeselectRowAt indexPath: IndexPath) {
        let cohort: WarbandExplorerCohort = viewModel.data()[indexPath.section]![indexPath.row]!
        tableView.deselectRow(at: indexPath, animated: false)
        stateController.deselectWarbandExplorerCohort(cohort: cohort)
        updateExploreWarbandButtonState()
    }
}

extension WarbandExplorerViewController {
    func isMaxRowsSelected() -> Bool {
        stateController.warbandExplorerWarband.count == viewModel.maxSelectedRows
    }
    func isExploreWarbandButtonEnabled() -> Bool {
        stateController.warbandExplorerWarband.count > 0
    }
    func updateExploreWarbandButtonState() {
        self.exploreWarbandButton.isEnabled = isExploreWarbandButtonEnabled()
    }
}

extension WarbandExplorerViewController {
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

extension WarbandExplorerViewController : WarbandExplorerViewModelWarbandSelectionListener {
    func routeToWarbandView(cohorts: [WarbandExplorerCohort]) {
        let controller = WEWarbandViewController(stateController: self.stateController)
        controller.title = "Warband"
        navigationController!.pushViewController(controller, animated: true)
    }
}
