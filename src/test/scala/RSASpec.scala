/* Copyright 2014, 2015 Richard Wiedenhöft <richard@wiedenhoeft.xyz>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xyz.wiedenhoeft.scalacrypt

import org.scalatest._
import scala.util.{ Try, Success, Failure }

class RSASpec extends FlatSpec with Matchers {
  val testKey =
""" |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
    |5zIfjLTf4x6AEgvDsQltpLsCxSXGx4c5w4PfRQ==""".stripMargin.toBase64Bytes.toKey[RSAKey].get

  val testKey2 =
""" |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
    |WbN8wknG5lqLs7Cu7w==""".stripMargin.toBase64Bytes.toKey[RSAKey].get

  "A generated RSAKey" should "be serializable." in {
    val bytes = testKey.bytes
    val bytes2 = testKey2.bytes
    val newKey = bytes.toKey[RSAKey].get
    val newKey2 = bytes2.toKey[RSAKey].get
    newKey should be (testKey)
    newKey2 should be (testKey2)
  }

  it should "have length 512." in {
    testKey.length should be (512)
    testKey2.length should be (512)
  }

  it should "export the public part." in {
    testKey.isPrivateKey should be (true)
    val pubKey = testKey.publicKey
    pubKey.isPrivateKey should be (false)
    pubKey.privateKey1 should be (None)
    pubKey.privateKey2 should be (None)
  }

  "RSAES_OAEP encryption" should "correctly encrypt and decrypt data" in {
    val suite = suites.RSAES_OAEP(testKey).get
    val suite2 = suites.RSAES_OAEP(testKey2).get
    val test = (0 until 16) map { _.toByte }
    val c = suite.encrypt(test).get
    val c2 = suite2.encrypt(test).get
    suite.decrypt(c).get should be (test)
    suite2.decrypt(c2).get should be (test)
  }

  it should "not fail on certain data inputs." in {
    val test = (Seq.fill[Byte](512 - 64) { 0.toByte }) ++ "AmzVJLEIo/6xoaqpZ6G5SutGJ8Rxh5Mk9mPhnuj+CBDnp+BE4jITQo1wtzFOLjQnwSp/nmK9zScDJoDsWYk9CA==".toBase64Bytes
    val rsa = new blockciphers.RSA { val key = testKey; }
    val rsa2 = new blockciphers.RSA { val key = testKey2; }
    rsa.decryptBlock(rsa.encryptBlock(test).get).get should be (test)
    rsa2.decryptBlock(rsa2.encryptBlock(test).get).get should be (test)
  }

  it should "conform to the test vector." in {
    val n = Seq(0xa8, 0xb3, 0xb2, 0x84, 0xaf, 0x8e, 0xb5, 0x0b, 0x38, 0x70, 0x34, 0xa8, 0x60, 0xf1, 0x46, 0xc4, 0x91, 0x9f, 0x31, 0x87, 0x63, 0xcd, 0x6c, 0x55, 0x98, 0xc8, 0xae, 0x48, 0x11, 0xa1, 0xe0, 0xab, 0xc4, 0xc7, 0xe0, 0xb0, 0x82, 0xd6, 0x93, 0xa5, 0xe7, 0xfc, 0xed, 0x67, 0x5c, 0xf4, 0x66, 0x85, 0x12, 0x77, 0x2c, 0x0c, 0xbc, 0x64, 0xa7, 0x42, 0xc6, 0xc6, 0x30, 0xf5, 0x33, 0xc8, 0xcc, 0x72, 0xf6, 0x2a, 0xe8, 0x33, 0xc4, 0x0b, 0xf2, 0x58, 0x42, 0xe9, 0x84, 0xbb, 0x78, 0xbd, 0xbf, 0x97, 0xc0, 0x10, 0x7d, 0x55, 0xbd, 0xb6, 0x62, 0xf5, 0xc4, 0xe0, 0xfa, 0xb9, 0x84, 0x5c, 0xb5, 0x14, 0x8e, 0xf7, 0x39, 0x2d, 0xd3, 0xaa, 0xff, 0x93, 0xae, 0x1e, 0x6b, 0x66, 0x7b, 0xb3, 0xd4, 0x24, 0x76, 0x16, 0xd4, 0xf5, 0xba, 0x10, 0xd4, 0xcf, 0xd2, 0x26, 0xde, 0x88, 0xd3, 0x9f, 0x16, 0xfb) map { _.toByte }

    val e = Seq(0x01, 0x00, 0x01) map { _.toByte }

    val d = Seq(0x53, 0x33, 0x9c, 0xfd, 0xb7, 0x9f, 0xc8, 0x46, 0x6a, 0x65, 0x5c, 0x73, 0x16, 0xac, 0xa8, 0x5c, 0x55, 0xfd, 0x8f, 0x6d, 0xd8, 0x98, 0xfd, 0xaf, 0x11, 0x95, 0x17, 0xef, 0x4f, 0x52, 0xe8, 0xfd, 0x8e, 0x25, 0x8d, 0xf9, 0x3f, 0xee, 0x18, 0x0f, 0xa0, 0xe4, 0xab, 0x29, 0x69, 0x3c, 0xd8, 0x3b, 0x15, 0x2a, 0x55, 0x3d, 0x4a, 0xc4, 0xd1, 0x81, 0x2b, 0x8b, 0x9f, 0xa5, 0xaf, 0x0e, 0x7f, 0x55, 0xfe, 0x73, 0x04, 0xdf, 0x41, 0x57, 0x09, 0x26, 0xf3, 0x31, 0x1f, 0x15, 0xc4, 0xd6, 0x5a, 0x73, 0x2c, 0x48, 0x31, 0x16, 0xee, 0x3d, 0x3d, 0x2d, 0x0a, 0xf3, 0x54, 0x9a, 0xd9, 0xbf, 0x7c, 0xbf, 0xb7, 0x8a, 0xd8, 0x84, 0xf8, 0x4d, 0x5b, 0xeb, 0x04, 0x72, 0x4d, 0xc7, 0x36, 0x9b, 0x31, 0xde, 0xf3, 0x7d, 0x0c, 0xf5, 0x39, 0xe9, 0xcf, 0xcd, 0xd3, 0xde, 0x65, 0x37, 0x29, 0xea, 0xd5, 0xd1) map { _.toByte }

    val seed = Seq(0x18, 0xb7, 0x76, 0xea, 0x21, 0x06, 0x9d, 0x69, 0x77, 0x6a, 0x33, 0xe9, 0x6b, 0xad, 0x48, 0xe1, 0xdd, 0xa0, 0xa5, 0xef) map { _.toByte }

    val m = Seq(0x66, 0x28, 0x19, 0x4e, 0x12, 0x07, 0x3d, 0xb0, 0x3b, 0xa9, 0x4c, 0xda, 0x9e, 0xf9, 0x53, 0x23, 0x97, 0xd5, 0x0d, 0xba, 0x79, 0xb9, 0x87, 0x00, 0x4a, 0xfe, 0xfe, 0x34) map { _.toByte }

    val c = Seq(0x35, 0x4f, 0xe6, 0x7b, 0x4a, 0x12, 0x6d, 0x5d, 0x35, 0xfe, 0x36, 0xc7, 0x77, 0x79, 0x1a, 0x3f, 0x7b, 0xa1, 0x3d, 0xef, 0x48, 0x4e, 0x2d, 0x39, 0x08, 0xaf, 0xf7, 0x22, 0xfa, 0xd4, 0x68, 0xfb, 0x21, 0x69, 0x6d, 0xe9, 0x5d, 0x0b, 0xe9, 0x11, 0xc2, 0xd3, 0x17, 0x4f, 0x8a, 0xfc, 0xc2, 0x01, 0x03, 0x5f, 0x7b, 0x6d, 0x8e, 0x69, 0x40, 0x2d, 0xe5, 0x45, 0x16, 0x18, 0xc2, 0x1a, 0x53, 0x5f, 0xa9, 0xd7, 0xbf, 0xc5, 0xb8, 0xdd, 0x9f, 0xc2, 0x43, 0xf8, 0xcf, 0x92, 0x7d, 0xb3, 0x13, 0x22, 0xd6, 0xe8, 0x81, 0xea, 0xa9, 0x1a, 0x99, 0x61, 0x70, 0xe6, 0x57, 0xa0, 0x5a, 0x26, 0x64, 0x26, 0xd9, 0x8c, 0x88, 0x00, 0x3f, 0x84, 0x77, 0xc1, 0x22, 0x70, 0x94, 0xa0, 0xd9, 0xfa, 0x1e, 0x8c, 0x40, 0x24, 0x30, 0x9c, 0xe1, 0xec, 0xcc, 0xb5, 0x21, 0x00, 0x35, 0xd4, 0x7a, 0xc7, 0x2e, 0x8a) map { _.toByte }

    val publicKey = (e, n).toKey[RSAKey].get
    val privateKey = (e, d, n).toKey[RSAKey].get

    val encryptor = suites.RSAES_OAEP(publicKey, Seq[Byte](), hash.SHA1, { _ ⇒ seed }).get
    val decryptor = suites.RSAES_OAEP(privateKey, Seq[Byte](), hash.SHA1, { _ ⇒ seed }).get

    encryptor.encrypt(m).get should be (c)
    decryptor.decrypt(c).get should be (m)
  }
}