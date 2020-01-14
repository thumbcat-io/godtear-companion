//
//  StaticTextCell.swift
//  GodtearCompanion
//
//  Copyright © 2020 Thumbcat Software Solutions, LLC. All rights reserved.
//

import UIKit

class StaticTextCell : UITableViewCell {
    
    var staticText: String? {
        didSet {
            staticTextLabel.text = staticText
        }
    }
    
    private let staticTextLabel : UILabel = {
        let lbl = UILabel()
        lbl.textColor = .gray
        lbl.font = UIFont.systemFont(ofSize: 14)
        lbl.textAlignment = .left
        lbl.lineBreakMode = .byWordWrapping
        lbl.numberOfLines = 0
        return lbl
    }()
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        addSubview(staticTextLabel)
        self.isUserInteractionEnabled = false
        
        staticTextLabel.anchor(
            top: topAnchor,
            left: leftAnchor,
            bottom: bottomAnchor,
            right: nil,
            paddingTop: 5,
            paddingLeft: 15,
            paddingBottom: 5,
            paddingRight: 15,
            width: frame.size.width,
            height: 50,
            enableInsets: false
        )
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
