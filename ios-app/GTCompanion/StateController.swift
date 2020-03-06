//
//  VolatileStateController.swift
//  GTCompanion
//
//  Created by Nicholas J Halase on 3/5/20.
//  Copyright © 2020 Nicholas J Halase. All rights reserved.
//

import Foundation
import MultiPlatformLibrary

protocol StateController {
    var isVolatile: Bool { get }
    var cohortsExplorerWarband: [Int: [Int: CohortsExplorerRowData]] { get set }
}

class VolatileStateController : StateController {
    let isVolatile: Bool = true
    var cohortsExplorerWarband: [Int: [Int: CohortsExplorerRowData]] = [:]
}
