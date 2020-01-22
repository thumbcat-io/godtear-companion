//
//  CohortsViewController.swift
//  GTCompanion
//
//  Copyright © 2020 Thumbcat Software Solutions, LLC. All rights reserved.
//

import UIKit
import MultiPlatformLibrary

class CohortsViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    
    private let cellIdentifier = "CohortsCell"
    
    private let tableView = UITableView()
    
    weak var settingsViewController: SettingsViewController?
    var cohorts: [Int: [Int: Cohort]]!

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
        tableView.dataSource = self
        tableView.delegate = self
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: cellIdentifier)
        setBackgroundColors()
        let cohortsByType = Dictionary(grouping: SerializableCohortsKt.cohorts().sorted {
            $0.champion.name < $1.champion.name
        }) { (cohort: Cohort) -> String in
            cohort.classification.name
        }
        let types = Array(cohortsByType.keys).sorted {
            $0 < $1
        }
        var cohorts: [Int: [Int: Cohort]] = [:]
        for i in 0..<types.count {
            let type: String = types[i]
            let cohortsOfType: [Cohort] = cohortsByType[type]!
            var indexedCohorts: [Int: Cohort] = [:]
            for j in 0..<cohortsOfType.count {
                indexedCohorts[j] = cohortsOfType[j]
            }
            cohorts[i] = indexedCohorts
        }
        self.cohorts = cohorts
        self.navigationItem.leftBarButtonItem = UIBarButtonItem(barButtonSystemItem: .bookmarks, target: self, action: #selector(showSettingsView))
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

    func numberOfSections(in tableView: UITableView) -> Int {
        cohorts.keys.count
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        cohorts[section]!.keys.count
    }

    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        cohorts[section]![0]!.classification.name
    }

    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let cohort: Cohort = cohorts[indexPath.section]![indexPath.row]!
        let cohortViewController = CohortViewController()
        cohortViewController.title = cohort.champion.name
        cohortViewController.cohort = cohort
        navigationController!.pushViewController(cohortViewController, animated: true)
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: cellIdentifier, for: indexPath)
        cell.textLabel?.text = cohorts[indexPath.section]![indexPath.row]!.name
        return cell
    }
    
    @objc
    func showSettingsView() {
        if settingsViewController == nil {
            fatalError("settingsViewController was not set")
        }
        navigationController?.pushViewController(settingsViewController!, animated: true)
    }
}

extension CohortsViewController {
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
