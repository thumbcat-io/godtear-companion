//
//  CohortViewController.swift
//  GodtearCompanion
//
//  Copyright © 2020 Thumbcat Software Solutions, LLC. All rights reserved.
//

import UIKit
import MultiPlatformLibrary

class CohortViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    
    private let cellIdentifier = "CohortCell"
    
    private let tableView = UITableView()
    
    weak var cohort: Cohort!

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
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: cellIdentifier)
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
        let warbandViewController = WarbandViewController(isChampionStart: isChampionStart)
        warbandViewController.cohort = cohort
        let title: String
        if isChampionStart {
            title = cohort.champion.name
        } else {
            title = cohort.follower.name
        }
        warbandViewController.title = title
        navigationController!.pushViewController(warbandViewController, animated: true)
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: cellIdentifier, for: indexPath)
        if indexPath.row == 0 {
            cell.textLabel?.text = cohort.champion.name
        } else {
            cell.textLabel?.text = cohort.follower.name
        }
        return cell
    }
}

extension CohortViewController {
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
