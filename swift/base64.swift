#!/usr/bin/env swift

import Foundation

public extension String {
    func base64Decoded() -> Data? {
        NSData(base64Encoded: self, options: NSData.Base64DecodingOptions(rawValue: 0)) as? Data
    }

    func base64DecodedString() -> String? {
        guard let it = NSData(base64Encoded: self, options: NSData.Base64DecodingOptions(rawValue: 0)) else {
            return nil
        }
        return NSString(data: it as Data, encoding: String.Encoding.utf8.rawValue) as? String
    }

    func base64Encoded() -> Data? {
        data(using: .utf8)?.base64EncodedData()
    }

    func base64EncodedString() -> String? {
        data(using: .utf8)?.base64EncodedString()
    }

    func urlDecoded() -> String? {
        removingPercentEncoding
    }

    func urlEncoded() -> String? {
        addingPercentEncoding(withAllowedCharacters: .alphanumerics)
    }
}

print("5LiA5LqM5LiJ5Zub".base64DecodedString()!)
