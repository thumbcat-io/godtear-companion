//
//  SceneDelegate.swift
//  GTCompanion
//
//  Created by Nicholas J Halase on 2/16/20.
//  Copyright © 2020 Nicholas J Halase. All rights reserved.
//

import UIKit

@available(iOS 13.0, *)
class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?
    let stateController = VolatileStateController()

    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        if let windowScene = scene as? UIWindowScene {
            self.window = UIWindow(windowScene: windowScene)
            
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
            
            self.window?.rootViewController = tabBarController
            self.window!.makeKeyAndVisible()
        }
    }

    func sceneDidDisconnect(_ scene: UIScene) {
        // Called as the scene is being released by the system.
        // This occurs shortly after the scene enters the background, or when its session is discarded.
        // Release any resources associated with this scene that can be re-created the next time the scene connects.
        // The scene may re-connect later, as its session was not neccessarily discarded (see `application:didDiscardSceneSessions` instead).
    }

    func sceneDidBecomeActive(_ scene: UIScene) {
        // Called when the scene has moved from an inactive state to an active state.
        // Use this method to restart any tasks that were paused (or not yet started) when the scene was inactive.
    }

    func sceneWillResignActive(_ scene: UIScene) {
        // Called when the scene will move from an active state to an inactive state.
        // This may occur due to temporary interruptions (ex. an incoming phone call).
    }

    func sceneWillEnterForeground(_ scene: UIScene) {
        // Called as the scene transitions from the background to the foreground.
        // Use this method to undo the changes made on entering the background.
    }

    func sceneDidEnterBackground(_ scene: UIScene) {
        // Called as the scene transitions from the foreground to the background.
        // Use this method to save data, release shared resources, and store enough scene-specific state information
        // to restore the scene back to its current state.
    }
}
