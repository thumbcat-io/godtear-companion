//
//  AppDelegate.swift
//  GTCompanion
//
//  Created by Nicholas J Halase on 2/16/20.
//  Copyright © 2020 Nicholas J Halase. All rights reserved.
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
            
            let cohortsViewController = CohortsExplorerViewController(
                stateController: stateController,
                settingsViewController: settingsViewController
            )
            cohortsViewController.title = "Cohorts"
            
            let cohortsTabBarItem = UITabBarItem(title: "Browse", image: UIImage(systemName: "person.2"), tag: 0)
            cohortsViewController.tabBarItem = cohortsTabBarItem

            let controllers = [cohortsViewController]
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
