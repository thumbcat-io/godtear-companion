//
//  CECohortRosterViewController.swift
//  GTCompanion
//
//  Copyright © 2020 Thumbcat Software Solutions, LLC. All rights reserved.
//

import UIKit
import MultiPlatformLibrary

fileprivate let cohortRosterCohortCell = "CohortRosterCohortCell"

class CECohortRosterViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    let tableView: UITableView = .init()
    let stateController: StateController
    let cohort: CohortsExplorerCohort
    
    init(
        stateController: StateController,
        cohort: CohortsExplorerCohort
    ) {
        self.stateController = stateController
        self.cohort = cohort
        super.init(nibName: nil, bundle: nil)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        setBackgroundColors()
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
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: cohortRosterCohortCell)
        tableView.dataSource = self
        tableView.delegate = self
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        setBackgroundColors()
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        tableView.deselectAllRows(animated: false)
    }

    override func traitCollectionDidChange(_ previousTraitCollection: UITraitCollection?) {
        super.traitCollectionDidChange(previousTraitCollection)
        if #available(iOS 13.0, *) {
            if previousTraitCollection?.hasDifferentColorAppearance(comparedTo: traitCollection) ?? false {
                setBackgroundColors()
            }
        } else {
            // Fallback on earlier versions
        }
    }

    func numberOfSections(in tableView: UITableView) -> Int {
        1
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        2
    }

    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let isChampionStart = indexPath.row == 0
        let cohortViewController = CECohortViewController(
            stateController: stateController,
            cohort: cohort,
            isChampionStart: isChampionStart
        )
        let title: String
        if isChampionStart {
            title = cohort.championName
        } else {
            title = cohort.followerName
        }
        cohortViewController.title = title
        navigationController!.pushViewController(cohortViewController, animated: true)
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: cohortRosterCohortCell, for: indexPath)
        if indexPath.row == 0 {
            cell.textLabel?.text = cohort.championName
        } else {
            cell.textLabel?.text = cohort.followerName
        }
        return cell
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

extension CECohortRosterViewController {
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
