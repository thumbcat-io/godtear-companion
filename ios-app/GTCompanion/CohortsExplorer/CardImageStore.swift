//
//  CardImageStore.swift
//  GTCompanion
//
//  Copyright © 2020 Thumbcat Software Solutions, LLC. All rights reserved.
//

import CoreImage
import UIKit

final class CardImageStore {

    typealias ImageDictionary = [String: UIImage]

    fileprivate var images: ImageDictionary = [:]

    static var shared = CardImageStore()

    func image(name: String) -> UIImage {
        let index = guaranteeImage(name: name)
        return images.values[index]
    }

    static func loadImage(name: String) -> UIImage {
        guard let image = UIImage(named: "\(name)") else {
            fatalError("Couldn't load image \(name).png from main bundle.")
        }
        return image
    }

    fileprivate func guaranteeImage(name: String) -> ImageDictionary.Index {
        if let index = images.index(forKey: name) {
            return index
        }
        images[name] = CardImageStore.loadImage(name: name)
        return images.index(forKey: name)!
    }
}
