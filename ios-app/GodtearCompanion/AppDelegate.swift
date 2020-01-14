//
//  AppDelegate.swift
//  Godtear Companion
//
//  Copyright © 2020 Thumbcat Software Solutions, LLC. All rights reserved.
//

import MultiPlatformLibrary
import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    
    var window: UIWindow?
    
    private let tabBarController = UITabBarController()
    private let settingsViewController = SettingsViewController()
    private let cohortsViewController = CohortsViewController()

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        
        cohortsViewController.title = "Cohorts"
        cohortsViewController.settingsViewController = self.settingsViewController

        if #available(iOS 13.0, *) {
            let cohortsTabBarItem = UITabBarItem(title: "Browse", image: UIImage(systemName: "person.2"), tag: 0)
            cohortsViewController.tabBarItem = cohortsTabBarItem
        } else {
            let cohortsTabBarItem = UITabBarItem(tabBarSystemItem: .mostViewed, tag: 0)
            cohortsTabBarItem.title = "Browse"
            cohortsViewController.tabBarItem = cohortsTabBarItem
        }

        let controllers = [cohortsViewController]
        tabBarController.viewControllers = controllers.map {
            UINavigationController(rootViewController: $0)
        }
        
        window = UIWindow(frame: UIScreen.main.bounds)
        window?.rootViewController = tabBarController
        window?.makeKeyAndVisible()
        
        return true
    }
}
