//
//  CECohortViewController.swift
//  GTCompanion
//
//  Copyright © 2020 Thumbcat Software Solutions, LLC. All rights reserved.
//

import UIKit
import MultiPlatformLibrary

class CECohortViewController: UIViewController, UIScrollViewDelegate {
    private let stateController: StateController
    private var scrollView: UIScrollView!
    private var championScrollView: UIScrollView!
    private var followerScrollView: UIScrollView!
    private let traitViewController = CETraitViewController()
    
    var championImages: [UIImage]!
    var followerImages: [UIImage]!
    
    let cohort: CohortsExplorerCohort
    let isChampionStart: Bool
    
    init(
        stateController: StateController,
        cohort: CohortsExplorerCohort,
        isChampionStart: Bool
    ) {
        self.stateController = stateController
        self.cohort = cohort
        self.isChampionStart = isChampionStart
        super.init(nibName: nil, bundle: nil)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        let mainFrame = getCalculatedMainFrame()
        setBackgroundColors()
        navigationItem.rightBarButtonItem = UIBarButtonItem(
            barButtonSystemItem: .organize,
            target: self,
            action: #selector(showTraitView)
        )
        
        championImages = cohort.championImages
        followerImages = cohort.followerImages
        
        scrollView = UIScrollView(frame: mainFrame)
        scrollView.isPagingEnabled = true
        scrollView.showsVerticalScrollIndicator = false
        scrollView.showsHorizontalScrollIndicator = false
        scrollView.delegate = self
        scrollView.contentSize = CGSize(
            width: UIScreen.main.bounds.width,
            height: mainFrame.height * 2
        )
        view.addSubview(scrollView)
        
        let startY: CGFloat
        if isChampionStart {
            startY = scrollView.bounds.origin.y
        } else {
            startY = scrollView.frame.height
        }
        
        championScrollView = UIScrollView(frame:
            CGRect(
                x: 0,
                y: scrollView.bounds.origin.y,
                width: scrollView.frame.width,
                height: scrollView.frame.height
            )
        )
        championScrollView.isPagingEnabled = true
        championScrollView.showsVerticalScrollIndicator = false
        championScrollView.showsHorizontalScrollIndicator = false
        championScrollView.delegate = self
        scrollView.addSubview(championScrollView)
        
        for i in 0..<championImages.count {
            let image: UIImage = championImages[i]
            let oSize: CGSize = image.size
            let oWidth = oSize.width
            let scale = scrollView.frame.width / oWidth
            let width = oWidth * scale

            let imageView = UIImageView(image: image)
            imageView.tag = i
            let xPosition = UIScreen.main.bounds.width * CGFloat(i)
            imageView.frame = CGRect(
                x: xPosition,
                y: 0,
                width: width,
                height: scrollView.frame.height
            )
            championScrollView.contentSize.width = width * CGFloat(i + 1)
            championScrollView.addSubview(imageView)
            imageView.contentMode = .scaleAspectFit
        }
        
        followerScrollView = UIScrollView(frame:
            CGRect(
                x: 0,
                y: scrollView.frame.height,
                width: scrollView.frame.width,
                height: scrollView.frame.height
            )
        )
        followerScrollView.isPagingEnabled = true
        followerScrollView.showsVerticalScrollIndicator = false
        followerScrollView.showsHorizontalScrollIndicator = false
        followerScrollView.delegate = self
        scrollView.addSubview(followerScrollView)
        
        for i in 0..<followerImages.count {
            let image: UIImage = followerImages[i]
            let oSize: CGSize = image.size
            let oWidth = oSize.width
            let scale = scrollView.frame.width / oWidth
            let width = oWidth * scale

            let imageView = UIImageView(image: image)
            imageView.tag = i
            let xPosition = UIScreen.main.bounds.width * CGFloat(i)
            imageView.frame = CGRect(
                x: xPosition,
                y: 0,
                width: width,
                height: scrollView.frame.height
            )
            followerScrollView.contentSize.width = width * CGFloat(i + 1)
            followerScrollView.addSubview(imageView)
            imageView.contentMode = .scaleAspectFit
        }
        
        scrollView.bounds.origin.y = startY
    }
    
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        if scrollView === self.scrollView {
            let y = scrollView.bounds.origin.y
            if y == 0 {
                self.navigationItem.title = cohort.championName
            } else if y == scrollView.frame.height {
//                self.navigationItem.title = cohort.followerUnit.name
                self.navigationItem.title = cohort.championName
            } else {
                // no need to update until scrollDidEnd
            }
            return
        }
        let sv: UIScrollView
        if scrollView === championScrollView {
            sv = followerScrollView
        } else {
            sv = championScrollView
        }
        if scrollView.bounds.origin.x == 0 {
            sv.bounds.origin.x = 0
        } else {
            sv.bounds.origin.x = scrollView.frame.width
        }
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        setBackgroundColors()
    }
    
    override func traitCollectionDidChange(_ previousTraitCollection: UITraitCollection?) {
        super.traitCollectionDidChange(previousTraitCollection)
        setBackgroundColors()
    }

    @objc
    func showTraitView() {
        traitViewController.images = [cohort.championTraits[0]]
        navigationController?.pushViewController(traitViewController, animated: true)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

extension CECohortViewController {
    func setBackgroundColors() {
        if traitCollection.userInterfaceStyle == .dark {
            view.backgroundColor = .black
        } else {
            view.backgroundColor = .white
        }
    }
    func getCalculatedMainFrame() -> CGRect {
        let navigationBarHeight = navigationController!.navigationBar.frame.height
        let statusBarFrameHeight = UIApplication.shared.statusBarFrame.height
        let tabBarHeight = tabBarController!.tabBar.frame.height
        let y = navigationBarHeight + statusBarFrameHeight
        return CGRect(
            x: 0,
            y: y,
            width: view.frame.width,
            height: view.frame.height - y - tabBarHeight
        )
    }
}
