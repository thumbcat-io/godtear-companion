//
//  VolatileStateController.swift
//  GTCompanion
//
//  Copyright © 2020 Thumbcat Software Solutions, LLC. All rights reserved.
//

import Foundation
import MultiPlatformLibrary

protocol StateController {
    var isVolatile: Bool { get }
    var cohortsExplorerCohort: CohortsExplorerCohort? { get }
    func selectCohortsExplorerCohort(cohort: CohortsExplorerCohort)
    func deselectCohortsExplorerCohort()
    
    var warbandExplorerWarband: [WarbandExplorerCohort] { get }
    func selectWarbandExplorerCohort(cohort: WarbandExplorerCohort)
    func deselectWarbandExplorerCohort(cohort: WarbandExplorerCohort)
}

class VolatileStateController : StateController {
    let isVolatile: Bool = true
    
    var cohortsExplorerCohort: CohortsExplorerCohort? = nil
    func selectCohortsExplorerCohort(cohort: CohortsExplorerCohort) {
        cohortsExplorerCohort = cohort
    }
    func deselectCohortsExplorerCohort() {
        cohortsExplorerCohort = nil
    }
    
    var warbandExplorerWarband: [WarbandExplorerCohort] = []
    func selectWarbandExplorerCohort(cohort: WarbandExplorerCohort) {
        warbandExplorerWarband.append(cohort)
    }
    func deselectWarbandExplorerCohort(cohort: WarbandExplorerCohort) {
        for (index, old) in warbandExplorerWarband.enumerated() {
            if old.championKey == cohort.championKey && old.followerKey == cohort.followerKey {
                warbandExplorerWarband.remove(at: index)
                break
            }
        }
    }
}
