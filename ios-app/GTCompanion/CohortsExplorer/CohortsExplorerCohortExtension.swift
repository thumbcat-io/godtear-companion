//
//  CohortsExplorerCohortExtension.swift
//  GTCompanion
//
//  Copyright © 2020 Thumbcat Software Solutions, LLC. All rights reserved.
//

import UIKit
import MultiPlatformLibrary

extension CohortsExplorerCohort {

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
        CECardImageStore.shared.image(name: imageChampionLegendaryUnusedName())
    }

    var imageChampionLegendaryUsed: UIImage {
        CECardImageStore.shared.image(name: imageChampionLegendaryUsedName())
    }

    var imageChampionPlot: UIImage {
        CECardImageStore.shared.image(name: imageChampionPlotName())
    }

    var imageChampionClash: UIImage {
        CECardImageStore.shared.image(name: imageChampionClashName())
    }

    var imageFollowerPlot: UIImage {
        CECardImageStore.shared.image(name: imageFollowerPlotName())
    }

    var imageFollowerClash: UIImage {
        CECardImageStore.shared.image(name: imageFollowerClashName())
    }

    var championTraits: [UIImage] {
        [
            CECardImageStore.shared.image(name: imageChampionLegendaryUnusedName()),
            CECardImageStore.shared.image(name: imageChampionLegendaryUsedName())
        ]
    }

    var championImages: [UIImage] {
        [
            CECardImageStore.shared.image(name: imageChampionPlotName()),
            CECardImageStore.shared.image(name: imageChampionClashName())
        ]
    }

    var followerImages: [UIImage] {
        [
            CECardImageStore.shared.image(name: imageFollowerPlotName()),
            CECardImageStore.shared.image(name: imageFollowerClashName())
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
