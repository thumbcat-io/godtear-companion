//
//  CohortExtension.swift
//  GTCompanion
//
//  Copyright © 2020 Thumbcat Software Solutions, LLC. All rights reserved.
//

import UIKit
import MultiPlatformLibrary

extension CohortsExplorerRowData {

    fileprivate func imageChampionLegendaryUnusedName() -> String {
        "\(championKey)LegendaryUnused"
    }

    fileprivate func imageChampionLegendaryUsedName() -> String {
        "\(championKey)LegendaryUsed"
    }

    fileprivate func imageChampionPlotName() -> String {
        "\(championKey)Plot"
    }

    fileprivate func imageChampionClashName() -> String {
        "\(championKey)Clash"
    }

    fileprivate func imageFollowerPlotName() -> String {
        "\(followerKey)Plot"
    }

    fileprivate func imageFollowerClashName() -> String {
        "\(followerKey)Clash"
    }

    var imageChampionLegendaryUnused: UIImage {
        CardImageStore.shared.image(name: imageChampionLegendaryUnusedName())
    }

    var imageChampionLegendaryUsed: UIImage {
        CardImageStore.shared.image(name: imageChampionLegendaryUsedName())
    }

    var imageChampionPlot: UIImage {
        CardImageStore.shared.image(name: imageChampionPlotName())
    }

    var imageChampionClash: UIImage {
        CardImageStore.shared.image(name: imageChampionClashName())
    }

    var imageFollowerPlot: UIImage {
        CardImageStore.shared.image(name: imageFollowerPlotName())
    }

    var imageFollowerClash: UIImage {
        CardImageStore.shared.image(name: imageFollowerClashName())
    }

    var championTraits: [UIImage] {
        [
            CardImageStore.shared.image(name: imageChampionLegendaryUnusedName()),
            CardImageStore.shared.image(name: imageChampionLegendaryUsedName())
        ]
    }

    var championImages: [UIImage] {
        [
            CardImageStore.shared.image(name: imageChampionPlotName()),
            CardImageStore.shared.image(name: imageChampionClashName())
        ]
    }

    var followerImages: [UIImage] {
        [
            CardImageStore.shared.image(name: imageFollowerPlotName()),
            CardImageStore.shared.image(name: imageFollowerClashName())
        ]
    }
    
    var cohortImages: [UIImage] {
        [
            championTraits[0],
            championImages[0],
            championImages[1],
            followerImages[0],
            followerImages[1]
        ]
    }
}
