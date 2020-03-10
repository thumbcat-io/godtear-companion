//
//  AppDelegate.swift
//  GTCompanion
//
//  Copyright © 2020 Thumbcat Software Solutions, LLC. All rights reserved.
//

import UIKit
import MultiPlatformLibrary

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    
    var window: UIWindow?
    let stateController = VolatileStateController()

    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
    ) -> Bool {
        // Override point for customization after application launch.
        KoiniOSKt.doInitKoin()
        
        if #available(iOS 13, *) {
            // do only pure app launch stuff, not interface stuff
        } else {
            let tabBarController = UITabBarController()
            let settingsViewController = SettingsViewController()
            
            let cohortsExplorerViewController = CohortsExplorerViewController(
                stateController: stateController,
                settingsViewController: settingsViewController
            )
            cohortsExplorerViewController.title = "Cohorts Explorer"
            
            let cohortsExplorerTabBarItem = UITabBarItem(title: "Explore", image: UIImage(systemName: "person.2"), tag: 0)
            cohortsExplorerViewController.tabBarItem = cohortsExplorerTabBarItem
            
            let warbandExplorerViewController = WarbandExplorerViewController(
                stateController: stateController,
                settingsViewController: settingsViewController
            )
            warbandExplorerViewController.title = "Warband Explorer"
            
            let warbandsExplorerTabBarItem = UITabBarItem(title: "Warband", image: UIImage(systemName: "person.2"), tag: 1)
            warbandExplorerViewController.tabBarItem = warbandsExplorerTabBarItem

            let controllers = [cohortsExplorerViewController, warbandExplorerViewController]
            tabBarController.viewControllers = controllers.map {
                UINavigationController(rootViewController: $0)
            }
            
            window = UIWindow(frame: UIScreen.main.bounds)
            window?.rootViewController = tabBarController
            window?.makeKeyAndVisible()
        }
        return true
    }
}
