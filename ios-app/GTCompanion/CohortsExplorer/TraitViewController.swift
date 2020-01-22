//
//  TraitViewController.swift
//  GTCompanion
//
//  Copyright © 2020 Thumbcat Software Solutions, LLC. All rights reserved.
//

import UIKit
import MultiPlatformLibrary

class TraitViewController: UIViewController, UIScrollViewDelegate {

    private let scrollView = UIScrollView()

    var images: [UIImage]!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setBackgroundColors()
        view.addSubview(scrollView)
        scrollView.isPagingEnabled = true
        scrollView.showsVerticalScrollIndicator = false
        scrollView.showsHorizontalScrollIndicator = false
        scrollView.frame = CGRect(
            x: 0,
            y: 0,
            width: UIScreen.main.bounds.width,
            height: UIScreen.main.bounds.height
        )
        scrollView.delegate = self
        let navBarHeight = navigationController!.navigationBar.intrinsicContentSize.height
        let layoutGuide = navigationController!.view.safeAreaLayoutGuide
        let layoutFrame = layoutGuide.layoutFrame
        for i in 0..<images.count {
            let image: UIImage = images[i]
            let oSize: CGSize = image.size
            let oWidth = oSize.width
            let scale = layoutFrame.width / oWidth
            let width = oWidth * scale

            let imageView = UIImageView(image: image)
            imageView.tag = i
            let xPosition = UIScreen.main.bounds.width * CGFloat(i)
            imageView.frame = CGRect(
                    x: xPosition,
                    y: 0 - (navBarHeight / 2),
                    width: width,
                    height: layoutFrame.height
            )
            imageView.contentMode = .scaleAspectFit
            scrollView.contentSize.width = width * CGFloat(i + 1)
            scrollView.addSubview(imageView)
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
}

extension TraitViewController {
    func setBackgroundColors() {
        if traitCollection.userInterfaceStyle == .dark {
            view.backgroundColor = .black
        } else {
            view.backgroundColor = .white
        }
    }
}
