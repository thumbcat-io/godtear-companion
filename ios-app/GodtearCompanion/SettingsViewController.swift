//
//  SettingsViewController.swift
//  GodtearCompanion
//
//  Copyright © 2020 Thumbcat Software Solutions, LLC. All rights reserved.
//

import UIKit
import MultiPlatformLibrary

fileprivate struct SettingsViewRow {
    let text: String
    let textColor: UIColor?
    let textAlignment: NSTextAlignment?
    let font: UIFont?
    let isUserInteractionEnabled: Bool?
    let cellIdentifier: String
    let includeSeparator: Bool
    let url: URL?
}

fileprivate struct SettingsViewSection {
    let text: String
    let rows: [Int: SettingsViewRow]
}

class SettingsViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    
    private let titleCellIdentifier = "SettingsTitleCell"
    private let textCellIdentifier = "SettingsTextCell"
    private let staticTextCellIdentifier = "SettingsStaticTextCell"
    
    private var sections: [Int: SettingsViewSection]!

    let infoTableView = UITableView()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        title = "About"
        view.addSubview(infoTableView)
        sections = [
            0: SettingsViewSection(text: "", rows: [
                0: SettingsViewRow(
                    text: "Godtear Companion",
                    textColor: nil,
                    textAlignment: .center,
                    font: UIFont.boldSystemFont(ofSize: 22),
                    isUserInteractionEnabled: false,
                    cellIdentifier: titleCellIdentifier,
                    includeSeparator: false,
                    url: nil
                ),
                1: SettingsViewRow(
                    text: "v \(Bundle.main.object(forInfoDictionaryKey: "CFBundleShortVersionString") ?? "unknown") (\(Bundle.main.object(forInfoDictionaryKey: "CFBundleVersion") ?? "unknown"))",
                    textColor: .gray,
                    textAlignment: .center,
                    font: UIFont.boldSystemFont(ofSize: 12),
                    isUserInteractionEnabled: false,
                    cellIdentifier: titleCellIdentifier,
                    includeSeparator: false,
                    url: nil
                )
            ]),
            1: SettingsViewSection(text: "SUPPORT", rows: [
                0: SettingsViewRow(
                    text: "Godtear Companion Website",
                    textColor: nil,
                    textAlignment: nil,
                    font: nil,
                    isUserInteractionEnabled: nil,
                    cellIdentifier: textCellIdentifier,
                    includeSeparator: true,
                    url: URL(string: "https://thumbcat.io/godtear-companion")
                ),
                1: SettingsViewRow(
                    text: "Contact Support",
                    textColor: nil,
                    textAlignment: nil,
                    font: nil,
                    isUserInteractionEnabled: nil,
                    cellIdentifier: textCellIdentifier,
                    includeSeparator: true,
                    url: URL(string: "mailto:godtear@thumbcat.io")
                ),
                2: SettingsViewRow(
                    text: "GitHub",
                    textColor: nil,
                    textAlignment: nil,
                    font: nil,
                    isUserInteractionEnabled: nil,
                    cellIdentifier: textCellIdentifier,
                    includeSeparator: true,
                    url: URL(string: "https://github.com/thumbcat-io/godtear-companion")
                ),
                3: SettingsViewRow(
                    text: "Godtear Companion is an open source project licensed under the Apache License, Version 2.0.",
                    textColor: nil,
                    textAlignment: nil,
                    font: nil,
                    isUserInteractionEnabled: nil,
                    cellIdentifier: staticTextCellIdentifier,
                    includeSeparator: true,
                    url: nil
                )
            ]),
            2: SettingsViewSection(text: "GODTEAR", rows: [
                0: SettingsViewRow(
                    text: "Official Godtear Website",
                    textColor: nil,
                    textAlignment: nil,
                    font: nil,
                    isUserInteractionEnabled: nil,
                    cellIdentifier: textCellIdentifier,
                    includeSeparator: true,
                    url: URL(string: "https://steamforged.com/godtear")
                ),
                1: SettingsViewRow(
                    text: "Godtear, Associated Logos, Cards and Terms are © 2020 Steamforged Games Ltd.",
                    textColor: nil,
                    textAlignment: nil,
                    font: nil,
                    isUserInteractionEnabled: nil,
                    cellIdentifier: staticTextCellIdentifier,
                    includeSeparator: false,
                    url: nil
                ),
                2: SettingsViewRow(
                    text: "Godtear Companion © Copyright 2020 Thumbcat Software Solutions, LLC.",
                    textColor: nil,
                    textAlignment: nil,
                    font: nil,
                    isUserInteractionEnabled: nil,
                    cellIdentifier: staticTextCellIdentifier,
                    includeSeparator: false,
                    url: nil
                )
            ])
        ]
        infoTableView.translatesAutoresizingMaskIntoConstraints = false
        infoTableView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor).isActive = true
        infoTableView.leftAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leftAnchor).isActive = true
        infoTableView.rightAnchor.constraint(equalTo: view.safeAreaLayoutGuide.rightAnchor).isActive = true
        infoTableView.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor).isActive = true
        infoTableView.register(UITableViewCell.self, forCellReuseIdentifier: textCellIdentifier)
        infoTableView.register(UITableViewCell.self, forCellReuseIdentifier: titleCellIdentifier)
        infoTableView.register(StaticTextCell.self, forCellReuseIdentifier: staticTextCellIdentifier)
        infoTableView.separatorStyle = .none
        infoTableView.dataSource = self
        infoTableView.delegate = self
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        sections.count
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        sections[section]?.rows.count ?? 0
    }

    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        sections[section]?.text
    }

    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let row = sections[indexPath.section]?.rows[indexPath.row]
        tableView.deselectAllRows(animated: false)
        if let url = row?.url {
            UIApplication.shared.open(url)
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if indexPath.section == 0 && indexPath.row == 0 {
            return 20
        } else {
            return tableView.rowHeight
        }
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        50
    }
    
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        if let title = self.tableView(tableView, titleForHeaderInSection: section) {
            let view = UIView(frame: CGRect(x: 0, y: 0, width: tableView.bounds.width, height: 50))
            let lbl = UILabel(frame: CGRect(x: 15, y: 15, width: tableView.bounds.width, height: 50))
            lbl.font = UIFont.systemFont(ofSize: 12)
            lbl.textColor = .gray
            lbl.text = title
            view.addSubview(lbl)
            return view
        }
        return nil
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let lineSeparator = UIView(frame: CGRect(x: 0, y: 0, width: tableView.bounds.width, height: 0.5))
        lineSeparator.backgroundColor = infoTableView.separatorColor
        let cell: UITableViewCell!
        let row = sections[indexPath.section]!.rows[indexPath.row]!
        if row.cellIdentifier == staticTextCellIdentifier {
            cell = tableView.dequeueReusableCell(withIdentifier: row.cellIdentifier, for: indexPath)
            (cell as! StaticTextCell).staticText = row.text
            if row.includeSeparator {
                cell.addSubview(lineSeparator)
            }
        } else {
            cell = tableView.dequeueReusableCell(withIdentifier: row.cellIdentifier, for: indexPath)
            if row.includeSeparator {
                cell.addSubview(lineSeparator)
            }
            cell.textLabel?.text = row.text
            if let font = row.font {
                cell.textLabel?.font = font
            }
            if let textAlignment = row.textAlignment {
                cell.textLabel?.textAlignment = textAlignment
            }
            if let font = row.font {
                cell.textLabel?.font = font
            }
            if let textColor = row.textColor {
                cell.textLabel?.textColor = textColor
            }
            if let isUserInteractionEnabled = row.isUserInteractionEnabled {
                cell.isUserInteractionEnabled = isUserInteractionEnabled
            }
        }
        return cell
    }
}
