@file:JvmName("Index_h")
package org.graphiks.kextract.clang.libclang

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

private object kextract_runtime {
    val C_BOOL: ValueLayout = ValueLayout.JAVA_BOOLEAN
    val C_CHAR: ValueLayout = ValueLayout.JAVA_BYTE
    val C_SHORT: ValueLayout = ValueLayout.JAVA_SHORT
    val C_INT: ValueLayout = ValueLayout.JAVA_INT
    val C_LONG: ValueLayout = ValueLayout.JAVA_LONG
    val C_LONG_LONG: ValueLayout = ValueLayout.JAVA_LONG
    val C_FLOAT: ValueLayout = ValueLayout.JAVA_FLOAT
    val C_DOUBLE: ValueLayout = ValueLayout.JAVA_DOUBLE
    val C_POINTER: ValueLayout = ValueLayout.ADDRESS

    init {
        System.loadLibrary("clang")
    }
}

// Ensure kextract_runtime (and therefore System.loadLibrary("clang")) runs before any SymbolLookup
private val _init = kextract_runtime

/**
 * {@snippet lang=c : #define __has_safe_buffers 1
 */
fun _has_safe_buffers(): Int = 1

/**
 * {@snippet lang=c : #define __DARWIN_ONLY_64_BIT_INO_T 1
 */
fun _DARWIN_ONLY_64_BIT_INO_T(): Int = 1

/**
 * {@snippet lang=c : #define __DARWIN_ONLY_UNIX_CONFORMANCE 1
 */
fun _DARWIN_ONLY_UNIX_CONFORMANCE(): Int = 1

/**
 * {@snippet lang=c : #define __DARWIN_ONLY_VERS_1050 1
 */
fun _DARWIN_ONLY_VERS_1050(): Int = 1

/**
 * {@snippet lang=c : #define __DARWIN_UNIX03 1
 */
fun _DARWIN_UNIX03(): Int = 1

/**
 * {@snippet lang=c : #define __DARWIN_64_BIT_INO_T 1
 */
fun _DARWIN_64_BIT_INO_T(): Int = 1

/**
 * {@snippet lang=c : #define __DARWIN_VERS_1050 1
 */
fun _DARWIN_VERS_1050(): Int = 1

/**
 * {@snippet lang=c : #define __DARWIN_NON_CANCELABLE 0
 */
fun _DARWIN_NON_CANCELABLE(): Int = 0

/**
 * {@snippet lang=c : #define __STDC_WANT_LIB_EXT1__ 1
 */
fun _STDC_WANT_LIB_EXT1_(): Int = 1

/**
 * {@snippet lang=c : #define __DARWIN_NO_LONG_LONG 0
 */
fun _DARWIN_NO_LONG_LONG(): Int = 0

/**
 * {@snippet lang=c : #define _DARWIN_FEATURE_64_BIT_INODE 1
 */
fun _DARWIN_FEATURE_64_BIT_INODE(): Int = 1

/**
 * {@snippet lang=c : #define _DARWIN_FEATURE_ONLY_64_BIT_INODE 1
 */
fun _DARWIN_FEATURE_ONLY_64_BIT_INODE(): Int = 1

/**
 * {@snippet lang=c : #define _DARWIN_FEATURE_ONLY_VERS_1050 1
 */
fun _DARWIN_FEATURE_ONLY_VERS_1050(): Int = 1

/**
 * {@snippet lang=c : #define _DARWIN_FEATURE_ONLY_UNIX_CONFORMANCE 1
 */
fun _DARWIN_FEATURE_ONLY_UNIX_CONFORMANCE(): Int = 1

/**
 * {@snippet lang=c : #define _DARWIN_FEATURE_UNIX_CONFORMANCE 3
 */
fun _DARWIN_FEATURE_UNIX_CONFORMANCE(): Int = 3

/**
 * {@snippet lang=c : #define __has_ptrcheck 0
 */
fun _has_ptrcheck(): Int = 0

/**
 * {@snippet lang=c : #define USE_CLANG_TYPES 0
 */
fun USE_CLANG_TYPES(): Int = 0


/**
 * {@snippet lang=c : #define __PTHREAD_SIZE__ 8176
 */
fun _PTHREAD_SIZE_(): Int = 8176

/**
 * {@snippet lang=c : #define __PTHREAD_ATTR_SIZE__ 56
 */
fun _PTHREAD_ATTR_SIZE_(): Int = 56

/**
 * {@snippet lang=c : #define __PTHREAD_MUTEXATTR_SIZE__ 8
 */
fun _PTHREAD_MUTEXATTR_SIZE_(): Int = 8

/**
 * {@snippet lang=c : #define __PTHREAD_MUTEX_SIZE__ 56
 */
fun _PTHREAD_MUTEX_SIZE_(): Int = 56

/**
 * {@snippet lang=c : #define __PTHREAD_CONDATTR_SIZE__ 8
 */
fun _PTHREAD_CONDATTR_SIZE_(): Int = 8

/**
 * {@snippet lang=c : #define __PTHREAD_COND_SIZE__ 40
 */
fun _PTHREAD_COND_SIZE_(): Int = 40

/**
 * {@snippet lang=c : #define __PTHREAD_ONCE_SIZE__ 8
 */
fun _PTHREAD_ONCE_SIZE_(): Int = 8

/**
 * {@snippet lang=c : #define __PTHREAD_RWLOCK_SIZE__ 192
 */
fun _PTHREAD_RWLOCK_SIZE_(): Int = 192

/**
 * {@snippet lang=c : #define __PTHREAD_RWLOCKATTR_SIZE__ 16
 */
fun _PTHREAD_RWLOCKATTR_SIZE_(): Int = 16

/**
 * {@snippet lang=c : #define _FORTIFY_SOURCE 2
 */
fun _FORTIFY_SOURCE(): Int = 2

/**
 * {@snippet lang=c : #define __API_TO_BE_DEPRECATED 100000
 */
fun _API_TO_BE_DEPRECATED(): Int = 100000

/**
 * {@snippet lang=c : #define __API_TO_BE_DEPRECATED_MACOS 100000
 */
fun _API_TO_BE_DEPRECATED_MACOS(): Int = 100000

/**
 * {@snippet lang=c : #define __API_TO_BE_DEPRECATED_MACOSAPPLICATIONEXTENSION 100000
 */
fun _API_TO_BE_DEPRECATED_MACOSAPPLICATIONEXTENSION(): Int = 100000

/**
 * {@snippet lang=c : #define __API_TO_BE_DEPRECATED_IOS 100000
 */
fun _API_TO_BE_DEPRECATED_IOS(): Int = 100000

/**
 * {@snippet lang=c : #define __API_TO_BE_DEPRECATED_IOSAPPLICATIONEXTENSION 100000
 */
fun _API_TO_BE_DEPRECATED_IOSAPPLICATIONEXTENSION(): Int = 100000

/**
 * {@snippet lang=c : #define __API_TO_BE_DEPRECATED_MACCATALYST 100000
 */
fun _API_TO_BE_DEPRECATED_MACCATALYST(): Int = 100000

/**
 * {@snippet lang=c : #define __API_TO_BE_DEPRECATED_MACCATALYSTAPPLICATIONEXTENSION 100000
 */
fun _API_TO_BE_DEPRECATED_MACCATALYSTAPPLICATIONEXTENSION(): Int = 100000

/**
 * {@snippet lang=c : #define __API_TO_BE_DEPRECATED_WATCHOS 100000
 */
fun _API_TO_BE_DEPRECATED_WATCHOS(): Int = 100000

/**
 * {@snippet lang=c : #define __API_TO_BE_DEPRECATED_WATCHOSAPPLICATIONEXTENSION 100000
 */
fun _API_TO_BE_DEPRECATED_WATCHOSAPPLICATIONEXTENSION(): Int = 100000

/**
 * {@snippet lang=c : #define __API_TO_BE_DEPRECATED_TVOS 100000
 */
fun _API_TO_BE_DEPRECATED_TVOS(): Int = 100000

/**
 * {@snippet lang=c : #define __API_TO_BE_DEPRECATED_TVOSAPPLICATIONEXTENSION 100000
 */
fun _API_TO_BE_DEPRECATED_TVOSAPPLICATIONEXTENSION(): Int = 100000

/**
 * {@snippet lang=c : #define __API_TO_BE_DEPRECATED_DRIVERKIT 100000
 */
fun _API_TO_BE_DEPRECATED_DRIVERKIT(): Int = 100000

/**
 * {@snippet lang=c : #define __API_TO_BE_DEPRECATED_VISIONOS 100000
 */
fun _API_TO_BE_DEPRECATED_VISIONOS(): Int = 100000

/**
 * {@snippet lang=c : #define __API_TO_BE_DEPRECATED_VISIONOSAPPLICATIONEXTENSION 100000
 */
fun _API_TO_BE_DEPRECATED_VISIONOSAPPLICATIONEXTENSION(): Int = 100000

/**
 * {@snippet lang=c : #define __API_TO_BE_DEPRECATED_KERNELKIT 100000
 */
fun _API_TO_BE_DEPRECATED_KERNELKIT(): Int = 100000

/**
 * {@snippet lang=c : #define __MAC_10_0 1000
 */
fun _MAC_10_0(): Int = 1000

/**
 * {@snippet lang=c : #define __MAC_10_1 1010
 */
fun _MAC_10_1(): Int = 1010

/**
 * {@snippet lang=c : #define __MAC_10_2 1020
 */
fun _MAC_10_2(): Int = 1020

/**
 * {@snippet lang=c : #define __MAC_10_3 1030
 */
fun _MAC_10_3(): Int = 1030

/**
 * {@snippet lang=c : #define __MAC_10_4 1040
 */
fun _MAC_10_4(): Int = 1040

/**
 * {@snippet lang=c : #define __MAC_10_5 1050
 */
fun _MAC_10_5(): Int = 1050

/**
 * {@snippet lang=c : #define __MAC_10_6 1060
 */
fun _MAC_10_6(): Int = 1060

/**
 * {@snippet lang=c : #define __MAC_10_7 1070
 */
fun _MAC_10_7(): Int = 1070

/**
 * {@snippet lang=c : #define __MAC_10_8 1080
 */
fun _MAC_10_8(): Int = 1080

/**
 * {@snippet lang=c : #define __MAC_10_9 1090
 */
fun _MAC_10_9(): Int = 1090

/**
 * {@snippet lang=c : #define __MAC_10_10 101000
 */
fun _MAC_10_10(): Int = 101000

/**
 * {@snippet lang=c : #define __MAC_10_10_2 101002
 */
fun _MAC_10_10_2(): Int = 101002

/**
 * {@snippet lang=c : #define __MAC_10_10_3 101003
 */
fun _MAC_10_10_3(): Int = 101003

/**
 * {@snippet lang=c : #define __MAC_10_11 101100
 */
fun _MAC_10_11(): Int = 101100

/**
 * {@snippet lang=c : #define __MAC_10_11_2 101102
 */
fun _MAC_10_11_2(): Int = 101102

/**
 * {@snippet lang=c : #define __MAC_10_11_3 101103
 */
fun _MAC_10_11_3(): Int = 101103

/**
 * {@snippet lang=c : #define __MAC_10_11_4 101104
 */
fun _MAC_10_11_4(): Int = 101104

/**
 * {@snippet lang=c : #define __MAC_10_12 101200
 */
fun _MAC_10_12(): Int = 101200

/**
 * {@snippet lang=c : #define __MAC_10_12_1 101201
 */
fun _MAC_10_12_1(): Int = 101201

/**
 * {@snippet lang=c : #define __MAC_10_12_2 101202
 */
fun _MAC_10_12_2(): Int = 101202

/**
 * {@snippet lang=c : #define __MAC_10_12_4 101204
 */
fun _MAC_10_12_4(): Int = 101204

/**
 * {@snippet lang=c : #define __MAC_10_13 101300
 */
fun _MAC_10_13(): Int = 101300

/**
 * {@snippet lang=c : #define __MAC_10_13_1 101301
 */
fun _MAC_10_13_1(): Int = 101301

/**
 * {@snippet lang=c : #define __MAC_10_13_2 101302
 */
fun _MAC_10_13_2(): Int = 101302

/**
 * {@snippet lang=c : #define __MAC_10_13_4 101304
 */
fun _MAC_10_13_4(): Int = 101304

/**
 * {@snippet lang=c : #define __MAC_10_14 101400
 */
fun _MAC_10_14(): Int = 101400

/**
 * {@snippet lang=c : #define __MAC_10_14_1 101401
 */
fun _MAC_10_14_1(): Int = 101401

/**
 * {@snippet lang=c : #define __MAC_10_14_4 101404
 */
fun _MAC_10_14_4(): Int = 101404

/**
 * {@snippet lang=c : #define __MAC_10_14_5 101405
 */
fun _MAC_10_14_5(): Int = 101405

/**
 * {@snippet lang=c : #define __MAC_10_14_6 101406
 */
fun _MAC_10_14_6(): Int = 101406

/**
 * {@snippet lang=c : #define __MAC_10_15 101500
 */
fun _MAC_10_15(): Int = 101500

/**
 * {@snippet lang=c : #define __MAC_10_15_1 101501
 */
fun _MAC_10_15_1(): Int = 101501

/**
 * {@snippet lang=c : #define __MAC_10_15_4 101504
 */
fun _MAC_10_15_4(): Int = 101504

/**
 * {@snippet lang=c : #define __MAC_10_16 101600
 */
fun _MAC_10_16(): Int = 101600

/**
 * {@snippet lang=c : #define __MAC_11_0 110000
 */
fun _MAC_11_0(): Int = 110000

/**
 * {@snippet lang=c : #define __MAC_11_1 110100
 */
fun _MAC_11_1(): Int = 110100

/**
 * {@snippet lang=c : #define __MAC_11_3 110300
 */
fun _MAC_11_3(): Int = 110300

/**
 * {@snippet lang=c : #define __MAC_11_4 110400
 */
fun _MAC_11_4(): Int = 110400

/**
 * {@snippet lang=c : #define __MAC_11_5 110500
 */
fun _MAC_11_5(): Int = 110500

/**
 * {@snippet lang=c : #define __MAC_11_6 110600
 */
fun _MAC_11_6(): Int = 110600

/**
 * {@snippet lang=c : #define __MAC_12_0 120000
 */
fun _MAC_12_0(): Int = 120000

/**
 * {@snippet lang=c : #define __MAC_12_1 120100
 */
fun _MAC_12_1(): Int = 120100

/**
 * {@snippet lang=c : #define __MAC_12_2 120200
 */
fun _MAC_12_2(): Int = 120200

/**
 * {@snippet lang=c : #define __MAC_12_3 120300
 */
fun _MAC_12_3(): Int = 120300

/**
 * {@snippet lang=c : #define __MAC_12_4 120400
 */
fun _MAC_12_4(): Int = 120400

/**
 * {@snippet lang=c : #define __MAC_12_5 120500
 */
fun _MAC_12_5(): Int = 120500

/**
 * {@snippet lang=c : #define __MAC_12_6 120600
 */
fun _MAC_12_6(): Int = 120600

/**
 * {@snippet lang=c : #define __MAC_12_7 120700
 */
fun _MAC_12_7(): Int = 120700

/**
 * {@snippet lang=c : #define __MAC_13_0 130000
 */
fun _MAC_13_0(): Int = 130000

/**
 * {@snippet lang=c : #define __MAC_13_1 130100
 */
fun _MAC_13_1(): Int = 130100

/**
 * {@snippet lang=c : #define __MAC_13_2 130200
 */
fun _MAC_13_2(): Int = 130200

/**
 * {@snippet lang=c : #define __MAC_13_3 130300
 */
fun _MAC_13_3(): Int = 130300

/**
 * {@snippet lang=c : #define __MAC_13_4 130400
 */
fun _MAC_13_4(): Int = 130400

/**
 * {@snippet lang=c : #define __MAC_13_5 130500
 */
fun _MAC_13_5(): Int = 130500

/**
 * {@snippet lang=c : #define __MAC_13_6 130600
 */
fun _MAC_13_6(): Int = 130600

/**
 * {@snippet lang=c : #define __MAC_13_7 130700
 */
fun _MAC_13_7(): Int = 130700

/**
 * {@snippet lang=c : #define __MAC_14_0 140000
 */
fun _MAC_14_0(): Int = 140000

/**
 * {@snippet lang=c : #define __MAC_14_1 140100
 */
fun _MAC_14_1(): Int = 140100

/**
 * {@snippet lang=c : #define __MAC_14_2 140200
 */
fun _MAC_14_2(): Int = 140200

/**
 * {@snippet lang=c : #define __MAC_14_3 140300
 */
fun _MAC_14_3(): Int = 140300

/**
 * {@snippet lang=c : #define __MAC_14_4 140400
 */
fun _MAC_14_4(): Int = 140400

/**
 * {@snippet lang=c : #define __MAC_14_5 140500
 */
fun _MAC_14_5(): Int = 140500

/**
 * {@snippet lang=c : #define __MAC_14_6 140600
 */
fun _MAC_14_6(): Int = 140600

/**
 * {@snippet lang=c : #define __MAC_14_7 140700
 */
fun _MAC_14_7(): Int = 140700

/**
 * {@snippet lang=c : #define __MAC_15_0 150000
 */
fun _MAC_15_0(): Int = 150000

/**
 * {@snippet lang=c : #define __MAC_15_1 150100
 */
fun _MAC_15_1(): Int = 150100

/**
 * {@snippet lang=c : #define __MAC_15_2 150200
 */
fun _MAC_15_2(): Int = 150200

/**
 * {@snippet lang=c : #define __MAC_15_3 150300
 */
fun _MAC_15_3(): Int = 150300

/**
 * {@snippet lang=c : #define __MAC_15_4 150400
 */
fun _MAC_15_4(): Int = 150400

/**
 * {@snippet lang=c : #define __MAC_15_5 150500
 */
fun _MAC_15_5(): Int = 150500

/**
 * {@snippet lang=c : #define __MAC_15_6 150600
 */
fun _MAC_15_6(): Int = 150600

/**
 * {@snippet lang=c : #define __MAC_16_0 160000
 */
fun _MAC_16_0(): Int = 160000

/**
 * {@snippet lang=c : #define __MAC_26_0 260000
 */
fun _MAC_26_0(): Int = 260000

/**
 * {@snippet lang=c : #define __MAC_26_1 260100
 */
fun _MAC_26_1(): Int = 260100

/**
 * {@snippet lang=c : #define __MAC_26_2 260200
 */
fun _MAC_26_2(): Int = 260200

/**
 * {@snippet lang=c : #define __MAC_26_3 260300
 */
fun _MAC_26_3(): Int = 260300

/**
 * {@snippet lang=c : #define __MAC_26_4 260400
 */
fun _MAC_26_4(): Int = 260400

/**
 * {@snippet lang=c : #define __MAC_26_5 260500
 */
fun _MAC_26_5(): Int = 260500

/**
 * {@snippet lang=c : #define __IPHONE_2_0 20000
 */
fun _IPHONE_2_0(): Int = 20000

/**
 * {@snippet lang=c : #define __IPHONE_2_1 20100
 */
fun _IPHONE_2_1(): Int = 20100

/**
 * {@snippet lang=c : #define __IPHONE_2_2 20200
 */
fun _IPHONE_2_2(): Int = 20200

/**
 * {@snippet lang=c : #define __IPHONE_3_0 30000
 */
fun _IPHONE_3_0(): Int = 30000

/**
 * {@snippet lang=c : #define __IPHONE_3_1 30100
 */
fun _IPHONE_3_1(): Int = 30100

/**
 * {@snippet lang=c : #define __IPHONE_3_2 30200
 */
fun _IPHONE_3_2(): Int = 30200

/**
 * {@snippet lang=c : #define __IPHONE_4_0 40000
 */
fun _IPHONE_4_0(): Int = 40000

/**
 * {@snippet lang=c : #define __IPHONE_4_1 40100
 */
fun _IPHONE_4_1(): Int = 40100

/**
 * {@snippet lang=c : #define __IPHONE_4_2 40200
 */
fun _IPHONE_4_2(): Int = 40200

/**
 * {@snippet lang=c : #define __IPHONE_4_3 40300
 */
fun _IPHONE_4_3(): Int = 40300

/**
 * {@snippet lang=c : #define __IPHONE_5_0 50000
 */
fun _IPHONE_5_0(): Int = 50000

/**
 * {@snippet lang=c : #define __IPHONE_5_1 50100
 */
fun _IPHONE_5_1(): Int = 50100

/**
 * {@snippet lang=c : #define __IPHONE_6_0 60000
 */
fun _IPHONE_6_0(): Int = 60000

/**
 * {@snippet lang=c : #define __IPHONE_6_1 60100
 */
fun _IPHONE_6_1(): Int = 60100

/**
 * {@snippet lang=c : #define __IPHONE_7_0 70000
 */
fun _IPHONE_7_0(): Int = 70000

/**
 * {@snippet lang=c : #define __IPHONE_7_1 70100
 */
fun _IPHONE_7_1(): Int = 70100

/**
 * {@snippet lang=c : #define __IPHONE_8_0 80000
 */
fun _IPHONE_8_0(): Int = 80000

/**
 * {@snippet lang=c : #define __IPHONE_8_1 80100
 */
fun _IPHONE_8_1(): Int = 80100

/**
 * {@snippet lang=c : #define __IPHONE_8_2 80200
 */
fun _IPHONE_8_2(): Int = 80200

/**
 * {@snippet lang=c : #define __IPHONE_8_3 80300
 */
fun _IPHONE_8_3(): Int = 80300

/**
 * {@snippet lang=c : #define __IPHONE_8_4 80400
 */
fun _IPHONE_8_4(): Int = 80400

/**
 * {@snippet lang=c : #define __IPHONE_9_0 90000
 */
fun _IPHONE_9_0(): Int = 90000

/**
 * {@snippet lang=c : #define __IPHONE_9_1 90100
 */
fun _IPHONE_9_1(): Int = 90100

/**
 * {@snippet lang=c : #define __IPHONE_9_2 90200
 */
fun _IPHONE_9_2(): Int = 90200

/**
 * {@snippet lang=c : #define __IPHONE_9_3 90300
 */
fun _IPHONE_9_3(): Int = 90300

/**
 * {@snippet lang=c : #define __IPHONE_10_0 100000
 */
fun _IPHONE_10_0(): Int = 100000

/**
 * {@snippet lang=c : #define __IPHONE_10_1 100100
 */
fun _IPHONE_10_1(): Int = 100100

/**
 * {@snippet lang=c : #define __IPHONE_10_2 100200
 */
fun _IPHONE_10_2(): Int = 100200

/**
 * {@snippet lang=c : #define __IPHONE_10_3 100300
 */
fun _IPHONE_10_3(): Int = 100300

/**
 * {@snippet lang=c : #define __IPHONE_11_0 110000
 */
fun _IPHONE_11_0(): Int = 110000

/**
 * {@snippet lang=c : #define __IPHONE_11_1 110100
 */
fun _IPHONE_11_1(): Int = 110100

/**
 * {@snippet lang=c : #define __IPHONE_11_2 110200
 */
fun _IPHONE_11_2(): Int = 110200

/**
 * {@snippet lang=c : #define __IPHONE_11_3 110300
 */
fun _IPHONE_11_3(): Int = 110300

/**
 * {@snippet lang=c : #define __IPHONE_11_4 110400
 */
fun _IPHONE_11_4(): Int = 110400

/**
 * {@snippet lang=c : #define __IPHONE_12_0 120000
 */
fun _IPHONE_12_0(): Int = 120000

/**
 * {@snippet lang=c : #define __IPHONE_12_1 120100
 */
fun _IPHONE_12_1(): Int = 120100

/**
 * {@snippet lang=c : #define __IPHONE_12_2 120200
 */
fun _IPHONE_12_2(): Int = 120200

/**
 * {@snippet lang=c : #define __IPHONE_12_3 120300
 */
fun _IPHONE_12_3(): Int = 120300

/**
 * {@snippet lang=c : #define __IPHONE_12_4 120400
 */
fun _IPHONE_12_4(): Int = 120400

/**
 * {@snippet lang=c : #define __IPHONE_13_0 130000
 */
fun _IPHONE_13_0(): Int = 130000

/**
 * {@snippet lang=c : #define __IPHONE_13_1 130100
 */
fun _IPHONE_13_1(): Int = 130100

/**
 * {@snippet lang=c : #define __IPHONE_13_2 130200
 */
fun _IPHONE_13_2(): Int = 130200

/**
 * {@snippet lang=c : #define __IPHONE_13_3 130300
 */
fun _IPHONE_13_3(): Int = 130300

/**
 * {@snippet lang=c : #define __IPHONE_13_4 130400
 */
fun _IPHONE_13_4(): Int = 130400

/**
 * {@snippet lang=c : #define __IPHONE_13_5 130500
 */
fun _IPHONE_13_5(): Int = 130500

/**
 * {@snippet lang=c : #define __IPHONE_13_6 130600
 */
fun _IPHONE_13_6(): Int = 130600

/**
 * {@snippet lang=c : #define __IPHONE_13_7 130700
 */
fun _IPHONE_13_7(): Int = 130700

/**
 * {@snippet lang=c : #define __IPHONE_14_0 140000
 */
fun _IPHONE_14_0(): Int = 140000

/**
 * {@snippet lang=c : #define __IPHONE_14_1 140100
 */
fun _IPHONE_14_1(): Int = 140100

/**
 * {@snippet lang=c : #define __IPHONE_14_2 140200
 */
fun _IPHONE_14_2(): Int = 140200

/**
 * {@snippet lang=c : #define __IPHONE_14_3 140300
 */
fun _IPHONE_14_3(): Int = 140300

/**
 * {@snippet lang=c : #define __IPHONE_14_5 140500
 */
fun _IPHONE_14_5(): Int = 140500

/**
 * {@snippet lang=c : #define __IPHONE_14_6 140600
 */
fun _IPHONE_14_6(): Int = 140600

/**
 * {@snippet lang=c : #define __IPHONE_14_7 140700
 */
fun _IPHONE_14_7(): Int = 140700

/**
 * {@snippet lang=c : #define __IPHONE_14_8 140800
 */
fun _IPHONE_14_8(): Int = 140800

/**
 * {@snippet lang=c : #define __IPHONE_15_0 150000
 */
fun _IPHONE_15_0(): Int = 150000

/**
 * {@snippet lang=c : #define __IPHONE_15_1 150100
 */
fun _IPHONE_15_1(): Int = 150100

/**
 * {@snippet lang=c : #define __IPHONE_15_2 150200
 */
fun _IPHONE_15_2(): Int = 150200

/**
 * {@snippet lang=c : #define __IPHONE_15_3 150300
 */
fun _IPHONE_15_3(): Int = 150300

/**
 * {@snippet lang=c : #define __IPHONE_15_4 150400
 */
fun _IPHONE_15_4(): Int = 150400

/**
 * {@snippet lang=c : #define __IPHONE_15_5 150500
 */
fun _IPHONE_15_5(): Int = 150500

/**
 * {@snippet lang=c : #define __IPHONE_15_6 150600
 */
fun _IPHONE_15_6(): Int = 150600

/**
 * {@snippet lang=c : #define __IPHONE_15_7 150700
 */
fun _IPHONE_15_7(): Int = 150700

/**
 * {@snippet lang=c : #define __IPHONE_15_8 150800
 */
fun _IPHONE_15_8(): Int = 150800

/**
 * {@snippet lang=c : #define __IPHONE_16_0 160000
 */
fun _IPHONE_16_0(): Int = 160000

/**
 * {@snippet lang=c : #define __IPHONE_16_1 160100
 */
fun _IPHONE_16_1(): Int = 160100

/**
 * {@snippet lang=c : #define __IPHONE_16_2 160200
 */
fun _IPHONE_16_2(): Int = 160200

/**
 * {@snippet lang=c : #define __IPHONE_16_3 160300
 */
fun _IPHONE_16_3(): Int = 160300

/**
 * {@snippet lang=c : #define __IPHONE_16_4 160400
 */
fun _IPHONE_16_4(): Int = 160400

/**
 * {@snippet lang=c : #define __IPHONE_16_5 160500
 */
fun _IPHONE_16_5(): Int = 160500

/**
 * {@snippet lang=c : #define __IPHONE_16_6 160600
 */
fun _IPHONE_16_6(): Int = 160600

/**
 * {@snippet lang=c : #define __IPHONE_16_7 160700
 */
fun _IPHONE_16_7(): Int = 160700

/**
 * {@snippet lang=c : #define __IPHONE_17_0 170000
 */
fun _IPHONE_17_0(): Int = 170000

/**
 * {@snippet lang=c : #define __IPHONE_17_1 170100
 */
fun _IPHONE_17_1(): Int = 170100

/**
 * {@snippet lang=c : #define __IPHONE_17_2 170200
 */
fun _IPHONE_17_2(): Int = 170200

/**
 * {@snippet lang=c : #define __IPHONE_17_3 170300
 */
fun _IPHONE_17_3(): Int = 170300

/**
 * {@snippet lang=c : #define __IPHONE_17_4 170400
 */
fun _IPHONE_17_4(): Int = 170400

/**
 * {@snippet lang=c : #define __IPHONE_17_5 170500
 */
fun _IPHONE_17_5(): Int = 170500

/**
 * {@snippet lang=c : #define __IPHONE_17_6 170600
 */
fun _IPHONE_17_6(): Int = 170600

/**
 * {@snippet lang=c : #define __IPHONE_17_7 170700
 */
fun _IPHONE_17_7(): Int = 170700

/**
 * {@snippet lang=c : #define __IPHONE_18_0 180000
 */
fun _IPHONE_18_0(): Int = 180000

/**
 * {@snippet lang=c : #define __IPHONE_18_1 180100
 */
fun _IPHONE_18_1(): Int = 180100

/**
 * {@snippet lang=c : #define __IPHONE_18_2 180200
 */
fun _IPHONE_18_2(): Int = 180200

/**
 * {@snippet lang=c : #define __IPHONE_18_3 180300
 */
fun _IPHONE_18_3(): Int = 180300

/**
 * {@snippet lang=c : #define __IPHONE_18_4 180400
 */
fun _IPHONE_18_4(): Int = 180400

/**
 * {@snippet lang=c : #define __IPHONE_18_5 180500
 */
fun _IPHONE_18_5(): Int = 180500

/**
 * {@snippet lang=c : #define __IPHONE_18_6 180600
 */
fun _IPHONE_18_6(): Int = 180600

/**
 * {@snippet lang=c : #define __IPHONE_19_0 190000
 */
fun _IPHONE_19_0(): Int = 190000

/**
 * {@snippet lang=c : #define __IPHONE_26_0 260000
 */
fun _IPHONE_26_0(): Int = 260000

/**
 * {@snippet lang=c : #define __IPHONE_26_1 260100
 */
fun _IPHONE_26_1(): Int = 260100

/**
 * {@snippet lang=c : #define __IPHONE_26_2 260200
 */
fun _IPHONE_26_2(): Int = 260200

/**
 * {@snippet lang=c : #define __IPHONE_26_3 260300
 */
fun _IPHONE_26_3(): Int = 260300

/**
 * {@snippet lang=c : #define __IPHONE_26_4 260400
 */
fun _IPHONE_26_4(): Int = 260400

/**
 * {@snippet lang=c : #define __IPHONE_26_5 260500
 */
fun _IPHONE_26_5(): Int = 260500

/**
 * {@snippet lang=c : #define __WATCHOS_1_0 10000
 */
fun _WATCHOS_1_0(): Int = 10000

/**
 * {@snippet lang=c : #define __WATCHOS_2_0 20000
 */
fun _WATCHOS_2_0(): Int = 20000

/**
 * {@snippet lang=c : #define __WATCHOS_2_1 20100
 */
fun _WATCHOS_2_1(): Int = 20100

/**
 * {@snippet lang=c : #define __WATCHOS_2_2 20200
 */
fun _WATCHOS_2_2(): Int = 20200

/**
 * {@snippet lang=c : #define __WATCHOS_3_0 30000
 */
fun _WATCHOS_3_0(): Int = 30000

/**
 * {@snippet lang=c : #define __WATCHOS_3_1 30100
 */
fun _WATCHOS_3_1(): Int = 30100

/**
 * {@snippet lang=c : #define __WATCHOS_3_1_1 30101
 */
fun _WATCHOS_3_1_1(): Int = 30101

/**
 * {@snippet lang=c : #define __WATCHOS_3_2 30200
 */
fun _WATCHOS_3_2(): Int = 30200

/**
 * {@snippet lang=c : #define __WATCHOS_4_0 40000
 */
fun _WATCHOS_4_0(): Int = 40000

/**
 * {@snippet lang=c : #define __WATCHOS_4_1 40100
 */
fun _WATCHOS_4_1(): Int = 40100

/**
 * {@snippet lang=c : #define __WATCHOS_4_2 40200
 */
fun _WATCHOS_4_2(): Int = 40200

/**
 * {@snippet lang=c : #define __WATCHOS_4_3 40300
 */
fun _WATCHOS_4_3(): Int = 40300

/**
 * {@snippet lang=c : #define __WATCHOS_5_0 50000
 */
fun _WATCHOS_5_0(): Int = 50000

/**
 * {@snippet lang=c : #define __WATCHOS_5_1 50100
 */
fun _WATCHOS_5_1(): Int = 50100

/**
 * {@snippet lang=c : #define __WATCHOS_5_2 50200
 */
fun _WATCHOS_5_2(): Int = 50200

/**
 * {@snippet lang=c : #define __WATCHOS_5_3 50300
 */
fun _WATCHOS_5_3(): Int = 50300

/**
 * {@snippet lang=c : #define __WATCHOS_6_0 60000
 */
fun _WATCHOS_6_0(): Int = 60000

/**
 * {@snippet lang=c : #define __WATCHOS_6_1 60100
 */
fun _WATCHOS_6_1(): Int = 60100

/**
 * {@snippet lang=c : #define __WATCHOS_6_2 60200
 */
fun _WATCHOS_6_2(): Int = 60200

/**
 * {@snippet lang=c : #define __WATCHOS_7_0 70000
 */
fun _WATCHOS_7_0(): Int = 70000

/**
 * {@snippet lang=c : #define __WATCHOS_7_1 70100
 */
fun _WATCHOS_7_1(): Int = 70100

/**
 * {@snippet lang=c : #define __WATCHOS_7_2 70200
 */
fun _WATCHOS_7_2(): Int = 70200

/**
 * {@snippet lang=c : #define __WATCHOS_7_3 70300
 */
fun _WATCHOS_7_3(): Int = 70300

/**
 * {@snippet lang=c : #define __WATCHOS_7_4 70400
 */
fun _WATCHOS_7_4(): Int = 70400

/**
 * {@snippet lang=c : #define __WATCHOS_7_5 70500
 */
fun _WATCHOS_7_5(): Int = 70500

/**
 * {@snippet lang=c : #define __WATCHOS_7_6 70600
 */
fun _WATCHOS_7_6(): Int = 70600

/**
 * {@snippet lang=c : #define __WATCHOS_8_0 80000
 */
fun _WATCHOS_8_0(): Int = 80000

/**
 * {@snippet lang=c : #define __WATCHOS_8_1 80100
 */
fun _WATCHOS_8_1(): Int = 80100

/**
 * {@snippet lang=c : #define __WATCHOS_8_3 80300
 */
fun _WATCHOS_8_3(): Int = 80300

/**
 * {@snippet lang=c : #define __WATCHOS_8_4 80400
 */
fun _WATCHOS_8_4(): Int = 80400

/**
 * {@snippet lang=c : #define __WATCHOS_8_5 80500
 */
fun _WATCHOS_8_5(): Int = 80500

/**
 * {@snippet lang=c : #define __WATCHOS_8_6 80600
 */
fun _WATCHOS_8_6(): Int = 80600

/**
 * {@snippet lang=c : #define __WATCHOS_8_7 80700
 */
fun _WATCHOS_8_7(): Int = 80700

/**
 * {@snippet lang=c : #define __WATCHOS_8_8 80800
 */
fun _WATCHOS_8_8(): Int = 80800

/**
 * {@snippet lang=c : #define __WATCHOS_9_0 90000
 */
fun _WATCHOS_9_0(): Int = 90000

/**
 * {@snippet lang=c : #define __WATCHOS_9_1 90100
 */
fun _WATCHOS_9_1(): Int = 90100

/**
 * {@snippet lang=c : #define __WATCHOS_9_2 90200
 */
fun _WATCHOS_9_2(): Int = 90200

/**
 * {@snippet lang=c : #define __WATCHOS_9_3 90300
 */
fun _WATCHOS_9_3(): Int = 90300

/**
 * {@snippet lang=c : #define __WATCHOS_9_4 90400
 */
fun _WATCHOS_9_4(): Int = 90400

/**
 * {@snippet lang=c : #define __WATCHOS_9_5 90500
 */
fun _WATCHOS_9_5(): Int = 90500

/**
 * {@snippet lang=c : #define __WATCHOS_9_6 90600
 */
fun _WATCHOS_9_6(): Int = 90600

/**
 * {@snippet lang=c : #define __WATCHOS_10_0 100000
 */
fun _WATCHOS_10_0(): Int = 100000

/**
 * {@snippet lang=c : #define __WATCHOS_10_1 100100
 */
fun _WATCHOS_10_1(): Int = 100100

/**
 * {@snippet lang=c : #define __WATCHOS_10_2 100200
 */
fun _WATCHOS_10_2(): Int = 100200

/**
 * {@snippet lang=c : #define __WATCHOS_10_3 100300
 */
fun _WATCHOS_10_3(): Int = 100300

/**
 * {@snippet lang=c : #define __WATCHOS_10_4 100400
 */
fun _WATCHOS_10_4(): Int = 100400

/**
 * {@snippet lang=c : #define __WATCHOS_10_5 100500
 */
fun _WATCHOS_10_5(): Int = 100500

/**
 * {@snippet lang=c : #define __WATCHOS_10_6 100600
 */
fun _WATCHOS_10_6(): Int = 100600

/**
 * {@snippet lang=c : #define __WATCHOS_10_7 100700
 */
fun _WATCHOS_10_7(): Int = 100700

/**
 * {@snippet lang=c : #define __WATCHOS_11_0 110000
 */
fun _WATCHOS_11_0(): Int = 110000

/**
 * {@snippet lang=c : #define __WATCHOS_11_1 110100
 */
fun _WATCHOS_11_1(): Int = 110100

/**
 * {@snippet lang=c : #define __WATCHOS_11_2 110200
 */
fun _WATCHOS_11_2(): Int = 110200

/**
 * {@snippet lang=c : #define __WATCHOS_11_3 110300
 */
fun _WATCHOS_11_3(): Int = 110300

/**
 * {@snippet lang=c : #define __WATCHOS_11_4 110400
 */
fun _WATCHOS_11_4(): Int = 110400

/**
 * {@snippet lang=c : #define __WATCHOS_11_5 110500
 */
fun _WATCHOS_11_5(): Int = 110500

/**
 * {@snippet lang=c : #define __WATCHOS_11_6 110600
 */
fun _WATCHOS_11_6(): Int = 110600

/**
 * {@snippet lang=c : #define __WATCHOS_12_0 120000
 */
fun _WATCHOS_12_0(): Int = 120000

/**
 * {@snippet lang=c : #define __WATCHOS_26_0 260000
 */
fun _WATCHOS_26_0(): Int = 260000

/**
 * {@snippet lang=c : #define __WATCHOS_26_1 260100
 */
fun _WATCHOS_26_1(): Int = 260100

/**
 * {@snippet lang=c : #define __WATCHOS_26_2 260200
 */
fun _WATCHOS_26_2(): Int = 260200

/**
 * {@snippet lang=c : #define __WATCHOS_26_3 260300
 */
fun _WATCHOS_26_3(): Int = 260300

/**
 * {@snippet lang=c : #define __WATCHOS_26_4 260400
 */
fun _WATCHOS_26_4(): Int = 260400

/**
 * {@snippet lang=c : #define __WATCHOS_26_5 260500
 */
fun _WATCHOS_26_5(): Int = 260500

/**
 * {@snippet lang=c : #define __TVOS_9_0 90000
 */
fun _TVOS_9_0(): Int = 90000

/**
 * {@snippet lang=c : #define __TVOS_9_1 90100
 */
fun _TVOS_9_1(): Int = 90100

/**
 * {@snippet lang=c : #define __TVOS_9_2 90200
 */
fun _TVOS_9_2(): Int = 90200

/**
 * {@snippet lang=c : #define __TVOS_10_0 100000
 */
fun _TVOS_10_0(): Int = 100000

/**
 * {@snippet lang=c : #define __TVOS_10_0_1 100001
 */
fun _TVOS_10_0_1(): Int = 100001

/**
 * {@snippet lang=c : #define __TVOS_10_1 100100
 */
fun _TVOS_10_1(): Int = 100100

/**
 * {@snippet lang=c : #define __TVOS_10_2 100200
 */
fun _TVOS_10_2(): Int = 100200

/**
 * {@snippet lang=c : #define __TVOS_11_0 110000
 */
fun _TVOS_11_0(): Int = 110000

/**
 * {@snippet lang=c : #define __TVOS_11_1 110100
 */
fun _TVOS_11_1(): Int = 110100

/**
 * {@snippet lang=c : #define __TVOS_11_2 110200
 */
fun _TVOS_11_2(): Int = 110200

/**
 * {@snippet lang=c : #define __TVOS_11_3 110300
 */
fun _TVOS_11_3(): Int = 110300

/**
 * {@snippet lang=c : #define __TVOS_11_4 110400
 */
fun _TVOS_11_4(): Int = 110400

/**
 * {@snippet lang=c : #define __TVOS_12_0 120000
 */
fun _TVOS_12_0(): Int = 120000

/**
 * {@snippet lang=c : #define __TVOS_12_1 120100
 */
fun _TVOS_12_1(): Int = 120100

/**
 * {@snippet lang=c : #define __TVOS_12_2 120200
 */
fun _TVOS_12_2(): Int = 120200

/**
 * {@snippet lang=c : #define __TVOS_12_3 120300
 */
fun _TVOS_12_3(): Int = 120300

/**
 * {@snippet lang=c : #define __TVOS_12_4 120400
 */
fun _TVOS_12_4(): Int = 120400

/**
 * {@snippet lang=c : #define __TVOS_13_0 130000
 */
fun _TVOS_13_0(): Int = 130000

/**
 * {@snippet lang=c : #define __TVOS_13_2 130200
 */
fun _TVOS_13_2(): Int = 130200

/**
 * {@snippet lang=c : #define __TVOS_13_3 130300
 */
fun _TVOS_13_3(): Int = 130300

/**
 * {@snippet lang=c : #define __TVOS_13_4 130400
 */
fun _TVOS_13_4(): Int = 130400

/**
 * {@snippet lang=c : #define __TVOS_14_0 140000
 */
fun _TVOS_14_0(): Int = 140000

/**
 * {@snippet lang=c : #define __TVOS_14_1 140100
 */
fun _TVOS_14_1(): Int = 140100

/**
 * {@snippet lang=c : #define __TVOS_14_2 140200
 */
fun _TVOS_14_2(): Int = 140200

/**
 * {@snippet lang=c : #define __TVOS_14_3 140300
 */
fun _TVOS_14_3(): Int = 140300

/**
 * {@snippet lang=c : #define __TVOS_14_5 140500
 */
fun _TVOS_14_5(): Int = 140500

/**
 * {@snippet lang=c : #define __TVOS_14_6 140600
 */
fun _TVOS_14_6(): Int = 140600

/**
 * {@snippet lang=c : #define __TVOS_14_7 140700
 */
fun _TVOS_14_7(): Int = 140700

/**
 * {@snippet lang=c : #define __TVOS_15_0 150000
 */
fun _TVOS_15_0(): Int = 150000

/**
 * {@snippet lang=c : #define __TVOS_15_1 150100
 */
fun _TVOS_15_1(): Int = 150100

/**
 * {@snippet lang=c : #define __TVOS_15_2 150200
 */
fun _TVOS_15_2(): Int = 150200

/**
 * {@snippet lang=c : #define __TVOS_15_3 150300
 */
fun _TVOS_15_3(): Int = 150300

/**
 * {@snippet lang=c : #define __TVOS_15_4 150400
 */
fun _TVOS_15_4(): Int = 150400

/**
 * {@snippet lang=c : #define __TVOS_15_5 150500
 */
fun _TVOS_15_5(): Int = 150500

/**
 * {@snippet lang=c : #define __TVOS_15_6 150600
 */
fun _TVOS_15_6(): Int = 150600

/**
 * {@snippet lang=c : #define __TVOS_16_0 160000
 */
fun _TVOS_16_0(): Int = 160000

/**
 * {@snippet lang=c : #define __TVOS_16_1 160100
 */
fun _TVOS_16_1(): Int = 160100

/**
 * {@snippet lang=c : #define __TVOS_16_2 160200
 */
fun _TVOS_16_2(): Int = 160200

/**
 * {@snippet lang=c : #define __TVOS_16_3 160300
 */
fun _TVOS_16_3(): Int = 160300

/**
 * {@snippet lang=c : #define __TVOS_16_4 160400
 */
fun _TVOS_16_4(): Int = 160400

/**
 * {@snippet lang=c : #define __TVOS_16_5 160500
 */
fun _TVOS_16_5(): Int = 160500

/**
 * {@snippet lang=c : #define __TVOS_16_6 160600
 */
fun _TVOS_16_6(): Int = 160600

/**
 * {@snippet lang=c : #define __TVOS_17_0 170000
 */
fun _TVOS_17_0(): Int = 170000

/**
 * {@snippet lang=c : #define __TVOS_17_1 170100
 */
fun _TVOS_17_1(): Int = 170100

/**
 * {@snippet lang=c : #define __TVOS_17_2 170200
 */
fun _TVOS_17_2(): Int = 170200

/**
 * {@snippet lang=c : #define __TVOS_17_3 170300
 */
fun _TVOS_17_3(): Int = 170300

/**
 * {@snippet lang=c : #define __TVOS_17_4 170400
 */
fun _TVOS_17_4(): Int = 170400

/**
 * {@snippet lang=c : #define __TVOS_17_5 170500
 */
fun _TVOS_17_5(): Int = 170500

/**
 * {@snippet lang=c : #define __TVOS_17_6 170600
 */
fun _TVOS_17_6(): Int = 170600

/**
 * {@snippet lang=c : #define __TVOS_18_0 180000
 */
fun _TVOS_18_0(): Int = 180000

/**
 * {@snippet lang=c : #define __TVOS_18_1 180100
 */
fun _TVOS_18_1(): Int = 180100

/**
 * {@snippet lang=c : #define __TVOS_18_2 180200
 */
fun _TVOS_18_2(): Int = 180200

/**
 * {@snippet lang=c : #define __TVOS_18_3 180300
 */
fun _TVOS_18_3(): Int = 180300

/**
 * {@snippet lang=c : #define __TVOS_18_4 180400
 */
fun _TVOS_18_4(): Int = 180400

/**
 * {@snippet lang=c : #define __TVOS_18_5 180500
 */
fun _TVOS_18_5(): Int = 180500

/**
 * {@snippet lang=c : #define __TVOS_18_6 180600
 */
fun _TVOS_18_6(): Int = 180600

/**
 * {@snippet lang=c : #define __TVOS_19_0 190000
 */
fun _TVOS_19_0(): Int = 190000

/**
 * {@snippet lang=c : #define __TVOS_26_0 260000
 */
fun _TVOS_26_0(): Int = 260000

/**
 * {@snippet lang=c : #define __TVOS_26_1 260100
 */
fun _TVOS_26_1(): Int = 260100

/**
 * {@snippet lang=c : #define __TVOS_26_2 260200
 */
fun _TVOS_26_2(): Int = 260200

/**
 * {@snippet lang=c : #define __TVOS_26_3 260300
 */
fun _TVOS_26_3(): Int = 260300

/**
 * {@snippet lang=c : #define __TVOS_26_4 260400
 */
fun _TVOS_26_4(): Int = 260400

/**
 * {@snippet lang=c : #define __TVOS_26_5 260500
 */
fun _TVOS_26_5(): Int = 260500

/**
 * {@snippet lang=c : #define __BRIDGEOS_2_0 20000
 */
fun _BRIDGEOS_2_0(): Int = 20000

/**
 * {@snippet lang=c : #define __BRIDGEOS_3_0 30000
 */
fun _BRIDGEOS_3_0(): Int = 30000

/**
 * {@snippet lang=c : #define __BRIDGEOS_3_1 30100
 */
fun _BRIDGEOS_3_1(): Int = 30100

/**
 * {@snippet lang=c : #define __BRIDGEOS_3_4 30400
 */
fun _BRIDGEOS_3_4(): Int = 30400

/**
 * {@snippet lang=c : #define __BRIDGEOS_4_0 40000
 */
fun _BRIDGEOS_4_0(): Int = 40000

/**
 * {@snippet lang=c : #define __BRIDGEOS_4_1 40100
 */
fun _BRIDGEOS_4_1(): Int = 40100

/**
 * {@snippet lang=c : #define __BRIDGEOS_5_0 50000
 */
fun _BRIDGEOS_5_0(): Int = 50000

/**
 * {@snippet lang=c : #define __BRIDGEOS_5_1 50100
 */
fun _BRIDGEOS_5_1(): Int = 50100

/**
 * {@snippet lang=c : #define __BRIDGEOS_5_3 50300
 */
fun _BRIDGEOS_5_3(): Int = 50300

/**
 * {@snippet lang=c : #define __BRIDGEOS_6_0 60000
 */
fun _BRIDGEOS_6_0(): Int = 60000

/**
 * {@snippet lang=c : #define __BRIDGEOS_6_2 60200
 */
fun _BRIDGEOS_6_2(): Int = 60200

/**
 * {@snippet lang=c : #define __BRIDGEOS_6_4 60400
 */
fun _BRIDGEOS_6_4(): Int = 60400

/**
 * {@snippet lang=c : #define __BRIDGEOS_6_5 60500
 */
fun _BRIDGEOS_6_5(): Int = 60500

/**
 * {@snippet lang=c : #define __BRIDGEOS_6_6 60600
 */
fun _BRIDGEOS_6_6(): Int = 60600

/**
 * {@snippet lang=c : #define __BRIDGEOS_7_0 70000
 */
fun _BRIDGEOS_7_0(): Int = 70000

/**
 * {@snippet lang=c : #define __BRIDGEOS_7_1 70100
 */
fun _BRIDGEOS_7_1(): Int = 70100

/**
 * {@snippet lang=c : #define __BRIDGEOS_7_2 70200
 */
fun _BRIDGEOS_7_2(): Int = 70200

/**
 * {@snippet lang=c : #define __BRIDGEOS_7_3 70300
 */
fun _BRIDGEOS_7_3(): Int = 70300

/**
 * {@snippet lang=c : #define __BRIDGEOS_7_4 70400
 */
fun _BRIDGEOS_7_4(): Int = 70400

/**
 * {@snippet lang=c : #define __BRIDGEOS_7_6 70600
 */
fun _BRIDGEOS_7_6(): Int = 70600

/**
 * {@snippet lang=c : #define __BRIDGEOS_8_0 80000
 */
fun _BRIDGEOS_8_0(): Int = 80000

/**
 * {@snippet lang=c : #define __BRIDGEOS_8_1 80100
 */
fun _BRIDGEOS_8_1(): Int = 80100

/**
 * {@snippet lang=c : #define __BRIDGEOS_8_2 80200
 */
fun _BRIDGEOS_8_2(): Int = 80200

/**
 * {@snippet lang=c : #define __BRIDGEOS_8_3 80300
 */
fun _BRIDGEOS_8_3(): Int = 80300

/**
 * {@snippet lang=c : #define __BRIDGEOS_8_4 80400
 */
fun _BRIDGEOS_8_4(): Int = 80400

/**
 * {@snippet lang=c : #define __BRIDGEOS_8_5 80500
 */
fun _BRIDGEOS_8_5(): Int = 80500

/**
 * {@snippet lang=c : #define __BRIDGEOS_8_6 80600
 */
fun _BRIDGEOS_8_6(): Int = 80600

/**
 * {@snippet lang=c : #define __BRIDGEOS_9_0 90000
 */
fun _BRIDGEOS_9_0(): Int = 90000

/**
 * {@snippet lang=c : #define __BRIDGEOS_9_1 90100
 */
fun _BRIDGEOS_9_1(): Int = 90100

/**
 * {@snippet lang=c : #define __BRIDGEOS_9_2 90200
 */
fun _BRIDGEOS_9_2(): Int = 90200

/**
 * {@snippet lang=c : #define __BRIDGEOS_9_3 90300
 */
fun _BRIDGEOS_9_3(): Int = 90300

/**
 * {@snippet lang=c : #define __BRIDGEOS_9_4 90400
 */
fun _BRIDGEOS_9_4(): Int = 90400

/**
 * {@snippet lang=c : #define __BRIDGEOS_9_5 90500
 */
fun _BRIDGEOS_9_5(): Int = 90500

/**
 * {@snippet lang=c : #define __BRIDGEOS_9_6 90600
 */
fun _BRIDGEOS_9_6(): Int = 90600

/**
 * {@snippet lang=c : #define __BRIDGEOS_10_0 100000
 */
fun _BRIDGEOS_10_0(): Int = 100000

/**
 * {@snippet lang=c : #define __BRIDGEOS_10_1 100100
 */
fun _BRIDGEOS_10_1(): Int = 100100

/**
 * {@snippet lang=c : #define __BRIDGEOS_10_2 100200
 */
fun _BRIDGEOS_10_2(): Int = 100200

/**
 * {@snippet lang=c : #define __BRIDGEOS_10_3 100300
 */
fun _BRIDGEOS_10_3(): Int = 100300

/**
 * {@snippet lang=c : #define __BRIDGEOS_10_4 100400
 */
fun _BRIDGEOS_10_4(): Int = 100400

/**
 * {@snippet lang=c : #define __BRIDGEOS_26_5 260500
 */
fun _BRIDGEOS_26_5(): Int = 260500

/**
 * {@snippet lang=c : #define __DRIVERKIT_19_0 190000
 */
fun _DRIVERKIT_19_0(): Int = 190000

/**
 * {@snippet lang=c : #define __DRIVERKIT_20_0 200000
 */
fun _DRIVERKIT_20_0(): Int = 200000

/**
 * {@snippet lang=c : #define __DRIVERKIT_21_0 210000
 */
fun _DRIVERKIT_21_0(): Int = 210000

/**
 * {@snippet lang=c : #define __DRIVERKIT_22_0 220000
 */
fun _DRIVERKIT_22_0(): Int = 220000

/**
 * {@snippet lang=c : #define __DRIVERKIT_22_4 220400
 */
fun _DRIVERKIT_22_4(): Int = 220400

/**
 * {@snippet lang=c : #define __DRIVERKIT_22_5 220500
 */
fun _DRIVERKIT_22_5(): Int = 220500

/**
 * {@snippet lang=c : #define __DRIVERKIT_22_6 220600
 */
fun _DRIVERKIT_22_6(): Int = 220600

/**
 * {@snippet lang=c : #define __DRIVERKIT_23_0 230000
 */
fun _DRIVERKIT_23_0(): Int = 230000

/**
 * {@snippet lang=c : #define __DRIVERKIT_23_1 230100
 */
fun _DRIVERKIT_23_1(): Int = 230100

/**
 * {@snippet lang=c : #define __DRIVERKIT_23_2 230200
 */
fun _DRIVERKIT_23_2(): Int = 230200

/**
 * {@snippet lang=c : #define __DRIVERKIT_23_3 230300
 */
fun _DRIVERKIT_23_3(): Int = 230300

/**
 * {@snippet lang=c : #define __DRIVERKIT_23_4 230400
 */
fun _DRIVERKIT_23_4(): Int = 230400

/**
 * {@snippet lang=c : #define __DRIVERKIT_23_5 230500
 */
fun _DRIVERKIT_23_5(): Int = 230500

/**
 * {@snippet lang=c : #define __DRIVERKIT_23_6 230600
 */
fun _DRIVERKIT_23_6(): Int = 230600

/**
 * {@snippet lang=c : #define __DRIVERKIT_24_0 240000
 */
fun _DRIVERKIT_24_0(): Int = 240000

/**
 * {@snippet lang=c : #define __DRIVERKIT_24_1 240100
 */
fun _DRIVERKIT_24_1(): Int = 240100

/**
 * {@snippet lang=c : #define __DRIVERKIT_24_2 240200
 */
fun _DRIVERKIT_24_2(): Int = 240200

/**
 * {@snippet lang=c : #define __DRIVERKIT_24_3 240300
 */
fun _DRIVERKIT_24_3(): Int = 240300

/**
 * {@snippet lang=c : #define __DRIVERKIT_24_4 240400
 */
fun _DRIVERKIT_24_4(): Int = 240400

/**
 * {@snippet lang=c : #define __DRIVERKIT_24_5 240500
 */
fun _DRIVERKIT_24_5(): Int = 240500

/**
 * {@snippet lang=c : #define __DRIVERKIT_24_6 240600
 */
fun _DRIVERKIT_24_6(): Int = 240600

/**
 * {@snippet lang=c : #define __DRIVERKIT_25_0 250000
 */
fun _DRIVERKIT_25_0(): Int = 250000

/**
 * {@snippet lang=c : #define __DRIVERKIT_25_1 250100
 */
fun _DRIVERKIT_25_1(): Int = 250100

/**
 * {@snippet lang=c : #define __DRIVERKIT_25_2 250200
 */
fun _DRIVERKIT_25_2(): Int = 250200

/**
 * {@snippet lang=c : #define __DRIVERKIT_25_3 250300
 */
fun _DRIVERKIT_25_3(): Int = 250300

/**
 * {@snippet lang=c : #define __DRIVERKIT_25_4 250400
 */
fun _DRIVERKIT_25_4(): Int = 250400

/**
 * {@snippet lang=c : #define __DRIVERKIT_25_5 250500
 */
fun _DRIVERKIT_25_5(): Int = 250500

/**
 * {@snippet lang=c : #define __VISIONOS_1_0 10000
 */
fun _VISIONOS_1_0(): Int = 10000

/**
 * {@snippet lang=c : #define __VISIONOS_1_1 10100
 */
fun _VISIONOS_1_1(): Int = 10100

/**
 * {@snippet lang=c : #define __VISIONOS_1_2 10200
 */
fun _VISIONOS_1_2(): Int = 10200

/**
 * {@snippet lang=c : #define __VISIONOS_1_3 10300
 */
fun _VISIONOS_1_3(): Int = 10300

/**
 * {@snippet lang=c : #define __VISIONOS_2_0 20000
 */
fun _VISIONOS_2_0(): Int = 20000

/**
 * {@snippet lang=c : #define __VISIONOS_2_1 20100
 */
fun _VISIONOS_2_1(): Int = 20100

/**
 * {@snippet lang=c : #define __VISIONOS_2_2 20200
 */
fun _VISIONOS_2_2(): Int = 20200

/**
 * {@snippet lang=c : #define __VISIONOS_2_3 20300
 */
fun _VISIONOS_2_3(): Int = 20300

/**
 * {@snippet lang=c : #define __VISIONOS_2_4 20400
 */
fun _VISIONOS_2_4(): Int = 20400

/**
 * {@snippet lang=c : #define __VISIONOS_2_5 20500
 */
fun _VISIONOS_2_5(): Int = 20500

/**
 * {@snippet lang=c : #define __VISIONOS_2_6 20600
 */
fun _VISIONOS_2_6(): Int = 20600

/**
 * {@snippet lang=c : #define __VISIONOS_3_0 30000
 */
fun _VISIONOS_3_0(): Int = 30000

/**
 * {@snippet lang=c : #define __VISIONOS_26_0 260000
 */
fun _VISIONOS_26_0(): Int = 260000

/**
 * {@snippet lang=c : #define __VISIONOS_26_1 260100
 */
fun _VISIONOS_26_1(): Int = 260100

/**
 * {@snippet lang=c : #define __VISIONOS_26_2 260200
 */
fun _VISIONOS_26_2(): Int = 260200

/**
 * {@snippet lang=c : #define __VISIONOS_26_3 260300
 */
fun _VISIONOS_26_3(): Int = 260300

/**
 * {@snippet lang=c : #define __VISIONOS_26_4 260400
 */
fun _VISIONOS_26_4(): Int = 260400

/**
 * {@snippet lang=c : #define __VISIONOS_26_5 260500
 */
fun _VISIONOS_26_5(): Int = 260500

/**
 * {@snippet lang=c : #define __ENABLE_LEGACY_MAC_AVAILABILITY 1
 */
fun _ENABLE_LEGACY_MAC_AVAILABILITY(): Int = 1

/**
 * {@snippet lang=c : #define USE_CLANG_STDDEF 0
 */
fun USE_CLANG_STDDEF(): Int = 0


/**
 * {@snippet lang=c : #define TIME_UTC 1
 */
fun TIME_UTC(): Int = 1

/**
 * {@snippet lang=c : #define CINDEX_VERSION_MAJOR 0
 */
fun CINDEX_VERSION_MAJOR(): Int = 0

/**
 * {@snippet lang=c : #define CINDEX_VERSION_MINOR 64
 */
fun CINDEX_VERSION_MINOR(): Int = 64

/**
 * {@snippet lang=c : #define CXError_Success 0
 */
fun CXError_Success(): Int = 0

/**
 * {@snippet lang=c : #define CXError_Failure 1
 */
fun CXError_Failure(): Int = 1

/**
 * {@snippet lang=c : #define CXError_Crashed 2
 */
fun CXError_Crashed(): Int = 2

/**
 * {@snippet lang=c : #define CXError_InvalidArguments 3
 */
fun CXError_InvalidArguments(): Int = 3

/**
 * {@snippet lang=c : #define CXError_ASTReadError 4
 */
fun CXError_ASTReadError(): Int = 4

/**
 * {@snippet lang=c : STRUCT CXString
 */
class CXString {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("data"),
            ValueLayout.JAVA_INT.withName("private_flags"),
            MemoryLayout.paddingLayout(4)
        ).withName("CXString")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val data__VH: VarHandle = layout.varHandle(groupElement("data"))
        
        @Suppress("UNCHECKED_CAST")
        fun data_(segment: MemorySegment): MemorySegment? =
            data__VH.get(segment, 0L) as MemorySegment
        
        fun data_(segment: MemorySegment, value: MemorySegment) =
            data__VH.set(segment, 0L, value)
        
        val private_flags_VH: VarHandle = layout.varHandle(groupElement("private_flags"))
        
        @Suppress("UNCHECKED_CAST")
        fun private_flags(segment: MemorySegment): Int =
            private_flags_VH.get(segment, 0L) as Int
        
        fun private_flags(segment: MemorySegment, value: Int) =
            private_flags_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : STRUCT CXStringSet
 */
class CXStringSet {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("Strings"),
            ValueLayout.JAVA_INT.withName("Count"),
            MemoryLayout.paddingLayout(4)
        ).withName("CXStringSet")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val Strings_VH: VarHandle = layout.varHandle(groupElement("Strings"))
        
        @Suppress("UNCHECKED_CAST")
        fun Strings(segment: MemorySegment): MemorySegment? =
            Strings_VH.get(segment, 0L) as MemorySegment
        
        fun Strings(segment: MemorySegment, value: MemorySegment) =
            Strings_VH.set(segment, 0L, value)
        
        val Count_VH: VarHandle = layout.varHandle(groupElement("Count"))
        
        @Suppress("UNCHECKED_CAST")
        fun Count(segment: MemorySegment): Int =
            Count_VH.get(segment, 0L) as Int
        
        fun Count(segment: MemorySegment, value: Int) =
            Count_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : clang_getCString (Char)*(typedef CXString = Declared(CXString))
 */
private val clang_getCString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, CXString.layout)
private val clang_getCString_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCString")
private val clang_getCString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCString_ADDR, clang_getCString_DESC)

fun clang_getCString(arg0: MemorySegment): MemorySegment {
    try {
        return clang_getCString_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_disposeString Void(typedef CXString = Declared(CXString))
 */
private val clang_disposeString_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CXString.layout)
private val clang_disposeString_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_disposeString")
private val clang_disposeString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_disposeString_ADDR, clang_disposeString_DESC)

fun clang_disposeString(arg0: MemorySegment): Unit {
    try {
        clang_disposeString_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_disposeStringSet Void((typedef CXStringSet = Declared(CXStringSet))*)
 */
private val clang_disposeStringSet_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val clang_disposeStringSet_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_disposeStringSet")
private val clang_disposeStringSet_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_disposeStringSet_ADDR, clang_disposeStringSet_DESC)

fun clang_disposeStringSet(arg0: MemorySegment): Unit {
    try {
        clang_disposeStringSet_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getBuildSessionTimestamp UNSIGNED = LongLong()
 */
private val clang_getBuildSessionTimestamp_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val clang_getBuildSessionTimestamp_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getBuildSessionTimestamp")
private val clang_getBuildSessionTimestamp_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getBuildSessionTimestamp_ADDR, clang_getBuildSessionTimestamp_DESC)

fun clang_getBuildSessionTimestamp(): Long {
    try {
        return clang_getBuildSessionTimestamp_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : STRUCT CXVirtualFileOverlayImpl
 */
class CXVirtualFileOverlayImpl {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("CXVirtualFileOverlayImpl")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
    }
}

/**
 * {@snippet lang=c : typedef (Declared(CXVirtualFileOverlayImpl))* CXVirtualFileOverlay;}
 */
typealias CXVirtualFileOverlay = MemorySegment?

/**
 * {@snippet lang=c : clang_VirtualFileOverlay_create typedef CXVirtualFileOverlay = (Declared(CXVirtualFileOverlayImpl))*(UNSIGNED = Int)
 */
private val clang_VirtualFileOverlay_create_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_VirtualFileOverlay_create_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_VirtualFileOverlay_create")
private val clang_VirtualFileOverlay_create_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_VirtualFileOverlay_create_ADDR, clang_VirtualFileOverlay_create_DESC)

fun clang_VirtualFileOverlay_create(arg0: Int): MemorySegment {
    try {
        return clang_VirtualFileOverlay_create_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_VirtualFileOverlay_addFileMapping Declared(CXErrorCode)(typedef CXVirtualFileOverlay = (Declared(CXVirtualFileOverlayImpl))*,(Char)*,(Char)*)
 */
private val clang_VirtualFileOverlay_addFileMapping_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_VirtualFileOverlay_addFileMapping_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_VirtualFileOverlay_addFileMapping")
private val clang_VirtualFileOverlay_addFileMapping_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_VirtualFileOverlay_addFileMapping_ADDR, clang_VirtualFileOverlay_addFileMapping_DESC)

fun clang_VirtualFileOverlay_addFileMapping(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Int {
    try {
        return clang_VirtualFileOverlay_addFileMapping_HANDLE.invokeExact(arg0, arg1, arg2) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_VirtualFileOverlay_setCaseSensitivity Declared(CXErrorCode)(typedef CXVirtualFileOverlay = (Declared(CXVirtualFileOverlayImpl))*,Int)
 */
private val clang_VirtualFileOverlay_setCaseSensitivity_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_VirtualFileOverlay_setCaseSensitivity_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_VirtualFileOverlay_setCaseSensitivity")
private val clang_VirtualFileOverlay_setCaseSensitivity_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_VirtualFileOverlay_setCaseSensitivity_ADDR, clang_VirtualFileOverlay_setCaseSensitivity_DESC)

fun clang_VirtualFileOverlay_setCaseSensitivity(arg0: MemorySegment, arg1: Int): Int {
    try {
        return clang_VirtualFileOverlay_setCaseSensitivity_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_VirtualFileOverlay_writeToBuffer Declared(CXErrorCode)(typedef CXVirtualFileOverlay = (Declared(CXVirtualFileOverlayImpl))*,UNSIGNED = Int,((Char)*)*,(UNSIGNED = Int)*)
 */
private val clang_VirtualFileOverlay_writeToBuffer_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_VirtualFileOverlay_writeToBuffer_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_VirtualFileOverlay_writeToBuffer")
private val clang_VirtualFileOverlay_writeToBuffer_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_VirtualFileOverlay_writeToBuffer_ADDR, clang_VirtualFileOverlay_writeToBuffer_DESC)

fun clang_VirtualFileOverlay_writeToBuffer(arg0: MemorySegment, arg1: Int, arg2: MemorySegment, arg3: MemorySegment): Int {
    try {
        return clang_VirtualFileOverlay_writeToBuffer_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_free Void((Void)*)
 */
private val clang_free_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val clang_free_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_free")
private val clang_free_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_free_ADDR, clang_free_DESC)

fun clang_free(arg0: MemorySegment): Unit {
    try {
        clang_free_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_VirtualFileOverlay_dispose Void(typedef CXVirtualFileOverlay = (Declared(CXVirtualFileOverlayImpl))*)
 */
private val clang_VirtualFileOverlay_dispose_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val clang_VirtualFileOverlay_dispose_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_VirtualFileOverlay_dispose")
private val clang_VirtualFileOverlay_dispose_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_VirtualFileOverlay_dispose_ADDR, clang_VirtualFileOverlay_dispose_DESC)

fun clang_VirtualFileOverlay_dispose(arg0: MemorySegment): Unit {
    try {
        clang_VirtualFileOverlay_dispose_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : STRUCT CXModuleMapDescriptorImpl
 */
class CXModuleMapDescriptorImpl {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("CXModuleMapDescriptorImpl")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
    }
}

/**
 * {@snippet lang=c : typedef (Declared(CXModuleMapDescriptorImpl))* CXModuleMapDescriptor;}
 */
typealias CXModuleMapDescriptor = MemorySegment?

/**
 * {@snippet lang=c : clang_ModuleMapDescriptor_create typedef CXModuleMapDescriptor = (Declared(CXModuleMapDescriptorImpl))*(UNSIGNED = Int)
 */
private val clang_ModuleMapDescriptor_create_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_ModuleMapDescriptor_create_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_ModuleMapDescriptor_create")
private val clang_ModuleMapDescriptor_create_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_ModuleMapDescriptor_create_ADDR, clang_ModuleMapDescriptor_create_DESC)

fun clang_ModuleMapDescriptor_create(arg0: Int): MemorySegment {
    try {
        return clang_ModuleMapDescriptor_create_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_ModuleMapDescriptor_setFrameworkModuleName Declared(CXErrorCode)(typedef CXModuleMapDescriptor = (Declared(CXModuleMapDescriptorImpl))*,(Char)*)
 */
private val clang_ModuleMapDescriptor_setFrameworkModuleName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_ModuleMapDescriptor_setFrameworkModuleName_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_ModuleMapDescriptor_setFrameworkModuleName")
private val clang_ModuleMapDescriptor_setFrameworkModuleName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_ModuleMapDescriptor_setFrameworkModuleName_ADDR, clang_ModuleMapDescriptor_setFrameworkModuleName_DESC)

fun clang_ModuleMapDescriptor_setFrameworkModuleName(arg0: MemorySegment, arg1: MemorySegment): Int {
    try {
        return clang_ModuleMapDescriptor_setFrameworkModuleName_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_ModuleMapDescriptor_setUmbrellaHeader Declared(CXErrorCode)(typedef CXModuleMapDescriptor = (Declared(CXModuleMapDescriptorImpl))*,(Char)*)
 */
private val clang_ModuleMapDescriptor_setUmbrellaHeader_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_ModuleMapDescriptor_setUmbrellaHeader_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_ModuleMapDescriptor_setUmbrellaHeader")
private val clang_ModuleMapDescriptor_setUmbrellaHeader_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_ModuleMapDescriptor_setUmbrellaHeader_ADDR, clang_ModuleMapDescriptor_setUmbrellaHeader_DESC)

fun clang_ModuleMapDescriptor_setUmbrellaHeader(arg0: MemorySegment, arg1: MemorySegment): Int {
    try {
        return clang_ModuleMapDescriptor_setUmbrellaHeader_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_ModuleMapDescriptor_writeToBuffer Declared(CXErrorCode)(typedef CXModuleMapDescriptor = (Declared(CXModuleMapDescriptorImpl))*,UNSIGNED = Int,((Char)*)*,(UNSIGNED = Int)*)
 */
private val clang_ModuleMapDescriptor_writeToBuffer_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_ModuleMapDescriptor_writeToBuffer_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_ModuleMapDescriptor_writeToBuffer")
private val clang_ModuleMapDescriptor_writeToBuffer_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_ModuleMapDescriptor_writeToBuffer_ADDR, clang_ModuleMapDescriptor_writeToBuffer_DESC)

fun clang_ModuleMapDescriptor_writeToBuffer(arg0: MemorySegment, arg1: Int, arg2: MemorySegment, arg3: MemorySegment): Int {
    try {
        return clang_ModuleMapDescriptor_writeToBuffer_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_ModuleMapDescriptor_dispose Void(typedef CXModuleMapDescriptor = (Declared(CXModuleMapDescriptorImpl))*)
 */
private val clang_ModuleMapDescriptor_dispose_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val clang_ModuleMapDescriptor_dispose_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_ModuleMapDescriptor_dispose")
private val clang_ModuleMapDescriptor_dispose_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_ModuleMapDescriptor_dispose_ADDR, clang_ModuleMapDescriptor_dispose_DESC)

fun clang_ModuleMapDescriptor_dispose(arg0: MemorySegment): Unit {
    try {
        clang_ModuleMapDescriptor_dispose_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : typedef SIGNED = Char __int8_t;}
 */
typealias _int8_t = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = Char __uint8_t;}
 */
typealias _uint8_t = Byte

/**
 * {@snippet lang=c : typedef Short __int16_t;}
 */
typealias _int16_t = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short __uint16_t;}
 */
typealias _uint16_t = Short

/**
 * {@snippet lang=c : typedef Int __int32_t;}
 */
typealias _int32_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int __uint32_t;}
 */
typealias _uint32_t = Int

/**
 * {@snippet lang=c : typedef LongLong __int64_t;}
 */
typealias _int64_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong __uint64_t;}
 */
typealias _uint64_t = Long

/**
 * {@snippet lang=c : typedef Long __darwin_intptr_t;}
 */
typealias _darwin_intptr_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int __darwin_natural_t;}
 */
typealias _darwin_natural_t = Int

/**
 * {@snippet lang=c : typedef Int __darwin_ct_rune_t;}
 */
typealias _darwin_ct_rune_t = Int

/**
 * WARNING: This was originally a C union. Fields overlap in memory!
 * {@snippet lang=c : UNION __mbstate_t
 */
/**
 * {@snippet lang=c : UNION __mbstate_t
 */
class _mbstate_t {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            MemoryLayout.sequenceLayout(128, ValueLayout.JAVA_BYTE).withName("__mbstate8"),
            ValueLayout.JAVA_LONG.withName("_mbstateL")
        ).withName("__mbstate_t")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        
        fun _mbstate8(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("__mbstate8")), layout.select(groupElement("__mbstate8")).byteSize())
        
        val _mbstateL_VH: VarHandle = layout.varHandle(groupElement("_mbstateL"))
        
        @Suppress("UNCHECKED_CAST")
        fun _mbstateL(segment: MemorySegment): Long =
            _mbstateL_VH.get(segment, 0L) as Long
        
        fun _mbstateL(segment: MemorySegment, value: Long) =
            _mbstateL_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : typedef Declared(__mbstate_t) __darwin_mbstate_t;}
 */
typealias _darwin_mbstate_t = MemorySegment

/**
 * {@snippet lang=c : typedef Long __darwin_ptrdiff_t;}
 */
typealias _darwin_ptrdiff_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long __darwin_size_t;}
 */
typealias _darwin_size_t = Long

/**
 * {@snippet lang=c : typedef (Char)* __darwin_va_list;}
 */
typealias _darwin_va_list = MemorySegment?

/**
 * {@snippet lang=c : typedef Int __darwin_wchar_t;}
 */
typealias _darwin_wchar_t = Int

/**
 * {@snippet lang=c : typedef Int __darwin_rune_t;}
 */
typealias _darwin_rune_t = Int

/**
 * {@snippet lang=c : typedef Int __darwin_wint_t;}
 */
typealias _darwin_wint_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Long __darwin_clock_t;}
 */
typealias _darwin_clock_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int __darwin_socklen_t;}
 */
typealias _darwin_socklen_t = Int

/**
 * {@snippet lang=c : typedef Long __darwin_ssize_t;}
 */
typealias _darwin_ssize_t = Long

/**
 * {@snippet lang=c : typedef Long __darwin_time_t;}
 */
typealias _darwin_time_t = Long

/**
 * {@snippet lang=c : typedef LongLong __darwin_blkcnt_t;}
 */
typealias _darwin_blkcnt_t = Long

/**
 * {@snippet lang=c : typedef Int __darwin_blksize_t;}
 */
typealias _darwin_blksize_t = Int

/**
 * {@snippet lang=c : typedef Int __darwin_dev_t;}
 */
typealias _darwin_dev_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int __darwin_fsblkcnt_t;}
 */
typealias _darwin_fsblkcnt_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int __darwin_fsfilcnt_t;}
 */
typealias _darwin_fsfilcnt_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int __darwin_gid_t;}
 */
typealias _darwin_gid_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int __darwin_id_t;}
 */
typealias _darwin_id_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong __darwin_ino64_t;}
 */
typealias _darwin_ino64_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong __darwin_ino_t;}
 */
typealias _darwin_ino_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int __darwin_mach_port_name_t;}
 */
typealias _darwin_mach_port_name_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int __darwin_mach_port_t;}
 */
typealias _darwin_mach_port_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short __darwin_mode_t;}
 */
typealias _darwin_mode_t = Short

/**
 * {@snippet lang=c : typedef LongLong __darwin_off_t;}
 */
typealias _darwin_off_t = Long

/**
 * {@snippet lang=c : typedef Int __darwin_pid_t;}
 */
typealias _darwin_pid_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int __darwin_sigset_t;}
 */
typealias _darwin_sigset_t = Int

/**
 * {@snippet lang=c : typedef Int __darwin_suseconds_t;}
 */
typealias _darwin_suseconds_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int __darwin_uid_t;}
 */
typealias _darwin_uid_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int __darwin_useconds_t;}
 */
typealias _darwin_useconds_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Char[16] __darwin_uuid_t;}
 */
typealias _darwin_uuid_t = MemorySegment

/**
 * {@snippet lang=c : typedef Char[37] __darwin_uuid_string_t;}
 */
typealias _darwin_uuid_string_t = MemorySegment

/**
 * {@snippet lang=c : STRUCT __darwin_pthread_handler_rec
 */
class _darwin_pthread_handler_rec {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("__routine"),
            ValueLayout.ADDRESS.withName("__arg"),
            ValueLayout.ADDRESS.withName("__next")
        ).withName("__darwin_pthread_handler_rec")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val _routine_VH: VarHandle = layout.varHandle(groupElement("__routine"))
        
        @Suppress("UNCHECKED_CAST")
        fun _routine(segment: MemorySegment): MemorySegment? =
            _routine_VH.get(segment, 0L) as MemorySegment
        
        fun _routine(segment: MemorySegment, value: MemorySegment) =
            _routine_VH.set(segment, 0L, value)
        
        val _arg_VH: VarHandle = layout.varHandle(groupElement("__arg"))
        
        @Suppress("UNCHECKED_CAST")
        fun _arg(segment: MemorySegment): MemorySegment? =
            _arg_VH.get(segment, 0L) as MemorySegment
        
        fun _arg(segment: MemorySegment, value: MemorySegment) =
            _arg_VH.set(segment, 0L, value)
        
        val _next_VH: VarHandle = layout.varHandle(groupElement("__next"))
        
        @Suppress("UNCHECKED_CAST")
        fun _next(segment: MemorySegment): MemorySegment? =
            _next_VH.get(segment, 0L) as MemorySegment
        
        fun _next(segment: MemorySegment, value: MemorySegment) =
            _next_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : STRUCT _opaque_pthread_attr_t
 */
class _opaque_pthread_attr_t {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("__sig"),
            MemoryLayout.sequenceLayout(56, ValueLayout.JAVA_BYTE).withName("__opaque")
        ).withName("_opaque_pthread_attr_t")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val _sig_VH: VarHandle = layout.varHandle(groupElement("__sig"))
        
        @Suppress("UNCHECKED_CAST")
        fun _sig(segment: MemorySegment): Long =
            _sig_VH.get(segment, 0L) as Long
        
        fun _sig(segment: MemorySegment, value: Long) =
            _sig_VH.set(segment, 0L, value)
        
        
        fun _opaque(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("__opaque")), layout.select(groupElement("__opaque")).byteSize())
    }
}

/**
 * {@snippet lang=c : STRUCT _opaque_pthread_cond_t
 */
class _opaque_pthread_cond_t {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("__sig"),
            MemoryLayout.sequenceLayout(40, ValueLayout.JAVA_BYTE).withName("__opaque")
        ).withName("_opaque_pthread_cond_t")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val _sig_VH: VarHandle = layout.varHandle(groupElement("__sig"))
        
        @Suppress("UNCHECKED_CAST")
        fun _sig(segment: MemorySegment): Long =
            _sig_VH.get(segment, 0L) as Long
        
        fun _sig(segment: MemorySegment, value: Long) =
            _sig_VH.set(segment, 0L, value)
        
        
        fun _opaque(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("__opaque")), layout.select(groupElement("__opaque")).byteSize())
    }
}

/**
 * {@snippet lang=c : STRUCT _opaque_pthread_condattr_t
 */
class _opaque_pthread_condattr_t {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("__sig"),
            MemoryLayout.sequenceLayout(8, ValueLayout.JAVA_BYTE).withName("__opaque")
        ).withName("_opaque_pthread_condattr_t")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val _sig_VH: VarHandle = layout.varHandle(groupElement("__sig"))
        
        @Suppress("UNCHECKED_CAST")
        fun _sig(segment: MemorySegment): Long =
            _sig_VH.get(segment, 0L) as Long
        
        fun _sig(segment: MemorySegment, value: Long) =
            _sig_VH.set(segment, 0L, value)
        
        
        fun _opaque(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("__opaque")), layout.select(groupElement("__opaque")).byteSize())
    }
}

/**
 * {@snippet lang=c : STRUCT _opaque_pthread_mutex_t
 */
class _opaque_pthread_mutex_t {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("__sig"),
            MemoryLayout.sequenceLayout(56, ValueLayout.JAVA_BYTE).withName("__opaque")
        ).withName("_opaque_pthread_mutex_t")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val _sig_VH: VarHandle = layout.varHandle(groupElement("__sig"))
        
        @Suppress("UNCHECKED_CAST")
        fun _sig(segment: MemorySegment): Long =
            _sig_VH.get(segment, 0L) as Long
        
        fun _sig(segment: MemorySegment, value: Long) =
            _sig_VH.set(segment, 0L, value)
        
        
        fun _opaque(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("__opaque")), layout.select(groupElement("__opaque")).byteSize())
    }
}

/**
 * {@snippet lang=c : STRUCT _opaque_pthread_mutexattr_t
 */
class _opaque_pthread_mutexattr_t {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("__sig"),
            MemoryLayout.sequenceLayout(8, ValueLayout.JAVA_BYTE).withName("__opaque")
        ).withName("_opaque_pthread_mutexattr_t")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val _sig_VH: VarHandle = layout.varHandle(groupElement("__sig"))
        
        @Suppress("UNCHECKED_CAST")
        fun _sig(segment: MemorySegment): Long =
            _sig_VH.get(segment, 0L) as Long
        
        fun _sig(segment: MemorySegment, value: Long) =
            _sig_VH.set(segment, 0L, value)
        
        
        fun _opaque(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("__opaque")), layout.select(groupElement("__opaque")).byteSize())
    }
}

/**
 * {@snippet lang=c : STRUCT _opaque_pthread_once_t
 */
class _opaque_pthread_once_t {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("__sig"),
            MemoryLayout.sequenceLayout(8, ValueLayout.JAVA_BYTE).withName("__opaque")
        ).withName("_opaque_pthread_once_t")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val _sig_VH: VarHandle = layout.varHandle(groupElement("__sig"))
        
        @Suppress("UNCHECKED_CAST")
        fun _sig(segment: MemorySegment): Long =
            _sig_VH.get(segment, 0L) as Long
        
        fun _sig(segment: MemorySegment, value: Long) =
            _sig_VH.set(segment, 0L, value)
        
        
        fun _opaque(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("__opaque")), layout.select(groupElement("__opaque")).byteSize())
    }
}

/**
 * {@snippet lang=c : STRUCT _opaque_pthread_rwlock_t
 */
class _opaque_pthread_rwlock_t {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("__sig"),
            MemoryLayout.sequenceLayout(192, ValueLayout.JAVA_BYTE).withName("__opaque")
        ).withName("_opaque_pthread_rwlock_t")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val _sig_VH: VarHandle = layout.varHandle(groupElement("__sig"))
        
        @Suppress("UNCHECKED_CAST")
        fun _sig(segment: MemorySegment): Long =
            _sig_VH.get(segment, 0L) as Long
        
        fun _sig(segment: MemorySegment, value: Long) =
            _sig_VH.set(segment, 0L, value)
        
        
        fun _opaque(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("__opaque")), layout.select(groupElement("__opaque")).byteSize())
    }
}

/**
 * {@snippet lang=c : STRUCT _opaque_pthread_rwlockattr_t
 */
class _opaque_pthread_rwlockattr_t {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("__sig"),
            MemoryLayout.sequenceLayout(16, ValueLayout.JAVA_BYTE).withName("__opaque")
        ).withName("_opaque_pthread_rwlockattr_t")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val _sig_VH: VarHandle = layout.varHandle(groupElement("__sig"))
        
        @Suppress("UNCHECKED_CAST")
        fun _sig(segment: MemorySegment): Long =
            _sig_VH.get(segment, 0L) as Long
        
        fun _sig(segment: MemorySegment, value: Long) =
            _sig_VH.set(segment, 0L, value)
        
        
        fun _opaque(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("__opaque")), layout.select(groupElement("__opaque")).byteSize())
    }
}

/**
 * {@snippet lang=c : STRUCT _opaque_pthread_t
 */
class _opaque_pthread_t {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("__sig"),
            ValueLayout.ADDRESS.withName("__cleanup_stack"),
            MemoryLayout.sequenceLayout(8176, ValueLayout.JAVA_BYTE).withName("__opaque")
        ).withName("_opaque_pthread_t")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val _sig_VH: VarHandle = layout.varHandle(groupElement("__sig"))
        
        @Suppress("UNCHECKED_CAST")
        fun _sig(segment: MemorySegment): Long =
            _sig_VH.get(segment, 0L) as Long
        
        fun _sig(segment: MemorySegment, value: Long) =
            _sig_VH.set(segment, 0L, value)
        
        val _cleanup_stack_VH: VarHandle = layout.varHandle(groupElement("__cleanup_stack"))
        
        @Suppress("UNCHECKED_CAST")
        fun _cleanup_stack(segment: MemorySegment): MemorySegment? =
            _cleanup_stack_VH.get(segment, 0L) as MemorySegment
        
        fun _cleanup_stack(segment: MemorySegment, value: MemorySegment) =
            _cleanup_stack_VH.set(segment, 0L, value)
        
        
        fun _opaque(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("__opaque")), layout.select(groupElement("__opaque")).byteSize())
    }
}

/**
 * {@snippet lang=c : typedef Declared(_opaque_pthread_attr_t) __darwin_pthread_attr_t;}
 */
typealias _darwin_pthread_attr_t = MemorySegment

/**
 * {@snippet lang=c : typedef Declared(_opaque_pthread_cond_t) __darwin_pthread_cond_t;}
 */
typealias _darwin_pthread_cond_t = MemorySegment

/**
 * {@snippet lang=c : typedef Declared(_opaque_pthread_condattr_t) __darwin_pthread_condattr_t;}
 */
typealias _darwin_pthread_condattr_t = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Long __darwin_pthread_key_t;}
 */
typealias _darwin_pthread_key_t = Long

/**
 * {@snippet lang=c : typedef Declared(_opaque_pthread_mutex_t) __darwin_pthread_mutex_t;}
 */
typealias _darwin_pthread_mutex_t = MemorySegment

/**
 * {@snippet lang=c : typedef Declared(_opaque_pthread_mutexattr_t) __darwin_pthread_mutexattr_t;}
 */
typealias _darwin_pthread_mutexattr_t = MemorySegment

/**
 * {@snippet lang=c : typedef Declared(_opaque_pthread_once_t) __darwin_pthread_once_t;}
 */
typealias _darwin_pthread_once_t = MemorySegment

/**
 * {@snippet lang=c : typedef Declared(_opaque_pthread_rwlock_t) __darwin_pthread_rwlock_t;}
 */
typealias _darwin_pthread_rwlock_t = MemorySegment

/**
 * {@snippet lang=c : typedef Declared(_opaque_pthread_rwlockattr_t) __darwin_pthread_rwlockattr_t;}
 */
typealias _darwin_pthread_rwlockattr_t = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(_opaque_pthread_t))* __darwin_pthread_t;}
 */
typealias _darwin_pthread_t = MemorySegment?

/**
 * {@snippet lang=c : typedef Int __darwin_nl_item;}
 */
typealias _darwin_nl_item = Int

/**
 * {@snippet lang=c : typedef Int __darwin_wctrans_t;}
 */
typealias _darwin_wctrans_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int __darwin_wctype_t;}
 */
typealias _darwin_wctype_t = Int

/**
 * {@snippet lang=c : typedef SIGNED = Char int8_t;}
 */
typealias int8_t = Byte

/**
 * {@snippet lang=c : typedef Short int16_t;}
 */
typealias int16_t = Short

/**
 * {@snippet lang=c : typedef Int int32_t;}
 */
typealias int32_t = Int

/**
 * {@snippet lang=c : typedef LongLong int64_t;}
 */
typealias int64_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Char u_int8_t;}
 */
typealias u_int8_t = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = Short u_int16_t;}
 */
typealias u_int16_t = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int u_int32_t;}
 */
typealias u_int32_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong u_int64_t;}
 */
typealias u_int64_t = Long

/**
 * {@snippet lang=c : typedef LongLong register_t;}
 */
typealias register_t = Long

/**
 * {@snippet lang=c : typedef Long intptr_t;}
 */
typealias intptr_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long uintptr_t;}
 */
typealias uintptr_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong user_addr_t;}
 */
typealias user_addr_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong user_size_t;}
 */
typealias user_size_t = Long

/**
 * {@snippet lang=c : typedef LongLong user_ssize_t;}
 */
typealias user_ssize_t = Long

/**
 * {@snippet lang=c : typedef LongLong user_long_t;}
 */
typealias user_long_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong user_ulong_t;}
 */
typealias user_ulong_t = Long

/**
 * {@snippet lang=c : typedef LongLong user_time_t;}
 */
typealias user_time_t = Long

/**
 * {@snippet lang=c : typedef LongLong user_off_t;}
 */
typealias user_off_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong syscall_arg_t;}
 */
typealias syscall_arg_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long clock_t;}
 */
typealias clock_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long size_t;}
 */
typealias size_t = Long

/**
 * {@snippet lang=c : typedef Long time_t;}
 */
typealias time_t = Long

/**
 * {@snippet lang=c : STRUCT timespec
 */
class timespec {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("tv_sec"),
            ValueLayout.JAVA_LONG.withName("tv_nsec")
        ).withName("timespec")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val tv_sec_VH: VarHandle = layout.varHandle(groupElement("tv_sec"))
        
        @Suppress("UNCHECKED_CAST")
        fun tv_sec(segment: MemorySegment): Long =
            tv_sec_VH.get(segment, 0L) as Long
        
        fun tv_sec(segment: MemorySegment, value: Long) =
            tv_sec_VH.set(segment, 0L, value)
        
        val tv_nsec_VH: VarHandle = layout.varHandle(groupElement("tv_nsec"))
        
        @Suppress("UNCHECKED_CAST")
        fun tv_nsec(segment: MemorySegment): Long =
            tv_nsec_VH.get(segment, 0L) as Long
        
        fun tv_nsec(segment: MemorySegment, value: Long) =
            tv_nsec_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : STRUCT tm
 */
class tm {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("tm_sec"),
            ValueLayout.JAVA_INT.withName("tm_min"),
            ValueLayout.JAVA_INT.withName("tm_hour"),
            ValueLayout.JAVA_INT.withName("tm_mday"),
            ValueLayout.JAVA_INT.withName("tm_mon"),
            ValueLayout.JAVA_INT.withName("tm_year"),
            ValueLayout.JAVA_INT.withName("tm_wday"),
            ValueLayout.JAVA_INT.withName("tm_yday"),
            ValueLayout.JAVA_INT.withName("tm_isdst"),
            ValueLayout.JAVA_LONG.withName("tm_gmtoff"),
            ValueLayout.ADDRESS.withName("tm_zone")
        ).withName("tm")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val tm_sec_VH: VarHandle = layout.varHandle(groupElement("tm_sec"))
        
        @Suppress("UNCHECKED_CAST")
        fun tm_sec(segment: MemorySegment): Int =
            tm_sec_VH.get(segment, 0L) as Int
        
        fun tm_sec(segment: MemorySegment, value: Int) =
            tm_sec_VH.set(segment, 0L, value)
        
        val tm_min_VH: VarHandle = layout.varHandle(groupElement("tm_min"))
        
        @Suppress("UNCHECKED_CAST")
        fun tm_min(segment: MemorySegment): Int =
            tm_min_VH.get(segment, 0L) as Int
        
        fun tm_min(segment: MemorySegment, value: Int) =
            tm_min_VH.set(segment, 0L, value)
        
        val tm_hour_VH: VarHandle = layout.varHandle(groupElement("tm_hour"))
        
        @Suppress("UNCHECKED_CAST")
        fun tm_hour(segment: MemorySegment): Int =
            tm_hour_VH.get(segment, 0L) as Int
        
        fun tm_hour(segment: MemorySegment, value: Int) =
            tm_hour_VH.set(segment, 0L, value)
        
        val tm_mday_VH: VarHandle = layout.varHandle(groupElement("tm_mday"))
        
        @Suppress("UNCHECKED_CAST")
        fun tm_mday(segment: MemorySegment): Int =
            tm_mday_VH.get(segment, 0L) as Int
        
        fun tm_mday(segment: MemorySegment, value: Int) =
            tm_mday_VH.set(segment, 0L, value)
        
        val tm_mon_VH: VarHandle = layout.varHandle(groupElement("tm_mon"))
        
        @Suppress("UNCHECKED_CAST")
        fun tm_mon(segment: MemorySegment): Int =
            tm_mon_VH.get(segment, 0L) as Int
        
        fun tm_mon(segment: MemorySegment, value: Int) =
            tm_mon_VH.set(segment, 0L, value)
        
        val tm_year_VH: VarHandle = layout.varHandle(groupElement("tm_year"))
        
        @Suppress("UNCHECKED_CAST")
        fun tm_year(segment: MemorySegment): Int =
            tm_year_VH.get(segment, 0L) as Int
        
        fun tm_year(segment: MemorySegment, value: Int) =
            tm_year_VH.set(segment, 0L, value)
        
        val tm_wday_VH: VarHandle = layout.varHandle(groupElement("tm_wday"))
        
        @Suppress("UNCHECKED_CAST")
        fun tm_wday(segment: MemorySegment): Int =
            tm_wday_VH.get(segment, 0L) as Int
        
        fun tm_wday(segment: MemorySegment, value: Int) =
            tm_wday_VH.set(segment, 0L, value)
        
        val tm_yday_VH: VarHandle = layout.varHandle(groupElement("tm_yday"))
        
        @Suppress("UNCHECKED_CAST")
        fun tm_yday(segment: MemorySegment): Int =
            tm_yday_VH.get(segment, 0L) as Int
        
        fun tm_yday(segment: MemorySegment, value: Int) =
            tm_yday_VH.set(segment, 0L, value)
        
        val tm_isdst_VH: VarHandle = layout.varHandle(groupElement("tm_isdst"))
        
        @Suppress("UNCHECKED_CAST")
        fun tm_isdst(segment: MemorySegment): Int =
            tm_isdst_VH.get(segment, 0L) as Int
        
        fun tm_isdst(segment: MemorySegment, value: Int) =
            tm_isdst_VH.set(segment, 0L, value)
        
        val tm_gmtoff_VH: VarHandle = layout.varHandle(groupElement("tm_gmtoff"))
        
        @Suppress("UNCHECKED_CAST")
        fun tm_gmtoff(segment: MemorySegment): Long =
            tm_gmtoff_VH.get(segment, 0L) as Long
        
        fun tm_gmtoff(segment: MemorySegment, value: Long) =
            tm_gmtoff_VH.set(segment, 0L, value)
        
        val tm_zone_VH: VarHandle = layout.varHandle(groupElement("tm_zone"))
        
        @Suppress("UNCHECKED_CAST")
        fun tm_zone(segment: MemorySegment): MemorySegment? =
            tm_zone_VH.get(segment, 0L) as MemorySegment
        
        fun tm_zone(segment: MemorySegment, value: MemorySegment) =
            tm_zone_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : tzname (Char)*[]
 */
// tzname is an array type: use the raw MemorySegment directly
private val tzname_SEGMENT: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("tzname")

val tzname: MemorySegment
    get() = tzname_SEGMENT

/**
 * {@snippet lang=c : getdate_err Int
 */
private val getdate_err_LAYOUT: ValueLayout = ValueLayout.JAVA_INT
private val getdate_err_SEGMENT: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("getdate_err")
private val getdate_err_VH: VarHandle = getdate_err_LAYOUT.varHandle()

var getdate_err: Int
    @Suppress("UNCHECKED_CAST")
    get() = getdate_err_VH.get(getdate_err_SEGMENT) as Int
    set(value) = getdate_err_VH.set(getdate_err_SEGMENT, value)

/**
 * {@snippet lang=c : timezone Long
 */
private val timezone_LAYOUT: ValueLayout = ValueLayout.JAVA_LONG
private val timezone_SEGMENT: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("timezone")
private val timezone_VH: VarHandle = timezone_LAYOUT.varHandle()

var timezone: Long
    @Suppress("UNCHECKED_CAST")
    get() = timezone_VH.get(timezone_SEGMENT) as Long
    set(value) = timezone_VH.set(timezone_SEGMENT, value)

/**
 * {@snippet lang=c : daylight Int
 */
private val daylight_LAYOUT: ValueLayout = ValueLayout.JAVA_INT
private val daylight_SEGMENT: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("daylight")
private val daylight_VH: VarHandle = daylight_LAYOUT.varHandle()

var daylight: Int
    @Suppress("UNCHECKED_CAST")
    get() = daylight_VH.get(daylight_SEGMENT) as Int
    set(value) = daylight_VH.set(daylight_SEGMENT, value)

/**
 * {@snippet lang=c : asctime (Char)*((Declared(tm))*)
 */
private val asctime_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val asctime_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("asctime")
private val asctime_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(asctime_ADDR, asctime_DESC)

fun asctime(arg0: MemorySegment): MemorySegment {
    try {
        return asctime_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clock typedef clock_t = UNSIGNED = Long()
 */
private val clock_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val clock_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clock")
private val clock_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clock_ADDR, clock_DESC)

fun clock(): Long {
    try {
        return clock_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : ctime (Char)*((typedef time_t = Long)*)
 */
private val ctime_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val ctime_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("ctime")
private val ctime_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(ctime_ADDR, ctime_DESC)

fun ctime(arg0: MemorySegment): MemorySegment {
    try {
        return ctime_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : difftime Double(typedef time_t = Long,typedef time_t = Long)
 */
private val difftime_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)
private val difftime_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("difftime")
private val difftime_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(difftime_ADDR, difftime_DESC)

fun difftime(arg0: Long, arg1: Long): Double {
    try {
        return difftime_HANDLE.invokeExact(arg0, arg1) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : getdate (Declared(tm))*((Char)*)
 */
private val getdate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val getdate_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("getdate")
private val getdate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(getdate_ADDR, getdate_DESC)

fun getdate(arg0: MemorySegment): MemorySegment {
    try {
        return getdate_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : gmtime (Declared(tm))*((typedef time_t = Long)*)
 */
private val gmtime_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val gmtime_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("gmtime")
private val gmtime_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(gmtime_ADDR, gmtime_DESC)

fun gmtime(arg0: MemorySegment): MemorySegment {
    try {
        return gmtime_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : localtime (Declared(tm))*((typedef time_t = Long)*)
 */
private val localtime_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val localtime_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("localtime")
private val localtime_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(localtime_ADDR, localtime_DESC)

fun localtime(arg0: MemorySegment): MemorySegment {
    try {
        return localtime_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : mktime typedef time_t = Long((Declared(tm))*)
 */
private val mktime_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val mktime_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("mktime")
private val mktime_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(mktime_ADDR, mktime_DESC)

fun mktime(arg0: MemorySegment): Long {
    try {
        return mktime_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : strftime typedef size_t = UNSIGNED = Long((Char)*,typedef size_t = UNSIGNED = Long,(Char)*,(Declared(tm))*)
 */
private val strftime_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val strftime_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("strftime")
private val strftime_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(strftime_ADDR, strftime_DESC)

fun strftime(arg0: MemorySegment, arg1: Long, arg2: MemorySegment, arg3: MemorySegment): Long {
    try {
        return strftime_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : strptime (Char)*((Char)*,(Char)*,(Declared(tm))*)
 */
private val strptime_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val strptime_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("strptime")
private val strptime_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(strptime_ADDR, strptime_DESC)

fun strptime(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return strptime_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : time typedef time_t = Long((typedef time_t = Long)*)
 */
private val time_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val time_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("time")
private val time_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(time_ADDR, time_DESC)

fun time(arg0: MemorySegment): Long {
    try {
        return time_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : tzset Void()
 */
private val tzset_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid()
private val tzset_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("tzset")
private val tzset_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(tzset_ADDR, tzset_DESC)

fun tzset(): Unit {
    try {
        tzset_HANDLE.invokeExact()
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : asctime_r (Char)*((Declared(tm))*,(Char)*)
 */
private val asctime_r_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val asctime_r_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("asctime_r")
private val asctime_r_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(asctime_r_ADDR, asctime_r_DESC)

fun asctime_r(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return asctime_r_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : ctime_r (Char)*((typedef time_t = Long)*,(Char)*)
 */
private val ctime_r_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val ctime_r_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("ctime_r")
private val ctime_r_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(ctime_r_ADDR, ctime_r_DESC)

fun ctime_r(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return ctime_r_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : gmtime_r (Declared(tm))*((typedef time_t = Long)*,(Declared(tm))*)
 */
private val gmtime_r_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val gmtime_r_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("gmtime_r")
private val gmtime_r_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(gmtime_r_ADDR, gmtime_r_DESC)

fun gmtime_r(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return gmtime_r_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : localtime_r (Declared(tm))*((typedef time_t = Long)*,(Declared(tm))*)
 */
private val localtime_r_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val localtime_r_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("localtime_r")
private val localtime_r_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(localtime_r_ADDR, localtime_r_DESC)

fun localtime_r(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return localtime_r_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : posix2time typedef time_t = Long(typedef time_t = Long)
 */
private val posix2time_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)
private val posix2time_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("posix2time")
private val posix2time_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(posix2time_ADDR, posix2time_DESC)

fun posix2time(arg0: Long): Long {
    try {
        return posix2time_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : tzsetwall Void()
 */
private val tzsetwall_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid()
private val tzsetwall_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("tzsetwall")
private val tzsetwall_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(tzsetwall_ADDR, tzsetwall_DESC)

fun tzsetwall(): Unit {
    try {
        tzsetwall_HANDLE.invokeExact()
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : time2posix typedef time_t = Long(typedef time_t = Long)
 */
private val time2posix_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)
private val time2posix_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("time2posix")
private val time2posix_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(time2posix_ADDR, time2posix_DESC)

fun time2posix(arg0: Long): Long {
    try {
        return time2posix_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : timelocal typedef time_t = Long((Declared(tm))*)
 */
private val timelocal_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val timelocal_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("timelocal")
private val timelocal_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(timelocal_ADDR, timelocal_DESC)

fun timelocal(arg0: MemorySegment): Long {
    try {
        return timelocal_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : timegm typedef time_t = Long((Declared(tm))*)
 */
private val timegm_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val timegm_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("timegm")
private val timegm_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(timegm_ADDR, timegm_DESC)

fun timegm(arg0: MemorySegment): Long {
    try {
        return timegm_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : nanosleep Int((Declared(timespec))*,(Declared(timespec))*)
 */
private val nanosleep_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val nanosleep_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("nanosleep")
private val nanosleep_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(nanosleep_ADDR, nanosleep_DESC)

fun nanosleep(arg0: MemorySegment, arg1: MemorySegment): Int {
    try {
        return nanosleep_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define _CLOCK_REALTIME 0
 */
fun _CLOCK_REALTIME(): Int = 0

/**
 * {@snippet lang=c : #define _CLOCK_MONOTONIC 6
 */
fun _CLOCK_MONOTONIC(): Int = 6

/**
 * {@snippet lang=c : #define _CLOCK_MONOTONIC_RAW 4
 */
fun _CLOCK_MONOTONIC_RAW(): Int = 4

/**
 * {@snippet lang=c : #define _CLOCK_MONOTONIC_RAW_APPROX 5
 */
fun _CLOCK_MONOTONIC_RAW_APPROX(): Int = 5

/**
 * {@snippet lang=c : #define _CLOCK_UPTIME_RAW 8
 */
fun _CLOCK_UPTIME_RAW(): Int = 8

/**
 * {@snippet lang=c : #define _CLOCK_UPTIME_RAW_APPROX 9
 */
fun _CLOCK_UPTIME_RAW_APPROX(): Int = 9

/**
 * {@snippet lang=c : #define _CLOCK_PROCESS_CPUTIME_ID 12
 */
fun _CLOCK_PROCESS_CPUTIME_ID(): Int = 12

/**
 * {@snippet lang=c : #define _CLOCK_THREAD_CPUTIME_ID 16
 */
fun _CLOCK_THREAD_CPUTIME_ID(): Int = 16

/**
 * {@snippet lang=c : clock_getres Int(typedef clockid_t = Declared(clockid_t),(Declared(timespec))*)
 */
private val clock_getres_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clock_getres_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clock_getres")
private val clock_getres_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clock_getres_ADDR, clock_getres_DESC)

fun clock_getres(arg0: Int, arg1: MemorySegment): Int {
    try {
        return clock_getres_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clock_gettime Int(typedef clockid_t = Declared(clockid_t),(Declared(timespec))*)
 */
private val clock_gettime_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clock_gettime_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clock_gettime")
private val clock_gettime_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clock_gettime_ADDR, clock_gettime_DESC)

fun clock_gettime(arg0: Int, arg1: MemorySegment): Int {
    try {
        return clock_gettime_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clock_gettime_nsec_np typedef __uint64_t = UNSIGNED = LongLong(typedef clockid_t = Declared(clockid_t))
 */
private val clock_gettime_nsec_np_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT)
private val clock_gettime_nsec_np_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clock_gettime_nsec_np")
private val clock_gettime_nsec_np_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clock_gettime_nsec_np_ADDR, clock_gettime_nsec_np_DESC)

fun clock_gettime_nsec_np(arg0: Int): Long {
    try {
        return clock_gettime_nsec_np_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clock_settime Int(typedef clockid_t = Declared(clockid_t),(Declared(timespec))*)
 */
private val clock_settime_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clock_settime_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clock_settime")
private val clock_settime_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clock_settime_ADDR, clock_settime_DESC)

fun clock_settime(arg0: Int, arg1: MemorySegment): Int {
    try {
        return clock_settime_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : timespec_get Int((Declared(timespec))*,Int)
 */
private val timespec_get_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val timespec_get_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("timespec_get")
private val timespec_get_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(timespec_get_ADDR, timespec_get_DESC)

fun timespec_get(arg0: MemorySegment, arg1: Int): Int {
    try {
        return timespec_get_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : typedef (Void)* CXFile;}
 */
typealias CXFile = MemorySegment?

/**
 * {@snippet lang=c : clang_getFileName typedef CXString = Declared(CXString)(typedef CXFile = (Void)*)
 */
private val clang_getFileName_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS)
private val clang_getFileName_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getFileName")
private val clang_getFileName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getFileName_ADDR, clang_getFileName_DESC)

fun clang_getFileName(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getFileName_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getFileTime typedef time_t = Long(typedef CXFile = (Void)*)
 */
private val clang_getFileTime_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val clang_getFileTime_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getFileTime")
private val clang_getFileTime_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getFileTime_ADDR, clang_getFileTime_DESC)

fun clang_getFileTime(arg0: MemorySegment): Long {
    try {
        return clang_getFileTime_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : STRUCT CXFileUniqueID
 */
class CXFileUniqueID {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            MemoryLayout.sequenceLayout(3, ValueLayout.JAVA_LONG).withName("data")
        ).withName("CXFileUniqueID")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        
        fun data_(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("data")), layout.select(groupElement("data")).byteSize())
    }
}

/**
 * {@snippet lang=c : clang_getFileUniqueID Int(typedef CXFile = (Void)*,(typedef CXFileUniqueID = Declared(CXFileUniqueID))*)
 */
private val clang_getFileUniqueID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_getFileUniqueID_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getFileUniqueID")
private val clang_getFileUniqueID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getFileUniqueID_ADDR, clang_getFileUniqueID_DESC)

fun clang_getFileUniqueID(arg0: MemorySegment, arg1: MemorySegment): Int {
    try {
        return clang_getFileUniqueID_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_File_isEqual Int(typedef CXFile = (Void)*,typedef CXFile = (Void)*)
 */
private val clang_File_isEqual_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_File_isEqual_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_File_isEqual")
private val clang_File_isEqual_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_File_isEqual_ADDR, clang_File_isEqual_DESC)

fun clang_File_isEqual(arg0: MemorySegment, arg1: MemorySegment): Int {
    try {
        return clang_File_isEqual_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_File_tryGetRealPathName typedef CXString = Declared(CXString)(typedef CXFile = (Void)*)
 */
private val clang_File_tryGetRealPathName_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS)
private val clang_File_tryGetRealPathName_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_File_tryGetRealPathName")
private val clang_File_tryGetRealPathName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_File_tryGetRealPathName_ADDR, clang_File_tryGetRealPathName_DESC)

fun clang_File_tryGetRealPathName(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_File_tryGetRealPathName_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : STRUCT CXSourceLocation
 */
class CXSourceLocation {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            MemoryLayout.sequenceLayout(2, ValueLayout.ADDRESS).withName("ptr_data"),
            ValueLayout.JAVA_INT.withName("int_data"),
            MemoryLayout.paddingLayout(4)
        ).withName("CXSourceLocation")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        
        fun ptr_data(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("ptr_data")), layout.select(groupElement("ptr_data")).byteSize())
        
        val int_data_VH: VarHandle = layout.varHandle(groupElement("int_data"))
        
        @Suppress("UNCHECKED_CAST")
        fun int_data(segment: MemorySegment): Int =
            int_data_VH.get(segment, 0L) as Int
        
        fun int_data(segment: MemorySegment, value: Int) =
            int_data_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : STRUCT CXSourceRange
 */
class CXSourceRange {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            MemoryLayout.sequenceLayout(2, ValueLayout.ADDRESS).withName("ptr_data"),
            ValueLayout.JAVA_INT.withName("begin_int_data"),
            ValueLayout.JAVA_INT.withName("end_int_data")
        ).withName("CXSourceRange")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        
        fun ptr_data(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("ptr_data")), layout.select(groupElement("ptr_data")).byteSize())
        
        val begin_int_data_VH: VarHandle = layout.varHandle(groupElement("begin_int_data"))
        
        @Suppress("UNCHECKED_CAST")
        fun begin_int_data(segment: MemorySegment): Int =
            begin_int_data_VH.get(segment, 0L) as Int
        
        fun begin_int_data(segment: MemorySegment, value: Int) =
            begin_int_data_VH.set(segment, 0L, value)
        
        val end_int_data_VH: VarHandle = layout.varHandle(groupElement("end_int_data"))
        
        @Suppress("UNCHECKED_CAST")
        fun end_int_data(segment: MemorySegment): Int =
            end_int_data_VH.get(segment, 0L) as Int
        
        fun end_int_data(segment: MemorySegment, value: Int) =
            end_int_data_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : clang_getNullLocation typedef CXSourceLocation = Declared(CXSourceLocation)()
 */
private val clang_getNullLocation_DESC: FunctionDescriptor = FunctionDescriptor.of(CXSourceLocation.layout)
private val clang_getNullLocation_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getNullLocation")
private val clang_getNullLocation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getNullLocation_ADDR, clang_getNullLocation_DESC)

fun clang_getNullLocation(allocator: SegmentAllocator): MemorySegment {
    try {
        return clang_getNullLocation_HANDLE.invokeExact(allocator) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_equalLocations UNSIGNED = Int(typedef CXSourceLocation = Declared(CXSourceLocation),typedef CXSourceLocation = Declared(CXSourceLocation))
 */
private val clang_equalLocations_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXSourceLocation.layout, CXSourceLocation.layout)
private val clang_equalLocations_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_equalLocations")
private val clang_equalLocations_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_equalLocations_ADDR, clang_equalLocations_DESC)

fun clang_equalLocations(arg0: MemorySegment, arg1: MemorySegment): Int {
    try {
        return clang_equalLocations_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_isBeforeInTranslationUnit UNSIGNED = Int(typedef CXSourceLocation = Declared(CXSourceLocation),typedef CXSourceLocation = Declared(CXSourceLocation))
 */
private val clang_isBeforeInTranslationUnit_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXSourceLocation.layout, CXSourceLocation.layout)
private val clang_isBeforeInTranslationUnit_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_isBeforeInTranslationUnit")
private val clang_isBeforeInTranslationUnit_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_isBeforeInTranslationUnit_ADDR, clang_isBeforeInTranslationUnit_DESC)

fun clang_isBeforeInTranslationUnit(arg0: MemorySegment, arg1: MemorySegment): Int {
    try {
        return clang_isBeforeInTranslationUnit_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Location_isInSystemHeader Int(typedef CXSourceLocation = Declared(CXSourceLocation))
 */
private val clang_Location_isInSystemHeader_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXSourceLocation.layout)
private val clang_Location_isInSystemHeader_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Location_isInSystemHeader")
private val clang_Location_isInSystemHeader_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Location_isInSystemHeader_ADDR, clang_Location_isInSystemHeader_DESC)

fun clang_Location_isInSystemHeader(arg0: MemorySegment): Int {
    try {
        return clang_Location_isInSystemHeader_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Location_isFromMainFile Int(typedef CXSourceLocation = Declared(CXSourceLocation))
 */
private val clang_Location_isFromMainFile_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXSourceLocation.layout)
private val clang_Location_isFromMainFile_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Location_isFromMainFile")
private val clang_Location_isFromMainFile_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Location_isFromMainFile_ADDR, clang_Location_isFromMainFile_DESC)

fun clang_Location_isFromMainFile(arg0: MemorySegment): Int {
    try {
        return clang_Location_isFromMainFile_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getNullRange typedef CXSourceRange = Declared(CXSourceRange)()
 */
private val clang_getNullRange_DESC: FunctionDescriptor = FunctionDescriptor.of(CXSourceRange.layout)
private val clang_getNullRange_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getNullRange")
private val clang_getNullRange_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getNullRange_ADDR, clang_getNullRange_DESC)

fun clang_getNullRange(allocator: SegmentAllocator): MemorySegment {
    try {
        return clang_getNullRange_HANDLE.invokeExact(allocator) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getRange typedef CXSourceRange = Declared(CXSourceRange)(typedef CXSourceLocation = Declared(CXSourceLocation),typedef CXSourceLocation = Declared(CXSourceLocation))
 */
private val clang_getRange_DESC: FunctionDescriptor = FunctionDescriptor.of(CXSourceRange.layout, CXSourceLocation.layout, CXSourceLocation.layout)
private val clang_getRange_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getRange")
private val clang_getRange_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getRange_ADDR, clang_getRange_DESC)

fun clang_getRange(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return clang_getRange_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_equalRanges UNSIGNED = Int(typedef CXSourceRange = Declared(CXSourceRange),typedef CXSourceRange = Declared(CXSourceRange))
 */
private val clang_equalRanges_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXSourceRange.layout, CXSourceRange.layout)
private val clang_equalRanges_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_equalRanges")
private val clang_equalRanges_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_equalRanges_ADDR, clang_equalRanges_DESC)

fun clang_equalRanges(arg0: MemorySegment, arg1: MemorySegment): Int {
    try {
        return clang_equalRanges_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Range_isNull Int(typedef CXSourceRange = Declared(CXSourceRange))
 */
private val clang_Range_isNull_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXSourceRange.layout)
private val clang_Range_isNull_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Range_isNull")
private val clang_Range_isNull_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Range_isNull_ADDR, clang_Range_isNull_DESC)

fun clang_Range_isNull(arg0: MemorySegment): Int {
    try {
        return clang_Range_isNull_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getExpansionLocation Void(typedef CXSourceLocation = Declared(CXSourceLocation),(typedef CXFile = (Void)*)*,(UNSIGNED = Int)*,(UNSIGNED = Int)*,(UNSIGNED = Int)*)
 */
private val clang_getExpansionLocation_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CXSourceLocation.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_getExpansionLocation_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getExpansionLocation")
private val clang_getExpansionLocation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getExpansionLocation_ADDR, clang_getExpansionLocation_DESC)

fun clang_getExpansionLocation(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment): Unit {
    try {
        clang_getExpansionLocation_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getPresumedLocation Void(typedef CXSourceLocation = Declared(CXSourceLocation),(typedef CXString = Declared(CXString))*,(UNSIGNED = Int)*,(UNSIGNED = Int)*)
 */
private val clang_getPresumedLocation_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CXSourceLocation.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_getPresumedLocation_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getPresumedLocation")
private val clang_getPresumedLocation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getPresumedLocation_ADDR, clang_getPresumedLocation_DESC)

fun clang_getPresumedLocation(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): Unit {
    try {
        clang_getPresumedLocation_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getInstantiationLocation Void(typedef CXSourceLocation = Declared(CXSourceLocation),(typedef CXFile = (Void)*)*,(UNSIGNED = Int)*,(UNSIGNED = Int)*,(UNSIGNED = Int)*)
 */
private val clang_getInstantiationLocation_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CXSourceLocation.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_getInstantiationLocation_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getInstantiationLocation")
private val clang_getInstantiationLocation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getInstantiationLocation_ADDR, clang_getInstantiationLocation_DESC)

fun clang_getInstantiationLocation(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment): Unit {
    try {
        clang_getInstantiationLocation_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getSpellingLocation Void(typedef CXSourceLocation = Declared(CXSourceLocation),(typedef CXFile = (Void)*)*,(UNSIGNED = Int)*,(UNSIGNED = Int)*,(UNSIGNED = Int)*)
 */
private val clang_getSpellingLocation_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CXSourceLocation.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_getSpellingLocation_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getSpellingLocation")
private val clang_getSpellingLocation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getSpellingLocation_ADDR, clang_getSpellingLocation_DESC)

fun clang_getSpellingLocation(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment): Unit {
    try {
        clang_getSpellingLocation_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getFileLocation Void(typedef CXSourceLocation = Declared(CXSourceLocation),(typedef CXFile = (Void)*)*,(UNSIGNED = Int)*,(UNSIGNED = Int)*,(UNSIGNED = Int)*)
 */
private val clang_getFileLocation_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CXSourceLocation.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_getFileLocation_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getFileLocation")
private val clang_getFileLocation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getFileLocation_ADDR, clang_getFileLocation_DESC)

fun clang_getFileLocation(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment): Unit {
    try {
        clang_getFileLocation_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getRangeStart typedef CXSourceLocation = Declared(CXSourceLocation)(typedef CXSourceRange = Declared(CXSourceRange))
 */
private val clang_getRangeStart_DESC: FunctionDescriptor = FunctionDescriptor.of(CXSourceLocation.layout, CXSourceRange.layout)
private val clang_getRangeStart_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getRangeStart")
private val clang_getRangeStart_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getRangeStart_ADDR, clang_getRangeStart_DESC)

fun clang_getRangeStart(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getRangeStart_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getRangeEnd typedef CXSourceLocation = Declared(CXSourceLocation)(typedef CXSourceRange = Declared(CXSourceRange))
 */
private val clang_getRangeEnd_DESC: FunctionDescriptor = FunctionDescriptor.of(CXSourceLocation.layout, CXSourceRange.layout)
private val clang_getRangeEnd_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getRangeEnd")
private val clang_getRangeEnd_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getRangeEnd_ADDR, clang_getRangeEnd_DESC)

fun clang_getRangeEnd(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getRangeEnd_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : STRUCT CXSourceRangeList
 */
class CXSourceRangeList {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("count"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.ADDRESS.withName("ranges")
        ).withName("CXSourceRangeList")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val count_VH: VarHandle = layout.varHandle(groupElement("count"))
        
        @Suppress("UNCHECKED_CAST")
        fun count(segment: MemorySegment): Int =
            count_VH.get(segment, 0L) as Int
        
        fun count(segment: MemorySegment, value: Int) =
            count_VH.set(segment, 0L, value)
        
        val ranges_VH: VarHandle = layout.varHandle(groupElement("ranges"))
        
        @Suppress("UNCHECKED_CAST")
        fun ranges(segment: MemorySegment): MemorySegment? =
            ranges_VH.get(segment, 0L) as MemorySegment
        
        fun ranges(segment: MemorySegment, value: MemorySegment) =
            ranges_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : clang_disposeSourceRangeList Void((typedef CXSourceRangeList = Declared(CXSourceRangeList))*)
 */
private val clang_disposeSourceRangeList_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val clang_disposeSourceRangeList_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_disposeSourceRangeList")
private val clang_disposeSourceRangeList_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_disposeSourceRangeList_ADDR, clang_disposeSourceRangeList_DESC)

fun clang_disposeSourceRangeList(arg0: MemorySegment): Unit {
    try {
        clang_disposeSourceRangeList_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXDiagnostic_Ignored 0
 */
fun CXDiagnostic_Ignored(): Int = 0

/**
 * {@snippet lang=c : #define CXDiagnostic_Note 1
 */
fun CXDiagnostic_Note(): Int = 1

/**
 * {@snippet lang=c : #define CXDiagnostic_Warning 2
 */
fun CXDiagnostic_Warning(): Int = 2

/**
 * {@snippet lang=c : #define CXDiagnostic_Error 3
 */
fun CXDiagnostic_Error(): Int = 3

/**
 * {@snippet lang=c : #define CXDiagnostic_Fatal 4
 */
fun CXDiagnostic_Fatal(): Int = 4

/**
 * {@snippet lang=c : typedef (Void)* CXDiagnostic;}
 */
typealias CXDiagnostic = MemorySegment?

/**
 * {@snippet lang=c : typedef (Void)* CXDiagnosticSet;}
 */
typealias CXDiagnosticSet = MemorySegment?

/**
 * {@snippet lang=c : clang_getNumDiagnosticsInSet UNSIGNED = Int(typedef CXDiagnosticSet = (Void)*)
 */
private val clang_getNumDiagnosticsInSet_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_getNumDiagnosticsInSet_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getNumDiagnosticsInSet")
private val clang_getNumDiagnosticsInSet_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getNumDiagnosticsInSet_ADDR, clang_getNumDiagnosticsInSet_DESC)

fun clang_getNumDiagnosticsInSet(arg0: MemorySegment): Int {
    try {
        return clang_getNumDiagnosticsInSet_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getDiagnosticInSet typedef CXDiagnostic = (Void)*(typedef CXDiagnosticSet = (Void)*,UNSIGNED = Int)
 */
private val clang_getDiagnosticInSet_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_getDiagnosticInSet_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getDiagnosticInSet")
private val clang_getDiagnosticInSet_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getDiagnosticInSet_ADDR, clang_getDiagnosticInSet_DESC)

fun clang_getDiagnosticInSet(arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return clang_getDiagnosticInSet_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXLoadDiag_None 0
 */
fun CXLoadDiag_None(): Int = 0

/**
 * {@snippet lang=c : #define CXLoadDiag_Unknown 1
 */
fun CXLoadDiag_Unknown(): Int = 1

/**
 * {@snippet lang=c : #define CXLoadDiag_CannotLoad 2
 */
fun CXLoadDiag_CannotLoad(): Int = 2

/**
 * {@snippet lang=c : #define CXLoadDiag_InvalidFile 3
 */
fun CXLoadDiag_InvalidFile(): Int = 3

/**
 * {@snippet lang=c : clang_loadDiagnostics typedef CXDiagnosticSet = (Void)*((Char)*,(Declared(CXLoadDiag_Error))*,(typedef CXString = Declared(CXString))*)
 */
private val clang_loadDiagnostics_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_loadDiagnostics_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_loadDiagnostics")
private val clang_loadDiagnostics_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_loadDiagnostics_ADDR, clang_loadDiagnostics_DESC)

fun clang_loadDiagnostics(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return clang_loadDiagnostics_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_disposeDiagnosticSet Void(typedef CXDiagnosticSet = (Void)*)
 */
private val clang_disposeDiagnosticSet_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val clang_disposeDiagnosticSet_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_disposeDiagnosticSet")
private val clang_disposeDiagnosticSet_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_disposeDiagnosticSet_ADDR, clang_disposeDiagnosticSet_DESC)

fun clang_disposeDiagnosticSet(arg0: MemorySegment): Unit {
    try {
        clang_disposeDiagnosticSet_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getChildDiagnostics typedef CXDiagnosticSet = (Void)*(typedef CXDiagnostic = (Void)*)
 */
private val clang_getChildDiagnostics_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_getChildDiagnostics_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getChildDiagnostics")
private val clang_getChildDiagnostics_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getChildDiagnostics_ADDR, clang_getChildDiagnostics_DESC)

fun clang_getChildDiagnostics(arg0: MemorySegment): MemorySegment {
    try {
        return clang_getChildDiagnostics_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_disposeDiagnostic Void(typedef CXDiagnostic = (Void)*)
 */
private val clang_disposeDiagnostic_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val clang_disposeDiagnostic_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_disposeDiagnostic")
private val clang_disposeDiagnostic_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_disposeDiagnostic_ADDR, clang_disposeDiagnostic_DESC)

fun clang_disposeDiagnostic(arg0: MemorySegment): Unit {
    try {
        clang_disposeDiagnostic_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXDiagnostic_DisplaySourceLocation 1
 */
fun CXDiagnostic_DisplaySourceLocation(): Int = 1

/**
 * {@snippet lang=c : #define CXDiagnostic_DisplayColumn 2
 */
fun CXDiagnostic_DisplayColumn(): Int = 2

/**
 * {@snippet lang=c : #define CXDiagnostic_DisplaySourceRanges 4
 */
fun CXDiagnostic_DisplaySourceRanges(): Int = 4

/**
 * {@snippet lang=c : #define CXDiagnostic_DisplayOption 8
 */
fun CXDiagnostic_DisplayOption(): Int = 8

/**
 * {@snippet lang=c : #define CXDiagnostic_DisplayCategoryId 16
 */
fun CXDiagnostic_DisplayCategoryId(): Int = 16

/**
 * {@snippet lang=c : #define CXDiagnostic_DisplayCategoryName 32
 */
fun CXDiagnostic_DisplayCategoryName(): Int = 32

/**
 * {@snippet lang=c : clang_formatDiagnostic typedef CXString = Declared(CXString)(typedef CXDiagnostic = (Void)*,UNSIGNED = Int)
 */
private val clang_formatDiagnostic_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_formatDiagnostic_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_formatDiagnostic")
private val clang_formatDiagnostic_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_formatDiagnostic_ADDR, clang_formatDiagnostic_DESC)

fun clang_formatDiagnostic(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return clang_formatDiagnostic_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_defaultDiagnosticDisplayOptions UNSIGNED = Int()
 */
private val clang_defaultDiagnosticDisplayOptions_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT)
private val clang_defaultDiagnosticDisplayOptions_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_defaultDiagnosticDisplayOptions")
private val clang_defaultDiagnosticDisplayOptions_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_defaultDiagnosticDisplayOptions_ADDR, clang_defaultDiagnosticDisplayOptions_DESC)

fun clang_defaultDiagnosticDisplayOptions(): Int {
    try {
        return clang_defaultDiagnosticDisplayOptions_HANDLE.invokeExact() as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getDiagnosticSeverity Declared(CXDiagnosticSeverity)(typedef CXDiagnostic = (Void)*)
 */
private val clang_getDiagnosticSeverity_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_getDiagnosticSeverity_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getDiagnosticSeverity")
private val clang_getDiagnosticSeverity_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getDiagnosticSeverity_ADDR, clang_getDiagnosticSeverity_DESC)

fun clang_getDiagnosticSeverity(arg0: MemorySegment): Int {
    try {
        return clang_getDiagnosticSeverity_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getDiagnosticLocation typedef CXSourceLocation = Declared(CXSourceLocation)(typedef CXDiagnostic = (Void)*)
 */
private val clang_getDiagnosticLocation_DESC: FunctionDescriptor = FunctionDescriptor.of(CXSourceLocation.layout, ValueLayout.ADDRESS)
private val clang_getDiagnosticLocation_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getDiagnosticLocation")
private val clang_getDiagnosticLocation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getDiagnosticLocation_ADDR, clang_getDiagnosticLocation_DESC)

fun clang_getDiagnosticLocation(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getDiagnosticLocation_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getDiagnosticSpelling typedef CXString = Declared(CXString)(typedef CXDiagnostic = (Void)*)
 */
private val clang_getDiagnosticSpelling_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS)
private val clang_getDiagnosticSpelling_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getDiagnosticSpelling")
private val clang_getDiagnosticSpelling_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getDiagnosticSpelling_ADDR, clang_getDiagnosticSpelling_DESC)

fun clang_getDiagnosticSpelling(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getDiagnosticSpelling_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getDiagnosticOption typedef CXString = Declared(CXString)(typedef CXDiagnostic = (Void)*,(typedef CXString = Declared(CXString))*)
 */
private val clang_getDiagnosticOption_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_getDiagnosticOption_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getDiagnosticOption")
private val clang_getDiagnosticOption_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getDiagnosticOption_ADDR, clang_getDiagnosticOption_DESC)

fun clang_getDiagnosticOption(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return clang_getDiagnosticOption_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getDiagnosticCategory UNSIGNED = Int(typedef CXDiagnostic = (Void)*)
 */
private val clang_getDiagnosticCategory_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_getDiagnosticCategory_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getDiagnosticCategory")
private val clang_getDiagnosticCategory_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getDiagnosticCategory_ADDR, clang_getDiagnosticCategory_DESC)

fun clang_getDiagnosticCategory(arg0: MemorySegment): Int {
    try {
        return clang_getDiagnosticCategory_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getDiagnosticCategoryName typedef CXString = Declared(CXString)(UNSIGNED = Int)
 */
private val clang_getDiagnosticCategoryName_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.JAVA_INT)
private val clang_getDiagnosticCategoryName_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getDiagnosticCategoryName")
private val clang_getDiagnosticCategoryName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getDiagnosticCategoryName_ADDR, clang_getDiagnosticCategoryName_DESC)

fun clang_getDiagnosticCategoryName(allocator: SegmentAllocator, arg0: Int): MemorySegment {
    try {
        return clang_getDiagnosticCategoryName_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getDiagnosticCategoryText typedef CXString = Declared(CXString)(typedef CXDiagnostic = (Void)*)
 */
private val clang_getDiagnosticCategoryText_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS)
private val clang_getDiagnosticCategoryText_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getDiagnosticCategoryText")
private val clang_getDiagnosticCategoryText_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getDiagnosticCategoryText_ADDR, clang_getDiagnosticCategoryText_DESC)

fun clang_getDiagnosticCategoryText(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getDiagnosticCategoryText_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getDiagnosticNumRanges UNSIGNED = Int(typedef CXDiagnostic = (Void)*)
 */
private val clang_getDiagnosticNumRanges_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_getDiagnosticNumRanges_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getDiagnosticNumRanges")
private val clang_getDiagnosticNumRanges_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getDiagnosticNumRanges_ADDR, clang_getDiagnosticNumRanges_DESC)

fun clang_getDiagnosticNumRanges(arg0: MemorySegment): Int {
    try {
        return clang_getDiagnosticNumRanges_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getDiagnosticRange typedef CXSourceRange = Declared(CXSourceRange)(typedef CXDiagnostic = (Void)*,UNSIGNED = Int)
 */
private val clang_getDiagnosticRange_DESC: FunctionDescriptor = FunctionDescriptor.of(CXSourceRange.layout, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_getDiagnosticRange_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getDiagnosticRange")
private val clang_getDiagnosticRange_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getDiagnosticRange_ADDR, clang_getDiagnosticRange_DESC)

fun clang_getDiagnosticRange(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return clang_getDiagnosticRange_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getDiagnosticNumFixIts UNSIGNED = Int(typedef CXDiagnostic = (Void)*)
 */
private val clang_getDiagnosticNumFixIts_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_getDiagnosticNumFixIts_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getDiagnosticNumFixIts")
private val clang_getDiagnosticNumFixIts_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getDiagnosticNumFixIts_ADDR, clang_getDiagnosticNumFixIts_DESC)

fun clang_getDiagnosticNumFixIts(arg0: MemorySegment): Int {
    try {
        return clang_getDiagnosticNumFixIts_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getDiagnosticFixIt typedef CXString = Declared(CXString)(typedef CXDiagnostic = (Void)*,UNSIGNED = Int,(typedef CXSourceRange = Declared(CXSourceRange))*)
 */
private val clang_getDiagnosticFixIt_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_getDiagnosticFixIt_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getDiagnosticFixIt")
private val clang_getDiagnosticFixIt_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getDiagnosticFixIt_ADDR, clang_getDiagnosticFixIt_DESC)

fun clang_getDiagnosticFixIt(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Int, arg2: MemorySegment): MemorySegment {
    try {
        return clang_getDiagnosticFixIt_HANDLE.invokeExact(allocator, arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : typedef (Void)* CXIndex;}
 */
typealias CXIndex = MemorySegment?

/**
 * {@snippet lang=c : STRUCT CXTargetInfoImpl
 */
class CXTargetInfoImpl {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("CXTargetInfoImpl")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
    }
}

/**
 * {@snippet lang=c : typedef (Declared(CXTargetInfoImpl))* CXTargetInfo;}
 */
typealias CXTargetInfo = MemorySegment?

/**
 * {@snippet lang=c : STRUCT CXTranslationUnitImpl
 */
class CXTranslationUnitImpl {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("CXTranslationUnitImpl")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
    }
}

/**
 * {@snippet lang=c : typedef (Declared(CXTranslationUnitImpl))* CXTranslationUnit;}
 */
typealias CXTranslationUnit = MemorySegment?

/**
 * {@snippet lang=c : typedef (Void)* CXClientData;}
 */
typealias CXClientData = MemorySegment?

/**
 * {@snippet lang=c : STRUCT CXUnsavedFile
 */
class CXUnsavedFile {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("Filename"),
            ValueLayout.ADDRESS.withName("Contents"),
            ValueLayout.JAVA_LONG.withName("Length")
        ).withName("CXUnsavedFile")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val Filename_VH: VarHandle = layout.varHandle(groupElement("Filename"))
        
        @Suppress("UNCHECKED_CAST")
        fun Filename(segment: MemorySegment): MemorySegment? =
            Filename_VH.get(segment, 0L) as MemorySegment
        
        fun Filename(segment: MemorySegment, value: MemorySegment) =
            Filename_VH.set(segment, 0L, value)
        
        val Contents_VH: VarHandle = layout.varHandle(groupElement("Contents"))
        
        @Suppress("UNCHECKED_CAST")
        fun Contents(segment: MemorySegment): MemorySegment? =
            Contents_VH.get(segment, 0L) as MemorySegment
        
        fun Contents(segment: MemorySegment, value: MemorySegment) =
            Contents_VH.set(segment, 0L, value)
        
        val Length_VH: VarHandle = layout.varHandle(groupElement("Length"))
        
        @Suppress("UNCHECKED_CAST")
        fun Length(segment: MemorySegment): Long =
            Length_VH.get(segment, 0L) as Long
        
        fun Length(segment: MemorySegment, value: Long) =
            Length_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : #define CXAvailability_Available 0
 */
fun CXAvailability_Available(): Int = 0

/**
 * {@snippet lang=c : #define CXAvailability_Deprecated 1
 */
fun CXAvailability_Deprecated(): Int = 1

/**
 * {@snippet lang=c : #define CXAvailability_NotAvailable 2
 */
fun CXAvailability_NotAvailable(): Int = 2

/**
 * {@snippet lang=c : #define CXAvailability_NotAccessible 3
 */
fun CXAvailability_NotAccessible(): Int = 3

/**
 * {@snippet lang=c : STRUCT CXVersion
 */
class CXVersion {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("Major"),
            ValueLayout.JAVA_INT.withName("Minor"),
            ValueLayout.JAVA_INT.withName("Subminor")
        ).withName("CXVersion")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val Major_VH: VarHandle = layout.varHandle(groupElement("Major"))
        
        @Suppress("UNCHECKED_CAST")
        fun Major(segment: MemorySegment): Int =
            Major_VH.get(segment, 0L) as Int
        
        fun Major(segment: MemorySegment, value: Int) =
            Major_VH.set(segment, 0L, value)
        
        val Minor_VH: VarHandle = layout.varHandle(groupElement("Minor"))
        
        @Suppress("UNCHECKED_CAST")
        fun Minor(segment: MemorySegment): Int =
            Minor_VH.get(segment, 0L) as Int
        
        fun Minor(segment: MemorySegment, value: Int) =
            Minor_VH.set(segment, 0L, value)
        
        val Subminor_VH: VarHandle = layout.varHandle(groupElement("Subminor"))
        
        @Suppress("UNCHECKED_CAST")
        fun Subminor(segment: MemorySegment): Int =
            Subminor_VH.get(segment, 0L) as Int
        
        fun Subminor(segment: MemorySegment, value: Int) =
            Subminor_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : #define CXCursor_ExceptionSpecificationKind_None 0
 */
fun CXCursor_ExceptionSpecificationKind_None(): Int = 0

/**
 * {@snippet lang=c : #define CXCursor_ExceptionSpecificationKind_DynamicNone 1
 */
fun CXCursor_ExceptionSpecificationKind_DynamicNone(): Int = 1

/**
 * {@snippet lang=c : #define CXCursor_ExceptionSpecificationKind_Dynamic 2
 */
fun CXCursor_ExceptionSpecificationKind_Dynamic(): Int = 2

/**
 * {@snippet lang=c : #define CXCursor_ExceptionSpecificationKind_MSAny 3
 */
fun CXCursor_ExceptionSpecificationKind_MSAny(): Int = 3

/**
 * {@snippet lang=c : #define CXCursor_ExceptionSpecificationKind_BasicNoexcept 4
 */
fun CXCursor_ExceptionSpecificationKind_BasicNoexcept(): Int = 4

/**
 * {@snippet lang=c : #define CXCursor_ExceptionSpecificationKind_ComputedNoexcept 5
 */
fun CXCursor_ExceptionSpecificationKind_ComputedNoexcept(): Int = 5

/**
 * {@snippet lang=c : #define CXCursor_ExceptionSpecificationKind_Unevaluated 6
 */
fun CXCursor_ExceptionSpecificationKind_Unevaluated(): Int = 6

/**
 * {@snippet lang=c : #define CXCursor_ExceptionSpecificationKind_Uninstantiated 7
 */
fun CXCursor_ExceptionSpecificationKind_Uninstantiated(): Int = 7

/**
 * {@snippet lang=c : #define CXCursor_ExceptionSpecificationKind_Unparsed 8
 */
fun CXCursor_ExceptionSpecificationKind_Unparsed(): Int = 8

/**
 * {@snippet lang=c : #define CXCursor_ExceptionSpecificationKind_NoThrow 9
 */
fun CXCursor_ExceptionSpecificationKind_NoThrow(): Int = 9

/**
 * {@snippet lang=c : clang_createIndex typedef CXIndex = (Void)*(Int,Int)
 */
private val clang_createIndex_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val clang_createIndex_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_createIndex")
private val clang_createIndex_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_createIndex_ADDR, clang_createIndex_DESC)

fun clang_createIndex(arg0: Int, arg1: Int): MemorySegment {
    try {
        return clang_createIndex_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_disposeIndex Void(typedef CXIndex = (Void)*)
 */
private val clang_disposeIndex_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val clang_disposeIndex_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_disposeIndex")
private val clang_disposeIndex_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_disposeIndex_ADDR, clang_disposeIndex_DESC)

fun clang_disposeIndex(arg0: MemorySegment): Unit {
    try {
        clang_disposeIndex_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXChoice_Default 0
 */
fun CXChoice_Default(): Int = 0

/**
 * {@snippet lang=c : #define CXChoice_Enabled 1
 */
fun CXChoice_Enabled(): Int = 1

/**
 * {@snippet lang=c : #define CXChoice_Disabled 2
 */
fun CXChoice_Disabled(): Int = 2

/**
 * {@snippet lang=c : #define CXGlobalOpt_None 0
 */
fun CXGlobalOpt_None(): Int = 0

/**
 * {@snippet lang=c : #define CXGlobalOpt_ThreadBackgroundPriorityForIndexing 1
 */
fun CXGlobalOpt_ThreadBackgroundPriorityForIndexing(): Int = 1

/**
 * {@snippet lang=c : #define CXGlobalOpt_ThreadBackgroundPriorityForEditing 2
 */
fun CXGlobalOpt_ThreadBackgroundPriorityForEditing(): Int = 2

/**
 * {@snippet lang=c : #define CXGlobalOpt_ThreadBackgroundPriorityForAll 3
 */
fun CXGlobalOpt_ThreadBackgroundPriorityForAll(): Int = 3

/**
 * {@snippet lang=c : STRUCT CXIndexOptions
 */
class CXIndexOptions {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("Size"),
            ValueLayout.JAVA_BYTE.withName("ThreadBackgroundPriorityForIndexing"),
            ValueLayout.JAVA_BYTE.withName("ThreadBackgroundPriorityForEditing"),
            MemoryLayout.paddingLayout(2),
            ValueLayout.ADDRESS.withName("PreambleStoragePath"),
            ValueLayout.ADDRESS.withName("InvocationEmissionPath")
        ).withName("CXIndexOptions")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val Size_VH: VarHandle = layout.varHandle(groupElement("Size"))
        
        @Suppress("UNCHECKED_CAST")
        fun Size(segment: MemorySegment): Int =
            Size_VH.get(segment, 0L) as Int
        
        fun Size(segment: MemorySegment, value: Int) =
            Size_VH.set(segment, 0L, value)
        
        val ThreadBackgroundPriorityForIndexing_VH: VarHandle = layout.varHandle(groupElement("ThreadBackgroundPriorityForIndexing"))
        
        @Suppress("UNCHECKED_CAST")
        fun ThreadBackgroundPriorityForIndexing(segment: MemorySegment): Byte =
            ThreadBackgroundPriorityForIndexing_VH.get(segment, 0L) as Byte
        
        fun ThreadBackgroundPriorityForIndexing(segment: MemorySegment, value: Byte) =
            ThreadBackgroundPriorityForIndexing_VH.set(segment, 0L, value)
        
        val ThreadBackgroundPriorityForEditing_VH: VarHandle = layout.varHandle(groupElement("ThreadBackgroundPriorityForEditing"))
        
        @Suppress("UNCHECKED_CAST")
        fun ThreadBackgroundPriorityForEditing(segment: MemorySegment): Byte =
            ThreadBackgroundPriorityForEditing_VH.get(segment, 0L) as Byte
        
        fun ThreadBackgroundPriorityForEditing(segment: MemorySegment, value: Byte) =
            ThreadBackgroundPriorityForEditing_VH.set(segment, 0L, value)
        
        val PreambleStoragePath_VH: VarHandle = layout.varHandle(groupElement("PreambleStoragePath"))
        
        @Suppress("UNCHECKED_CAST")
        fun PreambleStoragePath(segment: MemorySegment): MemorySegment? =
            PreambleStoragePath_VH.get(segment, 0L) as MemorySegment
        
        fun PreambleStoragePath(segment: MemorySegment, value: MemorySegment) =
            PreambleStoragePath_VH.set(segment, 0L, value)
        
        val InvocationEmissionPath_VH: VarHandle = layout.varHandle(groupElement("InvocationEmissionPath"))
        
        @Suppress("UNCHECKED_CAST")
        fun InvocationEmissionPath(segment: MemorySegment): MemorySegment? =
            InvocationEmissionPath_VH.get(segment, 0L) as MemorySegment
        
        fun InvocationEmissionPath(segment: MemorySegment, value: MemorySegment) =
            InvocationEmissionPath_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : clang_createIndexWithOptions typedef CXIndex = (Void)*((typedef CXIndexOptions = Declared(CXIndexOptions))*)
 */
private val clang_createIndexWithOptions_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_createIndexWithOptions_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_createIndexWithOptions")
private val clang_createIndexWithOptions_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_createIndexWithOptions_ADDR, clang_createIndexWithOptions_DESC)

fun clang_createIndexWithOptions(arg0: MemorySegment): MemorySegment {
    try {
        return clang_createIndexWithOptions_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_CXIndex_setGlobalOptions Void(typedef CXIndex = (Void)*,UNSIGNED = Int)
 */
private val clang_CXIndex_setGlobalOptions_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_CXIndex_setGlobalOptions_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_CXIndex_setGlobalOptions")
private val clang_CXIndex_setGlobalOptions_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_CXIndex_setGlobalOptions_ADDR, clang_CXIndex_setGlobalOptions_DESC)

fun clang_CXIndex_setGlobalOptions(arg0: MemorySegment, arg1: Int): Unit {
    try {
        clang_CXIndex_setGlobalOptions_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_CXIndex_getGlobalOptions UNSIGNED = Int(typedef CXIndex = (Void)*)
 */
private val clang_CXIndex_getGlobalOptions_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_CXIndex_getGlobalOptions_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_CXIndex_getGlobalOptions")
private val clang_CXIndex_getGlobalOptions_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_CXIndex_getGlobalOptions_ADDR, clang_CXIndex_getGlobalOptions_DESC)

fun clang_CXIndex_getGlobalOptions(arg0: MemorySegment): Int {
    try {
        return clang_CXIndex_getGlobalOptions_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_CXIndex_setInvocationEmissionPathOption Void(typedef CXIndex = (Void)*,(Char)*)
 */
private val clang_CXIndex_setInvocationEmissionPathOption_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_CXIndex_setInvocationEmissionPathOption_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_CXIndex_setInvocationEmissionPathOption")
private val clang_CXIndex_setInvocationEmissionPathOption_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_CXIndex_setInvocationEmissionPathOption_ADDR, clang_CXIndex_setInvocationEmissionPathOption_DESC)

fun clang_CXIndex_setInvocationEmissionPathOption(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        clang_CXIndex_setInvocationEmissionPathOption_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_isFileMultipleIncludeGuarded UNSIGNED = Int(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*,typedef CXFile = (Void)*)
 */
private val clang_isFileMultipleIncludeGuarded_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_isFileMultipleIncludeGuarded_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_isFileMultipleIncludeGuarded")
private val clang_isFileMultipleIncludeGuarded_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_isFileMultipleIncludeGuarded_ADDR, clang_isFileMultipleIncludeGuarded_DESC)

fun clang_isFileMultipleIncludeGuarded(arg0: MemorySegment, arg1: MemorySegment): Int {
    try {
        return clang_isFileMultipleIncludeGuarded_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getFile typedef CXFile = (Void)*(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*,(Char)*)
 */
private val clang_getFile_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_getFile_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getFile")
private val clang_getFile_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getFile_ADDR, clang_getFile_DESC)

fun clang_getFile(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return clang_getFile_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getFileContents (Char)*(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*,typedef CXFile = (Void)*,(typedef size_t = UNSIGNED = Long)*)
 */
private val clang_getFileContents_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_getFileContents_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getFileContents")
private val clang_getFileContents_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getFileContents_ADDR, clang_getFileContents_DESC)

fun clang_getFileContents(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return clang_getFileContents_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getLocation typedef CXSourceLocation = Declared(CXSourceLocation)(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*,typedef CXFile = (Void)*,UNSIGNED = Int,UNSIGNED = Int)
 */
private val clang_getLocation_DESC: FunctionDescriptor = FunctionDescriptor.of(CXSourceLocation.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val clang_getLocation_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getLocation")
private val clang_getLocation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getLocation_ADDR, clang_getLocation_DESC)

fun clang_getLocation(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment, arg2: Int, arg3: Int): MemorySegment {
    try {
        return clang_getLocation_HANDLE.invokeExact(allocator, arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getLocationForOffset typedef CXSourceLocation = Declared(CXSourceLocation)(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*,typedef CXFile = (Void)*,UNSIGNED = Int)
 */
private val clang_getLocationForOffset_DESC: FunctionDescriptor = FunctionDescriptor.of(CXSourceLocation.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_getLocationForOffset_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getLocationForOffset")
private val clang_getLocationForOffset_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getLocationForOffset_ADDR, clang_getLocationForOffset_DESC)

fun clang_getLocationForOffset(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment, arg2: Int): MemorySegment {
    try {
        return clang_getLocationForOffset_HANDLE.invokeExact(allocator, arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getSkippedRanges (typedef CXSourceRangeList = Declared(CXSourceRangeList))*(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*,typedef CXFile = (Void)*)
 */
private val clang_getSkippedRanges_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_getSkippedRanges_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getSkippedRanges")
private val clang_getSkippedRanges_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getSkippedRanges_ADDR, clang_getSkippedRanges_DESC)

fun clang_getSkippedRanges(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return clang_getSkippedRanges_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getAllSkippedRanges (typedef CXSourceRangeList = Declared(CXSourceRangeList))*(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*)
 */
private val clang_getAllSkippedRanges_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_getAllSkippedRanges_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getAllSkippedRanges")
private val clang_getAllSkippedRanges_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getAllSkippedRanges_ADDR, clang_getAllSkippedRanges_DESC)

fun clang_getAllSkippedRanges(arg0: MemorySegment): MemorySegment {
    try {
        return clang_getAllSkippedRanges_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getNumDiagnostics UNSIGNED = Int(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*)
 */
private val clang_getNumDiagnostics_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_getNumDiagnostics_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getNumDiagnostics")
private val clang_getNumDiagnostics_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getNumDiagnostics_ADDR, clang_getNumDiagnostics_DESC)

fun clang_getNumDiagnostics(arg0: MemorySegment): Int {
    try {
        return clang_getNumDiagnostics_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getDiagnostic typedef CXDiagnostic = (Void)*(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*,UNSIGNED = Int)
 */
private val clang_getDiagnostic_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_getDiagnostic_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getDiagnostic")
private val clang_getDiagnostic_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getDiagnostic_ADDR, clang_getDiagnostic_DESC)

fun clang_getDiagnostic(arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return clang_getDiagnostic_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getDiagnosticSetFromTU typedef CXDiagnosticSet = (Void)*(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*)
 */
private val clang_getDiagnosticSetFromTU_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_getDiagnosticSetFromTU_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getDiagnosticSetFromTU")
private val clang_getDiagnosticSetFromTU_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getDiagnosticSetFromTU_ADDR, clang_getDiagnosticSetFromTU_DESC)

fun clang_getDiagnosticSetFromTU(arg0: MemorySegment): MemorySegment {
    try {
        return clang_getDiagnosticSetFromTU_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getTranslationUnitSpelling typedef CXString = Declared(CXString)(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*)
 */
private val clang_getTranslationUnitSpelling_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS)
private val clang_getTranslationUnitSpelling_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getTranslationUnitSpelling")
private val clang_getTranslationUnitSpelling_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getTranslationUnitSpelling_ADDR, clang_getTranslationUnitSpelling_DESC)

fun clang_getTranslationUnitSpelling(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getTranslationUnitSpelling_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_createTranslationUnitFromSourceFile typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*(typedef CXIndex = (Void)*,(Char)*,Int,((Char)*)*,UNSIGNED = Int,(Declared(CXUnsavedFile))*)
 */
private val clang_createTranslationUnitFromSourceFile_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_createTranslationUnitFromSourceFile_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_createTranslationUnitFromSourceFile")
private val clang_createTranslationUnitFromSourceFile_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_createTranslationUnitFromSourceFile_ADDR, clang_createTranslationUnitFromSourceFile_DESC)

fun clang_createTranslationUnitFromSourceFile(arg0: MemorySegment, arg1: MemorySegment, arg2: Int, arg3: MemorySegment, arg4: Int, arg5: MemorySegment): MemorySegment {
    try {
        return clang_createTranslationUnitFromSourceFile_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_createTranslationUnit typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*(typedef CXIndex = (Void)*,(Char)*)
 */
private val clang_createTranslationUnit_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_createTranslationUnit_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_createTranslationUnit")
private val clang_createTranslationUnit_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_createTranslationUnit_ADDR, clang_createTranslationUnit_DESC)

fun clang_createTranslationUnit(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return clang_createTranslationUnit_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_createTranslationUnit2 Declared(CXErrorCode)(typedef CXIndex = (Void)*,(Char)*,(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*)*)
 */
private val clang_createTranslationUnit2_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_createTranslationUnit2_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_createTranslationUnit2")
private val clang_createTranslationUnit2_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_createTranslationUnit2_ADDR, clang_createTranslationUnit2_DESC)

fun clang_createTranslationUnit2(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Int {
    try {
        return clang_createTranslationUnit2_HANDLE.invokeExact(arg0, arg1, arg2) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXTranslationUnit_None 0
 */
fun CXTranslationUnit_None(): Int = 0

/**
 * {@snippet lang=c : #define CXTranslationUnit_DetailedPreprocessingRecord 1
 */
fun CXTranslationUnit_DetailedPreprocessingRecord(): Int = 1

/**
 * {@snippet lang=c : #define CXTranslationUnit_Incomplete 2
 */
fun CXTranslationUnit_Incomplete(): Int = 2

/**
 * {@snippet lang=c : #define CXTranslationUnit_PrecompiledPreamble 4
 */
fun CXTranslationUnit_PrecompiledPreamble(): Int = 4

/**
 * {@snippet lang=c : #define CXTranslationUnit_CacheCompletionResults 8
 */
fun CXTranslationUnit_CacheCompletionResults(): Int = 8

/**
 * {@snippet lang=c : #define CXTranslationUnit_ForSerialization 16
 */
fun CXTranslationUnit_ForSerialization(): Int = 16

/**
 * {@snippet lang=c : #define CXTranslationUnit_CXXChainedPCH 32
 */
fun CXTranslationUnit_CXXChainedPCH(): Int = 32

/**
 * {@snippet lang=c : #define CXTranslationUnit_SkipFunctionBodies 64
 */
fun CXTranslationUnit_SkipFunctionBodies(): Int = 64

/**
 * {@snippet lang=c : #define CXTranslationUnit_IncludeBriefCommentsInCodeCompletion 128
 */
fun CXTranslationUnit_IncludeBriefCommentsInCodeCompletion(): Int = 128

/**
 * {@snippet lang=c : #define CXTranslationUnit_CreatePreambleOnFirstParse 256
 */
fun CXTranslationUnit_CreatePreambleOnFirstParse(): Int = 256

/**
 * {@snippet lang=c : #define CXTranslationUnit_KeepGoing 512
 */
fun CXTranslationUnit_KeepGoing(): Int = 512

/**
 * {@snippet lang=c : #define CXTranslationUnit_SingleFileParse 1024
 */
fun CXTranslationUnit_SingleFileParse(): Int = 1024

/**
 * {@snippet lang=c : #define CXTranslationUnit_LimitSkipFunctionBodiesToPreamble 2048
 */
fun CXTranslationUnit_LimitSkipFunctionBodiesToPreamble(): Int = 2048

/**
 * {@snippet lang=c : #define CXTranslationUnit_IncludeAttributedTypes 4096
 */
fun CXTranslationUnit_IncludeAttributedTypes(): Int = 4096

/**
 * {@snippet lang=c : #define CXTranslationUnit_VisitImplicitAttributes 8192
 */
fun CXTranslationUnit_VisitImplicitAttributes(): Int = 8192

/**
 * {@snippet lang=c : #define CXTranslationUnit_IgnoreNonErrorsFromIncludedFiles 16384
 */
fun CXTranslationUnit_IgnoreNonErrorsFromIncludedFiles(): Int = 16384

/**
 * {@snippet lang=c : #define CXTranslationUnit_RetainExcludedConditionalBlocks 32768
 */
fun CXTranslationUnit_RetainExcludedConditionalBlocks(): Int = 32768

/**
 * {@snippet lang=c : clang_defaultEditingTranslationUnitOptions UNSIGNED = Int()
 */
private val clang_defaultEditingTranslationUnitOptions_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT)
private val clang_defaultEditingTranslationUnitOptions_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_defaultEditingTranslationUnitOptions")
private val clang_defaultEditingTranslationUnitOptions_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_defaultEditingTranslationUnitOptions_ADDR, clang_defaultEditingTranslationUnitOptions_DESC)

fun clang_defaultEditingTranslationUnitOptions(): Int {
    try {
        return clang_defaultEditingTranslationUnitOptions_HANDLE.invokeExact() as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_parseTranslationUnit typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*(typedef CXIndex = (Void)*,(Char)*,((Char)*)*,Int,(Declared(CXUnsavedFile))*,UNSIGNED = Int,UNSIGNED = Int)
 */
private val clang_parseTranslationUnit_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val clang_parseTranslationUnit_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_parseTranslationUnit")
private val clang_parseTranslationUnit_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_parseTranslationUnit_ADDR, clang_parseTranslationUnit_DESC)

fun clang_parseTranslationUnit(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Int, arg4: MemorySegment, arg5: Int, arg6: Int): MemorySegment {
    try {
        return clang_parseTranslationUnit_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_parseTranslationUnit2 Declared(CXErrorCode)(typedef CXIndex = (Void)*,(Char)*,((Char)*)*,Int,(Declared(CXUnsavedFile))*,UNSIGNED = Int,UNSIGNED = Int,(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*)*)
 */
private val clang_parseTranslationUnit2_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_parseTranslationUnit2_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_parseTranslationUnit2")
private val clang_parseTranslationUnit2_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_parseTranslationUnit2_ADDR, clang_parseTranslationUnit2_DESC)

fun clang_parseTranslationUnit2(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Int, arg4: MemorySegment, arg5: Int, arg6: Int, arg7: MemorySegment): Int {
    try {
        return clang_parseTranslationUnit2_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_parseTranslationUnit2FullArgv Declared(CXErrorCode)(typedef CXIndex = (Void)*,(Char)*,((Char)*)*,Int,(Declared(CXUnsavedFile))*,UNSIGNED = Int,UNSIGNED = Int,(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*)*)
 */
private val clang_parseTranslationUnit2FullArgv_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_parseTranslationUnit2FullArgv_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_parseTranslationUnit2FullArgv")
private val clang_parseTranslationUnit2FullArgv_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_parseTranslationUnit2FullArgv_ADDR, clang_parseTranslationUnit2FullArgv_DESC)

fun clang_parseTranslationUnit2FullArgv(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Int, arg4: MemorySegment, arg5: Int, arg6: Int, arg7: MemorySegment): Int {
    try {
        return clang_parseTranslationUnit2FullArgv_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXSaveTranslationUnit_None 0
 */
fun CXSaveTranslationUnit_None(): Int = 0

/**
 * {@snippet lang=c : clang_defaultSaveOptions UNSIGNED = Int(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*)
 */
private val clang_defaultSaveOptions_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_defaultSaveOptions_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_defaultSaveOptions")
private val clang_defaultSaveOptions_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_defaultSaveOptions_ADDR, clang_defaultSaveOptions_DESC)

fun clang_defaultSaveOptions(arg0: MemorySegment): Int {
    try {
        return clang_defaultSaveOptions_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXSaveError_None 0
 */
fun CXSaveError_None(): Int = 0

/**
 * {@snippet lang=c : #define CXSaveError_Unknown 1
 */
fun CXSaveError_Unknown(): Int = 1

/**
 * {@snippet lang=c : #define CXSaveError_TranslationErrors 2
 */
fun CXSaveError_TranslationErrors(): Int = 2

/**
 * {@snippet lang=c : #define CXSaveError_InvalidTU 3
 */
fun CXSaveError_InvalidTU(): Int = 3

/**
 * {@snippet lang=c : clang_saveTranslationUnit Int(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*,(Char)*,UNSIGNED = Int)
 */
private val clang_saveTranslationUnit_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_saveTranslationUnit_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_saveTranslationUnit")
private val clang_saveTranslationUnit_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_saveTranslationUnit_ADDR, clang_saveTranslationUnit_DESC)

fun clang_saveTranslationUnit(arg0: MemorySegment, arg1: MemorySegment, arg2: Int): Int {
    try {
        return clang_saveTranslationUnit_HANDLE.invokeExact(arg0, arg1, arg2) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_suspendTranslationUnit UNSIGNED = Int(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*)
 */
private val clang_suspendTranslationUnit_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_suspendTranslationUnit_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_suspendTranslationUnit")
private val clang_suspendTranslationUnit_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_suspendTranslationUnit_ADDR, clang_suspendTranslationUnit_DESC)

fun clang_suspendTranslationUnit(arg0: MemorySegment): Int {
    try {
        return clang_suspendTranslationUnit_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_disposeTranslationUnit Void(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*)
 */
private val clang_disposeTranslationUnit_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val clang_disposeTranslationUnit_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_disposeTranslationUnit")
private val clang_disposeTranslationUnit_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_disposeTranslationUnit_ADDR, clang_disposeTranslationUnit_DESC)

fun clang_disposeTranslationUnit(arg0: MemorySegment): Unit {
    try {
        clang_disposeTranslationUnit_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXReparse_None 0
 */
fun CXReparse_None(): Int = 0

/**
 * {@snippet lang=c : clang_defaultReparseOptions UNSIGNED = Int(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*)
 */
private val clang_defaultReparseOptions_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_defaultReparseOptions_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_defaultReparseOptions")
private val clang_defaultReparseOptions_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_defaultReparseOptions_ADDR, clang_defaultReparseOptions_DESC)

fun clang_defaultReparseOptions(arg0: MemorySegment): Int {
    try {
        return clang_defaultReparseOptions_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_reparseTranslationUnit Int(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*,UNSIGNED = Int,(Declared(CXUnsavedFile))*,UNSIGNED = Int)
 */
private val clang_reparseTranslationUnit_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_reparseTranslationUnit_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_reparseTranslationUnit")
private val clang_reparseTranslationUnit_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_reparseTranslationUnit_ADDR, clang_reparseTranslationUnit_DESC)

fun clang_reparseTranslationUnit(arg0: MemorySegment, arg1: Int, arg2: MemorySegment, arg3: Int): Int {
    try {
        return clang_reparseTranslationUnit_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXTUResourceUsage_AST 1
 */
fun CXTUResourceUsage_AST(): Int = 1

/**
 * {@snippet lang=c : #define CXTUResourceUsage_Identifiers 2
 */
fun CXTUResourceUsage_Identifiers(): Int = 2

/**
 * {@snippet lang=c : #define CXTUResourceUsage_Selectors 3
 */
fun CXTUResourceUsage_Selectors(): Int = 3

/**
 * {@snippet lang=c : #define CXTUResourceUsage_GlobalCompletionResults 4
 */
fun CXTUResourceUsage_GlobalCompletionResults(): Int = 4

/**
 * {@snippet lang=c : #define CXTUResourceUsage_SourceManagerContentCache 5
 */
fun CXTUResourceUsage_SourceManagerContentCache(): Int = 5

/**
 * {@snippet lang=c : #define CXTUResourceUsage_AST_SideTables 6
 */
fun CXTUResourceUsage_AST_SideTables(): Int = 6

/**
 * {@snippet lang=c : #define CXTUResourceUsage_SourceManager_Membuffer_Malloc 7
 */
fun CXTUResourceUsage_SourceManager_Membuffer_Malloc(): Int = 7

/**
 * {@snippet lang=c : #define CXTUResourceUsage_SourceManager_Membuffer_MMap 8
 */
fun CXTUResourceUsage_SourceManager_Membuffer_MMap(): Int = 8

/**
 * {@snippet lang=c : #define CXTUResourceUsage_ExternalASTSource_Membuffer_Malloc 9
 */
fun CXTUResourceUsage_ExternalASTSource_Membuffer_Malloc(): Int = 9

/**
 * {@snippet lang=c : #define CXTUResourceUsage_ExternalASTSource_Membuffer_MMap 10
 */
fun CXTUResourceUsage_ExternalASTSource_Membuffer_MMap(): Int = 10

/**
 * {@snippet lang=c : #define CXTUResourceUsage_Preprocessor 11
 */
fun CXTUResourceUsage_Preprocessor(): Int = 11

/**
 * {@snippet lang=c : #define CXTUResourceUsage_PreprocessingRecord 12
 */
fun CXTUResourceUsage_PreprocessingRecord(): Int = 12

/**
 * {@snippet lang=c : #define CXTUResourceUsage_SourceManager_DataStructures 13
 */
fun CXTUResourceUsage_SourceManager_DataStructures(): Int = 13

/**
 * {@snippet lang=c : #define CXTUResourceUsage_Preprocessor_HeaderSearch 14
 */
fun CXTUResourceUsage_Preprocessor_HeaderSearch(): Int = 14

/**
 * {@snippet lang=c : #define CXTUResourceUsage_MEMORY_IN_BYTES_BEGIN 1
 */
fun CXTUResourceUsage_MEMORY_IN_BYTES_BEGIN(): Int = 1

/**
 * {@snippet lang=c : #define CXTUResourceUsage_MEMORY_IN_BYTES_END 14
 */
fun CXTUResourceUsage_MEMORY_IN_BYTES_END(): Int = 14

/**
 * {@snippet lang=c : #define CXTUResourceUsage_First 1
 */
fun CXTUResourceUsage_First(): Int = 1

/**
 * {@snippet lang=c : #define CXTUResourceUsage_Last 14
 */
fun CXTUResourceUsage_Last(): Int = 14

/**
 * {@snippet lang=c : clang_getTUResourceUsageName (Char)*(Declared(CXTUResourceUsageKind))
 */
private val clang_getTUResourceUsageName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_getTUResourceUsageName_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getTUResourceUsageName")
private val clang_getTUResourceUsageName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getTUResourceUsageName_ADDR, clang_getTUResourceUsageName_DESC)

fun clang_getTUResourceUsageName(arg0: Int): MemorySegment {
    try {
        return clang_getTUResourceUsageName_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : STRUCT CXTUResourceUsageEntry
 */
class CXTUResourceUsageEntry {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("kind"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.JAVA_LONG.withName("amount")
        ).withName("CXTUResourceUsageEntry")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val kind_VH: VarHandle = layout.varHandle(groupElement("kind"))
        
        @Suppress("UNCHECKED_CAST")
        fun kind(segment: MemorySegment): Int =
            kind_VH.get(segment, 0L) as Int
        
        fun kind(segment: MemorySegment, value: Int) =
            kind_VH.set(segment, 0L, value)
        
        val amount_VH: VarHandle = layout.varHandle(groupElement("amount"))
        
        @Suppress("UNCHECKED_CAST")
        fun amount(segment: MemorySegment): Long =
            amount_VH.get(segment, 0L) as Long
        
        fun amount(segment: MemorySegment, value: Long) =
            amount_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : STRUCT CXTUResourceUsage
 */
class CXTUResourceUsage {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("data"),
            ValueLayout.JAVA_INT.withName("numEntries"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.ADDRESS.withName("entries")
        ).withName("CXTUResourceUsage")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val data__VH: VarHandle = layout.varHandle(groupElement("data"))
        
        @Suppress("UNCHECKED_CAST")
        fun data_(segment: MemorySegment): MemorySegment? =
            data__VH.get(segment, 0L) as MemorySegment
        
        fun data_(segment: MemorySegment, value: MemorySegment) =
            data__VH.set(segment, 0L, value)
        
        val numEntries_VH: VarHandle = layout.varHandle(groupElement("numEntries"))
        
        @Suppress("UNCHECKED_CAST")
        fun numEntries(segment: MemorySegment): Int =
            numEntries_VH.get(segment, 0L) as Int
        
        fun numEntries(segment: MemorySegment, value: Int) =
            numEntries_VH.set(segment, 0L, value)
        
        val entries_VH: VarHandle = layout.varHandle(groupElement("entries"))
        
        @Suppress("UNCHECKED_CAST")
        fun entries(segment: MemorySegment): MemorySegment? =
            entries_VH.get(segment, 0L) as MemorySegment
        
        fun entries(segment: MemorySegment, value: MemorySegment) =
            entries_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : clang_getCXTUResourceUsage typedef CXTUResourceUsage = Declared(CXTUResourceUsage)(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*)
 */
private val clang_getCXTUResourceUsage_DESC: FunctionDescriptor = FunctionDescriptor.of(CXTUResourceUsage.layout, ValueLayout.ADDRESS)
private val clang_getCXTUResourceUsage_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCXTUResourceUsage")
private val clang_getCXTUResourceUsage_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCXTUResourceUsage_ADDR, clang_getCXTUResourceUsage_DESC)

fun clang_getCXTUResourceUsage(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getCXTUResourceUsage_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_disposeCXTUResourceUsage Void(typedef CXTUResourceUsage = Declared(CXTUResourceUsage))
 */
private val clang_disposeCXTUResourceUsage_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CXTUResourceUsage.layout)
private val clang_disposeCXTUResourceUsage_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_disposeCXTUResourceUsage")
private val clang_disposeCXTUResourceUsage_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_disposeCXTUResourceUsage_ADDR, clang_disposeCXTUResourceUsage_DESC)

fun clang_disposeCXTUResourceUsage(arg0: MemorySegment): Unit {
    try {
        clang_disposeCXTUResourceUsage_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getTranslationUnitTargetInfo typedef CXTargetInfo = (Declared(CXTargetInfoImpl))*(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*)
 */
private val clang_getTranslationUnitTargetInfo_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_getTranslationUnitTargetInfo_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getTranslationUnitTargetInfo")
private val clang_getTranslationUnitTargetInfo_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getTranslationUnitTargetInfo_ADDR, clang_getTranslationUnitTargetInfo_DESC)

fun clang_getTranslationUnitTargetInfo(arg0: MemorySegment): MemorySegment {
    try {
        return clang_getTranslationUnitTargetInfo_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_TargetInfo_dispose Void(typedef CXTargetInfo = (Declared(CXTargetInfoImpl))*)
 */
private val clang_TargetInfo_dispose_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val clang_TargetInfo_dispose_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_TargetInfo_dispose")
private val clang_TargetInfo_dispose_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_TargetInfo_dispose_ADDR, clang_TargetInfo_dispose_DESC)

fun clang_TargetInfo_dispose(arg0: MemorySegment): Unit {
    try {
        clang_TargetInfo_dispose_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_TargetInfo_getTriple typedef CXString = Declared(CXString)(typedef CXTargetInfo = (Declared(CXTargetInfoImpl))*)
 */
private val clang_TargetInfo_getTriple_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS)
private val clang_TargetInfo_getTriple_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_TargetInfo_getTriple")
private val clang_TargetInfo_getTriple_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_TargetInfo_getTriple_ADDR, clang_TargetInfo_getTriple_DESC)

fun clang_TargetInfo_getTriple(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_TargetInfo_getTriple_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_TargetInfo_getPointerWidth Int(typedef CXTargetInfo = (Declared(CXTargetInfoImpl))*)
 */
private val clang_TargetInfo_getPointerWidth_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_TargetInfo_getPointerWidth_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_TargetInfo_getPointerWidth")
private val clang_TargetInfo_getPointerWidth_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_TargetInfo_getPointerWidth_ADDR, clang_TargetInfo_getPointerWidth_DESC)

fun clang_TargetInfo_getPointerWidth(arg0: MemorySegment): Int {
    try {
        return clang_TargetInfo_getPointerWidth_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXCursor_UnexposedDecl 1
 */
fun CXCursor_UnexposedDecl(): Int = 1

/**
 * {@snippet lang=c : #define CXCursor_StructDecl 2
 */
fun CXCursor_StructDecl(): Int = 2

/**
 * {@snippet lang=c : #define CXCursor_UnionDecl 3
 */
fun CXCursor_UnionDecl(): Int = 3

/**
 * {@snippet lang=c : #define CXCursor_ClassDecl 4
 */
fun CXCursor_ClassDecl(): Int = 4

/**
 * {@snippet lang=c : #define CXCursor_EnumDecl 5
 */
fun CXCursor_EnumDecl(): Int = 5

/**
 * {@snippet lang=c : #define CXCursor_FieldDecl 6
 */
fun CXCursor_FieldDecl(): Int = 6

/**
 * {@snippet lang=c : #define CXCursor_EnumConstantDecl 7
 */
fun CXCursor_EnumConstantDecl(): Int = 7

/**
 * {@snippet lang=c : #define CXCursor_FunctionDecl 8
 */
fun CXCursor_FunctionDecl(): Int = 8

/**
 * {@snippet lang=c : #define CXCursor_VarDecl 9
 */
fun CXCursor_VarDecl(): Int = 9

/**
 * {@snippet lang=c : #define CXCursor_ParmDecl 10
 */
fun CXCursor_ParmDecl(): Int = 10

/**
 * {@snippet lang=c : #define CXCursor_ObjCInterfaceDecl 11
 */
fun CXCursor_ObjCInterfaceDecl(): Int = 11

/**
 * {@snippet lang=c : #define CXCursor_ObjCCategoryDecl 12
 */
fun CXCursor_ObjCCategoryDecl(): Int = 12

/**
 * {@snippet lang=c : #define CXCursor_ObjCProtocolDecl 13
 */
fun CXCursor_ObjCProtocolDecl(): Int = 13

/**
 * {@snippet lang=c : #define CXCursor_ObjCPropertyDecl 14
 */
fun CXCursor_ObjCPropertyDecl(): Int = 14

/**
 * {@snippet lang=c : #define CXCursor_ObjCIvarDecl 15
 */
fun CXCursor_ObjCIvarDecl(): Int = 15

/**
 * {@snippet lang=c : #define CXCursor_ObjCInstanceMethodDecl 16
 */
fun CXCursor_ObjCInstanceMethodDecl(): Int = 16

/**
 * {@snippet lang=c : #define CXCursor_ObjCClassMethodDecl 17
 */
fun CXCursor_ObjCClassMethodDecl(): Int = 17

/**
 * {@snippet lang=c : #define CXCursor_ObjCImplementationDecl 18
 */
fun CXCursor_ObjCImplementationDecl(): Int = 18

/**
 * {@snippet lang=c : #define CXCursor_ObjCCategoryImplDecl 19
 */
fun CXCursor_ObjCCategoryImplDecl(): Int = 19

/**
 * {@snippet lang=c : #define CXCursor_TypedefDecl 20
 */
fun CXCursor_TypedefDecl(): Int = 20

/**
 * {@snippet lang=c : #define CXCursor_CXXMethod 21
 */
fun CXCursor_CXXMethod(): Int = 21

/**
 * {@snippet lang=c : #define CXCursor_Namespace 22
 */
fun CXCursor_Namespace(): Int = 22

/**
 * {@snippet lang=c : #define CXCursor_LinkageSpec 23
 */
fun CXCursor_LinkageSpec(): Int = 23

/**
 * {@snippet lang=c : #define CXCursor_Constructor 24
 */
fun CXCursor_Constructor(): Int = 24

/**
 * {@snippet lang=c : #define CXCursor_Destructor 25
 */
fun CXCursor_Destructor(): Int = 25

/**
 * {@snippet lang=c : #define CXCursor_ConversionFunction 26
 */
fun CXCursor_ConversionFunction(): Int = 26

/**
 * {@snippet lang=c : #define CXCursor_TemplateTypeParameter 27
 */
fun CXCursor_TemplateTypeParameter(): Int = 27

/**
 * {@snippet lang=c : #define CXCursor_NonTypeTemplateParameter 28
 */
fun CXCursor_NonTypeTemplateParameter(): Int = 28

/**
 * {@snippet lang=c : #define CXCursor_TemplateTemplateParameter 29
 */
fun CXCursor_TemplateTemplateParameter(): Int = 29

/**
 * {@snippet lang=c : #define CXCursor_FunctionTemplate 30
 */
fun CXCursor_FunctionTemplate(): Int = 30

/**
 * {@snippet lang=c : #define CXCursor_ClassTemplate 31
 */
fun CXCursor_ClassTemplate(): Int = 31

/**
 * {@snippet lang=c : #define CXCursor_ClassTemplatePartialSpecialization 32
 */
fun CXCursor_ClassTemplatePartialSpecialization(): Int = 32

/**
 * {@snippet lang=c : #define CXCursor_NamespaceAlias 33
 */
fun CXCursor_NamespaceAlias(): Int = 33

/**
 * {@snippet lang=c : #define CXCursor_UsingDirective 34
 */
fun CXCursor_UsingDirective(): Int = 34

/**
 * {@snippet lang=c : #define CXCursor_UsingDeclaration 35
 */
fun CXCursor_UsingDeclaration(): Int = 35

/**
 * {@snippet lang=c : #define CXCursor_TypeAliasDecl 36
 */
fun CXCursor_TypeAliasDecl(): Int = 36

/**
 * {@snippet lang=c : #define CXCursor_ObjCSynthesizeDecl 37
 */
fun CXCursor_ObjCSynthesizeDecl(): Int = 37

/**
 * {@snippet lang=c : #define CXCursor_ObjCDynamicDecl 38
 */
fun CXCursor_ObjCDynamicDecl(): Int = 38

/**
 * {@snippet lang=c : #define CXCursor_CXXAccessSpecifier 39
 */
fun CXCursor_CXXAccessSpecifier(): Int = 39

/**
 * {@snippet lang=c : #define CXCursor_FirstDecl 1
 */
fun CXCursor_FirstDecl(): Int = 1

/**
 * {@snippet lang=c : #define CXCursor_LastDecl 39
 */
fun CXCursor_LastDecl(): Int = 39

/**
 * {@snippet lang=c : #define CXCursor_FirstRef 40
 */
fun CXCursor_FirstRef(): Int = 40

/**
 * {@snippet lang=c : #define CXCursor_ObjCSuperClassRef 40
 */
fun CXCursor_ObjCSuperClassRef(): Int = 40

/**
 * {@snippet lang=c : #define CXCursor_ObjCProtocolRef 41
 */
fun CXCursor_ObjCProtocolRef(): Int = 41

/**
 * {@snippet lang=c : #define CXCursor_ObjCClassRef 42
 */
fun CXCursor_ObjCClassRef(): Int = 42

/**
 * {@snippet lang=c : #define CXCursor_TypeRef 43
 */
fun CXCursor_TypeRef(): Int = 43

/**
 * {@snippet lang=c : #define CXCursor_CXXBaseSpecifier 44
 */
fun CXCursor_CXXBaseSpecifier(): Int = 44

/**
 * {@snippet lang=c : #define CXCursor_TemplateRef 45
 */
fun CXCursor_TemplateRef(): Int = 45

/**
 * {@snippet lang=c : #define CXCursor_NamespaceRef 46
 */
fun CXCursor_NamespaceRef(): Int = 46

/**
 * {@snippet lang=c : #define CXCursor_MemberRef 47
 */
fun CXCursor_MemberRef(): Int = 47

/**
 * {@snippet lang=c : #define CXCursor_LabelRef 48
 */
fun CXCursor_LabelRef(): Int = 48

/**
 * {@snippet lang=c : #define CXCursor_OverloadedDeclRef 49
 */
fun CXCursor_OverloadedDeclRef(): Int = 49

/**
 * {@snippet lang=c : #define CXCursor_VariableRef 50
 */
fun CXCursor_VariableRef(): Int = 50

/**
 * {@snippet lang=c : #define CXCursor_LastRef 50
 */
fun CXCursor_LastRef(): Int = 50

/**
 * {@snippet lang=c : #define CXCursor_FirstInvalid 70
 */
fun CXCursor_FirstInvalid(): Int = 70

/**
 * {@snippet lang=c : #define CXCursor_InvalidFile 70
 */
fun CXCursor_InvalidFile(): Int = 70

/**
 * {@snippet lang=c : #define CXCursor_NoDeclFound 71
 */
fun CXCursor_NoDeclFound(): Int = 71

/**
 * {@snippet lang=c : #define CXCursor_NotImplemented 72
 */
fun CXCursor_NotImplemented(): Int = 72

/**
 * {@snippet lang=c : #define CXCursor_InvalidCode 73
 */
fun CXCursor_InvalidCode(): Int = 73

/**
 * {@snippet lang=c : #define CXCursor_LastInvalid 73
 */
fun CXCursor_LastInvalid(): Int = 73

/**
 * {@snippet lang=c : #define CXCursor_FirstExpr 100
 */
fun CXCursor_FirstExpr(): Int = 100

/**
 * {@snippet lang=c : #define CXCursor_UnexposedExpr 100
 */
fun CXCursor_UnexposedExpr(): Int = 100

/**
 * {@snippet lang=c : #define CXCursor_DeclRefExpr 101
 */
fun CXCursor_DeclRefExpr(): Int = 101

/**
 * {@snippet lang=c : #define CXCursor_MemberRefExpr 102
 */
fun CXCursor_MemberRefExpr(): Int = 102

/**
 * {@snippet lang=c : #define CXCursor_CallExpr 103
 */
fun CXCursor_CallExpr(): Int = 103

/**
 * {@snippet lang=c : #define CXCursor_ObjCMessageExpr 104
 */
fun CXCursor_ObjCMessageExpr(): Int = 104

/**
 * {@snippet lang=c : #define CXCursor_BlockExpr 105
 */
fun CXCursor_BlockExpr(): Int = 105

/**
 * {@snippet lang=c : #define CXCursor_IntegerLiteral 106
 */
fun CXCursor_IntegerLiteral(): Int = 106

/**
 * {@snippet lang=c : #define CXCursor_FloatingLiteral 107
 */
fun CXCursor_FloatingLiteral(): Int = 107

/**
 * {@snippet lang=c : #define CXCursor_ImaginaryLiteral 108
 */
fun CXCursor_ImaginaryLiteral(): Int = 108

/**
 * {@snippet lang=c : #define CXCursor_StringLiteral 109
 */
fun CXCursor_StringLiteral(): Int = 109

/**
 * {@snippet lang=c : #define CXCursor_CharacterLiteral 110
 */
fun CXCursor_CharacterLiteral(): Int = 110

/**
 * {@snippet lang=c : #define CXCursor_ParenExpr 111
 */
fun CXCursor_ParenExpr(): Int = 111

/**
 * {@snippet lang=c : #define CXCursor_UnaryOperator 112
 */
fun CXCursor_UnaryOperator(): Int = 112

/**
 * {@snippet lang=c : #define CXCursor_ArraySubscriptExpr 113
 */
fun CXCursor_ArraySubscriptExpr(): Int = 113

/**
 * {@snippet lang=c : #define CXCursor_BinaryOperator 114
 */
fun CXCursor_BinaryOperator(): Int = 114

/**
 * {@snippet lang=c : #define CXCursor_CompoundAssignOperator 115
 */
fun CXCursor_CompoundAssignOperator(): Int = 115

/**
 * {@snippet lang=c : #define CXCursor_ConditionalOperator 116
 */
fun CXCursor_ConditionalOperator(): Int = 116

/**
 * {@snippet lang=c : #define CXCursor_CStyleCastExpr 117
 */
fun CXCursor_CStyleCastExpr(): Int = 117

/**
 * {@snippet lang=c : #define CXCursor_CompoundLiteralExpr 118
 */
fun CXCursor_CompoundLiteralExpr(): Int = 118

/**
 * {@snippet lang=c : #define CXCursor_InitListExpr 119
 */
fun CXCursor_InitListExpr(): Int = 119

/**
 * {@snippet lang=c : #define CXCursor_AddrLabelExpr 120
 */
fun CXCursor_AddrLabelExpr(): Int = 120

/**
 * {@snippet lang=c : #define CXCursor_StmtExpr 121
 */
fun CXCursor_StmtExpr(): Int = 121

/**
 * {@snippet lang=c : #define CXCursor_GenericSelectionExpr 122
 */
fun CXCursor_GenericSelectionExpr(): Int = 122

/**
 * {@snippet lang=c : #define CXCursor_GNUNullExpr 123
 */
fun CXCursor_GNUNullExpr(): Int = 123

/**
 * {@snippet lang=c : #define CXCursor_CXXStaticCastExpr 124
 */
fun CXCursor_CXXStaticCastExpr(): Int = 124

/**
 * {@snippet lang=c : #define CXCursor_CXXDynamicCastExpr 125
 */
fun CXCursor_CXXDynamicCastExpr(): Int = 125

/**
 * {@snippet lang=c : #define CXCursor_CXXReinterpretCastExpr 126
 */
fun CXCursor_CXXReinterpretCastExpr(): Int = 126

/**
 * {@snippet lang=c : #define CXCursor_CXXConstCastExpr 127
 */
fun CXCursor_CXXConstCastExpr(): Int = 127

/**
 * {@snippet lang=c : #define CXCursor_CXXFunctionalCastExpr 128
 */
fun CXCursor_CXXFunctionalCastExpr(): Int = 128

/**
 * {@snippet lang=c : #define CXCursor_CXXTypeidExpr 129
 */
fun CXCursor_CXXTypeidExpr(): Int = 129

/**
 * {@snippet lang=c : #define CXCursor_CXXBoolLiteralExpr 130
 */
fun CXCursor_CXXBoolLiteralExpr(): Int = 130

/**
 * {@snippet lang=c : #define CXCursor_CXXNullPtrLiteralExpr 131
 */
fun CXCursor_CXXNullPtrLiteralExpr(): Int = 131

/**
 * {@snippet lang=c : #define CXCursor_CXXThisExpr 132
 */
fun CXCursor_CXXThisExpr(): Int = 132

/**
 * {@snippet lang=c : #define CXCursor_CXXThrowExpr 133
 */
fun CXCursor_CXXThrowExpr(): Int = 133

/**
 * {@snippet lang=c : #define CXCursor_CXXNewExpr 134
 */
fun CXCursor_CXXNewExpr(): Int = 134

/**
 * {@snippet lang=c : #define CXCursor_CXXDeleteExpr 135
 */
fun CXCursor_CXXDeleteExpr(): Int = 135

/**
 * {@snippet lang=c : #define CXCursor_UnaryExpr 136
 */
fun CXCursor_UnaryExpr(): Int = 136

/**
 * {@snippet lang=c : #define CXCursor_ObjCStringLiteral 137
 */
fun CXCursor_ObjCStringLiteral(): Int = 137

/**
 * {@snippet lang=c : #define CXCursor_ObjCEncodeExpr 138
 */
fun CXCursor_ObjCEncodeExpr(): Int = 138

/**
 * {@snippet lang=c : #define CXCursor_ObjCSelectorExpr 139
 */
fun CXCursor_ObjCSelectorExpr(): Int = 139

/**
 * {@snippet lang=c : #define CXCursor_ObjCProtocolExpr 140
 */
fun CXCursor_ObjCProtocolExpr(): Int = 140

/**
 * {@snippet lang=c : #define CXCursor_ObjCBridgedCastExpr 141
 */
fun CXCursor_ObjCBridgedCastExpr(): Int = 141

/**
 * {@snippet lang=c : #define CXCursor_PackExpansionExpr 142
 */
fun CXCursor_PackExpansionExpr(): Int = 142

/**
 * {@snippet lang=c : #define CXCursor_SizeOfPackExpr 143
 */
fun CXCursor_SizeOfPackExpr(): Int = 143

/**
 * {@snippet lang=c : #define CXCursor_LambdaExpr 144
 */
fun CXCursor_LambdaExpr(): Int = 144

/**
 * {@snippet lang=c : #define CXCursor_ObjCBoolLiteralExpr 145
 */
fun CXCursor_ObjCBoolLiteralExpr(): Int = 145

/**
 * {@snippet lang=c : #define CXCursor_ObjCSelfExpr 146
 */
fun CXCursor_ObjCSelfExpr(): Int = 146

/**
 * {@snippet lang=c : #define CXCursor_ArraySectionExpr 147
 */
fun CXCursor_ArraySectionExpr(): Int = 147

/**
 * {@snippet lang=c : #define CXCursor_ObjCAvailabilityCheckExpr 148
 */
fun CXCursor_ObjCAvailabilityCheckExpr(): Int = 148

/**
 * {@snippet lang=c : #define CXCursor_FixedPointLiteral 149
 */
fun CXCursor_FixedPointLiteral(): Int = 149

/**
 * {@snippet lang=c : #define CXCursor_OMPArrayShapingExpr 150
 */
fun CXCursor_OMPArrayShapingExpr(): Int = 150

/**
 * {@snippet lang=c : #define CXCursor_OMPIteratorExpr 151
 */
fun CXCursor_OMPIteratorExpr(): Int = 151

/**
 * {@snippet lang=c : #define CXCursor_CXXAddrspaceCastExpr 152
 */
fun CXCursor_CXXAddrspaceCastExpr(): Int = 152

/**
 * {@snippet lang=c : #define CXCursor_ConceptSpecializationExpr 153
 */
fun CXCursor_ConceptSpecializationExpr(): Int = 153

/**
 * {@snippet lang=c : #define CXCursor_RequiresExpr 154
 */
fun CXCursor_RequiresExpr(): Int = 154

/**
 * {@snippet lang=c : #define CXCursor_CXXParenListInitExpr 155
 */
fun CXCursor_CXXParenListInitExpr(): Int = 155

/**
 * {@snippet lang=c : #define CXCursor_PackIndexingExpr 156
 */
fun CXCursor_PackIndexingExpr(): Int = 156

/**
 * {@snippet lang=c : #define CXCursor_LastExpr 156
 */
fun CXCursor_LastExpr(): Int = 156

/**
 * {@snippet lang=c : #define CXCursor_FirstStmt 200
 */
fun CXCursor_FirstStmt(): Int = 200

/**
 * {@snippet lang=c : #define CXCursor_UnexposedStmt 200
 */
fun CXCursor_UnexposedStmt(): Int = 200

/**
 * {@snippet lang=c : #define CXCursor_LabelStmt 201
 */
fun CXCursor_LabelStmt(): Int = 201

/**
 * {@snippet lang=c : #define CXCursor_CompoundStmt 202
 */
fun CXCursor_CompoundStmt(): Int = 202

/**
 * {@snippet lang=c : #define CXCursor_CaseStmt 203
 */
fun CXCursor_CaseStmt(): Int = 203

/**
 * {@snippet lang=c : #define CXCursor_DefaultStmt 204
 */
fun CXCursor_DefaultStmt(): Int = 204

/**
 * {@snippet lang=c : #define CXCursor_IfStmt 205
 */
fun CXCursor_IfStmt(): Int = 205

/**
 * {@snippet lang=c : #define CXCursor_SwitchStmt 206
 */
fun CXCursor_SwitchStmt(): Int = 206

/**
 * {@snippet lang=c : #define CXCursor_WhileStmt 207
 */
fun CXCursor_WhileStmt(): Int = 207

/**
 * {@snippet lang=c : #define CXCursor_DoStmt 208
 */
fun CXCursor_DoStmt(): Int = 208

/**
 * {@snippet lang=c : #define CXCursor_ForStmt 209
 */
fun CXCursor_ForStmt(): Int = 209

/**
 * {@snippet lang=c : #define CXCursor_GotoStmt 210
 */
fun CXCursor_GotoStmt(): Int = 210

/**
 * {@snippet lang=c : #define CXCursor_IndirectGotoStmt 211
 */
fun CXCursor_IndirectGotoStmt(): Int = 211

/**
 * {@snippet lang=c : #define CXCursor_ContinueStmt 212
 */
fun CXCursor_ContinueStmt(): Int = 212

/**
 * {@snippet lang=c : #define CXCursor_BreakStmt 213
 */
fun CXCursor_BreakStmt(): Int = 213

/**
 * {@snippet lang=c : #define CXCursor_ReturnStmt 214
 */
fun CXCursor_ReturnStmt(): Int = 214

/**
 * {@snippet lang=c : #define CXCursor_GCCAsmStmt 215
 */
fun CXCursor_GCCAsmStmt(): Int = 215

/**
 * {@snippet lang=c : #define CXCursor_AsmStmt 215
 */
fun CXCursor_AsmStmt(): Int = 215

/**
 * {@snippet lang=c : #define CXCursor_ObjCAtTryStmt 216
 */
fun CXCursor_ObjCAtTryStmt(): Int = 216

/**
 * {@snippet lang=c : #define CXCursor_ObjCAtCatchStmt 217
 */
fun CXCursor_ObjCAtCatchStmt(): Int = 217

/**
 * {@snippet lang=c : #define CXCursor_ObjCAtFinallyStmt 218
 */
fun CXCursor_ObjCAtFinallyStmt(): Int = 218

/**
 * {@snippet lang=c : #define CXCursor_ObjCAtThrowStmt 219
 */
fun CXCursor_ObjCAtThrowStmt(): Int = 219

/**
 * {@snippet lang=c : #define CXCursor_ObjCAtSynchronizedStmt 220
 */
fun CXCursor_ObjCAtSynchronizedStmt(): Int = 220

/**
 * {@snippet lang=c : #define CXCursor_ObjCAutoreleasePoolStmt 221
 */
fun CXCursor_ObjCAutoreleasePoolStmt(): Int = 221

/**
 * {@snippet lang=c : #define CXCursor_ObjCForCollectionStmt 222
 */
fun CXCursor_ObjCForCollectionStmt(): Int = 222

/**
 * {@snippet lang=c : #define CXCursor_CXXCatchStmt 223
 */
fun CXCursor_CXXCatchStmt(): Int = 223

/**
 * {@snippet lang=c : #define CXCursor_CXXTryStmt 224
 */
fun CXCursor_CXXTryStmt(): Int = 224

/**
 * {@snippet lang=c : #define CXCursor_CXXForRangeStmt 225
 */
fun CXCursor_CXXForRangeStmt(): Int = 225

/**
 * {@snippet lang=c : #define CXCursor_SEHTryStmt 226
 */
fun CXCursor_SEHTryStmt(): Int = 226

/**
 * {@snippet lang=c : #define CXCursor_SEHExceptStmt 227
 */
fun CXCursor_SEHExceptStmt(): Int = 227

/**
 * {@snippet lang=c : #define CXCursor_SEHFinallyStmt 228
 */
fun CXCursor_SEHFinallyStmt(): Int = 228

/**
 * {@snippet lang=c : #define CXCursor_MSAsmStmt 229
 */
fun CXCursor_MSAsmStmt(): Int = 229

/**
 * {@snippet lang=c : #define CXCursor_NullStmt 230
 */
fun CXCursor_NullStmt(): Int = 230

/**
 * {@snippet lang=c : #define CXCursor_DeclStmt 231
 */
fun CXCursor_DeclStmt(): Int = 231

/**
 * {@snippet lang=c : #define CXCursor_OMPParallelDirective 232
 */
fun CXCursor_OMPParallelDirective(): Int = 232

/**
 * {@snippet lang=c : #define CXCursor_OMPSimdDirective 233
 */
fun CXCursor_OMPSimdDirective(): Int = 233

/**
 * {@snippet lang=c : #define CXCursor_OMPForDirective 234
 */
fun CXCursor_OMPForDirective(): Int = 234

/**
 * {@snippet lang=c : #define CXCursor_OMPSectionsDirective 235
 */
fun CXCursor_OMPSectionsDirective(): Int = 235

/**
 * {@snippet lang=c : #define CXCursor_OMPSectionDirective 236
 */
fun CXCursor_OMPSectionDirective(): Int = 236

/**
 * {@snippet lang=c : #define CXCursor_OMPSingleDirective 237
 */
fun CXCursor_OMPSingleDirective(): Int = 237

/**
 * {@snippet lang=c : #define CXCursor_OMPParallelForDirective 238
 */
fun CXCursor_OMPParallelForDirective(): Int = 238

/**
 * {@snippet lang=c : #define CXCursor_OMPParallelSectionsDirective 239
 */
fun CXCursor_OMPParallelSectionsDirective(): Int = 239

/**
 * {@snippet lang=c : #define CXCursor_OMPTaskDirective 240
 */
fun CXCursor_OMPTaskDirective(): Int = 240

/**
 * {@snippet lang=c : #define CXCursor_OMPMasterDirective 241
 */
fun CXCursor_OMPMasterDirective(): Int = 241

/**
 * {@snippet lang=c : #define CXCursor_OMPCriticalDirective 242
 */
fun CXCursor_OMPCriticalDirective(): Int = 242

/**
 * {@snippet lang=c : #define CXCursor_OMPTaskyieldDirective 243
 */
fun CXCursor_OMPTaskyieldDirective(): Int = 243

/**
 * {@snippet lang=c : #define CXCursor_OMPBarrierDirective 244
 */
fun CXCursor_OMPBarrierDirective(): Int = 244

/**
 * {@snippet lang=c : #define CXCursor_OMPTaskwaitDirective 245
 */
fun CXCursor_OMPTaskwaitDirective(): Int = 245

/**
 * {@snippet lang=c : #define CXCursor_OMPFlushDirective 246
 */
fun CXCursor_OMPFlushDirective(): Int = 246

/**
 * {@snippet lang=c : #define CXCursor_SEHLeaveStmt 247
 */
fun CXCursor_SEHLeaveStmt(): Int = 247

/**
 * {@snippet lang=c : #define CXCursor_OMPOrderedDirective 248
 */
fun CXCursor_OMPOrderedDirective(): Int = 248

/**
 * {@snippet lang=c : #define CXCursor_OMPAtomicDirective 249
 */
fun CXCursor_OMPAtomicDirective(): Int = 249

/**
 * {@snippet lang=c : #define CXCursor_OMPForSimdDirective 250
 */
fun CXCursor_OMPForSimdDirective(): Int = 250

/**
 * {@snippet lang=c : #define CXCursor_OMPParallelForSimdDirective 251
 */
fun CXCursor_OMPParallelForSimdDirective(): Int = 251

/**
 * {@snippet lang=c : #define CXCursor_OMPTargetDirective 252
 */
fun CXCursor_OMPTargetDirective(): Int = 252

/**
 * {@snippet lang=c : #define CXCursor_OMPTeamsDirective 253
 */
fun CXCursor_OMPTeamsDirective(): Int = 253

/**
 * {@snippet lang=c : #define CXCursor_OMPTaskgroupDirective 254
 */
fun CXCursor_OMPTaskgroupDirective(): Int = 254

/**
 * {@snippet lang=c : #define CXCursor_OMPCancellationPointDirective 255
 */
fun CXCursor_OMPCancellationPointDirective(): Int = 255

/**
 * {@snippet lang=c : #define CXCursor_OMPCancelDirective 256
 */
fun CXCursor_OMPCancelDirective(): Int = 256

/**
 * {@snippet lang=c : #define CXCursor_OMPTargetDataDirective 257
 */
fun CXCursor_OMPTargetDataDirective(): Int = 257

/**
 * {@snippet lang=c : #define CXCursor_OMPTaskLoopDirective 258
 */
fun CXCursor_OMPTaskLoopDirective(): Int = 258

/**
 * {@snippet lang=c : #define CXCursor_OMPTaskLoopSimdDirective 259
 */
fun CXCursor_OMPTaskLoopSimdDirective(): Int = 259

/**
 * {@snippet lang=c : #define CXCursor_OMPDistributeDirective 260
 */
fun CXCursor_OMPDistributeDirective(): Int = 260

/**
 * {@snippet lang=c : #define CXCursor_OMPTargetEnterDataDirective 261
 */
fun CXCursor_OMPTargetEnterDataDirective(): Int = 261

/**
 * {@snippet lang=c : #define CXCursor_OMPTargetExitDataDirective 262
 */
fun CXCursor_OMPTargetExitDataDirective(): Int = 262

/**
 * {@snippet lang=c : #define CXCursor_OMPTargetParallelDirective 263
 */
fun CXCursor_OMPTargetParallelDirective(): Int = 263

/**
 * {@snippet lang=c : #define CXCursor_OMPTargetParallelForDirective 264
 */
fun CXCursor_OMPTargetParallelForDirective(): Int = 264

/**
 * {@snippet lang=c : #define CXCursor_OMPTargetUpdateDirective 265
 */
fun CXCursor_OMPTargetUpdateDirective(): Int = 265

/**
 * {@snippet lang=c : #define CXCursor_OMPDistributeParallelForDirective 266
 */
fun CXCursor_OMPDistributeParallelForDirective(): Int = 266

/**
 * {@snippet lang=c : #define CXCursor_OMPDistributeParallelForSimdDirective 267
 */
fun CXCursor_OMPDistributeParallelForSimdDirective(): Int = 267

/**
 * {@snippet lang=c : #define CXCursor_OMPDistributeSimdDirective 268
 */
fun CXCursor_OMPDistributeSimdDirective(): Int = 268

/**
 * {@snippet lang=c : #define CXCursor_OMPTargetParallelForSimdDirective 269
 */
fun CXCursor_OMPTargetParallelForSimdDirective(): Int = 269

/**
 * {@snippet lang=c : #define CXCursor_OMPTargetSimdDirective 270
 */
fun CXCursor_OMPTargetSimdDirective(): Int = 270

/**
 * {@snippet lang=c : #define CXCursor_OMPTeamsDistributeDirective 271
 */
fun CXCursor_OMPTeamsDistributeDirective(): Int = 271

/**
 * {@snippet lang=c : #define CXCursor_OMPTeamsDistributeSimdDirective 272
 */
fun CXCursor_OMPTeamsDistributeSimdDirective(): Int = 272

/**
 * {@snippet lang=c : #define CXCursor_OMPTeamsDistributeParallelForSimdDirective 273
 */
fun CXCursor_OMPTeamsDistributeParallelForSimdDirective(): Int = 273

/**
 * {@snippet lang=c : #define CXCursor_OMPTeamsDistributeParallelForDirective 274
 */
fun CXCursor_OMPTeamsDistributeParallelForDirective(): Int = 274

/**
 * {@snippet lang=c : #define CXCursor_OMPTargetTeamsDirective 275
 */
fun CXCursor_OMPTargetTeamsDirective(): Int = 275

/**
 * {@snippet lang=c : #define CXCursor_OMPTargetTeamsDistributeDirective 276
 */
fun CXCursor_OMPTargetTeamsDistributeDirective(): Int = 276

/**
 * {@snippet lang=c : #define CXCursor_OMPTargetTeamsDistributeParallelForDirective 277
 */
fun CXCursor_OMPTargetTeamsDistributeParallelForDirective(): Int = 277

/**
 * {@snippet lang=c : #define CXCursor_OMPTargetTeamsDistributeParallelForSimdDirective 278
 */
fun CXCursor_OMPTargetTeamsDistributeParallelForSimdDirective(): Int = 278

/**
 * {@snippet lang=c : #define CXCursor_OMPTargetTeamsDistributeSimdDirective 279
 */
fun CXCursor_OMPTargetTeamsDistributeSimdDirective(): Int = 279

/**
 * {@snippet lang=c : #define CXCursor_BuiltinBitCastExpr 280
 */
fun CXCursor_BuiltinBitCastExpr(): Int = 280

/**
 * {@snippet lang=c : #define CXCursor_OMPMasterTaskLoopDirective 281
 */
fun CXCursor_OMPMasterTaskLoopDirective(): Int = 281

/**
 * {@snippet lang=c : #define CXCursor_OMPParallelMasterTaskLoopDirective 282
 */
fun CXCursor_OMPParallelMasterTaskLoopDirective(): Int = 282

/**
 * {@snippet lang=c : #define CXCursor_OMPMasterTaskLoopSimdDirective 283
 */
fun CXCursor_OMPMasterTaskLoopSimdDirective(): Int = 283

/**
 * {@snippet lang=c : #define CXCursor_OMPParallelMasterTaskLoopSimdDirective 284
 */
fun CXCursor_OMPParallelMasterTaskLoopSimdDirective(): Int = 284

/**
 * {@snippet lang=c : #define CXCursor_OMPParallelMasterDirective 285
 */
fun CXCursor_OMPParallelMasterDirective(): Int = 285

/**
 * {@snippet lang=c : #define CXCursor_OMPDepobjDirective 286
 */
fun CXCursor_OMPDepobjDirective(): Int = 286

/**
 * {@snippet lang=c : #define CXCursor_OMPScanDirective 287
 */
fun CXCursor_OMPScanDirective(): Int = 287

/**
 * {@snippet lang=c : #define CXCursor_OMPTileDirective 288
 */
fun CXCursor_OMPTileDirective(): Int = 288

/**
 * {@snippet lang=c : #define CXCursor_OMPCanonicalLoop 289
 */
fun CXCursor_OMPCanonicalLoop(): Int = 289

/**
 * {@snippet lang=c : #define CXCursor_OMPInteropDirective 290
 */
fun CXCursor_OMPInteropDirective(): Int = 290

/**
 * {@snippet lang=c : #define CXCursor_OMPDispatchDirective 291
 */
fun CXCursor_OMPDispatchDirective(): Int = 291

/**
 * {@snippet lang=c : #define CXCursor_OMPMaskedDirective 292
 */
fun CXCursor_OMPMaskedDirective(): Int = 292

/**
 * {@snippet lang=c : #define CXCursor_OMPUnrollDirective 293
 */
fun CXCursor_OMPUnrollDirective(): Int = 293

/**
 * {@snippet lang=c : #define CXCursor_OMPMetaDirective 294
 */
fun CXCursor_OMPMetaDirective(): Int = 294

/**
 * {@snippet lang=c : #define CXCursor_OMPGenericLoopDirective 295
 */
fun CXCursor_OMPGenericLoopDirective(): Int = 295

/**
 * {@snippet lang=c : #define CXCursor_OMPTeamsGenericLoopDirective 296
 */
fun CXCursor_OMPTeamsGenericLoopDirective(): Int = 296

/**
 * {@snippet lang=c : #define CXCursor_OMPTargetTeamsGenericLoopDirective 297
 */
fun CXCursor_OMPTargetTeamsGenericLoopDirective(): Int = 297

/**
 * {@snippet lang=c : #define CXCursor_OMPParallelGenericLoopDirective 298
 */
fun CXCursor_OMPParallelGenericLoopDirective(): Int = 298

/**
 * {@snippet lang=c : #define CXCursor_OMPTargetParallelGenericLoopDirective 299
 */
fun CXCursor_OMPTargetParallelGenericLoopDirective(): Int = 299

/**
 * {@snippet lang=c : #define CXCursor_OMPParallelMaskedDirective 300
 */
fun CXCursor_OMPParallelMaskedDirective(): Int = 300

/**
 * {@snippet lang=c : #define CXCursor_OMPMaskedTaskLoopDirective 301
 */
fun CXCursor_OMPMaskedTaskLoopDirective(): Int = 301

/**
 * {@snippet lang=c : #define CXCursor_OMPMaskedTaskLoopSimdDirective 302
 */
fun CXCursor_OMPMaskedTaskLoopSimdDirective(): Int = 302

/**
 * {@snippet lang=c : #define CXCursor_OMPParallelMaskedTaskLoopDirective 303
 */
fun CXCursor_OMPParallelMaskedTaskLoopDirective(): Int = 303

/**
 * {@snippet lang=c : #define CXCursor_OMPParallelMaskedTaskLoopSimdDirective 304
 */
fun CXCursor_OMPParallelMaskedTaskLoopSimdDirective(): Int = 304

/**
 * {@snippet lang=c : #define CXCursor_OMPErrorDirective 305
 */
fun CXCursor_OMPErrorDirective(): Int = 305

/**
 * {@snippet lang=c : #define CXCursor_OMPScopeDirective 306
 */
fun CXCursor_OMPScopeDirective(): Int = 306

/**
 * {@snippet lang=c : #define CXCursor_OMPReverseDirective 307
 */
fun CXCursor_OMPReverseDirective(): Int = 307

/**
 * {@snippet lang=c : #define CXCursor_OMPInterchangeDirective 308
 */
fun CXCursor_OMPInterchangeDirective(): Int = 308

/**
 * {@snippet lang=c : #define CXCursor_OMPAssumeDirective 309
 */
fun CXCursor_OMPAssumeDirective(): Int = 309

/**
 * {@snippet lang=c : #define CXCursor_OMPStripeDirective 310
 */
fun CXCursor_OMPStripeDirective(): Int = 310

/**
 * {@snippet lang=c : #define CXCursor_OMPFuseDirective 311
 */
fun CXCursor_OMPFuseDirective(): Int = 311

/**
 * {@snippet lang=c : #define CXCursor_OpenACCComputeConstruct 320
 */
fun CXCursor_OpenACCComputeConstruct(): Int = 320

/**
 * {@snippet lang=c : #define CXCursor_OpenACCLoopConstruct 321
 */
fun CXCursor_OpenACCLoopConstruct(): Int = 321

/**
 * {@snippet lang=c : #define CXCursor_OpenACCCombinedConstruct 322
 */
fun CXCursor_OpenACCCombinedConstruct(): Int = 322

/**
 * {@snippet lang=c : #define CXCursor_OpenACCDataConstruct 323
 */
fun CXCursor_OpenACCDataConstruct(): Int = 323

/**
 * {@snippet lang=c : #define CXCursor_OpenACCEnterDataConstruct 324
 */
fun CXCursor_OpenACCEnterDataConstruct(): Int = 324

/**
 * {@snippet lang=c : #define CXCursor_OpenACCExitDataConstruct 325
 */
fun CXCursor_OpenACCExitDataConstruct(): Int = 325

/**
 * {@snippet lang=c : #define CXCursor_OpenACCHostDataConstruct 326
 */
fun CXCursor_OpenACCHostDataConstruct(): Int = 326

/**
 * {@snippet lang=c : #define CXCursor_OpenACCWaitConstruct 327
 */
fun CXCursor_OpenACCWaitConstruct(): Int = 327

/**
 * {@snippet lang=c : #define CXCursor_OpenACCInitConstruct 328
 */
fun CXCursor_OpenACCInitConstruct(): Int = 328

/**
 * {@snippet lang=c : #define CXCursor_OpenACCShutdownConstruct 329
 */
fun CXCursor_OpenACCShutdownConstruct(): Int = 329

/**
 * {@snippet lang=c : #define CXCursor_OpenACCSetConstruct 330
 */
fun CXCursor_OpenACCSetConstruct(): Int = 330

/**
 * {@snippet lang=c : #define CXCursor_OpenACCUpdateConstruct 331
 */
fun CXCursor_OpenACCUpdateConstruct(): Int = 331

/**
 * {@snippet lang=c : #define CXCursor_OpenACCAtomicConstruct 332
 */
fun CXCursor_OpenACCAtomicConstruct(): Int = 332

/**
 * {@snippet lang=c : #define CXCursor_OpenACCCacheConstruct 333
 */
fun CXCursor_OpenACCCacheConstruct(): Int = 333

/**
 * {@snippet lang=c : #define CXCursor_LastStmt 333
 */
fun CXCursor_LastStmt(): Int = 333

/**
 * {@snippet lang=c : #define CXCursor_TranslationUnit 350
 */
fun CXCursor_TranslationUnit(): Int = 350

/**
 * {@snippet lang=c : #define CXCursor_FirstAttr 400
 */
fun CXCursor_FirstAttr(): Int = 400

/**
 * {@snippet lang=c : #define CXCursor_UnexposedAttr 400
 */
fun CXCursor_UnexposedAttr(): Int = 400

/**
 * {@snippet lang=c : #define CXCursor_IBActionAttr 401
 */
fun CXCursor_IBActionAttr(): Int = 401

/**
 * {@snippet lang=c : #define CXCursor_IBOutletAttr 402
 */
fun CXCursor_IBOutletAttr(): Int = 402

/**
 * {@snippet lang=c : #define CXCursor_IBOutletCollectionAttr 403
 */
fun CXCursor_IBOutletCollectionAttr(): Int = 403

/**
 * {@snippet lang=c : #define CXCursor_CXXFinalAttr 404
 */
fun CXCursor_CXXFinalAttr(): Int = 404

/**
 * {@snippet lang=c : #define CXCursor_CXXOverrideAttr 405
 */
fun CXCursor_CXXOverrideAttr(): Int = 405

/**
 * {@snippet lang=c : #define CXCursor_AnnotateAttr 406
 */
fun CXCursor_AnnotateAttr(): Int = 406

/**
 * {@snippet lang=c : #define CXCursor_AsmLabelAttr 407
 */
fun CXCursor_AsmLabelAttr(): Int = 407

/**
 * {@snippet lang=c : #define CXCursor_PackedAttr 408
 */
fun CXCursor_PackedAttr(): Int = 408

/**
 * {@snippet lang=c : #define CXCursor_PureAttr 409
 */
fun CXCursor_PureAttr(): Int = 409

/**
 * {@snippet lang=c : #define CXCursor_ConstAttr 410
 */
fun CXCursor_ConstAttr(): Int = 410

/**
 * {@snippet lang=c : #define CXCursor_NoDuplicateAttr 411
 */
fun CXCursor_NoDuplicateAttr(): Int = 411

/**
 * {@snippet lang=c : #define CXCursor_CUDAConstantAttr 412
 */
fun CXCursor_CUDAConstantAttr(): Int = 412

/**
 * {@snippet lang=c : #define CXCursor_CUDADeviceAttr 413
 */
fun CXCursor_CUDADeviceAttr(): Int = 413

/**
 * {@snippet lang=c : #define CXCursor_CUDAGlobalAttr 414
 */
fun CXCursor_CUDAGlobalAttr(): Int = 414

/**
 * {@snippet lang=c : #define CXCursor_CUDAHostAttr 415
 */
fun CXCursor_CUDAHostAttr(): Int = 415

/**
 * {@snippet lang=c : #define CXCursor_CUDASharedAttr 416
 */
fun CXCursor_CUDASharedAttr(): Int = 416

/**
 * {@snippet lang=c : #define CXCursor_VisibilityAttr 417
 */
fun CXCursor_VisibilityAttr(): Int = 417

/**
 * {@snippet lang=c : #define CXCursor_DLLExport 418
 */
fun CXCursor_DLLExport(): Int = 418

/**
 * {@snippet lang=c : #define CXCursor_DLLImport 419
 */
fun CXCursor_DLLImport(): Int = 419

/**
 * {@snippet lang=c : #define CXCursor_NSReturnsRetained 420
 */
fun CXCursor_NSReturnsRetained(): Int = 420

/**
 * {@snippet lang=c : #define CXCursor_NSReturnsNotRetained 421
 */
fun CXCursor_NSReturnsNotRetained(): Int = 421

/**
 * {@snippet lang=c : #define CXCursor_NSReturnsAutoreleased 422
 */
fun CXCursor_NSReturnsAutoreleased(): Int = 422

/**
 * {@snippet lang=c : #define CXCursor_NSConsumesSelf 423
 */
fun CXCursor_NSConsumesSelf(): Int = 423

/**
 * {@snippet lang=c : #define CXCursor_NSConsumed 424
 */
fun CXCursor_NSConsumed(): Int = 424

/**
 * {@snippet lang=c : #define CXCursor_ObjCException 425
 */
fun CXCursor_ObjCException(): Int = 425

/**
 * {@snippet lang=c : #define CXCursor_ObjCNSObject 426
 */
fun CXCursor_ObjCNSObject(): Int = 426

/**
 * {@snippet lang=c : #define CXCursor_ObjCIndependentClass 427
 */
fun CXCursor_ObjCIndependentClass(): Int = 427

/**
 * {@snippet lang=c : #define CXCursor_ObjCPreciseLifetime 428
 */
fun CXCursor_ObjCPreciseLifetime(): Int = 428

/**
 * {@snippet lang=c : #define CXCursor_ObjCReturnsInnerPointer 429
 */
fun CXCursor_ObjCReturnsInnerPointer(): Int = 429

/**
 * {@snippet lang=c : #define CXCursor_ObjCRequiresSuper 430
 */
fun CXCursor_ObjCRequiresSuper(): Int = 430

/**
 * {@snippet lang=c : #define CXCursor_ObjCRootClass 431
 */
fun CXCursor_ObjCRootClass(): Int = 431

/**
 * {@snippet lang=c : #define CXCursor_ObjCSubclassingRestricted 432
 */
fun CXCursor_ObjCSubclassingRestricted(): Int = 432

/**
 * {@snippet lang=c : #define CXCursor_ObjCExplicitProtocolImpl 433
 */
fun CXCursor_ObjCExplicitProtocolImpl(): Int = 433

/**
 * {@snippet lang=c : #define CXCursor_ObjCDesignatedInitializer 434
 */
fun CXCursor_ObjCDesignatedInitializer(): Int = 434

/**
 * {@snippet lang=c : #define CXCursor_ObjCRuntimeVisible 435
 */
fun CXCursor_ObjCRuntimeVisible(): Int = 435

/**
 * {@snippet lang=c : #define CXCursor_ObjCBoxable 436
 */
fun CXCursor_ObjCBoxable(): Int = 436

/**
 * {@snippet lang=c : #define CXCursor_FlagEnum 437
 */
fun CXCursor_FlagEnum(): Int = 437

/**
 * {@snippet lang=c : #define CXCursor_ConvergentAttr 438
 */
fun CXCursor_ConvergentAttr(): Int = 438

/**
 * {@snippet lang=c : #define CXCursor_WarnUnusedAttr 439
 */
fun CXCursor_WarnUnusedAttr(): Int = 439

/**
 * {@snippet lang=c : #define CXCursor_WarnUnusedResultAttr 440
 */
fun CXCursor_WarnUnusedResultAttr(): Int = 440

/**
 * {@snippet lang=c : #define CXCursor_AlignedAttr 441
 */
fun CXCursor_AlignedAttr(): Int = 441

/**
 * {@snippet lang=c : #define CXCursor_LastAttr 441
 */
fun CXCursor_LastAttr(): Int = 441

/**
 * {@snippet lang=c : #define CXCursor_PreprocessingDirective 500
 */
fun CXCursor_PreprocessingDirective(): Int = 500

/**
 * {@snippet lang=c : #define CXCursor_MacroDefinition 501
 */
fun CXCursor_MacroDefinition(): Int = 501

/**
 * {@snippet lang=c : #define CXCursor_MacroExpansion 502
 */
fun CXCursor_MacroExpansion(): Int = 502

/**
 * {@snippet lang=c : #define CXCursor_MacroInstantiation 502
 */
fun CXCursor_MacroInstantiation(): Int = 502

/**
 * {@snippet lang=c : #define CXCursor_InclusionDirective 503
 */
fun CXCursor_InclusionDirective(): Int = 503

/**
 * {@snippet lang=c : #define CXCursor_FirstPreprocessing 500
 */
fun CXCursor_FirstPreprocessing(): Int = 500

/**
 * {@snippet lang=c : #define CXCursor_LastPreprocessing 503
 */
fun CXCursor_LastPreprocessing(): Int = 503

/**
 * {@snippet lang=c : #define CXCursor_ModuleImportDecl 600
 */
fun CXCursor_ModuleImportDecl(): Int = 600

/**
 * {@snippet lang=c : #define CXCursor_TypeAliasTemplateDecl 601
 */
fun CXCursor_TypeAliasTemplateDecl(): Int = 601

/**
 * {@snippet lang=c : #define CXCursor_StaticAssert 602
 */
fun CXCursor_StaticAssert(): Int = 602

/**
 * {@snippet lang=c : #define CXCursor_FriendDecl 603
 */
fun CXCursor_FriendDecl(): Int = 603

/**
 * {@snippet lang=c : #define CXCursor_ConceptDecl 604
 */
fun CXCursor_ConceptDecl(): Int = 604

/**
 * {@snippet lang=c : #define CXCursor_FirstExtraDecl 600
 */
fun CXCursor_FirstExtraDecl(): Int = 600

/**
 * {@snippet lang=c : #define CXCursor_LastExtraDecl 604
 */
fun CXCursor_LastExtraDecl(): Int = 604

/**
 * {@snippet lang=c : #define CXCursor_OverloadCandidate 700
 */
fun CXCursor_OverloadCandidate(): Int = 700

/**
 * {@snippet lang=c : STRUCT CXCursor
 */
class CXCursor {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("kind"),
            ValueLayout.JAVA_INT.withName("xdata"),
            MemoryLayout.sequenceLayout(3, ValueLayout.ADDRESS).withName("data")
        ).withName("CXCursor")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val kind_VH: VarHandle = layout.varHandle(groupElement("kind"))
        
        @Suppress("UNCHECKED_CAST")
        fun kind(segment: MemorySegment): Int =
            kind_VH.get(segment, 0L) as Int
        
        fun kind(segment: MemorySegment, value: Int) =
            kind_VH.set(segment, 0L, value)
        
        val xdata_VH: VarHandle = layout.varHandle(groupElement("xdata"))
        
        @Suppress("UNCHECKED_CAST")
        fun xdata(segment: MemorySegment): Int =
            xdata_VH.get(segment, 0L) as Int
        
        fun xdata(segment: MemorySegment, value: Int) =
            xdata_VH.set(segment, 0L, value)
        
        
        fun data_(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("data")), layout.select(groupElement("data")).byteSize())
    }
}

/**
 * {@snippet lang=c : clang_getNullCursor typedef CXCursor = Declared(CXCursor)()
 */
private val clang_getNullCursor_DESC: FunctionDescriptor = FunctionDescriptor.of(CXCursor.layout)
private val clang_getNullCursor_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getNullCursor")
private val clang_getNullCursor_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getNullCursor_ADDR, clang_getNullCursor_DESC)

fun clang_getNullCursor(allocator: SegmentAllocator): MemorySegment {
    try {
        return clang_getNullCursor_HANDLE.invokeExact(allocator) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getTranslationUnitCursor typedef CXCursor = Declared(CXCursor)(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*)
 */
private val clang_getTranslationUnitCursor_DESC: FunctionDescriptor = FunctionDescriptor.of(CXCursor.layout, ValueLayout.ADDRESS)
private val clang_getTranslationUnitCursor_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getTranslationUnitCursor")
private val clang_getTranslationUnitCursor_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getTranslationUnitCursor_ADDR, clang_getTranslationUnitCursor_DESC)

fun clang_getTranslationUnitCursor(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getTranslationUnitCursor_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_equalCursors UNSIGNED = Int(typedef CXCursor = Declared(CXCursor),typedef CXCursor = Declared(CXCursor))
 */
private val clang_equalCursors_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout, CXCursor.layout)
private val clang_equalCursors_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_equalCursors")
private val clang_equalCursors_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_equalCursors_ADDR, clang_equalCursors_DESC)

fun clang_equalCursors(arg0: MemorySegment, arg1: MemorySegment): Int {
    try {
        return clang_equalCursors_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_isNull Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_isNull_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_isNull_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_isNull")
private val clang_Cursor_isNull_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_isNull_ADDR, clang_Cursor_isNull_DESC)

fun clang_Cursor_isNull(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_isNull_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_hashCursor UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_hashCursor_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_hashCursor_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_hashCursor")
private val clang_hashCursor_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_hashCursor_ADDR, clang_hashCursor_DESC)

fun clang_hashCursor(arg0: MemorySegment): Int {
    try {
        return clang_hashCursor_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCursorKind Declared(CXCursorKind)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getCursorKind_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_getCursorKind_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorKind")
private val clang_getCursorKind_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorKind_ADDR, clang_getCursorKind_DESC)

fun clang_getCursorKind(arg0: MemorySegment): Int {
    try {
        return clang_getCursorKind_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_isDeclaration UNSIGNED = Int(Declared(CXCursorKind))
 */
private val clang_isDeclaration_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val clang_isDeclaration_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_isDeclaration")
private val clang_isDeclaration_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_isDeclaration_ADDR, clang_isDeclaration_DESC)

fun clang_isDeclaration(arg0: Int): Int {
    try {
        return clang_isDeclaration_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_isInvalidDeclaration UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_isInvalidDeclaration_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_isInvalidDeclaration_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_isInvalidDeclaration")
private val clang_isInvalidDeclaration_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_isInvalidDeclaration_ADDR, clang_isInvalidDeclaration_DESC)

fun clang_isInvalidDeclaration(arg0: MemorySegment): Int {
    try {
        return clang_isInvalidDeclaration_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_isReference UNSIGNED = Int(Declared(CXCursorKind))
 */
private val clang_isReference_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val clang_isReference_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_isReference")
private val clang_isReference_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_isReference_ADDR, clang_isReference_DESC)

fun clang_isReference(arg0: Int): Int {
    try {
        return clang_isReference_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_isExpression UNSIGNED = Int(Declared(CXCursorKind))
 */
private val clang_isExpression_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val clang_isExpression_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_isExpression")
private val clang_isExpression_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_isExpression_ADDR, clang_isExpression_DESC)

fun clang_isExpression(arg0: Int): Int {
    try {
        return clang_isExpression_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_isStatement UNSIGNED = Int(Declared(CXCursorKind))
 */
private val clang_isStatement_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val clang_isStatement_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_isStatement")
private val clang_isStatement_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_isStatement_ADDR, clang_isStatement_DESC)

fun clang_isStatement(arg0: Int): Int {
    try {
        return clang_isStatement_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_isAttribute UNSIGNED = Int(Declared(CXCursorKind))
 */
private val clang_isAttribute_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val clang_isAttribute_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_isAttribute")
private val clang_isAttribute_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_isAttribute_ADDR, clang_isAttribute_DESC)

fun clang_isAttribute(arg0: Int): Int {
    try {
        return clang_isAttribute_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_hasAttrs UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_hasAttrs_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_hasAttrs_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_hasAttrs")
private val clang_Cursor_hasAttrs_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_hasAttrs_ADDR, clang_Cursor_hasAttrs_DESC)

fun clang_Cursor_hasAttrs(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_hasAttrs_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_isInvalid UNSIGNED = Int(Declared(CXCursorKind))
 */
private val clang_isInvalid_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val clang_isInvalid_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_isInvalid")
private val clang_isInvalid_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_isInvalid_ADDR, clang_isInvalid_DESC)

fun clang_isInvalid(arg0: Int): Int {
    try {
        return clang_isInvalid_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_isTranslationUnit UNSIGNED = Int(Declared(CXCursorKind))
 */
private val clang_isTranslationUnit_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val clang_isTranslationUnit_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_isTranslationUnit")
private val clang_isTranslationUnit_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_isTranslationUnit_ADDR, clang_isTranslationUnit_DESC)

fun clang_isTranslationUnit(arg0: Int): Int {
    try {
        return clang_isTranslationUnit_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_isPreprocessing UNSIGNED = Int(Declared(CXCursorKind))
 */
private val clang_isPreprocessing_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val clang_isPreprocessing_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_isPreprocessing")
private val clang_isPreprocessing_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_isPreprocessing_ADDR, clang_isPreprocessing_DESC)

fun clang_isPreprocessing(arg0: Int): Int {
    try {
        return clang_isPreprocessing_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_isUnexposed UNSIGNED = Int(Declared(CXCursorKind))
 */
private val clang_isUnexposed_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val clang_isUnexposed_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_isUnexposed")
private val clang_isUnexposed_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_isUnexposed_ADDR, clang_isUnexposed_DESC)

fun clang_isUnexposed(arg0: Int): Int {
    try {
        return clang_isUnexposed_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXLinkage_Invalid 0
 */
fun CXLinkage_Invalid(): Int = 0

/**
 * {@snippet lang=c : #define CXLinkage_NoLinkage 1
 */
fun CXLinkage_NoLinkage(): Int = 1

/**
 * {@snippet lang=c : #define CXLinkage_Internal 2
 */
fun CXLinkage_Internal(): Int = 2

/**
 * {@snippet lang=c : #define CXLinkage_UniqueExternal 3
 */
fun CXLinkage_UniqueExternal(): Int = 3

/**
 * {@snippet lang=c : #define CXLinkage_External 4
 */
fun CXLinkage_External(): Int = 4

/**
 * {@snippet lang=c : clang_getCursorLinkage Declared(CXLinkageKind)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getCursorLinkage_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_getCursorLinkage_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorLinkage")
private val clang_getCursorLinkage_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorLinkage_ADDR, clang_getCursorLinkage_DESC)

fun clang_getCursorLinkage(arg0: MemorySegment): Int {
    try {
        return clang_getCursorLinkage_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXVisibility_Invalid 0
 */
fun CXVisibility_Invalid(): Int = 0

/**
 * {@snippet lang=c : #define CXVisibility_Hidden 1
 */
fun CXVisibility_Hidden(): Int = 1

/**
 * {@snippet lang=c : #define CXVisibility_Protected 2
 */
fun CXVisibility_Protected(): Int = 2

/**
 * {@snippet lang=c : #define CXVisibility_Default 3
 */
fun CXVisibility_Default(): Int = 3

/**
 * {@snippet lang=c : clang_getCursorVisibility Declared(CXVisibilityKind)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getCursorVisibility_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_getCursorVisibility_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorVisibility")
private val clang_getCursorVisibility_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorVisibility_ADDR, clang_getCursorVisibility_DESC)

fun clang_getCursorVisibility(arg0: MemorySegment): Int {
    try {
        return clang_getCursorVisibility_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCursorAvailability Declared(CXAvailabilityKind)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getCursorAvailability_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_getCursorAvailability_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorAvailability")
private val clang_getCursorAvailability_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorAvailability_ADDR, clang_getCursorAvailability_DESC)

fun clang_getCursorAvailability(arg0: MemorySegment): Int {
    try {
        return clang_getCursorAvailability_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : STRUCT CXPlatformAvailability
 */
class CXPlatformAvailability {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            CXString.layout.withName("Platform"),
            CXVersion.layout.withName("Introduced"),
            CXVersion.layout.withName("Deprecated"),
            CXVersion.layout.withName("Obsoleted"),
            ValueLayout.JAVA_INT.withName("Unavailable"),
            CXString.layout.withName("Message")
        ).withName("CXPlatformAvailability")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val Platform_VH: VarHandle = layout.varHandle(groupElement("Platform"))
        
        fun Platform(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("Platform")), layout.select(groupElement("Platform")).byteSize())
        
        val Introduced_VH: VarHandle = layout.varHandle(groupElement("Introduced"))
        
        fun Introduced(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("Introduced")), layout.select(groupElement("Introduced")).byteSize())
        
        val Deprecated_VH: VarHandle = layout.varHandle(groupElement("Deprecated"))
        
        fun Deprecated(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("Deprecated")), layout.select(groupElement("Deprecated")).byteSize())
        
        val Obsoleted_VH: VarHandle = layout.varHandle(groupElement("Obsoleted"))
        
        fun Obsoleted(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("Obsoleted")), layout.select(groupElement("Obsoleted")).byteSize())
        
        val Unavailable_VH: VarHandle = layout.varHandle(groupElement("Unavailable"))
        
        @Suppress("UNCHECKED_CAST")
        fun Unavailable(segment: MemorySegment): Int =
            Unavailable_VH.get(segment, 0L) as Int
        
        fun Unavailable(segment: MemorySegment, value: Int) =
            Unavailable_VH.set(segment, 0L, value)
        
        val Message_VH: VarHandle = layout.varHandle(groupElement("Message"))
        
        fun Message(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("Message")), layout.select(groupElement("Message")).byteSize())
    }
}

/**
 * {@snippet lang=c : clang_getCursorPlatformAvailability Int(typedef CXCursor = Declared(CXCursor),(Int)*,(typedef CXString = Declared(CXString))*,(Int)*,(typedef CXString = Declared(CXString))*,(typedef CXPlatformAvailability = Declared(CXPlatformAvailability))*,Int)
 */
private val clang_getCursorPlatformAvailability_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_getCursorPlatformAvailability_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorPlatformAvailability")
private val clang_getCursorPlatformAvailability_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorPlatformAvailability_ADDR, clang_getCursorPlatformAvailability_DESC)

fun clang_getCursorPlatformAvailability(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment, arg5: MemorySegment, arg6: Int): Int {
    try {
        return clang_getCursorPlatformAvailability_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_disposeCXPlatformAvailability Void((typedef CXPlatformAvailability = Declared(CXPlatformAvailability))*)
 */
private val clang_disposeCXPlatformAvailability_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val clang_disposeCXPlatformAvailability_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_disposeCXPlatformAvailability")
private val clang_disposeCXPlatformAvailability_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_disposeCXPlatformAvailability_ADDR, clang_disposeCXPlatformAvailability_DESC)

fun clang_disposeCXPlatformAvailability(arg0: MemorySegment): Unit {
    try {
        clang_disposeCXPlatformAvailability_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getVarDeclInitializer typedef CXCursor = Declared(CXCursor)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_getVarDeclInitializer_DESC: FunctionDescriptor = FunctionDescriptor.of(CXCursor.layout, CXCursor.layout)
private val clang_Cursor_getVarDeclInitializer_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getVarDeclInitializer")
private val clang_Cursor_getVarDeclInitializer_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getVarDeclInitializer_ADDR, clang_Cursor_getVarDeclInitializer_DESC)

fun clang_Cursor_getVarDeclInitializer(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_Cursor_getVarDeclInitializer_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_hasVarDeclGlobalStorage Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_hasVarDeclGlobalStorage_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_hasVarDeclGlobalStorage_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_hasVarDeclGlobalStorage")
private val clang_Cursor_hasVarDeclGlobalStorage_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_hasVarDeclGlobalStorage_ADDR, clang_Cursor_hasVarDeclGlobalStorage_DESC)

fun clang_Cursor_hasVarDeclGlobalStorage(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_hasVarDeclGlobalStorage_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_hasVarDeclExternalStorage Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_hasVarDeclExternalStorage_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_hasVarDeclExternalStorage_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_hasVarDeclExternalStorage")
private val clang_Cursor_hasVarDeclExternalStorage_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_hasVarDeclExternalStorage_ADDR, clang_Cursor_hasVarDeclExternalStorage_DESC)

fun clang_Cursor_hasVarDeclExternalStorage(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_hasVarDeclExternalStorage_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXLanguage_Invalid 0
 */
fun CXLanguage_Invalid(): Int = 0

/**
 * {@snippet lang=c : #define CXLanguage_C 1
 */
fun CXLanguage_C(): Int = 1

/**
 * {@snippet lang=c : #define CXLanguage_ObjC 2
 */
fun CXLanguage_ObjC(): Int = 2

/**
 * {@snippet lang=c : #define CXLanguage_CPlusPlus 3
 */
fun CXLanguage_CPlusPlus(): Int = 3

/**
 * {@snippet lang=c : clang_getCursorLanguage Declared(CXLanguageKind)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getCursorLanguage_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_getCursorLanguage_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorLanguage")
private val clang_getCursorLanguage_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorLanguage_ADDR, clang_getCursorLanguage_DESC)

fun clang_getCursorLanguage(arg0: MemorySegment): Int {
    try {
        return clang_getCursorLanguage_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXTLS_None 0
 */
fun CXTLS_None(): Int = 0

/**
 * {@snippet lang=c : #define CXTLS_Dynamic 1
 */
fun CXTLS_Dynamic(): Int = 1

/**
 * {@snippet lang=c : #define CXTLS_Static 2
 */
fun CXTLS_Static(): Int = 2

/**
 * {@snippet lang=c : clang_getCursorTLSKind Declared(CXTLSKind)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getCursorTLSKind_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_getCursorTLSKind_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorTLSKind")
private val clang_getCursorTLSKind_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorTLSKind_ADDR, clang_getCursorTLSKind_DESC)

fun clang_getCursorTLSKind(arg0: MemorySegment): Int {
    try {
        return clang_getCursorTLSKind_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getTranslationUnit typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_getTranslationUnit_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, CXCursor.layout)
private val clang_Cursor_getTranslationUnit_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getTranslationUnit")
private val clang_Cursor_getTranslationUnit_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getTranslationUnit_ADDR, clang_Cursor_getTranslationUnit_DESC)

fun clang_Cursor_getTranslationUnit(arg0: MemorySegment): MemorySegment {
    try {
        return clang_Cursor_getTranslationUnit_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : STRUCT CXCursorSetImpl
 */
class CXCursorSetImpl {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
        ).withName("CXCursorSetImpl")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
    }
}

/**
 * {@snippet lang=c : typedef (Declared(CXCursorSetImpl))* CXCursorSet;}
 */
typealias CXCursorSet = MemorySegment?

/**
 * {@snippet lang=c : clang_createCXCursorSet typedef CXCursorSet = (Declared(CXCursorSetImpl))*()
 */
private val clang_createCXCursorSet_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val clang_createCXCursorSet_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_createCXCursorSet")
private val clang_createCXCursorSet_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_createCXCursorSet_ADDR, clang_createCXCursorSet_DESC)

fun clang_createCXCursorSet(): MemorySegment {
    try {
        return clang_createCXCursorSet_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_disposeCXCursorSet Void(typedef CXCursorSet = (Declared(CXCursorSetImpl))*)
 */
private val clang_disposeCXCursorSet_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val clang_disposeCXCursorSet_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_disposeCXCursorSet")
private val clang_disposeCXCursorSet_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_disposeCXCursorSet_ADDR, clang_disposeCXCursorSet_DESC)

fun clang_disposeCXCursorSet(arg0: MemorySegment): Unit {
    try {
        clang_disposeCXCursorSet_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_CXCursorSet_contains UNSIGNED = Int(typedef CXCursorSet = (Declared(CXCursorSetImpl))*,typedef CXCursor = Declared(CXCursor))
 */
private val clang_CXCursorSet_contains_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, CXCursor.layout)
private val clang_CXCursorSet_contains_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_CXCursorSet_contains")
private val clang_CXCursorSet_contains_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_CXCursorSet_contains_ADDR, clang_CXCursorSet_contains_DESC)

fun clang_CXCursorSet_contains(arg0: MemorySegment, arg1: MemorySegment): Int {
    try {
        return clang_CXCursorSet_contains_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_CXCursorSet_insert UNSIGNED = Int(typedef CXCursorSet = (Declared(CXCursorSetImpl))*,typedef CXCursor = Declared(CXCursor))
 */
private val clang_CXCursorSet_insert_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, CXCursor.layout)
private val clang_CXCursorSet_insert_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_CXCursorSet_insert")
private val clang_CXCursorSet_insert_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_CXCursorSet_insert_ADDR, clang_CXCursorSet_insert_DESC)

fun clang_CXCursorSet_insert(arg0: MemorySegment, arg1: MemorySegment): Int {
    try {
        return clang_CXCursorSet_insert_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCursorSemanticParent typedef CXCursor = Declared(CXCursor)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getCursorSemanticParent_DESC: FunctionDescriptor = FunctionDescriptor.of(CXCursor.layout, CXCursor.layout)
private val clang_getCursorSemanticParent_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorSemanticParent")
private val clang_getCursorSemanticParent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorSemanticParent_ADDR, clang_getCursorSemanticParent_DESC)

fun clang_getCursorSemanticParent(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getCursorSemanticParent_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCursorLexicalParent typedef CXCursor = Declared(CXCursor)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getCursorLexicalParent_DESC: FunctionDescriptor = FunctionDescriptor.of(CXCursor.layout, CXCursor.layout)
private val clang_getCursorLexicalParent_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorLexicalParent")
private val clang_getCursorLexicalParent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorLexicalParent_ADDR, clang_getCursorLexicalParent_DESC)

fun clang_getCursorLexicalParent(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getCursorLexicalParent_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getOverriddenCursors Void(typedef CXCursor = Declared(CXCursor),((typedef CXCursor = Declared(CXCursor))*)*,(UNSIGNED = Int)*)
 */
private val clang_getOverriddenCursors_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CXCursor.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_getOverriddenCursors_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getOverriddenCursors")
private val clang_getOverriddenCursors_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getOverriddenCursors_ADDR, clang_getOverriddenCursors_DESC)

fun clang_getOverriddenCursors(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        clang_getOverriddenCursors_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_disposeOverriddenCursors Void((typedef CXCursor = Declared(CXCursor))*)
 */
private val clang_disposeOverriddenCursors_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val clang_disposeOverriddenCursors_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_disposeOverriddenCursors")
private val clang_disposeOverriddenCursors_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_disposeOverriddenCursors_ADDR, clang_disposeOverriddenCursors_DESC)

fun clang_disposeOverriddenCursors(arg0: MemorySegment): Unit {
    try {
        clang_disposeOverriddenCursors_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getIncludedFile typedef CXFile = (Void)*(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getIncludedFile_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, CXCursor.layout)
private val clang_getIncludedFile_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getIncludedFile")
private val clang_getIncludedFile_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getIncludedFile_ADDR, clang_getIncludedFile_DESC)

fun clang_getIncludedFile(arg0: MemorySegment): MemorySegment {
    try {
        return clang_getIncludedFile_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCursor typedef CXCursor = Declared(CXCursor)(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*,typedef CXSourceLocation = Declared(CXSourceLocation))
 */
private val clang_getCursor_DESC: FunctionDescriptor = FunctionDescriptor.of(CXCursor.layout, ValueLayout.ADDRESS, CXSourceLocation.layout)
private val clang_getCursor_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursor")
private val clang_getCursor_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursor_ADDR, clang_getCursor_DESC)

fun clang_getCursor(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return clang_getCursor_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCursorLocation typedef CXSourceLocation = Declared(CXSourceLocation)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getCursorLocation_DESC: FunctionDescriptor = FunctionDescriptor.of(CXSourceLocation.layout, CXCursor.layout)
private val clang_getCursorLocation_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorLocation")
private val clang_getCursorLocation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorLocation_ADDR, clang_getCursorLocation_DESC)

fun clang_getCursorLocation(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getCursorLocation_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCursorExtent typedef CXSourceRange = Declared(CXSourceRange)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getCursorExtent_DESC: FunctionDescriptor = FunctionDescriptor.of(CXSourceRange.layout, CXCursor.layout)
private val clang_getCursorExtent_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorExtent")
private val clang_getCursorExtent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorExtent_ADDR, clang_getCursorExtent_DESC)

fun clang_getCursorExtent(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getCursorExtent_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXType_Invalid 0
 */
fun CXType_Invalid(): Int = 0

/**
 * {@snippet lang=c : #define CXType_Unexposed 1
 */
fun CXType_Unexposed(): Int = 1

/**
 * {@snippet lang=c : #define CXType_Void 2
 */
fun CXType_Void(): Int = 2

/**
 * {@snippet lang=c : #define CXType_Bool 3
 */
fun CXType_Bool(): Int = 3

/**
 * {@snippet lang=c : #define CXType_Char_U 4
 */
fun CXType_Char_U(): Int = 4

/**
 * {@snippet lang=c : #define CXType_UChar 5
 */
fun CXType_UChar(): Int = 5

/**
 * {@snippet lang=c : #define CXType_Char16 6
 */
fun CXType_Char16(): Int = 6

/**
 * {@snippet lang=c : #define CXType_Char32 7
 */
fun CXType_Char32(): Int = 7

/**
 * {@snippet lang=c : #define CXType_UShort 8
 */
fun CXType_UShort(): Int = 8

/**
 * {@snippet lang=c : #define CXType_UInt 9
 */
fun CXType_UInt(): Int = 9

/**
 * {@snippet lang=c : #define CXType_ULong 10
 */
fun CXType_ULong(): Int = 10

/**
 * {@snippet lang=c : #define CXType_ULongLong 11
 */
fun CXType_ULongLong(): Int = 11

/**
 * {@snippet lang=c : #define CXType_UInt128 12
 */
fun CXType_UInt128(): Int = 12

/**
 * {@snippet lang=c : #define CXType_Char_S 13
 */
fun CXType_Char_S(): Int = 13

/**
 * {@snippet lang=c : #define CXType_SChar 14
 */
fun CXType_SChar(): Int = 14

/**
 * {@snippet lang=c : #define CXType_WChar 15
 */
fun CXType_WChar(): Int = 15

/**
 * {@snippet lang=c : #define CXType_Short 16
 */
fun CXType_Short(): Int = 16

/**
 * {@snippet lang=c : #define CXType_Int 17
 */
fun CXType_Int(): Int = 17

/**
 * {@snippet lang=c : #define CXType_Long 18
 */
fun CXType_Long(): Int = 18

/**
 * {@snippet lang=c : #define CXType_LongLong 19
 */
fun CXType_LongLong(): Int = 19

/**
 * {@snippet lang=c : #define CXType_Int128 20
 */
fun CXType_Int128(): Int = 20

/**
 * {@snippet lang=c : #define CXType_Float 21
 */
fun CXType_Float(): Int = 21

/**
 * {@snippet lang=c : #define CXType_Double 22
 */
fun CXType_Double(): Int = 22

/**
 * {@snippet lang=c : #define CXType_LongDouble 23
 */
fun CXType_LongDouble(): Int = 23

/**
 * {@snippet lang=c : #define CXType_NullPtr 24
 */
fun CXType_NullPtr(): Int = 24

/**
 * {@snippet lang=c : #define CXType_Overload 25
 */
fun CXType_Overload(): Int = 25

/**
 * {@snippet lang=c : #define CXType_Dependent 26
 */
fun CXType_Dependent(): Int = 26

/**
 * {@snippet lang=c : #define CXType_ObjCId 27
 */
fun CXType_ObjCId(): Int = 27

/**
 * {@snippet lang=c : #define CXType_ObjCClass 28
 */
fun CXType_ObjCClass(): Int = 28

/**
 * {@snippet lang=c : #define CXType_ObjCSel 29
 */
fun CXType_ObjCSel(): Int = 29

/**
 * {@snippet lang=c : #define CXType_Float128 30
 */
fun CXType_Float128(): Int = 30

/**
 * {@snippet lang=c : #define CXType_Half 31
 */
fun CXType_Half(): Int = 31

/**
 * {@snippet lang=c : #define CXType_Float16 32
 */
fun CXType_Float16(): Int = 32

/**
 * {@snippet lang=c : #define CXType_ShortAccum 33
 */
fun CXType_ShortAccum(): Int = 33

/**
 * {@snippet lang=c : #define CXType_Accum 34
 */
fun CXType_Accum(): Int = 34

/**
 * {@snippet lang=c : #define CXType_LongAccum 35
 */
fun CXType_LongAccum(): Int = 35

/**
 * {@snippet lang=c : #define CXType_UShortAccum 36
 */
fun CXType_UShortAccum(): Int = 36

/**
 * {@snippet lang=c : #define CXType_UAccum 37
 */
fun CXType_UAccum(): Int = 37

/**
 * {@snippet lang=c : #define CXType_ULongAccum 38
 */
fun CXType_ULongAccum(): Int = 38

/**
 * {@snippet lang=c : #define CXType_BFloat16 39
 */
fun CXType_BFloat16(): Int = 39

/**
 * {@snippet lang=c : #define CXType_Ibm128 40
 */
fun CXType_Ibm128(): Int = 40

/**
 * {@snippet lang=c : #define CXType_FirstBuiltin 2
 */
fun CXType_FirstBuiltin(): Int = 2

/**
 * {@snippet lang=c : #define CXType_LastBuiltin 40
 */
fun CXType_LastBuiltin(): Int = 40

/**
 * {@snippet lang=c : #define CXType_Complex 100
 */
fun CXType_Complex(): Int = 100

/**
 * {@snippet lang=c : #define CXType_Pointer 101
 */
fun CXType_Pointer(): Int = 101

/**
 * {@snippet lang=c : #define CXType_BlockPointer 102
 */
fun CXType_BlockPointer(): Int = 102

/**
 * {@snippet lang=c : #define CXType_LValueReference 103
 */
fun CXType_LValueReference(): Int = 103

/**
 * {@snippet lang=c : #define CXType_RValueReference 104
 */
fun CXType_RValueReference(): Int = 104

/**
 * {@snippet lang=c : #define CXType_Record 105
 */
fun CXType_Record(): Int = 105

/**
 * {@snippet lang=c : #define CXType_Enum 106
 */
fun CXType_Enum(): Int = 106

/**
 * {@snippet lang=c : #define CXType_Typedef 107
 */
fun CXType_Typedef(): Int = 107

/**
 * {@snippet lang=c : #define CXType_ObjCInterface 108
 */
fun CXType_ObjCInterface(): Int = 108

/**
 * {@snippet lang=c : #define CXType_ObjCObjectPointer 109
 */
fun CXType_ObjCObjectPointer(): Int = 109

/**
 * {@snippet lang=c : #define CXType_FunctionNoProto 110
 */
fun CXType_FunctionNoProto(): Int = 110

/**
 * {@snippet lang=c : #define CXType_FunctionProto 111
 */
fun CXType_FunctionProto(): Int = 111

/**
 * {@snippet lang=c : #define CXType_ConstantArray 112
 */
fun CXType_ConstantArray(): Int = 112

/**
 * {@snippet lang=c : #define CXType_Vector 113
 */
fun CXType_Vector(): Int = 113

/**
 * {@snippet lang=c : #define CXType_IncompleteArray 114
 */
fun CXType_IncompleteArray(): Int = 114

/**
 * {@snippet lang=c : #define CXType_VariableArray 115
 */
fun CXType_VariableArray(): Int = 115

/**
 * {@snippet lang=c : #define CXType_DependentSizedArray 116
 */
fun CXType_DependentSizedArray(): Int = 116

/**
 * {@snippet lang=c : #define CXType_MemberPointer 117
 */
fun CXType_MemberPointer(): Int = 117

/**
 * {@snippet lang=c : #define CXType_Auto 118
 */
fun CXType_Auto(): Int = 118

/**
 * {@snippet lang=c : #define CXType_Elaborated 119
 */
fun CXType_Elaborated(): Int = 119

/**
 * {@snippet lang=c : #define CXType_Pipe 120
 */
fun CXType_Pipe(): Int = 120

/**
 * {@snippet lang=c : #define CXType_OCLImage1dRO 121
 */
fun CXType_OCLImage1dRO(): Int = 121

/**
 * {@snippet lang=c : #define CXType_OCLImage1dArrayRO 122
 */
fun CXType_OCLImage1dArrayRO(): Int = 122

/**
 * {@snippet lang=c : #define CXType_OCLImage1dBufferRO 123
 */
fun CXType_OCLImage1dBufferRO(): Int = 123

/**
 * {@snippet lang=c : #define CXType_OCLImage2dRO 124
 */
fun CXType_OCLImage2dRO(): Int = 124

/**
 * {@snippet lang=c : #define CXType_OCLImage2dArrayRO 125
 */
fun CXType_OCLImage2dArrayRO(): Int = 125

/**
 * {@snippet lang=c : #define CXType_OCLImage2dDepthRO 126
 */
fun CXType_OCLImage2dDepthRO(): Int = 126

/**
 * {@snippet lang=c : #define CXType_OCLImage2dArrayDepthRO 127
 */
fun CXType_OCLImage2dArrayDepthRO(): Int = 127

/**
 * {@snippet lang=c : #define CXType_OCLImage2dMSAARO 128
 */
fun CXType_OCLImage2dMSAARO(): Int = 128

/**
 * {@snippet lang=c : #define CXType_OCLImage2dArrayMSAARO 129
 */
fun CXType_OCLImage2dArrayMSAARO(): Int = 129

/**
 * {@snippet lang=c : #define CXType_OCLImage2dMSAADepthRO 130
 */
fun CXType_OCLImage2dMSAADepthRO(): Int = 130

/**
 * {@snippet lang=c : #define CXType_OCLImage2dArrayMSAADepthRO 131
 */
fun CXType_OCLImage2dArrayMSAADepthRO(): Int = 131

/**
 * {@snippet lang=c : #define CXType_OCLImage3dRO 132
 */
fun CXType_OCLImage3dRO(): Int = 132

/**
 * {@snippet lang=c : #define CXType_OCLImage1dWO 133
 */
fun CXType_OCLImage1dWO(): Int = 133

/**
 * {@snippet lang=c : #define CXType_OCLImage1dArrayWO 134
 */
fun CXType_OCLImage1dArrayWO(): Int = 134

/**
 * {@snippet lang=c : #define CXType_OCLImage1dBufferWO 135
 */
fun CXType_OCLImage1dBufferWO(): Int = 135

/**
 * {@snippet lang=c : #define CXType_OCLImage2dWO 136
 */
fun CXType_OCLImage2dWO(): Int = 136

/**
 * {@snippet lang=c : #define CXType_OCLImage2dArrayWO 137
 */
fun CXType_OCLImage2dArrayWO(): Int = 137

/**
 * {@snippet lang=c : #define CXType_OCLImage2dDepthWO 138
 */
fun CXType_OCLImage2dDepthWO(): Int = 138

/**
 * {@snippet lang=c : #define CXType_OCLImage2dArrayDepthWO 139
 */
fun CXType_OCLImage2dArrayDepthWO(): Int = 139

/**
 * {@snippet lang=c : #define CXType_OCLImage2dMSAAWO 140
 */
fun CXType_OCLImage2dMSAAWO(): Int = 140

/**
 * {@snippet lang=c : #define CXType_OCLImage2dArrayMSAAWO 141
 */
fun CXType_OCLImage2dArrayMSAAWO(): Int = 141

/**
 * {@snippet lang=c : #define CXType_OCLImage2dMSAADepthWO 142
 */
fun CXType_OCLImage2dMSAADepthWO(): Int = 142

/**
 * {@snippet lang=c : #define CXType_OCLImage2dArrayMSAADepthWO 143
 */
fun CXType_OCLImage2dArrayMSAADepthWO(): Int = 143

/**
 * {@snippet lang=c : #define CXType_OCLImage3dWO 144
 */
fun CXType_OCLImage3dWO(): Int = 144

/**
 * {@snippet lang=c : #define CXType_OCLImage1dRW 145
 */
fun CXType_OCLImage1dRW(): Int = 145

/**
 * {@snippet lang=c : #define CXType_OCLImage1dArrayRW 146
 */
fun CXType_OCLImage1dArrayRW(): Int = 146

/**
 * {@snippet lang=c : #define CXType_OCLImage1dBufferRW 147
 */
fun CXType_OCLImage1dBufferRW(): Int = 147

/**
 * {@snippet lang=c : #define CXType_OCLImage2dRW 148
 */
fun CXType_OCLImage2dRW(): Int = 148

/**
 * {@snippet lang=c : #define CXType_OCLImage2dArrayRW 149
 */
fun CXType_OCLImage2dArrayRW(): Int = 149

/**
 * {@snippet lang=c : #define CXType_OCLImage2dDepthRW 150
 */
fun CXType_OCLImage2dDepthRW(): Int = 150

/**
 * {@snippet lang=c : #define CXType_OCLImage2dArrayDepthRW 151
 */
fun CXType_OCLImage2dArrayDepthRW(): Int = 151

/**
 * {@snippet lang=c : #define CXType_OCLImage2dMSAARW 152
 */
fun CXType_OCLImage2dMSAARW(): Int = 152

/**
 * {@snippet lang=c : #define CXType_OCLImage2dArrayMSAARW 153
 */
fun CXType_OCLImage2dArrayMSAARW(): Int = 153

/**
 * {@snippet lang=c : #define CXType_OCLImage2dMSAADepthRW 154
 */
fun CXType_OCLImage2dMSAADepthRW(): Int = 154

/**
 * {@snippet lang=c : #define CXType_OCLImage2dArrayMSAADepthRW 155
 */
fun CXType_OCLImage2dArrayMSAADepthRW(): Int = 155

/**
 * {@snippet lang=c : #define CXType_OCLImage3dRW 156
 */
fun CXType_OCLImage3dRW(): Int = 156

/**
 * {@snippet lang=c : #define CXType_OCLSampler 157
 */
fun CXType_OCLSampler(): Int = 157

/**
 * {@snippet lang=c : #define CXType_OCLEvent 158
 */
fun CXType_OCLEvent(): Int = 158

/**
 * {@snippet lang=c : #define CXType_OCLQueue 159
 */
fun CXType_OCLQueue(): Int = 159

/**
 * {@snippet lang=c : #define CXType_OCLReserveID 160
 */
fun CXType_OCLReserveID(): Int = 160

/**
 * {@snippet lang=c : #define CXType_ObjCObject 161
 */
fun CXType_ObjCObject(): Int = 161

/**
 * {@snippet lang=c : #define CXType_ObjCTypeParam 162
 */
fun CXType_ObjCTypeParam(): Int = 162

/**
 * {@snippet lang=c : #define CXType_Attributed 163
 */
fun CXType_Attributed(): Int = 163

/**
 * {@snippet lang=c : #define CXType_OCLIntelSubgroupAVCMcePayload 164
 */
fun CXType_OCLIntelSubgroupAVCMcePayload(): Int = 164

/**
 * {@snippet lang=c : #define CXType_OCLIntelSubgroupAVCImePayload 165
 */
fun CXType_OCLIntelSubgroupAVCImePayload(): Int = 165

/**
 * {@snippet lang=c : #define CXType_OCLIntelSubgroupAVCRefPayload 166
 */
fun CXType_OCLIntelSubgroupAVCRefPayload(): Int = 166

/**
 * {@snippet lang=c : #define CXType_OCLIntelSubgroupAVCSicPayload 167
 */
fun CXType_OCLIntelSubgroupAVCSicPayload(): Int = 167

/**
 * {@snippet lang=c : #define CXType_OCLIntelSubgroupAVCMceResult 168
 */
fun CXType_OCLIntelSubgroupAVCMceResult(): Int = 168

/**
 * {@snippet lang=c : #define CXType_OCLIntelSubgroupAVCImeResult 169
 */
fun CXType_OCLIntelSubgroupAVCImeResult(): Int = 169

/**
 * {@snippet lang=c : #define CXType_OCLIntelSubgroupAVCRefResult 170
 */
fun CXType_OCLIntelSubgroupAVCRefResult(): Int = 170

/**
 * {@snippet lang=c : #define CXType_OCLIntelSubgroupAVCSicResult 171
 */
fun CXType_OCLIntelSubgroupAVCSicResult(): Int = 171

/**
 * {@snippet lang=c : #define CXType_OCLIntelSubgroupAVCImeResultSingleReferenceStreamout 172
 */
fun CXType_OCLIntelSubgroupAVCImeResultSingleReferenceStreamout(): Int = 172

/**
 * {@snippet lang=c : #define CXType_OCLIntelSubgroupAVCImeResultDualReferenceStreamout 173
 */
fun CXType_OCLIntelSubgroupAVCImeResultDualReferenceStreamout(): Int = 173

/**
 * {@snippet lang=c : #define CXType_OCLIntelSubgroupAVCImeSingleReferenceStreamin 174
 */
fun CXType_OCLIntelSubgroupAVCImeSingleReferenceStreamin(): Int = 174

/**
 * {@snippet lang=c : #define CXType_OCLIntelSubgroupAVCImeDualReferenceStreamin 175
 */
fun CXType_OCLIntelSubgroupAVCImeDualReferenceStreamin(): Int = 175

/**
 * {@snippet lang=c : #define CXType_OCLIntelSubgroupAVCImeResultSingleRefStreamout 172
 */
fun CXType_OCLIntelSubgroupAVCImeResultSingleRefStreamout(): Int = 172

/**
 * {@snippet lang=c : #define CXType_OCLIntelSubgroupAVCImeResultDualRefStreamout 173
 */
fun CXType_OCLIntelSubgroupAVCImeResultDualRefStreamout(): Int = 173

/**
 * {@snippet lang=c : #define CXType_OCLIntelSubgroupAVCImeSingleRefStreamin 174
 */
fun CXType_OCLIntelSubgroupAVCImeSingleRefStreamin(): Int = 174

/**
 * {@snippet lang=c : #define CXType_OCLIntelSubgroupAVCImeDualRefStreamin 175
 */
fun CXType_OCLIntelSubgroupAVCImeDualRefStreamin(): Int = 175

/**
 * {@snippet lang=c : #define CXType_ExtVector 176
 */
fun CXType_ExtVector(): Int = 176

/**
 * {@snippet lang=c : #define CXType_Atomic 177
 */
fun CXType_Atomic(): Int = 177

/**
 * {@snippet lang=c : #define CXType_BTFTagAttributed 178
 */
fun CXType_BTFTagAttributed(): Int = 178

/**
 * {@snippet lang=c : #define CXType_HLSLResource 179
 */
fun CXType_HLSLResource(): Int = 179

/**
 * {@snippet lang=c : #define CXType_HLSLAttributedResource 180
 */
fun CXType_HLSLAttributedResource(): Int = 180

/**
 * {@snippet lang=c : #define CXType_HLSLInlineSpirv 181
 */
fun CXType_HLSLInlineSpirv(): Int = 181

/**
 * {@snippet lang=c : #define CXCallingConv_Default 0
 */
fun CXCallingConv_Default(): Int = 0

/**
 * {@snippet lang=c : #define CXCallingConv_C 1
 */
fun CXCallingConv_C(): Int = 1

/**
 * {@snippet lang=c : #define CXCallingConv_X86StdCall 2
 */
fun CXCallingConv_X86StdCall(): Int = 2

/**
 * {@snippet lang=c : #define CXCallingConv_X86FastCall 3
 */
fun CXCallingConv_X86FastCall(): Int = 3

/**
 * {@snippet lang=c : #define CXCallingConv_X86ThisCall 4
 */
fun CXCallingConv_X86ThisCall(): Int = 4

/**
 * {@snippet lang=c : #define CXCallingConv_X86Pascal 5
 */
fun CXCallingConv_X86Pascal(): Int = 5

/**
 * {@snippet lang=c : #define CXCallingConv_AAPCS 6
 */
fun CXCallingConv_AAPCS(): Int = 6

/**
 * {@snippet lang=c : #define CXCallingConv_AAPCS_VFP 7
 */
fun CXCallingConv_AAPCS_VFP(): Int = 7

/**
 * {@snippet lang=c : #define CXCallingConv_X86RegCall 8
 */
fun CXCallingConv_X86RegCall(): Int = 8

/**
 * {@snippet lang=c : #define CXCallingConv_IntelOclBicc 9
 */
fun CXCallingConv_IntelOclBicc(): Int = 9

/**
 * {@snippet lang=c : #define CXCallingConv_Win64 10
 */
fun CXCallingConv_Win64(): Int = 10

/**
 * {@snippet lang=c : #define CXCallingConv_X86_64Win64 10
 */
fun CXCallingConv_X86_64Win64(): Int = 10

/**
 * {@snippet lang=c : #define CXCallingConv_X86_64SysV 11
 */
fun CXCallingConv_X86_64SysV(): Int = 11

/**
 * {@snippet lang=c : #define CXCallingConv_X86VectorCall 12
 */
fun CXCallingConv_X86VectorCall(): Int = 12

/**
 * {@snippet lang=c : #define CXCallingConv_Swift 13
 */
fun CXCallingConv_Swift(): Int = 13

/**
 * {@snippet lang=c : #define CXCallingConv_PreserveMost 14
 */
fun CXCallingConv_PreserveMost(): Int = 14

/**
 * {@snippet lang=c : #define CXCallingConv_PreserveAll 15
 */
fun CXCallingConv_PreserveAll(): Int = 15

/**
 * {@snippet lang=c : #define CXCallingConv_AArch64VectorCall 16
 */
fun CXCallingConv_AArch64VectorCall(): Int = 16

/**
 * {@snippet lang=c : #define CXCallingConv_SwiftAsync 17
 */
fun CXCallingConv_SwiftAsync(): Int = 17

/**
 * {@snippet lang=c : #define CXCallingConv_AArch64SVEPCS 18
 */
fun CXCallingConv_AArch64SVEPCS(): Int = 18

/**
 * {@snippet lang=c : #define CXCallingConv_M68kRTD 19
 */
fun CXCallingConv_M68kRTD(): Int = 19

/**
 * {@snippet lang=c : #define CXCallingConv_PreserveNone 20
 */
fun CXCallingConv_PreserveNone(): Int = 20

/**
 * {@snippet lang=c : #define CXCallingConv_RISCVVectorCall 21
 */
fun CXCallingConv_RISCVVectorCall(): Int = 21

/**
 * {@snippet lang=c : #define CXCallingConv_RISCVVLSCall_32 22
 */
fun CXCallingConv_RISCVVLSCall_32(): Int = 22

/**
 * {@snippet lang=c : #define CXCallingConv_RISCVVLSCall_64 23
 */
fun CXCallingConv_RISCVVLSCall_64(): Int = 23

/**
 * {@snippet lang=c : #define CXCallingConv_RISCVVLSCall_128 24
 */
fun CXCallingConv_RISCVVLSCall_128(): Int = 24

/**
 * {@snippet lang=c : #define CXCallingConv_RISCVVLSCall_256 25
 */
fun CXCallingConv_RISCVVLSCall_256(): Int = 25

/**
 * {@snippet lang=c : #define CXCallingConv_RISCVVLSCall_512 26
 */
fun CXCallingConv_RISCVVLSCall_512(): Int = 26

/**
 * {@snippet lang=c : #define CXCallingConv_RISCVVLSCall_1024 27
 */
fun CXCallingConv_RISCVVLSCall_1024(): Int = 27

/**
 * {@snippet lang=c : #define CXCallingConv_RISCVVLSCall_2048 28
 */
fun CXCallingConv_RISCVVLSCall_2048(): Int = 28

/**
 * {@snippet lang=c : #define CXCallingConv_RISCVVLSCall_4096 29
 */
fun CXCallingConv_RISCVVLSCall_4096(): Int = 29

/**
 * {@snippet lang=c : #define CXCallingConv_RISCVVLSCall_8192 30
 */
fun CXCallingConv_RISCVVLSCall_8192(): Int = 30

/**
 * {@snippet lang=c : #define CXCallingConv_RISCVVLSCall_16384 31
 */
fun CXCallingConv_RISCVVLSCall_16384(): Int = 31

/**
 * {@snippet lang=c : #define CXCallingConv_RISCVVLSCall_32768 32
 */
fun CXCallingConv_RISCVVLSCall_32768(): Int = 32

/**
 * {@snippet lang=c : #define CXCallingConv_RISCVVLSCall_65536 33
 */
fun CXCallingConv_RISCVVLSCall_65536(): Int = 33

/**
 * {@snippet lang=c : #define CXCallingConv_Invalid 100
 */
fun CXCallingConv_Invalid(): Int = 100

/**
 * {@snippet lang=c : #define CXCallingConv_Unexposed 200
 */
fun CXCallingConv_Unexposed(): Int = 200

/**
 * {@snippet lang=c : STRUCT CXType
 */
class CXType {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("kind"),
            MemoryLayout.paddingLayout(4),
            MemoryLayout.sequenceLayout(2, ValueLayout.ADDRESS).withName("data")
        ).withName("CXType")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val kind_VH: VarHandle = layout.varHandle(groupElement("kind"))
        
        @Suppress("UNCHECKED_CAST")
        fun kind(segment: MemorySegment): Int =
            kind_VH.get(segment, 0L) as Int
        
        fun kind(segment: MemorySegment, value: Int) =
            kind_VH.set(segment, 0L, value)
        
        
        fun data_(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("data")), layout.select(groupElement("data")).byteSize())
    }
}

/**
 * {@snippet lang=c : clang_getCursorType typedef CXType = Declared(CXType)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getCursorType_DESC: FunctionDescriptor = FunctionDescriptor.of(CXType.layout, CXCursor.layout)
private val clang_getCursorType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorType")
private val clang_getCursorType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorType_ADDR, clang_getCursorType_DESC)

fun clang_getCursorType(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getCursorType_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getTypeSpelling typedef CXString = Declared(CXString)(typedef CXType = Declared(CXType))
 */
private val clang_getTypeSpelling_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, CXType.layout)
private val clang_getTypeSpelling_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getTypeSpelling")
private val clang_getTypeSpelling_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getTypeSpelling_ADDR, clang_getTypeSpelling_DESC)

fun clang_getTypeSpelling(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getTypeSpelling_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getTypedefDeclUnderlyingType typedef CXType = Declared(CXType)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getTypedefDeclUnderlyingType_DESC: FunctionDescriptor = FunctionDescriptor.of(CXType.layout, CXCursor.layout)
private val clang_getTypedefDeclUnderlyingType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getTypedefDeclUnderlyingType")
private val clang_getTypedefDeclUnderlyingType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getTypedefDeclUnderlyingType_ADDR, clang_getTypedefDeclUnderlyingType_DESC)

fun clang_getTypedefDeclUnderlyingType(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getTypedefDeclUnderlyingType_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getEnumDeclIntegerType typedef CXType = Declared(CXType)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getEnumDeclIntegerType_DESC: FunctionDescriptor = FunctionDescriptor.of(CXType.layout, CXCursor.layout)
private val clang_getEnumDeclIntegerType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getEnumDeclIntegerType")
private val clang_getEnumDeclIntegerType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getEnumDeclIntegerType_ADDR, clang_getEnumDeclIntegerType_DESC)

fun clang_getEnumDeclIntegerType(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getEnumDeclIntegerType_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getEnumConstantDeclValue LongLong(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getEnumConstantDeclValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, CXCursor.layout)
private val clang_getEnumConstantDeclValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getEnumConstantDeclValue")
private val clang_getEnumConstantDeclValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getEnumConstantDeclValue_ADDR, clang_getEnumConstantDeclValue_DESC)

fun clang_getEnumConstantDeclValue(arg0: MemorySegment): Long {
    try {
        return clang_getEnumConstantDeclValue_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getEnumConstantDeclUnsignedValue UNSIGNED = LongLong(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getEnumConstantDeclUnsignedValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, CXCursor.layout)
private val clang_getEnumConstantDeclUnsignedValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getEnumConstantDeclUnsignedValue")
private val clang_getEnumConstantDeclUnsignedValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getEnumConstantDeclUnsignedValue_ADDR, clang_getEnumConstantDeclUnsignedValue_DESC)

fun clang_getEnumConstantDeclUnsignedValue(arg0: MemorySegment): Long {
    try {
        return clang_getEnumConstantDeclUnsignedValue_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_isBitField UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_isBitField_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_isBitField_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_isBitField")
private val clang_Cursor_isBitField_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_isBitField_ADDR, clang_Cursor_isBitField_DESC)

fun clang_Cursor_isBitField(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_isBitField_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getFieldDeclBitWidth Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getFieldDeclBitWidth_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_getFieldDeclBitWidth_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getFieldDeclBitWidth")
private val clang_getFieldDeclBitWidth_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getFieldDeclBitWidth_ADDR, clang_getFieldDeclBitWidth_DESC)

fun clang_getFieldDeclBitWidth(arg0: MemorySegment): Int {
    try {
        return clang_getFieldDeclBitWidth_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getNumArguments Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_getNumArguments_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_getNumArguments_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getNumArguments")
private val clang_Cursor_getNumArguments_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getNumArguments_ADDR, clang_Cursor_getNumArguments_DESC)

fun clang_Cursor_getNumArguments(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_getNumArguments_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getArgument typedef CXCursor = Declared(CXCursor)(typedef CXCursor = Declared(CXCursor),UNSIGNED = Int)
 */
private val clang_Cursor_getArgument_DESC: FunctionDescriptor = FunctionDescriptor.of(CXCursor.layout, CXCursor.layout, ValueLayout.JAVA_INT)
private val clang_Cursor_getArgument_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getArgument")
private val clang_Cursor_getArgument_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getArgument_ADDR, clang_Cursor_getArgument_DESC)

fun clang_Cursor_getArgument(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return clang_Cursor_getArgument_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXTemplateArgumentKind_Null 0
 */
fun CXTemplateArgumentKind_Null(): Int = 0

/**
 * {@snippet lang=c : #define CXTemplateArgumentKind_Type 1
 */
fun CXTemplateArgumentKind_Type(): Int = 1

/**
 * {@snippet lang=c : #define CXTemplateArgumentKind_Declaration 2
 */
fun CXTemplateArgumentKind_Declaration(): Int = 2

/**
 * {@snippet lang=c : #define CXTemplateArgumentKind_NullPtr 3
 */
fun CXTemplateArgumentKind_NullPtr(): Int = 3

/**
 * {@snippet lang=c : #define CXTemplateArgumentKind_Integral 4
 */
fun CXTemplateArgumentKind_Integral(): Int = 4

/**
 * {@snippet lang=c : #define CXTemplateArgumentKind_Template 5
 */
fun CXTemplateArgumentKind_Template(): Int = 5

/**
 * {@snippet lang=c : #define CXTemplateArgumentKind_TemplateExpansion 6
 */
fun CXTemplateArgumentKind_TemplateExpansion(): Int = 6

/**
 * {@snippet lang=c : #define CXTemplateArgumentKind_Expression 7
 */
fun CXTemplateArgumentKind_Expression(): Int = 7

/**
 * {@snippet lang=c : #define CXTemplateArgumentKind_Pack 8
 */
fun CXTemplateArgumentKind_Pack(): Int = 8

/**
 * {@snippet lang=c : #define CXTemplateArgumentKind_Invalid 9
 */
fun CXTemplateArgumentKind_Invalid(): Int = 9

/**
 * {@snippet lang=c : clang_Cursor_getNumTemplateArguments Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_getNumTemplateArguments_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_getNumTemplateArguments_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getNumTemplateArguments")
private val clang_Cursor_getNumTemplateArguments_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getNumTemplateArguments_ADDR, clang_Cursor_getNumTemplateArguments_DESC)

fun clang_Cursor_getNumTemplateArguments(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_getNumTemplateArguments_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getTemplateArgumentKind Declared(CXTemplateArgumentKind)(typedef CXCursor = Declared(CXCursor),UNSIGNED = Int)
 */
private val clang_Cursor_getTemplateArgumentKind_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout, ValueLayout.JAVA_INT)
private val clang_Cursor_getTemplateArgumentKind_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getTemplateArgumentKind")
private val clang_Cursor_getTemplateArgumentKind_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getTemplateArgumentKind_ADDR, clang_Cursor_getTemplateArgumentKind_DESC)

fun clang_Cursor_getTemplateArgumentKind(arg0: MemorySegment, arg1: Int): Int {
    try {
        return clang_Cursor_getTemplateArgumentKind_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getTemplateArgumentType typedef CXType = Declared(CXType)(typedef CXCursor = Declared(CXCursor),UNSIGNED = Int)
 */
private val clang_Cursor_getTemplateArgumentType_DESC: FunctionDescriptor = FunctionDescriptor.of(CXType.layout, CXCursor.layout, ValueLayout.JAVA_INT)
private val clang_Cursor_getTemplateArgumentType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getTemplateArgumentType")
private val clang_Cursor_getTemplateArgumentType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getTemplateArgumentType_ADDR, clang_Cursor_getTemplateArgumentType_DESC)

fun clang_Cursor_getTemplateArgumentType(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return clang_Cursor_getTemplateArgumentType_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getTemplateArgumentValue LongLong(typedef CXCursor = Declared(CXCursor),UNSIGNED = Int)
 */
private val clang_Cursor_getTemplateArgumentValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, CXCursor.layout, ValueLayout.JAVA_INT)
private val clang_Cursor_getTemplateArgumentValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getTemplateArgumentValue")
private val clang_Cursor_getTemplateArgumentValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getTemplateArgumentValue_ADDR, clang_Cursor_getTemplateArgumentValue_DESC)

fun clang_Cursor_getTemplateArgumentValue(arg0: MemorySegment, arg1: Int): Long {
    try {
        return clang_Cursor_getTemplateArgumentValue_HANDLE.invokeExact(arg0, arg1) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getTemplateArgumentUnsignedValue UNSIGNED = LongLong(typedef CXCursor = Declared(CXCursor),UNSIGNED = Int)
 */
private val clang_Cursor_getTemplateArgumentUnsignedValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, CXCursor.layout, ValueLayout.JAVA_INT)
private val clang_Cursor_getTemplateArgumentUnsignedValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getTemplateArgumentUnsignedValue")
private val clang_Cursor_getTemplateArgumentUnsignedValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getTemplateArgumentUnsignedValue_ADDR, clang_Cursor_getTemplateArgumentUnsignedValue_DESC)

fun clang_Cursor_getTemplateArgumentUnsignedValue(arg0: MemorySegment, arg1: Int): Long {
    try {
        return clang_Cursor_getTemplateArgumentUnsignedValue_HANDLE.invokeExact(arg0, arg1) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_equalTypes UNSIGNED = Int(typedef CXType = Declared(CXType),typedef CXType = Declared(CXType))
 */
private val clang_equalTypes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXType.layout, CXType.layout)
private val clang_equalTypes_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_equalTypes")
private val clang_equalTypes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_equalTypes_ADDR, clang_equalTypes_DESC)

fun clang_equalTypes(arg0: MemorySegment, arg1: MemorySegment): Int {
    try {
        return clang_equalTypes_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCanonicalType typedef CXType = Declared(CXType)(typedef CXType = Declared(CXType))
 */
private val clang_getCanonicalType_DESC: FunctionDescriptor = FunctionDescriptor.of(CXType.layout, CXType.layout)
private val clang_getCanonicalType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCanonicalType")
private val clang_getCanonicalType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCanonicalType_ADDR, clang_getCanonicalType_DESC)

fun clang_getCanonicalType(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getCanonicalType_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_isConstQualifiedType UNSIGNED = Int(typedef CXType = Declared(CXType))
 */
private val clang_isConstQualifiedType_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXType.layout)
private val clang_isConstQualifiedType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_isConstQualifiedType")
private val clang_isConstQualifiedType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_isConstQualifiedType_ADDR, clang_isConstQualifiedType_DESC)

fun clang_isConstQualifiedType(arg0: MemorySegment): Int {
    try {
        return clang_isConstQualifiedType_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_isMacroFunctionLike UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_isMacroFunctionLike_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_isMacroFunctionLike_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_isMacroFunctionLike")
private val clang_Cursor_isMacroFunctionLike_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_isMacroFunctionLike_ADDR, clang_Cursor_isMacroFunctionLike_DESC)

fun clang_Cursor_isMacroFunctionLike(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_isMacroFunctionLike_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_isMacroBuiltin UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_isMacroBuiltin_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_isMacroBuiltin_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_isMacroBuiltin")
private val clang_Cursor_isMacroBuiltin_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_isMacroBuiltin_ADDR, clang_Cursor_isMacroBuiltin_DESC)

fun clang_Cursor_isMacroBuiltin(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_isMacroBuiltin_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_isFunctionInlined UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_isFunctionInlined_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_isFunctionInlined_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_isFunctionInlined")
private val clang_Cursor_isFunctionInlined_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_isFunctionInlined_ADDR, clang_Cursor_isFunctionInlined_DESC)

fun clang_Cursor_isFunctionInlined(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_isFunctionInlined_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_isVolatileQualifiedType UNSIGNED = Int(typedef CXType = Declared(CXType))
 */
private val clang_isVolatileQualifiedType_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXType.layout)
private val clang_isVolatileQualifiedType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_isVolatileQualifiedType")
private val clang_isVolatileQualifiedType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_isVolatileQualifiedType_ADDR, clang_isVolatileQualifiedType_DESC)

fun clang_isVolatileQualifiedType(arg0: MemorySegment): Int {
    try {
        return clang_isVolatileQualifiedType_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_isRestrictQualifiedType UNSIGNED = Int(typedef CXType = Declared(CXType))
 */
private val clang_isRestrictQualifiedType_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXType.layout)
private val clang_isRestrictQualifiedType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_isRestrictQualifiedType")
private val clang_isRestrictQualifiedType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_isRestrictQualifiedType_ADDR, clang_isRestrictQualifiedType_DESC)

fun clang_isRestrictQualifiedType(arg0: MemorySegment): Int {
    try {
        return clang_isRestrictQualifiedType_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getAddressSpace UNSIGNED = Int(typedef CXType = Declared(CXType))
 */
private val clang_getAddressSpace_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXType.layout)
private val clang_getAddressSpace_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getAddressSpace")
private val clang_getAddressSpace_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getAddressSpace_ADDR, clang_getAddressSpace_DESC)

fun clang_getAddressSpace(arg0: MemorySegment): Int {
    try {
        return clang_getAddressSpace_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getTypedefName typedef CXString = Declared(CXString)(typedef CXType = Declared(CXType))
 */
private val clang_getTypedefName_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, CXType.layout)
private val clang_getTypedefName_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getTypedefName")
private val clang_getTypedefName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getTypedefName_ADDR, clang_getTypedefName_DESC)

fun clang_getTypedefName(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getTypedefName_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getPointeeType typedef CXType = Declared(CXType)(typedef CXType = Declared(CXType))
 */
private val clang_getPointeeType_DESC: FunctionDescriptor = FunctionDescriptor.of(CXType.layout, CXType.layout)
private val clang_getPointeeType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getPointeeType")
private val clang_getPointeeType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getPointeeType_ADDR, clang_getPointeeType_DESC)

fun clang_getPointeeType(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getPointeeType_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getUnqualifiedType typedef CXType = Declared(CXType)(typedef CXType = Declared(CXType))
 */
private val clang_getUnqualifiedType_DESC: FunctionDescriptor = FunctionDescriptor.of(CXType.layout, CXType.layout)
private val clang_getUnqualifiedType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getUnqualifiedType")
private val clang_getUnqualifiedType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getUnqualifiedType_ADDR, clang_getUnqualifiedType_DESC)

fun clang_getUnqualifiedType(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getUnqualifiedType_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getNonReferenceType typedef CXType = Declared(CXType)(typedef CXType = Declared(CXType))
 */
private val clang_getNonReferenceType_DESC: FunctionDescriptor = FunctionDescriptor.of(CXType.layout, CXType.layout)
private val clang_getNonReferenceType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getNonReferenceType")
private val clang_getNonReferenceType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getNonReferenceType_ADDR, clang_getNonReferenceType_DESC)

fun clang_getNonReferenceType(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getNonReferenceType_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getTypeDeclaration typedef CXCursor = Declared(CXCursor)(typedef CXType = Declared(CXType))
 */
private val clang_getTypeDeclaration_DESC: FunctionDescriptor = FunctionDescriptor.of(CXCursor.layout, CXType.layout)
private val clang_getTypeDeclaration_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getTypeDeclaration")
private val clang_getTypeDeclaration_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getTypeDeclaration_ADDR, clang_getTypeDeclaration_DESC)

fun clang_getTypeDeclaration(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getTypeDeclaration_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getDeclObjCTypeEncoding typedef CXString = Declared(CXString)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getDeclObjCTypeEncoding_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, CXCursor.layout)
private val clang_getDeclObjCTypeEncoding_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getDeclObjCTypeEncoding")
private val clang_getDeclObjCTypeEncoding_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getDeclObjCTypeEncoding_ADDR, clang_getDeclObjCTypeEncoding_DESC)

fun clang_getDeclObjCTypeEncoding(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getDeclObjCTypeEncoding_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Type_getObjCEncoding typedef CXString = Declared(CXString)(typedef CXType = Declared(CXType))
 */
private val clang_Type_getObjCEncoding_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, CXType.layout)
private val clang_Type_getObjCEncoding_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Type_getObjCEncoding")
private val clang_Type_getObjCEncoding_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Type_getObjCEncoding_ADDR, clang_Type_getObjCEncoding_DESC)

fun clang_Type_getObjCEncoding(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_Type_getObjCEncoding_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getTypeKindSpelling typedef CXString = Declared(CXString)(Declared(CXTypeKind))
 */
private val clang_getTypeKindSpelling_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.JAVA_INT)
private val clang_getTypeKindSpelling_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getTypeKindSpelling")
private val clang_getTypeKindSpelling_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getTypeKindSpelling_ADDR, clang_getTypeKindSpelling_DESC)

fun clang_getTypeKindSpelling(allocator: SegmentAllocator, arg0: Int): MemorySegment {
    try {
        return clang_getTypeKindSpelling_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getFunctionTypeCallingConv Declared(CXCallingConv)(typedef CXType = Declared(CXType))
 */
private val clang_getFunctionTypeCallingConv_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXType.layout)
private val clang_getFunctionTypeCallingConv_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getFunctionTypeCallingConv")
private val clang_getFunctionTypeCallingConv_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getFunctionTypeCallingConv_ADDR, clang_getFunctionTypeCallingConv_DESC)

fun clang_getFunctionTypeCallingConv(arg0: MemorySegment): Int {
    try {
        return clang_getFunctionTypeCallingConv_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getResultType typedef CXType = Declared(CXType)(typedef CXType = Declared(CXType))
 */
private val clang_getResultType_DESC: FunctionDescriptor = FunctionDescriptor.of(CXType.layout, CXType.layout)
private val clang_getResultType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getResultType")
private val clang_getResultType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getResultType_ADDR, clang_getResultType_DESC)

fun clang_getResultType(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getResultType_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getExceptionSpecificationType Int(typedef CXType = Declared(CXType))
 */
private val clang_getExceptionSpecificationType_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXType.layout)
private val clang_getExceptionSpecificationType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getExceptionSpecificationType")
private val clang_getExceptionSpecificationType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getExceptionSpecificationType_ADDR, clang_getExceptionSpecificationType_DESC)

fun clang_getExceptionSpecificationType(arg0: MemorySegment): Int {
    try {
        return clang_getExceptionSpecificationType_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getNumArgTypes Int(typedef CXType = Declared(CXType))
 */
private val clang_getNumArgTypes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXType.layout)
private val clang_getNumArgTypes_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getNumArgTypes")
private val clang_getNumArgTypes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getNumArgTypes_ADDR, clang_getNumArgTypes_DESC)

fun clang_getNumArgTypes(arg0: MemorySegment): Int {
    try {
        return clang_getNumArgTypes_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getArgType typedef CXType = Declared(CXType)(typedef CXType = Declared(CXType),UNSIGNED = Int)
 */
private val clang_getArgType_DESC: FunctionDescriptor = FunctionDescriptor.of(CXType.layout, CXType.layout, ValueLayout.JAVA_INT)
private val clang_getArgType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getArgType")
private val clang_getArgType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getArgType_ADDR, clang_getArgType_DESC)

fun clang_getArgType(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return clang_getArgType_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Type_getObjCObjectBaseType typedef CXType = Declared(CXType)(typedef CXType = Declared(CXType))
 */
private val clang_Type_getObjCObjectBaseType_DESC: FunctionDescriptor = FunctionDescriptor.of(CXType.layout, CXType.layout)
private val clang_Type_getObjCObjectBaseType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Type_getObjCObjectBaseType")
private val clang_Type_getObjCObjectBaseType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Type_getObjCObjectBaseType_ADDR, clang_Type_getObjCObjectBaseType_DESC)

fun clang_Type_getObjCObjectBaseType(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_Type_getObjCObjectBaseType_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Type_getNumObjCProtocolRefs UNSIGNED = Int(typedef CXType = Declared(CXType))
 */
private val clang_Type_getNumObjCProtocolRefs_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXType.layout)
private val clang_Type_getNumObjCProtocolRefs_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Type_getNumObjCProtocolRefs")
private val clang_Type_getNumObjCProtocolRefs_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Type_getNumObjCProtocolRefs_ADDR, clang_Type_getNumObjCProtocolRefs_DESC)

fun clang_Type_getNumObjCProtocolRefs(arg0: MemorySegment): Int {
    try {
        return clang_Type_getNumObjCProtocolRefs_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Type_getObjCProtocolDecl typedef CXCursor = Declared(CXCursor)(typedef CXType = Declared(CXType),UNSIGNED = Int)
 */
private val clang_Type_getObjCProtocolDecl_DESC: FunctionDescriptor = FunctionDescriptor.of(CXCursor.layout, CXType.layout, ValueLayout.JAVA_INT)
private val clang_Type_getObjCProtocolDecl_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Type_getObjCProtocolDecl")
private val clang_Type_getObjCProtocolDecl_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Type_getObjCProtocolDecl_ADDR, clang_Type_getObjCProtocolDecl_DESC)

fun clang_Type_getObjCProtocolDecl(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return clang_Type_getObjCProtocolDecl_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Type_getNumObjCTypeArgs UNSIGNED = Int(typedef CXType = Declared(CXType))
 */
private val clang_Type_getNumObjCTypeArgs_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXType.layout)
private val clang_Type_getNumObjCTypeArgs_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Type_getNumObjCTypeArgs")
private val clang_Type_getNumObjCTypeArgs_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Type_getNumObjCTypeArgs_ADDR, clang_Type_getNumObjCTypeArgs_DESC)

fun clang_Type_getNumObjCTypeArgs(arg0: MemorySegment): Int {
    try {
        return clang_Type_getNumObjCTypeArgs_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Type_getObjCTypeArg typedef CXType = Declared(CXType)(typedef CXType = Declared(CXType),UNSIGNED = Int)
 */
private val clang_Type_getObjCTypeArg_DESC: FunctionDescriptor = FunctionDescriptor.of(CXType.layout, CXType.layout, ValueLayout.JAVA_INT)
private val clang_Type_getObjCTypeArg_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Type_getObjCTypeArg")
private val clang_Type_getObjCTypeArg_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Type_getObjCTypeArg_ADDR, clang_Type_getObjCTypeArg_DESC)

fun clang_Type_getObjCTypeArg(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return clang_Type_getObjCTypeArg_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_isFunctionTypeVariadic UNSIGNED = Int(typedef CXType = Declared(CXType))
 */
private val clang_isFunctionTypeVariadic_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXType.layout)
private val clang_isFunctionTypeVariadic_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_isFunctionTypeVariadic")
private val clang_isFunctionTypeVariadic_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_isFunctionTypeVariadic_ADDR, clang_isFunctionTypeVariadic_DESC)

fun clang_isFunctionTypeVariadic(arg0: MemorySegment): Int {
    try {
        return clang_isFunctionTypeVariadic_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCursorResultType typedef CXType = Declared(CXType)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getCursorResultType_DESC: FunctionDescriptor = FunctionDescriptor.of(CXType.layout, CXCursor.layout)
private val clang_getCursorResultType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorResultType")
private val clang_getCursorResultType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorResultType_ADDR, clang_getCursorResultType_DESC)

fun clang_getCursorResultType(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getCursorResultType_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCursorExceptionSpecificationType Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getCursorExceptionSpecificationType_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_getCursorExceptionSpecificationType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorExceptionSpecificationType")
private val clang_getCursorExceptionSpecificationType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorExceptionSpecificationType_ADDR, clang_getCursorExceptionSpecificationType_DESC)

fun clang_getCursorExceptionSpecificationType(arg0: MemorySegment): Int {
    try {
        return clang_getCursorExceptionSpecificationType_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_isPODType UNSIGNED = Int(typedef CXType = Declared(CXType))
 */
private val clang_isPODType_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXType.layout)
private val clang_isPODType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_isPODType")
private val clang_isPODType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_isPODType_ADDR, clang_isPODType_DESC)

fun clang_isPODType(arg0: MemorySegment): Int {
    try {
        return clang_isPODType_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getElementType typedef CXType = Declared(CXType)(typedef CXType = Declared(CXType))
 */
private val clang_getElementType_DESC: FunctionDescriptor = FunctionDescriptor.of(CXType.layout, CXType.layout)
private val clang_getElementType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getElementType")
private val clang_getElementType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getElementType_ADDR, clang_getElementType_DESC)

fun clang_getElementType(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getElementType_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getNumElements LongLong(typedef CXType = Declared(CXType))
 */
private val clang_getNumElements_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, CXType.layout)
private val clang_getNumElements_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getNumElements")
private val clang_getNumElements_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getNumElements_ADDR, clang_getNumElements_DESC)

fun clang_getNumElements(arg0: MemorySegment): Long {
    try {
        return clang_getNumElements_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getArrayElementType typedef CXType = Declared(CXType)(typedef CXType = Declared(CXType))
 */
private val clang_getArrayElementType_DESC: FunctionDescriptor = FunctionDescriptor.of(CXType.layout, CXType.layout)
private val clang_getArrayElementType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getArrayElementType")
private val clang_getArrayElementType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getArrayElementType_ADDR, clang_getArrayElementType_DESC)

fun clang_getArrayElementType(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getArrayElementType_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getArraySize LongLong(typedef CXType = Declared(CXType))
 */
private val clang_getArraySize_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, CXType.layout)
private val clang_getArraySize_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getArraySize")
private val clang_getArraySize_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getArraySize_ADDR, clang_getArraySize_DESC)

fun clang_getArraySize(arg0: MemorySegment): Long {
    try {
        return clang_getArraySize_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Type_getNamedType typedef CXType = Declared(CXType)(typedef CXType = Declared(CXType))
 */
private val clang_Type_getNamedType_DESC: FunctionDescriptor = FunctionDescriptor.of(CXType.layout, CXType.layout)
private val clang_Type_getNamedType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Type_getNamedType")
private val clang_Type_getNamedType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Type_getNamedType_ADDR, clang_Type_getNamedType_DESC)

fun clang_Type_getNamedType(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_Type_getNamedType_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Type_isTransparentTagTypedef UNSIGNED = Int(typedef CXType = Declared(CXType))
 */
private val clang_Type_isTransparentTagTypedef_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXType.layout)
private val clang_Type_isTransparentTagTypedef_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Type_isTransparentTagTypedef")
private val clang_Type_isTransparentTagTypedef_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Type_isTransparentTagTypedef_ADDR, clang_Type_isTransparentTagTypedef_DESC)

fun clang_Type_isTransparentTagTypedef(arg0: MemorySegment): Int {
    try {
        return clang_Type_isTransparentTagTypedef_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXTypeNullability_NonNull 0
 */
fun CXTypeNullability_NonNull(): Int = 0

/**
 * {@snippet lang=c : #define CXTypeNullability_Nullable 1
 */
fun CXTypeNullability_Nullable(): Int = 1

/**
 * {@snippet lang=c : #define CXTypeNullability_Unspecified 2
 */
fun CXTypeNullability_Unspecified(): Int = 2

/**
 * {@snippet lang=c : #define CXTypeNullability_Invalid 3
 */
fun CXTypeNullability_Invalid(): Int = 3

/**
 * {@snippet lang=c : #define CXTypeNullability_NullableResult 4
 */
fun CXTypeNullability_NullableResult(): Int = 4

/**
 * {@snippet lang=c : clang_Type_getNullability Declared(CXTypeNullabilityKind)(typedef CXType = Declared(CXType))
 */
private val clang_Type_getNullability_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXType.layout)
private val clang_Type_getNullability_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Type_getNullability")
private val clang_Type_getNullability_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Type_getNullability_ADDR, clang_Type_getNullability_DESC)

fun clang_Type_getNullability(arg0: MemorySegment): Int {
    try {
        return clang_Type_getNullability_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXTypeLayoutError_Invalid -1
 */
fun CXTypeLayoutError_Invalid(): Int = -1

/**
 * {@snippet lang=c : #define CXTypeLayoutError_Incomplete -2
 */
fun CXTypeLayoutError_Incomplete(): Int = -2

/**
 * {@snippet lang=c : #define CXTypeLayoutError_Dependent -3
 */
fun CXTypeLayoutError_Dependent(): Int = -3

/**
 * {@snippet lang=c : #define CXTypeLayoutError_NotConstantSize -4
 */
fun CXTypeLayoutError_NotConstantSize(): Int = -4

/**
 * {@snippet lang=c : #define CXTypeLayoutError_InvalidFieldName -5
 */
fun CXTypeLayoutError_InvalidFieldName(): Int = -5

/**
 * {@snippet lang=c : #define CXTypeLayoutError_Undeduced -6
 */
fun CXTypeLayoutError_Undeduced(): Int = -6

/**
 * {@snippet lang=c : clang_Type_getAlignOf LongLong(typedef CXType = Declared(CXType))
 */
private val clang_Type_getAlignOf_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, CXType.layout)
private val clang_Type_getAlignOf_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Type_getAlignOf")
private val clang_Type_getAlignOf_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Type_getAlignOf_ADDR, clang_Type_getAlignOf_DESC)

fun clang_Type_getAlignOf(arg0: MemorySegment): Long {
    try {
        return clang_Type_getAlignOf_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Type_getClassType typedef CXType = Declared(CXType)(typedef CXType = Declared(CXType))
 */
private val clang_Type_getClassType_DESC: FunctionDescriptor = FunctionDescriptor.of(CXType.layout, CXType.layout)
private val clang_Type_getClassType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Type_getClassType")
private val clang_Type_getClassType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Type_getClassType_ADDR, clang_Type_getClassType_DESC)

fun clang_Type_getClassType(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_Type_getClassType_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Type_getSizeOf LongLong(typedef CXType = Declared(CXType))
 */
private val clang_Type_getSizeOf_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, CXType.layout)
private val clang_Type_getSizeOf_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Type_getSizeOf")
private val clang_Type_getSizeOf_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Type_getSizeOf_ADDR, clang_Type_getSizeOf_DESC)

fun clang_Type_getSizeOf(arg0: MemorySegment): Long {
    try {
        return clang_Type_getSizeOf_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Type_getOffsetOf LongLong(typedef CXType = Declared(CXType),(Char)*)
 */
private val clang_Type_getOffsetOf_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, CXType.layout, ValueLayout.ADDRESS)
private val clang_Type_getOffsetOf_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Type_getOffsetOf")
private val clang_Type_getOffsetOf_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Type_getOffsetOf_ADDR, clang_Type_getOffsetOf_DESC)

fun clang_Type_getOffsetOf(arg0: MemorySegment, arg1: MemorySegment): Long {
    try {
        return clang_Type_getOffsetOf_HANDLE.invokeExact(arg0, arg1) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Type_getModifiedType typedef CXType = Declared(CXType)(typedef CXType = Declared(CXType))
 */
private val clang_Type_getModifiedType_DESC: FunctionDescriptor = FunctionDescriptor.of(CXType.layout, CXType.layout)
private val clang_Type_getModifiedType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Type_getModifiedType")
private val clang_Type_getModifiedType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Type_getModifiedType_ADDR, clang_Type_getModifiedType_DESC)

fun clang_Type_getModifiedType(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_Type_getModifiedType_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Type_getValueType typedef CXType = Declared(CXType)(typedef CXType = Declared(CXType))
 */
private val clang_Type_getValueType_DESC: FunctionDescriptor = FunctionDescriptor.of(CXType.layout, CXType.layout)
private val clang_Type_getValueType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Type_getValueType")
private val clang_Type_getValueType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Type_getValueType_ADDR, clang_Type_getValueType_DESC)

fun clang_Type_getValueType(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_Type_getValueType_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getOffsetOfField LongLong(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_getOffsetOfField_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, CXCursor.layout)
private val clang_Cursor_getOffsetOfField_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getOffsetOfField")
private val clang_Cursor_getOffsetOfField_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getOffsetOfField_ADDR, clang_Cursor_getOffsetOfField_DESC)

fun clang_Cursor_getOffsetOfField(arg0: MemorySegment): Long {
    try {
        return clang_Cursor_getOffsetOfField_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_isAnonymous UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_isAnonymous_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_isAnonymous_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_isAnonymous")
private val clang_Cursor_isAnonymous_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_isAnonymous_ADDR, clang_Cursor_isAnonymous_DESC)

fun clang_Cursor_isAnonymous(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_isAnonymous_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_isAnonymousRecordDecl UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_isAnonymousRecordDecl_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_isAnonymousRecordDecl_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_isAnonymousRecordDecl")
private val clang_Cursor_isAnonymousRecordDecl_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_isAnonymousRecordDecl_ADDR, clang_Cursor_isAnonymousRecordDecl_DESC)

fun clang_Cursor_isAnonymousRecordDecl(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_isAnonymousRecordDecl_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_isInlineNamespace UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_isInlineNamespace_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_isInlineNamespace_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_isInlineNamespace")
private val clang_Cursor_isInlineNamespace_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_isInlineNamespace_ADDR, clang_Cursor_isInlineNamespace_DESC)

fun clang_Cursor_isInlineNamespace(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_isInlineNamespace_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXRefQualifier_None 0
 */
fun CXRefQualifier_None(): Int = 0

/**
 * {@snippet lang=c : #define CXRefQualifier_LValue 1
 */
fun CXRefQualifier_LValue(): Int = 1

/**
 * {@snippet lang=c : #define CXRefQualifier_RValue 2
 */
fun CXRefQualifier_RValue(): Int = 2

/**
 * {@snippet lang=c : clang_Type_getNumTemplateArguments Int(typedef CXType = Declared(CXType))
 */
private val clang_Type_getNumTemplateArguments_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXType.layout)
private val clang_Type_getNumTemplateArguments_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Type_getNumTemplateArguments")
private val clang_Type_getNumTemplateArguments_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Type_getNumTemplateArguments_ADDR, clang_Type_getNumTemplateArguments_DESC)

fun clang_Type_getNumTemplateArguments(arg0: MemorySegment): Int {
    try {
        return clang_Type_getNumTemplateArguments_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Type_getTemplateArgumentAsType typedef CXType = Declared(CXType)(typedef CXType = Declared(CXType),UNSIGNED = Int)
 */
private val clang_Type_getTemplateArgumentAsType_DESC: FunctionDescriptor = FunctionDescriptor.of(CXType.layout, CXType.layout, ValueLayout.JAVA_INT)
private val clang_Type_getTemplateArgumentAsType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Type_getTemplateArgumentAsType")
private val clang_Type_getTemplateArgumentAsType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Type_getTemplateArgumentAsType_ADDR, clang_Type_getTemplateArgumentAsType_DESC)

fun clang_Type_getTemplateArgumentAsType(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return clang_Type_getTemplateArgumentAsType_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Type_getCXXRefQualifier Declared(CXRefQualifierKind)(typedef CXType = Declared(CXType))
 */
private val clang_Type_getCXXRefQualifier_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXType.layout)
private val clang_Type_getCXXRefQualifier_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Type_getCXXRefQualifier")
private val clang_Type_getCXXRefQualifier_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Type_getCXXRefQualifier_ADDR, clang_Type_getCXXRefQualifier_DESC)

fun clang_Type_getCXXRefQualifier(arg0: MemorySegment): Int {
    try {
        return clang_Type_getCXXRefQualifier_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_isVirtualBase UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_isVirtualBase_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_isVirtualBase_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_isVirtualBase")
private val clang_isVirtualBase_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_isVirtualBase_ADDR, clang_isVirtualBase_DESC)

fun clang_isVirtualBase(arg0: MemorySegment): Int {
    try {
        return clang_isVirtualBase_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getOffsetOfBase LongLong(typedef CXCursor = Declared(CXCursor),typedef CXCursor = Declared(CXCursor))
 */
private val clang_getOffsetOfBase_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, CXCursor.layout, CXCursor.layout)
private val clang_getOffsetOfBase_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getOffsetOfBase")
private val clang_getOffsetOfBase_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getOffsetOfBase_ADDR, clang_getOffsetOfBase_DESC)

fun clang_getOffsetOfBase(arg0: MemorySegment, arg1: MemorySegment): Long {
    try {
        return clang_getOffsetOfBase_HANDLE.invokeExact(arg0, arg1) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CX_CXXInvalidAccessSpecifier 0
 */
fun CX_CXXInvalidAccessSpecifier(): Int = 0

/**
 * {@snippet lang=c : #define CX_CXXPublic 1
 */
fun CX_CXXPublic(): Int = 1

/**
 * {@snippet lang=c : #define CX_CXXProtected 2
 */
fun CX_CXXProtected(): Int = 2

/**
 * {@snippet lang=c : #define CX_CXXPrivate 3
 */
fun CX_CXXPrivate(): Int = 3

/**
 * {@snippet lang=c : clang_getCXXAccessSpecifier Declared(CX_CXXAccessSpecifier)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getCXXAccessSpecifier_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_getCXXAccessSpecifier_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCXXAccessSpecifier")
private val clang_getCXXAccessSpecifier_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCXXAccessSpecifier_ADDR, clang_getCXXAccessSpecifier_DESC)

fun clang_getCXXAccessSpecifier(arg0: MemorySegment): Int {
    try {
        return clang_getCXXAccessSpecifier_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CX_SC_Invalid 0
 */
fun CX_SC_Invalid(): Int = 0

/**
 * {@snippet lang=c : #define CX_SC_None 1
 */
fun CX_SC_None(): Int = 1

/**
 * {@snippet lang=c : #define CX_SC_Extern 2
 */
fun CX_SC_Extern(): Int = 2

/**
 * {@snippet lang=c : #define CX_SC_Static 3
 */
fun CX_SC_Static(): Int = 3

/**
 * {@snippet lang=c : #define CX_SC_PrivateExtern 4
 */
fun CX_SC_PrivateExtern(): Int = 4

/**
 * {@snippet lang=c : #define CX_SC_OpenCLWorkGroupLocal 5
 */
fun CX_SC_OpenCLWorkGroupLocal(): Int = 5

/**
 * {@snippet lang=c : #define CX_SC_Auto 6
 */
fun CX_SC_Auto(): Int = 6

/**
 * {@snippet lang=c : #define CX_SC_Register 7
 */
fun CX_SC_Register(): Int = 7

/**
 * {@snippet lang=c : #define CX_BO_Invalid 0
 */
fun CX_BO_Invalid(): Int = 0

/**
 * {@snippet lang=c : #define CX_BO_PtrMemD 1
 */
fun CX_BO_PtrMemD(): Int = 1

/**
 * {@snippet lang=c : #define CX_BO_PtrMemI 2
 */
fun CX_BO_PtrMemI(): Int = 2

/**
 * {@snippet lang=c : #define CX_BO_Mul 3
 */
fun CX_BO_Mul(): Int = 3

/**
 * {@snippet lang=c : #define CX_BO_Div 4
 */
fun CX_BO_Div(): Int = 4

/**
 * {@snippet lang=c : #define CX_BO_Rem 5
 */
fun CX_BO_Rem(): Int = 5

/**
 * {@snippet lang=c : #define CX_BO_Add 6
 */
fun CX_BO_Add(): Int = 6

/**
 * {@snippet lang=c : #define CX_BO_Sub 7
 */
fun CX_BO_Sub(): Int = 7

/**
 * {@snippet lang=c : #define CX_BO_Shl 8
 */
fun CX_BO_Shl(): Int = 8

/**
 * {@snippet lang=c : #define CX_BO_Shr 9
 */
fun CX_BO_Shr(): Int = 9

/**
 * {@snippet lang=c : #define CX_BO_Cmp 10
 */
fun CX_BO_Cmp(): Int = 10

/**
 * {@snippet lang=c : #define CX_BO_LT 11
 */
fun CX_BO_LT(): Int = 11

/**
 * {@snippet lang=c : #define CX_BO_GT 12
 */
fun CX_BO_GT(): Int = 12

/**
 * {@snippet lang=c : #define CX_BO_LE 13
 */
fun CX_BO_LE(): Int = 13

/**
 * {@snippet lang=c : #define CX_BO_GE 14
 */
fun CX_BO_GE(): Int = 14

/**
 * {@snippet lang=c : #define CX_BO_EQ 15
 */
fun CX_BO_EQ(): Int = 15

/**
 * {@snippet lang=c : #define CX_BO_NE 16
 */
fun CX_BO_NE(): Int = 16

/**
 * {@snippet lang=c : #define CX_BO_And 17
 */
fun CX_BO_And(): Int = 17

/**
 * {@snippet lang=c : #define CX_BO_Xor 18
 */
fun CX_BO_Xor(): Int = 18

/**
 * {@snippet lang=c : #define CX_BO_Or 19
 */
fun CX_BO_Or(): Int = 19

/**
 * {@snippet lang=c : #define CX_BO_LAnd 20
 */
fun CX_BO_LAnd(): Int = 20

/**
 * {@snippet lang=c : #define CX_BO_LOr 21
 */
fun CX_BO_LOr(): Int = 21

/**
 * {@snippet lang=c : #define CX_BO_Assign 22
 */
fun CX_BO_Assign(): Int = 22

/**
 * {@snippet lang=c : #define CX_BO_MulAssign 23
 */
fun CX_BO_MulAssign(): Int = 23

/**
 * {@snippet lang=c : #define CX_BO_DivAssign 24
 */
fun CX_BO_DivAssign(): Int = 24

/**
 * {@snippet lang=c : #define CX_BO_RemAssign 25
 */
fun CX_BO_RemAssign(): Int = 25

/**
 * {@snippet lang=c : #define CX_BO_AddAssign 26
 */
fun CX_BO_AddAssign(): Int = 26

/**
 * {@snippet lang=c : #define CX_BO_SubAssign 27
 */
fun CX_BO_SubAssign(): Int = 27

/**
 * {@snippet lang=c : #define CX_BO_ShlAssign 28
 */
fun CX_BO_ShlAssign(): Int = 28

/**
 * {@snippet lang=c : #define CX_BO_ShrAssign 29
 */
fun CX_BO_ShrAssign(): Int = 29

/**
 * {@snippet lang=c : #define CX_BO_AndAssign 30
 */
fun CX_BO_AndAssign(): Int = 30

/**
 * {@snippet lang=c : #define CX_BO_XorAssign 31
 */
fun CX_BO_XorAssign(): Int = 31

/**
 * {@snippet lang=c : #define CX_BO_OrAssign 32
 */
fun CX_BO_OrAssign(): Int = 32

/**
 * {@snippet lang=c : #define CX_BO_Comma 33
 */
fun CX_BO_Comma(): Int = 33

/**
 * {@snippet lang=c : #define CX_BO_LAST 33
 */
fun CX_BO_LAST(): Int = 33

/**
 * {@snippet lang=c : clang_Cursor_getBinaryOpcode Declared(CX_BinaryOperatorKind)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_getBinaryOpcode_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_getBinaryOpcode_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getBinaryOpcode")
private val clang_Cursor_getBinaryOpcode_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getBinaryOpcode_ADDR, clang_Cursor_getBinaryOpcode_DESC)

fun clang_Cursor_getBinaryOpcode(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_getBinaryOpcode_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getBinaryOpcodeStr typedef CXString = Declared(CXString)(Declared(CX_BinaryOperatorKind))
 */
private val clang_Cursor_getBinaryOpcodeStr_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.JAVA_INT)
private val clang_Cursor_getBinaryOpcodeStr_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getBinaryOpcodeStr")
private val clang_Cursor_getBinaryOpcodeStr_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getBinaryOpcodeStr_ADDR, clang_Cursor_getBinaryOpcodeStr_DESC)

fun clang_Cursor_getBinaryOpcodeStr(allocator: SegmentAllocator, arg0: Int): MemorySegment {
    try {
        return clang_Cursor_getBinaryOpcodeStr_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getStorageClass Declared(CX_StorageClass)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_getStorageClass_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_getStorageClass_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getStorageClass")
private val clang_Cursor_getStorageClass_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getStorageClass_ADDR, clang_Cursor_getStorageClass_DESC)

fun clang_Cursor_getStorageClass(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_getStorageClass_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getNumOverloadedDecls UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getNumOverloadedDecls_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_getNumOverloadedDecls_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getNumOverloadedDecls")
private val clang_getNumOverloadedDecls_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getNumOverloadedDecls_ADDR, clang_getNumOverloadedDecls_DESC)

fun clang_getNumOverloadedDecls(arg0: MemorySegment): Int {
    try {
        return clang_getNumOverloadedDecls_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getOverloadedDecl typedef CXCursor = Declared(CXCursor)(typedef CXCursor = Declared(CXCursor),UNSIGNED = Int)
 */
private val clang_getOverloadedDecl_DESC: FunctionDescriptor = FunctionDescriptor.of(CXCursor.layout, CXCursor.layout, ValueLayout.JAVA_INT)
private val clang_getOverloadedDecl_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getOverloadedDecl")
private val clang_getOverloadedDecl_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getOverloadedDecl_ADDR, clang_getOverloadedDecl_DESC)

fun clang_getOverloadedDecl(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return clang_getOverloadedDecl_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getIBOutletCollectionType typedef CXType = Declared(CXType)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getIBOutletCollectionType_DESC: FunctionDescriptor = FunctionDescriptor.of(CXType.layout, CXCursor.layout)
private val clang_getIBOutletCollectionType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getIBOutletCollectionType")
private val clang_getIBOutletCollectionType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getIBOutletCollectionType_ADDR, clang_getIBOutletCollectionType_DESC)

fun clang_getIBOutletCollectionType(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getIBOutletCollectionType_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXChildVisit_Break 0
 */
fun CXChildVisit_Break(): Int = 0

/**
 * {@snippet lang=c : #define CXChildVisit_Continue 1
 */
fun CXChildVisit_Continue(): Int = 1

/**
 * {@snippet lang=c : #define CXChildVisit_Recurse 2
 */
fun CXChildVisit_Recurse(): Int = 2

/**
 * {@snippet lang=c : typedef (Declared(CXChildVisitResult)(Declared(CXCursor),Declared(CXCursor),(Void)*))* CXCursorVisitor;}
 */
object CXCursorVisitor {
    private val DESC: FunctionDescriptor = FunctionDescriptor.of(
        ValueLayout.JAVA_INT,
        CXCursor.layout,
        CXCursor.layout,
        ValueLayout.ADDRESS
    )
    private val UP_MH: MethodHandle = MethodHandles.lookup().findVirtual(
        Function::class.java, "apply",
        MethodType.methodType(Int::class.java, MemorySegment::class.java, MemorySegment::class.java, MemorySegment::class.java)
    )

    fun interface Function {
        fun apply(cursor: MemorySegment, parent: MemorySegment, clientData: MemorySegment): Int
    }

    fun allocate(fi: Function, arena: Arena): MemorySegment =
        Linker.nativeLinker().upcallStub(UP_MH.bindTo(fi), DESC, arena)
}

/**
 * {@snippet lang=c : clang_visitChildren UNSIGNED = Int(typedef CXCursor = Declared(CXCursor),typedef CXCursorVisitor = (Declared(CXChildVisitResult)(Declared(CXCursor),Declared(CXCursor),(Void)*))*,typedef CXClientData = (Void)*)
 */
private val clang_visitChildren_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_visitChildren_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_visitChildren")
private val clang_visitChildren_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_visitChildren_ADDR, clang_visitChildren_DESC)

fun clang_visitChildren(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Int {
    try {
        return clang_visitChildren_HANDLE.invokeExact(arg0, arg1, arg2) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : typedef (Declared(CXChildVisitResult)(Declared(CXCursor),Declared(CXCursor)))* CXCursorVisitorBlock;}
 */
typealias CXCursorVisitorBlock = MemorySegment?

/**
 * {@snippet lang=c : clang_visitChildrenWithBlock UNSIGNED = Int(typedef CXCursor = Declared(CXCursor),typedef CXCursorVisitorBlock = (Declared(CXChildVisitResult)(Declared(CXCursor),Declared(CXCursor)))*)
 */
private val clang_visitChildrenWithBlock_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout, ValueLayout.ADDRESS)
private val clang_visitChildrenWithBlock_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_visitChildrenWithBlock")
private val clang_visitChildrenWithBlock_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_visitChildrenWithBlock_ADDR, clang_visitChildrenWithBlock_DESC)

fun clang_visitChildrenWithBlock(arg0: MemorySegment, arg1: MemorySegment): Int {
    try {
        return clang_visitChildrenWithBlock_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCursorUSR typedef CXString = Declared(CXString)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getCursorUSR_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, CXCursor.layout)
private val clang_getCursorUSR_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorUSR")
private val clang_getCursorUSR_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorUSR_ADDR, clang_getCursorUSR_DESC)

fun clang_getCursorUSR(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getCursorUSR_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_constructUSR_ObjCClass typedef CXString = Declared(CXString)((Char)*)
 */
private val clang_constructUSR_ObjCClass_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS)
private val clang_constructUSR_ObjCClass_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_constructUSR_ObjCClass")
private val clang_constructUSR_ObjCClass_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_constructUSR_ObjCClass_ADDR, clang_constructUSR_ObjCClass_DESC)

fun clang_constructUSR_ObjCClass(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_constructUSR_ObjCClass_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_constructUSR_ObjCCategory typedef CXString = Declared(CXString)((Char)*,(Char)*)
 */
private val clang_constructUSR_ObjCCategory_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_constructUSR_ObjCCategory_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_constructUSR_ObjCCategory")
private val clang_constructUSR_ObjCCategory_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_constructUSR_ObjCCategory_ADDR, clang_constructUSR_ObjCCategory_DESC)

fun clang_constructUSR_ObjCCategory(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return clang_constructUSR_ObjCCategory_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_constructUSR_ObjCProtocol typedef CXString = Declared(CXString)((Char)*)
 */
private val clang_constructUSR_ObjCProtocol_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS)
private val clang_constructUSR_ObjCProtocol_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_constructUSR_ObjCProtocol")
private val clang_constructUSR_ObjCProtocol_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_constructUSR_ObjCProtocol_ADDR, clang_constructUSR_ObjCProtocol_DESC)

fun clang_constructUSR_ObjCProtocol(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_constructUSR_ObjCProtocol_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_constructUSR_ObjCIvar typedef CXString = Declared(CXString)((Char)*,typedef CXString = Declared(CXString))
 */
private val clang_constructUSR_ObjCIvar_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS, CXString.layout)
private val clang_constructUSR_ObjCIvar_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_constructUSR_ObjCIvar")
private val clang_constructUSR_ObjCIvar_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_constructUSR_ObjCIvar_ADDR, clang_constructUSR_ObjCIvar_DESC)

fun clang_constructUSR_ObjCIvar(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return clang_constructUSR_ObjCIvar_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_constructUSR_ObjCMethod typedef CXString = Declared(CXString)((Char)*,UNSIGNED = Int,typedef CXString = Declared(CXString))
 */
private val clang_constructUSR_ObjCMethod_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, CXString.layout)
private val clang_constructUSR_ObjCMethod_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_constructUSR_ObjCMethod")
private val clang_constructUSR_ObjCMethod_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_constructUSR_ObjCMethod_ADDR, clang_constructUSR_ObjCMethod_DESC)

fun clang_constructUSR_ObjCMethod(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Int, arg2: MemorySegment): MemorySegment {
    try {
        return clang_constructUSR_ObjCMethod_HANDLE.invokeExact(allocator, arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_constructUSR_ObjCProperty typedef CXString = Declared(CXString)((Char)*,typedef CXString = Declared(CXString))
 */
private val clang_constructUSR_ObjCProperty_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS, CXString.layout)
private val clang_constructUSR_ObjCProperty_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_constructUSR_ObjCProperty")
private val clang_constructUSR_ObjCProperty_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_constructUSR_ObjCProperty_ADDR, clang_constructUSR_ObjCProperty_DESC)

fun clang_constructUSR_ObjCProperty(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return clang_constructUSR_ObjCProperty_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCursorSpelling typedef CXString = Declared(CXString)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getCursorSpelling_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, CXCursor.layout)
private val clang_getCursorSpelling_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorSpelling")
private val clang_getCursorSpelling_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorSpelling_ADDR, clang_getCursorSpelling_DESC)

fun clang_getCursorSpelling(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getCursorSpelling_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getSpellingNameRange typedef CXSourceRange = Declared(CXSourceRange)(typedef CXCursor = Declared(CXCursor),UNSIGNED = Int,UNSIGNED = Int)
 */
private val clang_Cursor_getSpellingNameRange_DESC: FunctionDescriptor = FunctionDescriptor.of(CXSourceRange.layout, CXCursor.layout, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val clang_Cursor_getSpellingNameRange_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getSpellingNameRange")
private val clang_Cursor_getSpellingNameRange_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getSpellingNameRange_ADDR, clang_Cursor_getSpellingNameRange_DESC)

fun clang_Cursor_getSpellingNameRange(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Int, arg2: Int): MemorySegment {
    try {
        return clang_Cursor_getSpellingNameRange_HANDLE.invokeExact(allocator, arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : typedef (Void)* CXPrintingPolicy;}
 */
typealias CXPrintingPolicy = MemorySegment?

/**
 * {@snippet lang=c : #define CXPrintingPolicy_Indentation 0
 */
fun CXPrintingPolicy_Indentation(): Int = 0

/**
 * {@snippet lang=c : #define CXPrintingPolicy_SuppressSpecifiers 1
 */
fun CXPrintingPolicy_SuppressSpecifiers(): Int = 1

/**
 * {@snippet lang=c : #define CXPrintingPolicy_SuppressTagKeyword 2
 */
fun CXPrintingPolicy_SuppressTagKeyword(): Int = 2

/**
 * {@snippet lang=c : #define CXPrintingPolicy_IncludeTagDefinition 3
 */
fun CXPrintingPolicy_IncludeTagDefinition(): Int = 3

/**
 * {@snippet lang=c : #define CXPrintingPolicy_SuppressScope 4
 */
fun CXPrintingPolicy_SuppressScope(): Int = 4

/**
 * {@snippet lang=c : #define CXPrintingPolicy_SuppressUnwrittenScope 5
 */
fun CXPrintingPolicy_SuppressUnwrittenScope(): Int = 5

/**
 * {@snippet lang=c : #define CXPrintingPolicy_SuppressInitializers 6
 */
fun CXPrintingPolicy_SuppressInitializers(): Int = 6

/**
 * {@snippet lang=c : #define CXPrintingPolicy_ConstantArraySizeAsWritten 7
 */
fun CXPrintingPolicy_ConstantArraySizeAsWritten(): Int = 7

/**
 * {@snippet lang=c : #define CXPrintingPolicy_AnonymousTagLocations 8
 */
fun CXPrintingPolicy_AnonymousTagLocations(): Int = 8

/**
 * {@snippet lang=c : #define CXPrintingPolicy_SuppressStrongLifetime 9
 */
fun CXPrintingPolicy_SuppressStrongLifetime(): Int = 9

/**
 * {@snippet lang=c : #define CXPrintingPolicy_SuppressLifetimeQualifiers 10
 */
fun CXPrintingPolicy_SuppressLifetimeQualifiers(): Int = 10

/**
 * {@snippet lang=c : #define CXPrintingPolicy_SuppressTemplateArgsInCXXConstructors 11
 */
fun CXPrintingPolicy_SuppressTemplateArgsInCXXConstructors(): Int = 11

/**
 * {@snippet lang=c : #define CXPrintingPolicy_Bool 12
 */
fun CXPrintingPolicy_Bool(): Int = 12

/**
 * {@snippet lang=c : #define CXPrintingPolicy_Restrict 13
 */
fun CXPrintingPolicy_Restrict(): Int = 13

/**
 * {@snippet lang=c : #define CXPrintingPolicy_Alignof 14
 */
fun CXPrintingPolicy_Alignof(): Int = 14

/**
 * {@snippet lang=c : #define CXPrintingPolicy_UnderscoreAlignof 15
 */
fun CXPrintingPolicy_UnderscoreAlignof(): Int = 15

/**
 * {@snippet lang=c : #define CXPrintingPolicy_UseVoidForZeroParams 16
 */
fun CXPrintingPolicy_UseVoidForZeroParams(): Int = 16

/**
 * {@snippet lang=c : #define CXPrintingPolicy_TerseOutput 17
 */
fun CXPrintingPolicy_TerseOutput(): Int = 17

/**
 * {@snippet lang=c : #define CXPrintingPolicy_PolishForDeclaration 18
 */
fun CXPrintingPolicy_PolishForDeclaration(): Int = 18

/**
 * {@snippet lang=c : #define CXPrintingPolicy_Half 19
 */
fun CXPrintingPolicy_Half(): Int = 19

/**
 * {@snippet lang=c : #define CXPrintingPolicy_MSWChar 20
 */
fun CXPrintingPolicy_MSWChar(): Int = 20

/**
 * {@snippet lang=c : #define CXPrintingPolicy_IncludeNewlines 21
 */
fun CXPrintingPolicy_IncludeNewlines(): Int = 21

/**
 * {@snippet lang=c : #define CXPrintingPolicy_MSVCFormatting 22
 */
fun CXPrintingPolicy_MSVCFormatting(): Int = 22

/**
 * {@snippet lang=c : #define CXPrintingPolicy_ConstantsAsWritten 23
 */
fun CXPrintingPolicy_ConstantsAsWritten(): Int = 23

/**
 * {@snippet lang=c : #define CXPrintingPolicy_SuppressImplicitBase 24
 */
fun CXPrintingPolicy_SuppressImplicitBase(): Int = 24

/**
 * {@snippet lang=c : #define CXPrintingPolicy_FullyQualifiedName 25
 */
fun CXPrintingPolicy_FullyQualifiedName(): Int = 25

/**
 * {@snippet lang=c : #define CXPrintingPolicy_LastProperty 25
 */
fun CXPrintingPolicy_LastProperty(): Int = 25

/**
 * {@snippet lang=c : clang_PrintingPolicy_getProperty UNSIGNED = Int(typedef CXPrintingPolicy = (Void)*,Declared(CXPrintingPolicyProperty))
 */
private val clang_PrintingPolicy_getProperty_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_PrintingPolicy_getProperty_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_PrintingPolicy_getProperty")
private val clang_PrintingPolicy_getProperty_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_PrintingPolicy_getProperty_ADDR, clang_PrintingPolicy_getProperty_DESC)

fun clang_PrintingPolicy_getProperty(arg0: MemorySegment, arg1: Int): Int {
    try {
        return clang_PrintingPolicy_getProperty_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_PrintingPolicy_setProperty Void(typedef CXPrintingPolicy = (Void)*,Declared(CXPrintingPolicyProperty),UNSIGNED = Int)
 */
private val clang_PrintingPolicy_setProperty_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val clang_PrintingPolicy_setProperty_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_PrintingPolicy_setProperty")
private val clang_PrintingPolicy_setProperty_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_PrintingPolicy_setProperty_ADDR, clang_PrintingPolicy_setProperty_DESC)

fun clang_PrintingPolicy_setProperty(arg0: MemorySegment, arg1: Int, arg2: Int): Unit {
    try {
        clang_PrintingPolicy_setProperty_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCursorPrintingPolicy typedef CXPrintingPolicy = (Void)*(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getCursorPrintingPolicy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, CXCursor.layout)
private val clang_getCursorPrintingPolicy_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorPrintingPolicy")
private val clang_getCursorPrintingPolicy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorPrintingPolicy_ADDR, clang_getCursorPrintingPolicy_DESC)

fun clang_getCursorPrintingPolicy(arg0: MemorySegment): MemorySegment {
    try {
        return clang_getCursorPrintingPolicy_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_PrintingPolicy_dispose Void(typedef CXPrintingPolicy = (Void)*)
 */
private val clang_PrintingPolicy_dispose_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val clang_PrintingPolicy_dispose_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_PrintingPolicy_dispose")
private val clang_PrintingPolicy_dispose_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_PrintingPolicy_dispose_ADDR, clang_PrintingPolicy_dispose_DESC)

fun clang_PrintingPolicy_dispose(arg0: MemorySegment): Unit {
    try {
        clang_PrintingPolicy_dispose_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCursorPrettyPrinted typedef CXString = Declared(CXString)(typedef CXCursor = Declared(CXCursor),typedef CXPrintingPolicy = (Void)*)
 */
private val clang_getCursorPrettyPrinted_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, CXCursor.layout, ValueLayout.ADDRESS)
private val clang_getCursorPrettyPrinted_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorPrettyPrinted")
private val clang_getCursorPrettyPrinted_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorPrettyPrinted_ADDR, clang_getCursorPrettyPrinted_DESC)

fun clang_getCursorPrettyPrinted(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return clang_getCursorPrettyPrinted_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getTypePrettyPrinted typedef CXString = Declared(CXString)(typedef CXType = Declared(CXType),typedef CXPrintingPolicy = (Void)*)
 */
private val clang_getTypePrettyPrinted_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, CXType.layout, ValueLayout.ADDRESS)
private val clang_getTypePrettyPrinted_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getTypePrettyPrinted")
private val clang_getTypePrettyPrinted_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getTypePrettyPrinted_ADDR, clang_getTypePrettyPrinted_DESC)

fun clang_getTypePrettyPrinted(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return clang_getTypePrettyPrinted_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getFullyQualifiedName typedef CXString = Declared(CXString)(typedef CXType = Declared(CXType),typedef CXPrintingPolicy = (Void)*,UNSIGNED = Int)
 */
private val clang_getFullyQualifiedName_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, CXType.layout, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_getFullyQualifiedName_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getFullyQualifiedName")
private val clang_getFullyQualifiedName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getFullyQualifiedName_ADDR, clang_getFullyQualifiedName_DESC)

fun clang_getFullyQualifiedName(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment, arg2: Int): MemorySegment {
    try {
        return clang_getFullyQualifiedName_HANDLE.invokeExact(allocator, arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCursorDisplayName typedef CXString = Declared(CXString)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getCursorDisplayName_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, CXCursor.layout)
private val clang_getCursorDisplayName_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorDisplayName")
private val clang_getCursorDisplayName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorDisplayName_ADDR, clang_getCursorDisplayName_DESC)

fun clang_getCursorDisplayName(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getCursorDisplayName_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCursorReferenced typedef CXCursor = Declared(CXCursor)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getCursorReferenced_DESC: FunctionDescriptor = FunctionDescriptor.of(CXCursor.layout, CXCursor.layout)
private val clang_getCursorReferenced_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorReferenced")
private val clang_getCursorReferenced_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorReferenced_ADDR, clang_getCursorReferenced_DESC)

fun clang_getCursorReferenced(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getCursorReferenced_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCursorDefinition typedef CXCursor = Declared(CXCursor)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getCursorDefinition_DESC: FunctionDescriptor = FunctionDescriptor.of(CXCursor.layout, CXCursor.layout)
private val clang_getCursorDefinition_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorDefinition")
private val clang_getCursorDefinition_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorDefinition_ADDR, clang_getCursorDefinition_DESC)

fun clang_getCursorDefinition(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getCursorDefinition_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_isCursorDefinition UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_isCursorDefinition_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_isCursorDefinition_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_isCursorDefinition")
private val clang_isCursorDefinition_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_isCursorDefinition_ADDR, clang_isCursorDefinition_DESC)

fun clang_isCursorDefinition(arg0: MemorySegment): Int {
    try {
        return clang_isCursorDefinition_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCanonicalCursor typedef CXCursor = Declared(CXCursor)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getCanonicalCursor_DESC: FunctionDescriptor = FunctionDescriptor.of(CXCursor.layout, CXCursor.layout)
private val clang_getCanonicalCursor_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCanonicalCursor")
private val clang_getCanonicalCursor_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCanonicalCursor_ADDR, clang_getCanonicalCursor_DESC)

fun clang_getCanonicalCursor(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getCanonicalCursor_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getObjCSelectorIndex Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_getObjCSelectorIndex_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_getObjCSelectorIndex_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getObjCSelectorIndex")
private val clang_Cursor_getObjCSelectorIndex_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getObjCSelectorIndex_ADDR, clang_Cursor_getObjCSelectorIndex_DESC)

fun clang_Cursor_getObjCSelectorIndex(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_getObjCSelectorIndex_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_isDynamicCall Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_isDynamicCall_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_isDynamicCall_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_isDynamicCall")
private val clang_Cursor_isDynamicCall_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_isDynamicCall_ADDR, clang_Cursor_isDynamicCall_DESC)

fun clang_Cursor_isDynamicCall(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_isDynamicCall_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getReceiverType typedef CXType = Declared(CXType)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_getReceiverType_DESC: FunctionDescriptor = FunctionDescriptor.of(CXType.layout, CXCursor.layout)
private val clang_Cursor_getReceiverType_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getReceiverType")
private val clang_Cursor_getReceiverType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getReceiverType_ADDR, clang_Cursor_getReceiverType_DESC)

fun clang_Cursor_getReceiverType(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_Cursor_getReceiverType_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXObjCPropertyAttr_noattr 0
 */
fun CXObjCPropertyAttr_noattr(): Int = 0

/**
 * {@snippet lang=c : #define CXObjCPropertyAttr_readonly 1
 */
fun CXObjCPropertyAttr_readonly(): Int = 1

/**
 * {@snippet lang=c : #define CXObjCPropertyAttr_getter 2
 */
fun CXObjCPropertyAttr_getter(): Int = 2

/**
 * {@snippet lang=c : #define CXObjCPropertyAttr_assign 4
 */
fun CXObjCPropertyAttr_assign(): Int = 4

/**
 * {@snippet lang=c : #define CXObjCPropertyAttr_readwrite 8
 */
fun CXObjCPropertyAttr_readwrite(): Int = 8

/**
 * {@snippet lang=c : #define CXObjCPropertyAttr_retain 16
 */
fun CXObjCPropertyAttr_retain(): Int = 16

/**
 * {@snippet lang=c : #define CXObjCPropertyAttr_copy 32
 */
fun CXObjCPropertyAttr_copy(): Int = 32

/**
 * {@snippet lang=c : #define CXObjCPropertyAttr_nonatomic 64
 */
fun CXObjCPropertyAttr_nonatomic(): Int = 64

/**
 * {@snippet lang=c : #define CXObjCPropertyAttr_setter 128
 */
fun CXObjCPropertyAttr_setter(): Int = 128

/**
 * {@snippet lang=c : #define CXObjCPropertyAttr_atomic 256
 */
fun CXObjCPropertyAttr_atomic(): Int = 256

/**
 * {@snippet lang=c : #define CXObjCPropertyAttr_weak 512
 */
fun CXObjCPropertyAttr_weak(): Int = 512

/**
 * {@snippet lang=c : #define CXObjCPropertyAttr_strong 1024
 */
fun CXObjCPropertyAttr_strong(): Int = 1024

/**
 * {@snippet lang=c : #define CXObjCPropertyAttr_unsafe_unretained 2048
 */
fun CXObjCPropertyAttr_unsafe_unretained(): Int = 2048

/**
 * {@snippet lang=c : #define CXObjCPropertyAttr_class 4096
 */
fun CXObjCPropertyAttr_class(): Int = 4096

/**
 * {@snippet lang=c : clang_Cursor_getObjCPropertyAttributes UNSIGNED = Int(typedef CXCursor = Declared(CXCursor),UNSIGNED = Int)
 */
private val clang_Cursor_getObjCPropertyAttributes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout, ValueLayout.JAVA_INT)
private val clang_Cursor_getObjCPropertyAttributes_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getObjCPropertyAttributes")
private val clang_Cursor_getObjCPropertyAttributes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getObjCPropertyAttributes_ADDR, clang_Cursor_getObjCPropertyAttributes_DESC)

fun clang_Cursor_getObjCPropertyAttributes(arg0: MemorySegment, arg1: Int): Int {
    try {
        return clang_Cursor_getObjCPropertyAttributes_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getObjCPropertyGetterName typedef CXString = Declared(CXString)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_getObjCPropertyGetterName_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, CXCursor.layout)
private val clang_Cursor_getObjCPropertyGetterName_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getObjCPropertyGetterName")
private val clang_Cursor_getObjCPropertyGetterName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getObjCPropertyGetterName_ADDR, clang_Cursor_getObjCPropertyGetterName_DESC)

fun clang_Cursor_getObjCPropertyGetterName(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_Cursor_getObjCPropertyGetterName_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getObjCPropertySetterName typedef CXString = Declared(CXString)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_getObjCPropertySetterName_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, CXCursor.layout)
private val clang_Cursor_getObjCPropertySetterName_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getObjCPropertySetterName")
private val clang_Cursor_getObjCPropertySetterName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getObjCPropertySetterName_ADDR, clang_Cursor_getObjCPropertySetterName_DESC)

fun clang_Cursor_getObjCPropertySetterName(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_Cursor_getObjCPropertySetterName_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXObjCDeclQualifier_None 0
 */
fun CXObjCDeclQualifier_None(): Int = 0

/**
 * {@snippet lang=c : #define CXObjCDeclQualifier_In 1
 */
fun CXObjCDeclQualifier_In(): Int = 1

/**
 * {@snippet lang=c : #define CXObjCDeclQualifier_Inout 2
 */
fun CXObjCDeclQualifier_Inout(): Int = 2

/**
 * {@snippet lang=c : #define CXObjCDeclQualifier_Out 4
 */
fun CXObjCDeclQualifier_Out(): Int = 4

/**
 * {@snippet lang=c : #define CXObjCDeclQualifier_Bycopy 8
 */
fun CXObjCDeclQualifier_Bycopy(): Int = 8

/**
 * {@snippet lang=c : #define CXObjCDeclQualifier_Byref 16
 */
fun CXObjCDeclQualifier_Byref(): Int = 16

/**
 * {@snippet lang=c : #define CXObjCDeclQualifier_Oneway 32
 */
fun CXObjCDeclQualifier_Oneway(): Int = 32

/**
 * {@snippet lang=c : clang_Cursor_getObjCDeclQualifiers UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_getObjCDeclQualifiers_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_getObjCDeclQualifiers_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getObjCDeclQualifiers")
private val clang_Cursor_getObjCDeclQualifiers_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getObjCDeclQualifiers_ADDR, clang_Cursor_getObjCDeclQualifiers_DESC)

fun clang_Cursor_getObjCDeclQualifiers(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_getObjCDeclQualifiers_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_isObjCOptional UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_isObjCOptional_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_isObjCOptional_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_isObjCOptional")
private val clang_Cursor_isObjCOptional_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_isObjCOptional_ADDR, clang_Cursor_isObjCOptional_DESC)

fun clang_Cursor_isObjCOptional(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_isObjCOptional_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_isVariadic UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_isVariadic_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_isVariadic_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_isVariadic")
private val clang_Cursor_isVariadic_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_isVariadic_ADDR, clang_Cursor_isVariadic_DESC)

fun clang_Cursor_isVariadic(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_isVariadic_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_isExternalSymbol UNSIGNED = Int(typedef CXCursor = Declared(CXCursor),(typedef CXString = Declared(CXString))*,(typedef CXString = Declared(CXString))*,(UNSIGNED = Int)*)
 */
private val clang_Cursor_isExternalSymbol_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_Cursor_isExternalSymbol_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_isExternalSymbol")
private val clang_Cursor_isExternalSymbol_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_isExternalSymbol_ADDR, clang_Cursor_isExternalSymbol_DESC)

fun clang_Cursor_isExternalSymbol(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): Int {
    try {
        return clang_Cursor_isExternalSymbol_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getCommentRange typedef CXSourceRange = Declared(CXSourceRange)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_getCommentRange_DESC: FunctionDescriptor = FunctionDescriptor.of(CXSourceRange.layout, CXCursor.layout)
private val clang_Cursor_getCommentRange_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getCommentRange")
private val clang_Cursor_getCommentRange_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getCommentRange_ADDR, clang_Cursor_getCommentRange_DESC)

fun clang_Cursor_getCommentRange(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_Cursor_getCommentRange_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getRawCommentText typedef CXString = Declared(CXString)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_getRawCommentText_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, CXCursor.layout)
private val clang_Cursor_getRawCommentText_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getRawCommentText")
private val clang_Cursor_getRawCommentText_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getRawCommentText_ADDR, clang_Cursor_getRawCommentText_DESC)

fun clang_Cursor_getRawCommentText(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_Cursor_getRawCommentText_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getBriefCommentText typedef CXString = Declared(CXString)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_getBriefCommentText_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, CXCursor.layout)
private val clang_Cursor_getBriefCommentText_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getBriefCommentText")
private val clang_Cursor_getBriefCommentText_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getBriefCommentText_ADDR, clang_Cursor_getBriefCommentText_DESC)

fun clang_Cursor_getBriefCommentText(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_Cursor_getBriefCommentText_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getMangling typedef CXString = Declared(CXString)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_getMangling_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, CXCursor.layout)
private val clang_Cursor_getMangling_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getMangling")
private val clang_Cursor_getMangling_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getMangling_ADDR, clang_Cursor_getMangling_DESC)

fun clang_Cursor_getMangling(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_Cursor_getMangling_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getCXXManglings (typedef CXStringSet = Declared(CXStringSet))*(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_getCXXManglings_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, CXCursor.layout)
private val clang_Cursor_getCXXManglings_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getCXXManglings")
private val clang_Cursor_getCXXManglings_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getCXXManglings_ADDR, clang_Cursor_getCXXManglings_DESC)

fun clang_Cursor_getCXXManglings(arg0: MemorySegment): MemorySegment {
    try {
        return clang_Cursor_getCXXManglings_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getObjCManglings (typedef CXStringSet = Declared(CXStringSet))*(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_getObjCManglings_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, CXCursor.layout)
private val clang_Cursor_getObjCManglings_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getObjCManglings")
private val clang_Cursor_getObjCManglings_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getObjCManglings_ADDR, clang_Cursor_getObjCManglings_DESC)

fun clang_Cursor_getObjCManglings(arg0: MemorySegment): MemorySegment {
    try {
        return clang_Cursor_getObjCManglings_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getGCCAssemblyTemplate typedef CXString = Declared(CXString)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_getGCCAssemblyTemplate_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, CXCursor.layout)
private val clang_Cursor_getGCCAssemblyTemplate_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getGCCAssemblyTemplate")
private val clang_Cursor_getGCCAssemblyTemplate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getGCCAssemblyTemplate_ADDR, clang_Cursor_getGCCAssemblyTemplate_DESC)

fun clang_Cursor_getGCCAssemblyTemplate(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_Cursor_getGCCAssemblyTemplate_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_isGCCAssemblyHasGoto UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_isGCCAssemblyHasGoto_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_isGCCAssemblyHasGoto_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_isGCCAssemblyHasGoto")
private val clang_Cursor_isGCCAssemblyHasGoto_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_isGCCAssemblyHasGoto_ADDR, clang_Cursor_isGCCAssemblyHasGoto_DESC)

fun clang_Cursor_isGCCAssemblyHasGoto(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_isGCCAssemblyHasGoto_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getGCCAssemblyNumOutputs UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_getGCCAssemblyNumOutputs_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_getGCCAssemblyNumOutputs_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getGCCAssemblyNumOutputs")
private val clang_Cursor_getGCCAssemblyNumOutputs_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getGCCAssemblyNumOutputs_ADDR, clang_Cursor_getGCCAssemblyNumOutputs_DESC)

fun clang_Cursor_getGCCAssemblyNumOutputs(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_getGCCAssemblyNumOutputs_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getGCCAssemblyNumInputs UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_getGCCAssemblyNumInputs_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_getGCCAssemblyNumInputs_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getGCCAssemblyNumInputs")
private val clang_Cursor_getGCCAssemblyNumInputs_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getGCCAssemblyNumInputs_ADDR, clang_Cursor_getGCCAssemblyNumInputs_DESC)

fun clang_Cursor_getGCCAssemblyNumInputs(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_getGCCAssemblyNumInputs_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getGCCAssemblyInput UNSIGNED = Int(typedef CXCursor = Declared(CXCursor),UNSIGNED = Int,(typedef CXString = Declared(CXString))*,(typedef CXCursor = Declared(CXCursor))*)
 */
private val clang_Cursor_getGCCAssemblyInput_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_Cursor_getGCCAssemblyInput_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getGCCAssemblyInput")
private val clang_Cursor_getGCCAssemblyInput_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getGCCAssemblyInput_ADDR, clang_Cursor_getGCCAssemblyInput_DESC)

fun clang_Cursor_getGCCAssemblyInput(arg0: MemorySegment, arg1: Int, arg2: MemorySegment, arg3: MemorySegment): Int {
    try {
        return clang_Cursor_getGCCAssemblyInput_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getGCCAssemblyOutput UNSIGNED = Int(typedef CXCursor = Declared(CXCursor),UNSIGNED = Int,(typedef CXString = Declared(CXString))*,(typedef CXCursor = Declared(CXCursor))*)
 */
private val clang_Cursor_getGCCAssemblyOutput_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_Cursor_getGCCAssemblyOutput_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getGCCAssemblyOutput")
private val clang_Cursor_getGCCAssemblyOutput_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getGCCAssemblyOutput_ADDR, clang_Cursor_getGCCAssemblyOutput_DESC)

fun clang_Cursor_getGCCAssemblyOutput(arg0: MemorySegment, arg1: Int, arg2: MemorySegment, arg3: MemorySegment): Int {
    try {
        return clang_Cursor_getGCCAssemblyOutput_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getGCCAssemblyNumClobbers UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_getGCCAssemblyNumClobbers_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_getGCCAssemblyNumClobbers_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getGCCAssemblyNumClobbers")
private val clang_Cursor_getGCCAssemblyNumClobbers_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getGCCAssemblyNumClobbers_ADDR, clang_Cursor_getGCCAssemblyNumClobbers_DESC)

fun clang_Cursor_getGCCAssemblyNumClobbers(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_getGCCAssemblyNumClobbers_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_getGCCAssemblyClobber typedef CXString = Declared(CXString)(typedef CXCursor = Declared(CXCursor),UNSIGNED = Int)
 */
private val clang_Cursor_getGCCAssemblyClobber_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, CXCursor.layout, ValueLayout.JAVA_INT)
private val clang_Cursor_getGCCAssemblyClobber_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getGCCAssemblyClobber")
private val clang_Cursor_getGCCAssemblyClobber_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getGCCAssemblyClobber_ADDR, clang_Cursor_getGCCAssemblyClobber_DESC)

fun clang_Cursor_getGCCAssemblyClobber(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return clang_Cursor_getGCCAssemblyClobber_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Cursor_isGCCAssemblyVolatile UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_isGCCAssemblyVolatile_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_Cursor_isGCCAssemblyVolatile_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_isGCCAssemblyVolatile")
private val clang_Cursor_isGCCAssemblyVolatile_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_isGCCAssemblyVolatile_ADDR, clang_Cursor_isGCCAssemblyVolatile_DESC)

fun clang_Cursor_isGCCAssemblyVolatile(arg0: MemorySegment): Int {
    try {
        return clang_Cursor_isGCCAssemblyVolatile_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : typedef (Void)* CXModule;}
 */
typealias CXModule = MemorySegment?

/**
 * {@snippet lang=c : clang_Cursor_getModule typedef CXModule = (Void)*(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_getModule_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, CXCursor.layout)
private val clang_Cursor_getModule_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_getModule")
private val clang_Cursor_getModule_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_getModule_ADDR, clang_Cursor_getModule_DESC)

fun clang_Cursor_getModule(arg0: MemorySegment): MemorySegment {
    try {
        return clang_Cursor_getModule_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getModuleForFile typedef CXModule = (Void)*(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*,typedef CXFile = (Void)*)
 */
private val clang_getModuleForFile_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_getModuleForFile_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getModuleForFile")
private val clang_getModuleForFile_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getModuleForFile_ADDR, clang_getModuleForFile_DESC)

fun clang_getModuleForFile(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return clang_getModuleForFile_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Module_getASTFile typedef CXFile = (Void)*(typedef CXModule = (Void)*)
 */
private val clang_Module_getASTFile_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_Module_getASTFile_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Module_getASTFile")
private val clang_Module_getASTFile_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Module_getASTFile_ADDR, clang_Module_getASTFile_DESC)

fun clang_Module_getASTFile(arg0: MemorySegment): MemorySegment {
    try {
        return clang_Module_getASTFile_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Module_getParent typedef CXModule = (Void)*(typedef CXModule = (Void)*)
 */
private val clang_Module_getParent_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_Module_getParent_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Module_getParent")
private val clang_Module_getParent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Module_getParent_ADDR, clang_Module_getParent_DESC)

fun clang_Module_getParent(arg0: MemorySegment): MemorySegment {
    try {
        return clang_Module_getParent_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Module_getName typedef CXString = Declared(CXString)(typedef CXModule = (Void)*)
 */
private val clang_Module_getName_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS)
private val clang_Module_getName_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Module_getName")
private val clang_Module_getName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Module_getName_ADDR, clang_Module_getName_DESC)

fun clang_Module_getName(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_Module_getName_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Module_getFullName typedef CXString = Declared(CXString)(typedef CXModule = (Void)*)
 */
private val clang_Module_getFullName_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS)
private val clang_Module_getFullName_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Module_getFullName")
private val clang_Module_getFullName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Module_getFullName_ADDR, clang_Module_getFullName_DESC)

fun clang_Module_getFullName(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_Module_getFullName_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Module_isSystem Int(typedef CXModule = (Void)*)
 */
private val clang_Module_isSystem_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_Module_isSystem_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Module_isSystem")
private val clang_Module_isSystem_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Module_isSystem_ADDR, clang_Module_isSystem_DESC)

fun clang_Module_isSystem(arg0: MemorySegment): Int {
    try {
        return clang_Module_isSystem_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Module_getNumTopLevelHeaders UNSIGNED = Int(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*,typedef CXModule = (Void)*)
 */
private val clang_Module_getNumTopLevelHeaders_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_Module_getNumTopLevelHeaders_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Module_getNumTopLevelHeaders")
private val clang_Module_getNumTopLevelHeaders_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Module_getNumTopLevelHeaders_ADDR, clang_Module_getNumTopLevelHeaders_DESC)

fun clang_Module_getNumTopLevelHeaders(arg0: MemorySegment, arg1: MemorySegment): Int {
    try {
        return clang_Module_getNumTopLevelHeaders_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_Module_getTopLevelHeader typedef CXFile = (Void)*(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*,typedef CXModule = (Void)*,UNSIGNED = Int)
 */
private val clang_Module_getTopLevelHeader_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_Module_getTopLevelHeader_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Module_getTopLevelHeader")
private val clang_Module_getTopLevelHeader_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Module_getTopLevelHeader_ADDR, clang_Module_getTopLevelHeader_DESC)

fun clang_Module_getTopLevelHeader(arg0: MemorySegment, arg1: MemorySegment, arg2: Int): MemorySegment {
    try {
        return clang_Module_getTopLevelHeader_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_CXXConstructor_isConvertingConstructor UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_CXXConstructor_isConvertingConstructor_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_CXXConstructor_isConvertingConstructor_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_CXXConstructor_isConvertingConstructor")
private val clang_CXXConstructor_isConvertingConstructor_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_CXXConstructor_isConvertingConstructor_ADDR, clang_CXXConstructor_isConvertingConstructor_DESC)

fun clang_CXXConstructor_isConvertingConstructor(arg0: MemorySegment): Int {
    try {
        return clang_CXXConstructor_isConvertingConstructor_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_CXXConstructor_isCopyConstructor UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_CXXConstructor_isCopyConstructor_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_CXXConstructor_isCopyConstructor_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_CXXConstructor_isCopyConstructor")
private val clang_CXXConstructor_isCopyConstructor_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_CXXConstructor_isCopyConstructor_ADDR, clang_CXXConstructor_isCopyConstructor_DESC)

fun clang_CXXConstructor_isCopyConstructor(arg0: MemorySegment): Int {
    try {
        return clang_CXXConstructor_isCopyConstructor_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_CXXConstructor_isDefaultConstructor UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_CXXConstructor_isDefaultConstructor_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_CXXConstructor_isDefaultConstructor_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_CXXConstructor_isDefaultConstructor")
private val clang_CXXConstructor_isDefaultConstructor_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_CXXConstructor_isDefaultConstructor_ADDR, clang_CXXConstructor_isDefaultConstructor_DESC)

fun clang_CXXConstructor_isDefaultConstructor(arg0: MemorySegment): Int {
    try {
        return clang_CXXConstructor_isDefaultConstructor_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_CXXConstructor_isMoveConstructor UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_CXXConstructor_isMoveConstructor_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_CXXConstructor_isMoveConstructor_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_CXXConstructor_isMoveConstructor")
private val clang_CXXConstructor_isMoveConstructor_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_CXXConstructor_isMoveConstructor_ADDR, clang_CXXConstructor_isMoveConstructor_DESC)

fun clang_CXXConstructor_isMoveConstructor(arg0: MemorySegment): Int {
    try {
        return clang_CXXConstructor_isMoveConstructor_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_CXXField_isMutable UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_CXXField_isMutable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_CXXField_isMutable_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_CXXField_isMutable")
private val clang_CXXField_isMutable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_CXXField_isMutable_ADDR, clang_CXXField_isMutable_DESC)

fun clang_CXXField_isMutable(arg0: MemorySegment): Int {
    try {
        return clang_CXXField_isMutable_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_CXXMethod_isDefaulted UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_CXXMethod_isDefaulted_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_CXXMethod_isDefaulted_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_CXXMethod_isDefaulted")
private val clang_CXXMethod_isDefaulted_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_CXXMethod_isDefaulted_ADDR, clang_CXXMethod_isDefaulted_DESC)

fun clang_CXXMethod_isDefaulted(arg0: MemorySegment): Int {
    try {
        return clang_CXXMethod_isDefaulted_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_CXXMethod_isDeleted UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_CXXMethod_isDeleted_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_CXXMethod_isDeleted_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_CXXMethod_isDeleted")
private val clang_CXXMethod_isDeleted_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_CXXMethod_isDeleted_ADDR, clang_CXXMethod_isDeleted_DESC)

fun clang_CXXMethod_isDeleted(arg0: MemorySegment): Int {
    try {
        return clang_CXXMethod_isDeleted_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_CXXMethod_isPureVirtual UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_CXXMethod_isPureVirtual_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_CXXMethod_isPureVirtual_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_CXXMethod_isPureVirtual")
private val clang_CXXMethod_isPureVirtual_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_CXXMethod_isPureVirtual_ADDR, clang_CXXMethod_isPureVirtual_DESC)

fun clang_CXXMethod_isPureVirtual(arg0: MemorySegment): Int {
    try {
        return clang_CXXMethod_isPureVirtual_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_CXXMethod_isStatic UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_CXXMethod_isStatic_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_CXXMethod_isStatic_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_CXXMethod_isStatic")
private val clang_CXXMethod_isStatic_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_CXXMethod_isStatic_ADDR, clang_CXXMethod_isStatic_DESC)

fun clang_CXXMethod_isStatic(arg0: MemorySegment): Int {
    try {
        return clang_CXXMethod_isStatic_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_CXXMethod_isVirtual UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_CXXMethod_isVirtual_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_CXXMethod_isVirtual_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_CXXMethod_isVirtual")
private val clang_CXXMethod_isVirtual_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_CXXMethod_isVirtual_ADDR, clang_CXXMethod_isVirtual_DESC)

fun clang_CXXMethod_isVirtual(arg0: MemorySegment): Int {
    try {
        return clang_CXXMethod_isVirtual_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_CXXMethod_isCopyAssignmentOperator UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_CXXMethod_isCopyAssignmentOperator_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_CXXMethod_isCopyAssignmentOperator_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_CXXMethod_isCopyAssignmentOperator")
private val clang_CXXMethod_isCopyAssignmentOperator_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_CXXMethod_isCopyAssignmentOperator_ADDR, clang_CXXMethod_isCopyAssignmentOperator_DESC)

fun clang_CXXMethod_isCopyAssignmentOperator(arg0: MemorySegment): Int {
    try {
        return clang_CXXMethod_isCopyAssignmentOperator_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_CXXMethod_isMoveAssignmentOperator UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_CXXMethod_isMoveAssignmentOperator_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_CXXMethod_isMoveAssignmentOperator_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_CXXMethod_isMoveAssignmentOperator")
private val clang_CXXMethod_isMoveAssignmentOperator_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_CXXMethod_isMoveAssignmentOperator_ADDR, clang_CXXMethod_isMoveAssignmentOperator_DESC)

fun clang_CXXMethod_isMoveAssignmentOperator(arg0: MemorySegment): Int {
    try {
        return clang_CXXMethod_isMoveAssignmentOperator_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_CXXMethod_isExplicit UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_CXXMethod_isExplicit_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_CXXMethod_isExplicit_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_CXXMethod_isExplicit")
private val clang_CXXMethod_isExplicit_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_CXXMethod_isExplicit_ADDR, clang_CXXMethod_isExplicit_DESC)

fun clang_CXXMethod_isExplicit(arg0: MemorySegment): Int {
    try {
        return clang_CXXMethod_isExplicit_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_CXXRecord_isAbstract UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_CXXRecord_isAbstract_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_CXXRecord_isAbstract_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_CXXRecord_isAbstract")
private val clang_CXXRecord_isAbstract_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_CXXRecord_isAbstract_ADDR, clang_CXXRecord_isAbstract_DESC)

fun clang_CXXRecord_isAbstract(arg0: MemorySegment): Int {
    try {
        return clang_CXXRecord_isAbstract_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_EnumDecl_isScoped UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_EnumDecl_isScoped_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_EnumDecl_isScoped_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_EnumDecl_isScoped")
private val clang_EnumDecl_isScoped_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_EnumDecl_isScoped_ADDR, clang_EnumDecl_isScoped_DESC)

fun clang_EnumDecl_isScoped(arg0: MemorySegment): Int {
    try {
        return clang_EnumDecl_isScoped_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_CXXMethod_isConst UNSIGNED = Int(typedef CXCursor = Declared(CXCursor))
 */
private val clang_CXXMethod_isConst_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_CXXMethod_isConst_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_CXXMethod_isConst")
private val clang_CXXMethod_isConst_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_CXXMethod_isConst_ADDR, clang_CXXMethod_isConst_DESC)

fun clang_CXXMethod_isConst(arg0: MemorySegment): Int {
    try {
        return clang_CXXMethod_isConst_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getTemplateCursorKind Declared(CXCursorKind)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getTemplateCursorKind_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_getTemplateCursorKind_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getTemplateCursorKind")
private val clang_getTemplateCursorKind_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getTemplateCursorKind_ADDR, clang_getTemplateCursorKind_DESC)

fun clang_getTemplateCursorKind(arg0: MemorySegment): Int {
    try {
        return clang_getTemplateCursorKind_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getSpecializedCursorTemplate typedef CXCursor = Declared(CXCursor)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getSpecializedCursorTemplate_DESC: FunctionDescriptor = FunctionDescriptor.of(CXCursor.layout, CXCursor.layout)
private val clang_getSpecializedCursorTemplate_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getSpecializedCursorTemplate")
private val clang_getSpecializedCursorTemplate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getSpecializedCursorTemplate_ADDR, clang_getSpecializedCursorTemplate_DESC)

fun clang_getSpecializedCursorTemplate(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getSpecializedCursorTemplate_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCursorReferenceNameRange typedef CXSourceRange = Declared(CXSourceRange)(typedef CXCursor = Declared(CXCursor),UNSIGNED = Int,UNSIGNED = Int)
 */
private val clang_getCursorReferenceNameRange_DESC: FunctionDescriptor = FunctionDescriptor.of(CXSourceRange.layout, CXCursor.layout, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val clang_getCursorReferenceNameRange_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorReferenceNameRange")
private val clang_getCursorReferenceNameRange_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorReferenceNameRange_ADDR, clang_getCursorReferenceNameRange_DESC)

fun clang_getCursorReferenceNameRange(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Int, arg2: Int): MemorySegment {
    try {
        return clang_getCursorReferenceNameRange_HANDLE.invokeExact(allocator, arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXNameRange_WantQualifier 1
 */
fun CXNameRange_WantQualifier(): Int = 1

/**
 * {@snippet lang=c : #define CXNameRange_WantTemplateArgs 2
 */
fun CXNameRange_WantTemplateArgs(): Int = 2

/**
 * {@snippet lang=c : #define CXNameRange_WantSinglePiece 4
 */
fun CXNameRange_WantSinglePiece(): Int = 4

/**
 * {@snippet lang=c : #define CXToken_Punctuation 0
 */
fun CXToken_Punctuation(): Int = 0

/**
 * {@snippet lang=c : #define CXToken_Keyword 1
 */
fun CXToken_Keyword(): Int = 1

/**
 * {@snippet lang=c : #define CXToken_Identifier 2
 */
fun CXToken_Identifier(): Int = 2

/**
 * {@snippet lang=c : #define CXToken_Literal 3
 */
fun CXToken_Literal(): Int = 3

/**
 * {@snippet lang=c : #define CXToken_Comment 4
 */
fun CXToken_Comment(): Int = 4

/**
 * {@snippet lang=c : STRUCT CXToken
 */
class CXToken {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            MemoryLayout.sequenceLayout(4, ValueLayout.JAVA_INT).withName("int_data"),
            ValueLayout.ADDRESS.withName("ptr_data")
        ).withName("CXToken")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        
        fun int_data(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("int_data")), layout.select(groupElement("int_data")).byteSize())
        
        val ptr_data_VH: VarHandle = layout.varHandle(groupElement("ptr_data"))
        
        @Suppress("UNCHECKED_CAST")
        fun ptr_data(segment: MemorySegment): MemorySegment? =
            ptr_data_VH.get(segment, 0L) as MemorySegment
        
        fun ptr_data(segment: MemorySegment, value: MemorySegment) =
            ptr_data_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : clang_getToken (typedef CXToken = Declared(CXToken))*(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*,typedef CXSourceLocation = Declared(CXSourceLocation))
 */
private val clang_getToken_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, CXSourceLocation.layout)
private val clang_getToken_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getToken")
private val clang_getToken_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getToken_ADDR, clang_getToken_DESC)

fun clang_getToken(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return clang_getToken_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getTokenKind typedef CXTokenKind = Declared(CXTokenKind)(typedef CXToken = Declared(CXToken))
 */
private val clang_getTokenKind_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXToken.layout)
private val clang_getTokenKind_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getTokenKind")
private val clang_getTokenKind_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getTokenKind_ADDR, clang_getTokenKind_DESC)

fun clang_getTokenKind(arg0: MemorySegment): Int {
    try {
        return clang_getTokenKind_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getTokenSpelling typedef CXString = Declared(CXString)(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*,typedef CXToken = Declared(CXToken))
 */
private val clang_getTokenSpelling_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS, CXToken.layout)
private val clang_getTokenSpelling_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getTokenSpelling")
private val clang_getTokenSpelling_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getTokenSpelling_ADDR, clang_getTokenSpelling_DESC)

fun clang_getTokenSpelling(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return clang_getTokenSpelling_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getTokenLocation typedef CXSourceLocation = Declared(CXSourceLocation)(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*,typedef CXToken = Declared(CXToken))
 */
private val clang_getTokenLocation_DESC: FunctionDescriptor = FunctionDescriptor.of(CXSourceLocation.layout, ValueLayout.ADDRESS, CXToken.layout)
private val clang_getTokenLocation_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getTokenLocation")
private val clang_getTokenLocation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getTokenLocation_ADDR, clang_getTokenLocation_DESC)

fun clang_getTokenLocation(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return clang_getTokenLocation_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getTokenExtent typedef CXSourceRange = Declared(CXSourceRange)(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*,typedef CXToken = Declared(CXToken))
 */
private val clang_getTokenExtent_DESC: FunctionDescriptor = FunctionDescriptor.of(CXSourceRange.layout, ValueLayout.ADDRESS, CXToken.layout)
private val clang_getTokenExtent_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getTokenExtent")
private val clang_getTokenExtent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getTokenExtent_ADDR, clang_getTokenExtent_DESC)

fun clang_getTokenExtent(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return clang_getTokenExtent_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_tokenize Void(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*,typedef CXSourceRange = Declared(CXSourceRange),((typedef CXToken = Declared(CXToken))*)*,(UNSIGNED = Int)*)
 */
private val clang_tokenize_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CXSourceRange.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_tokenize_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_tokenize")
private val clang_tokenize_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_tokenize_ADDR, clang_tokenize_DESC)

fun clang_tokenize(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): Unit {
    try {
        clang_tokenize_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_annotateTokens Void(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*,(typedef CXToken = Declared(CXToken))*,UNSIGNED = Int,(typedef CXCursor = Declared(CXCursor))*)
 */
private val clang_annotateTokens_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_annotateTokens_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_annotateTokens")
private val clang_annotateTokens_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_annotateTokens_ADDR, clang_annotateTokens_DESC)

fun clang_annotateTokens(arg0: MemorySegment, arg1: MemorySegment, arg2: Int, arg3: MemorySegment): Unit {
    try {
        clang_annotateTokens_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_disposeTokens Void(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*,(typedef CXToken = Declared(CXToken))*,UNSIGNED = Int)
 */
private val clang_disposeTokens_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_disposeTokens_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_disposeTokens")
private val clang_disposeTokens_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_disposeTokens_ADDR, clang_disposeTokens_DESC)

fun clang_disposeTokens(arg0: MemorySegment, arg1: MemorySegment, arg2: Int): Unit {
    try {
        clang_disposeTokens_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCursorKindSpelling typedef CXString = Declared(CXString)(Declared(CXCursorKind))
 */
private val clang_getCursorKindSpelling_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.JAVA_INT)
private val clang_getCursorKindSpelling_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorKindSpelling")
private val clang_getCursorKindSpelling_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorKindSpelling_ADDR, clang_getCursorKindSpelling_DESC)

fun clang_getCursorKindSpelling(allocator: SegmentAllocator, arg0: Int): MemorySegment {
    try {
        return clang_getCursorKindSpelling_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getDefinitionSpellingAndExtent Void(typedef CXCursor = Declared(CXCursor),((Char)*)*,((Char)*)*,(UNSIGNED = Int)*,(UNSIGNED = Int)*,(UNSIGNED = Int)*,(UNSIGNED = Int)*)
 */
private val clang_getDefinitionSpellingAndExtent_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CXCursor.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_getDefinitionSpellingAndExtent_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getDefinitionSpellingAndExtent")
private val clang_getDefinitionSpellingAndExtent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getDefinitionSpellingAndExtent_ADDR, clang_getDefinitionSpellingAndExtent_DESC)

fun clang_getDefinitionSpellingAndExtent(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment, arg5: MemorySegment, arg6: MemorySegment): Unit {
    try {
        clang_getDefinitionSpellingAndExtent_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_enableStackTraces Void()
 */
private val clang_enableStackTraces_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid()
private val clang_enableStackTraces_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_enableStackTraces")
private val clang_enableStackTraces_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_enableStackTraces_ADDR, clang_enableStackTraces_DESC)

fun clang_enableStackTraces(): Unit {
    try {
        clang_enableStackTraces_HANDLE.invokeExact()
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_executeOnThread Void((Void((Void)*))*,(Void)*,UNSIGNED = Int)
 */
private val clang_executeOnThread_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_executeOnThread_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_executeOnThread")
private val clang_executeOnThread_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_executeOnThread_ADDR, clang_executeOnThread_DESC)

fun clang_executeOnThread(arg0: MemorySegment, arg1: MemorySegment, arg2: Int): Unit {
    try {
        clang_executeOnThread_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : typedef (Void)* CXCompletionString;}
 */
typealias CXCompletionString = MemorySegment?

/**
 * {@snippet lang=c : STRUCT CXCompletionResult
 */
class CXCompletionResult {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("CursorKind"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.ADDRESS.withName("CompletionString")
        ).withName("CXCompletionResult")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val CursorKind_VH: VarHandle = layout.varHandle(groupElement("CursorKind"))
        
        @Suppress("UNCHECKED_CAST")
        fun CursorKind(segment: MemorySegment): Int =
            CursorKind_VH.get(segment, 0L) as Int
        
        fun CursorKind(segment: MemorySegment, value: Int) =
            CursorKind_VH.set(segment, 0L, value)
        
        val CompletionString_VH: VarHandle = layout.varHandle(groupElement("CompletionString"))
        
        @Suppress("UNCHECKED_CAST")
        fun CompletionString(segment: MemorySegment): MemorySegment? =
            CompletionString_VH.get(segment, 0L) as MemorySegment
        
        fun CompletionString(segment: MemorySegment, value: MemorySegment) =
            CompletionString_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : #define CXCompletionChunk_Optional 0
 */
fun CXCompletionChunk_Optional(): Int = 0

/**
 * {@snippet lang=c : #define CXCompletionChunk_TypedText 1
 */
fun CXCompletionChunk_TypedText(): Int = 1

/**
 * {@snippet lang=c : #define CXCompletionChunk_Text 2
 */
fun CXCompletionChunk_Text(): Int = 2

/**
 * {@snippet lang=c : #define CXCompletionChunk_Placeholder 3
 */
fun CXCompletionChunk_Placeholder(): Int = 3

/**
 * {@snippet lang=c : #define CXCompletionChunk_Informative 4
 */
fun CXCompletionChunk_Informative(): Int = 4

/**
 * {@snippet lang=c : #define CXCompletionChunk_CurrentParameter 5
 */
fun CXCompletionChunk_CurrentParameter(): Int = 5

/**
 * {@snippet lang=c : #define CXCompletionChunk_LeftParen 6
 */
fun CXCompletionChunk_LeftParen(): Int = 6

/**
 * {@snippet lang=c : #define CXCompletionChunk_RightParen 7
 */
fun CXCompletionChunk_RightParen(): Int = 7

/**
 * {@snippet lang=c : #define CXCompletionChunk_LeftBracket 8
 */
fun CXCompletionChunk_LeftBracket(): Int = 8

/**
 * {@snippet lang=c : #define CXCompletionChunk_RightBracket 9
 */
fun CXCompletionChunk_RightBracket(): Int = 9

/**
 * {@snippet lang=c : #define CXCompletionChunk_LeftBrace 10
 */
fun CXCompletionChunk_LeftBrace(): Int = 10

/**
 * {@snippet lang=c : #define CXCompletionChunk_RightBrace 11
 */
fun CXCompletionChunk_RightBrace(): Int = 11

/**
 * {@snippet lang=c : #define CXCompletionChunk_LeftAngle 12
 */
fun CXCompletionChunk_LeftAngle(): Int = 12

/**
 * {@snippet lang=c : #define CXCompletionChunk_RightAngle 13
 */
fun CXCompletionChunk_RightAngle(): Int = 13

/**
 * {@snippet lang=c : #define CXCompletionChunk_Comma 14
 */
fun CXCompletionChunk_Comma(): Int = 14

/**
 * {@snippet lang=c : #define CXCompletionChunk_ResultType 15
 */
fun CXCompletionChunk_ResultType(): Int = 15

/**
 * {@snippet lang=c : #define CXCompletionChunk_Colon 16
 */
fun CXCompletionChunk_Colon(): Int = 16

/**
 * {@snippet lang=c : #define CXCompletionChunk_SemiColon 17
 */
fun CXCompletionChunk_SemiColon(): Int = 17

/**
 * {@snippet lang=c : #define CXCompletionChunk_Equal 18
 */
fun CXCompletionChunk_Equal(): Int = 18

/**
 * {@snippet lang=c : #define CXCompletionChunk_HorizontalSpace 19
 */
fun CXCompletionChunk_HorizontalSpace(): Int = 19

/**
 * {@snippet lang=c : #define CXCompletionChunk_VerticalSpace 20
 */
fun CXCompletionChunk_VerticalSpace(): Int = 20

/**
 * {@snippet lang=c : clang_getCompletionChunkKind Declared(CXCompletionChunkKind)(typedef CXCompletionString = (Void)*,UNSIGNED = Int)
 */
private val clang_getCompletionChunkKind_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_getCompletionChunkKind_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCompletionChunkKind")
private val clang_getCompletionChunkKind_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCompletionChunkKind_ADDR, clang_getCompletionChunkKind_DESC)

fun clang_getCompletionChunkKind(arg0: MemorySegment, arg1: Int): Int {
    try {
        return clang_getCompletionChunkKind_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCompletionChunkText typedef CXString = Declared(CXString)(typedef CXCompletionString = (Void)*,UNSIGNED = Int)
 */
private val clang_getCompletionChunkText_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_getCompletionChunkText_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCompletionChunkText")
private val clang_getCompletionChunkText_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCompletionChunkText_ADDR, clang_getCompletionChunkText_DESC)

fun clang_getCompletionChunkText(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return clang_getCompletionChunkText_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCompletionChunkCompletionString typedef CXCompletionString = (Void)*(typedef CXCompletionString = (Void)*,UNSIGNED = Int)
 */
private val clang_getCompletionChunkCompletionString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_getCompletionChunkCompletionString_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCompletionChunkCompletionString")
private val clang_getCompletionChunkCompletionString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCompletionChunkCompletionString_ADDR, clang_getCompletionChunkCompletionString_DESC)

fun clang_getCompletionChunkCompletionString(arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return clang_getCompletionChunkCompletionString_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getNumCompletionChunks UNSIGNED = Int(typedef CXCompletionString = (Void)*)
 */
private val clang_getNumCompletionChunks_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_getNumCompletionChunks_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getNumCompletionChunks")
private val clang_getNumCompletionChunks_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getNumCompletionChunks_ADDR, clang_getNumCompletionChunks_DESC)

fun clang_getNumCompletionChunks(arg0: MemorySegment): Int {
    try {
        return clang_getNumCompletionChunks_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCompletionPriority UNSIGNED = Int(typedef CXCompletionString = (Void)*)
 */
private val clang_getCompletionPriority_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_getCompletionPriority_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCompletionPriority")
private val clang_getCompletionPriority_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCompletionPriority_ADDR, clang_getCompletionPriority_DESC)

fun clang_getCompletionPriority(arg0: MemorySegment): Int {
    try {
        return clang_getCompletionPriority_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCompletionAvailability Declared(CXAvailabilityKind)(typedef CXCompletionString = (Void)*)
 */
private val clang_getCompletionAvailability_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_getCompletionAvailability_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCompletionAvailability")
private val clang_getCompletionAvailability_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCompletionAvailability_ADDR, clang_getCompletionAvailability_DESC)

fun clang_getCompletionAvailability(arg0: MemorySegment): Int {
    try {
        return clang_getCompletionAvailability_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCompletionNumAnnotations UNSIGNED = Int(typedef CXCompletionString = (Void)*)
 */
private val clang_getCompletionNumAnnotations_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_getCompletionNumAnnotations_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCompletionNumAnnotations")
private val clang_getCompletionNumAnnotations_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCompletionNumAnnotations_ADDR, clang_getCompletionNumAnnotations_DESC)

fun clang_getCompletionNumAnnotations(arg0: MemorySegment): Int {
    try {
        return clang_getCompletionNumAnnotations_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCompletionAnnotation typedef CXString = Declared(CXString)(typedef CXCompletionString = (Void)*,UNSIGNED = Int)
 */
private val clang_getCompletionAnnotation_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_getCompletionAnnotation_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCompletionAnnotation")
private val clang_getCompletionAnnotation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCompletionAnnotation_ADDR, clang_getCompletionAnnotation_DESC)

fun clang_getCompletionAnnotation(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return clang_getCompletionAnnotation_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCompletionParent typedef CXString = Declared(CXString)(typedef CXCompletionString = (Void)*,(Declared(CXCursorKind))*)
 */
private val clang_getCompletionParent_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_getCompletionParent_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCompletionParent")
private val clang_getCompletionParent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCompletionParent_ADDR, clang_getCompletionParent_DESC)

fun clang_getCompletionParent(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return clang_getCompletionParent_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCompletionBriefComment typedef CXString = Declared(CXString)(typedef CXCompletionString = (Void)*)
 */
private val clang_getCompletionBriefComment_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS)
private val clang_getCompletionBriefComment_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCompletionBriefComment")
private val clang_getCompletionBriefComment_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCompletionBriefComment_ADDR, clang_getCompletionBriefComment_DESC)

fun clang_getCompletionBriefComment(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_getCompletionBriefComment_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCursorCompletionString typedef CXCompletionString = (Void)*(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getCursorCompletionString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, CXCursor.layout)
private val clang_getCursorCompletionString_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorCompletionString")
private val clang_getCursorCompletionString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorCompletionString_ADDR, clang_getCursorCompletionString_DESC)

fun clang_getCursorCompletionString(arg0: MemorySegment): MemorySegment {
    try {
        return clang_getCursorCompletionString_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : STRUCT CXCodeCompleteResults
 */
class CXCodeCompleteResults {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("Results"),
            ValueLayout.JAVA_INT.withName("NumResults"),
            MemoryLayout.paddingLayout(4)
        ).withName("CXCodeCompleteResults")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val Results_VH: VarHandle = layout.varHandle(groupElement("Results"))
        
        @Suppress("UNCHECKED_CAST")
        fun Results(segment: MemorySegment): MemorySegment? =
            Results_VH.get(segment, 0L) as MemorySegment
        
        fun Results(segment: MemorySegment, value: MemorySegment) =
            Results_VH.set(segment, 0L, value)
        
        val NumResults_VH: VarHandle = layout.varHandle(groupElement("NumResults"))
        
        @Suppress("UNCHECKED_CAST")
        fun NumResults(segment: MemorySegment): Int =
            NumResults_VH.get(segment, 0L) as Int
        
        fun NumResults(segment: MemorySegment, value: Int) =
            NumResults_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : clang_getCompletionNumFixIts UNSIGNED = Int((typedef CXCodeCompleteResults = Declared(CXCodeCompleteResults))*,UNSIGNED = Int)
 */
private val clang_getCompletionNumFixIts_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_getCompletionNumFixIts_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCompletionNumFixIts")
private val clang_getCompletionNumFixIts_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCompletionNumFixIts_ADDR, clang_getCompletionNumFixIts_DESC)

fun clang_getCompletionNumFixIts(arg0: MemorySegment, arg1: Int): Int {
    try {
        return clang_getCompletionNumFixIts_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCompletionFixIt typedef CXString = Declared(CXString)((typedef CXCodeCompleteResults = Declared(CXCodeCompleteResults))*,UNSIGNED = Int,UNSIGNED = Int,(typedef CXSourceRange = Declared(CXSourceRange))*)
 */
private val clang_getCompletionFixIt_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_getCompletionFixIt_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCompletionFixIt")
private val clang_getCompletionFixIt_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCompletionFixIt_ADDR, clang_getCompletionFixIt_DESC)

fun clang_getCompletionFixIt(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Int, arg2: Int, arg3: MemorySegment): MemorySegment {
    try {
        return clang_getCompletionFixIt_HANDLE.invokeExact(allocator, arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXCodeComplete_IncludeMacros 1
 */
fun CXCodeComplete_IncludeMacros(): Int = 1

/**
 * {@snippet lang=c : #define CXCodeComplete_IncludeCodePatterns 2
 */
fun CXCodeComplete_IncludeCodePatterns(): Int = 2

/**
 * {@snippet lang=c : #define CXCodeComplete_IncludeBriefComments 4
 */
fun CXCodeComplete_IncludeBriefComments(): Int = 4

/**
 * {@snippet lang=c : #define CXCodeComplete_SkipPreamble 8
 */
fun CXCodeComplete_SkipPreamble(): Int = 8

/**
 * {@snippet lang=c : #define CXCodeComplete_IncludeCompletionsWithFixIts 16
 */
fun CXCodeComplete_IncludeCompletionsWithFixIts(): Int = 16

/**
 * {@snippet lang=c : #define CXCompletionContext_Unexposed 0
 */
fun CXCompletionContext_Unexposed(): Int = 0

/**
 * {@snippet lang=c : #define CXCompletionContext_AnyType 1
 */
fun CXCompletionContext_AnyType(): Int = 1

/**
 * {@snippet lang=c : #define CXCompletionContext_AnyValue 2
 */
fun CXCompletionContext_AnyValue(): Int = 2

/**
 * {@snippet lang=c : #define CXCompletionContext_ObjCObjectValue 4
 */
fun CXCompletionContext_ObjCObjectValue(): Int = 4

/**
 * {@snippet lang=c : #define CXCompletionContext_ObjCSelectorValue 8
 */
fun CXCompletionContext_ObjCSelectorValue(): Int = 8

/**
 * {@snippet lang=c : #define CXCompletionContext_CXXClassTypeValue 16
 */
fun CXCompletionContext_CXXClassTypeValue(): Int = 16

/**
 * {@snippet lang=c : #define CXCompletionContext_DotMemberAccess 32
 */
fun CXCompletionContext_DotMemberAccess(): Int = 32

/**
 * {@snippet lang=c : #define CXCompletionContext_ArrowMemberAccess 64
 */
fun CXCompletionContext_ArrowMemberAccess(): Int = 64

/**
 * {@snippet lang=c : #define CXCompletionContext_ObjCPropertyAccess 128
 */
fun CXCompletionContext_ObjCPropertyAccess(): Int = 128

/**
 * {@snippet lang=c : #define CXCompletionContext_EnumTag 256
 */
fun CXCompletionContext_EnumTag(): Int = 256

/**
 * {@snippet lang=c : #define CXCompletionContext_UnionTag 512
 */
fun CXCompletionContext_UnionTag(): Int = 512

/**
 * {@snippet lang=c : #define CXCompletionContext_StructTag 1024
 */
fun CXCompletionContext_StructTag(): Int = 1024

/**
 * {@snippet lang=c : #define CXCompletionContext_ClassTag 2048
 */
fun CXCompletionContext_ClassTag(): Int = 2048

/**
 * {@snippet lang=c : #define CXCompletionContext_Namespace 4096
 */
fun CXCompletionContext_Namespace(): Int = 4096

/**
 * {@snippet lang=c : #define CXCompletionContext_NestedNameSpecifier 8192
 */
fun CXCompletionContext_NestedNameSpecifier(): Int = 8192

/**
 * {@snippet lang=c : #define CXCompletionContext_ObjCInterface 16384
 */
fun CXCompletionContext_ObjCInterface(): Int = 16384

/**
 * {@snippet lang=c : #define CXCompletionContext_ObjCProtocol 32768
 */
fun CXCompletionContext_ObjCProtocol(): Int = 32768

/**
 * {@snippet lang=c : #define CXCompletionContext_ObjCCategory 65536
 */
fun CXCompletionContext_ObjCCategory(): Int = 65536

/**
 * {@snippet lang=c : #define CXCompletionContext_ObjCInstanceMessage 131072
 */
fun CXCompletionContext_ObjCInstanceMessage(): Int = 131072

/**
 * {@snippet lang=c : #define CXCompletionContext_ObjCClassMessage 262144
 */
fun CXCompletionContext_ObjCClassMessage(): Int = 262144

/**
 * {@snippet lang=c : #define CXCompletionContext_ObjCSelectorName 524288
 */
fun CXCompletionContext_ObjCSelectorName(): Int = 524288

/**
 * {@snippet lang=c : #define CXCompletionContext_MacroName 1048576
 */
fun CXCompletionContext_MacroName(): Int = 1048576

/**
 * {@snippet lang=c : #define CXCompletionContext_NaturalLanguage 2097152
 */
fun CXCompletionContext_NaturalLanguage(): Int = 2097152

/**
 * {@snippet lang=c : #define CXCompletionContext_IncludedFile 4194304
 */
fun CXCompletionContext_IncludedFile(): Int = 4194304

/**
 * {@snippet lang=c : #define CXCompletionContext_Unknown 8388607
 */
fun CXCompletionContext_Unknown(): Int = 8388607

/**
 * {@snippet lang=c : clang_defaultCodeCompleteOptions UNSIGNED = Int()
 */
private val clang_defaultCodeCompleteOptions_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT)
private val clang_defaultCodeCompleteOptions_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_defaultCodeCompleteOptions")
private val clang_defaultCodeCompleteOptions_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_defaultCodeCompleteOptions_ADDR, clang_defaultCodeCompleteOptions_DESC)

fun clang_defaultCodeCompleteOptions(): Int {
    try {
        return clang_defaultCodeCompleteOptions_HANDLE.invokeExact() as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_codeCompleteAt (typedef CXCodeCompleteResults = Declared(CXCodeCompleteResults))*(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*,(Char)*,UNSIGNED = Int,UNSIGNED = Int,(Declared(CXUnsavedFile))*,UNSIGNED = Int,UNSIGNED = Int)
 */
private val clang_codeCompleteAt_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val clang_codeCompleteAt_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_codeCompleteAt")
private val clang_codeCompleteAt_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_codeCompleteAt_ADDR, clang_codeCompleteAt_DESC)

fun clang_codeCompleteAt(arg0: MemorySegment, arg1: MemorySegment, arg2: Int, arg3: Int, arg4: MemorySegment, arg5: Int, arg6: Int): MemorySegment {
    try {
        return clang_codeCompleteAt_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_sortCodeCompletionResults Void((typedef CXCompletionResult = Declared(CXCompletionResult))*,UNSIGNED = Int)
 */
private val clang_sortCodeCompletionResults_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_sortCodeCompletionResults_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_sortCodeCompletionResults")
private val clang_sortCodeCompletionResults_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_sortCodeCompletionResults_ADDR, clang_sortCodeCompletionResults_DESC)

fun clang_sortCodeCompletionResults(arg0: MemorySegment, arg1: Int): Unit {
    try {
        clang_sortCodeCompletionResults_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_disposeCodeCompleteResults Void((typedef CXCodeCompleteResults = Declared(CXCodeCompleteResults))*)
 */
private val clang_disposeCodeCompleteResults_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val clang_disposeCodeCompleteResults_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_disposeCodeCompleteResults")
private val clang_disposeCodeCompleteResults_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_disposeCodeCompleteResults_ADDR, clang_disposeCodeCompleteResults_DESC)

fun clang_disposeCodeCompleteResults(arg0: MemorySegment): Unit {
    try {
        clang_disposeCodeCompleteResults_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_codeCompleteGetNumDiagnostics UNSIGNED = Int((typedef CXCodeCompleteResults = Declared(CXCodeCompleteResults))*)
 */
private val clang_codeCompleteGetNumDiagnostics_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_codeCompleteGetNumDiagnostics_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_codeCompleteGetNumDiagnostics")
private val clang_codeCompleteGetNumDiagnostics_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_codeCompleteGetNumDiagnostics_ADDR, clang_codeCompleteGetNumDiagnostics_DESC)

fun clang_codeCompleteGetNumDiagnostics(arg0: MemorySegment): Int {
    try {
        return clang_codeCompleteGetNumDiagnostics_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_codeCompleteGetDiagnostic typedef CXDiagnostic = (Void)*((typedef CXCodeCompleteResults = Declared(CXCodeCompleteResults))*,UNSIGNED = Int)
 */
private val clang_codeCompleteGetDiagnostic_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_codeCompleteGetDiagnostic_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_codeCompleteGetDiagnostic")
private val clang_codeCompleteGetDiagnostic_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_codeCompleteGetDiagnostic_ADDR, clang_codeCompleteGetDiagnostic_DESC)

fun clang_codeCompleteGetDiagnostic(arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return clang_codeCompleteGetDiagnostic_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_codeCompleteGetContexts UNSIGNED = LongLong((typedef CXCodeCompleteResults = Declared(CXCodeCompleteResults))*)
 */
private val clang_codeCompleteGetContexts_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val clang_codeCompleteGetContexts_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_codeCompleteGetContexts")
private val clang_codeCompleteGetContexts_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_codeCompleteGetContexts_ADDR, clang_codeCompleteGetContexts_DESC)

fun clang_codeCompleteGetContexts(arg0: MemorySegment): Long {
    try {
        return clang_codeCompleteGetContexts_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_codeCompleteGetContainerKind Declared(CXCursorKind)((typedef CXCodeCompleteResults = Declared(CXCodeCompleteResults))*,(UNSIGNED = Int)*)
 */
private val clang_codeCompleteGetContainerKind_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_codeCompleteGetContainerKind_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_codeCompleteGetContainerKind")
private val clang_codeCompleteGetContainerKind_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_codeCompleteGetContainerKind_ADDR, clang_codeCompleteGetContainerKind_DESC)

fun clang_codeCompleteGetContainerKind(arg0: MemorySegment, arg1: MemorySegment): Int {
    try {
        return clang_codeCompleteGetContainerKind_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_codeCompleteGetContainerUSR typedef CXString = Declared(CXString)((typedef CXCodeCompleteResults = Declared(CXCodeCompleteResults))*)
 */
private val clang_codeCompleteGetContainerUSR_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS)
private val clang_codeCompleteGetContainerUSR_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_codeCompleteGetContainerUSR")
private val clang_codeCompleteGetContainerUSR_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_codeCompleteGetContainerUSR_ADDR, clang_codeCompleteGetContainerUSR_DESC)

fun clang_codeCompleteGetContainerUSR(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_codeCompleteGetContainerUSR_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_codeCompleteGetObjCSelector typedef CXString = Declared(CXString)((typedef CXCodeCompleteResults = Declared(CXCodeCompleteResults))*)
 */
private val clang_codeCompleteGetObjCSelector_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.ADDRESS)
private val clang_codeCompleteGetObjCSelector_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_codeCompleteGetObjCSelector")
private val clang_codeCompleteGetObjCSelector_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_codeCompleteGetObjCSelector_ADDR, clang_codeCompleteGetObjCSelector_DESC)

fun clang_codeCompleteGetObjCSelector(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_codeCompleteGetObjCSelector_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getClangVersion typedef CXString = Declared(CXString)()
 */
private val clang_getClangVersion_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout)
private val clang_getClangVersion_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getClangVersion")
private val clang_getClangVersion_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getClangVersion_ADDR, clang_getClangVersion_DESC)

fun clang_getClangVersion(allocator: SegmentAllocator): MemorySegment {
    try {
        return clang_getClangVersion_HANDLE.invokeExact(allocator) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_toggleCrashRecovery Void(UNSIGNED = Int)
 */
private val clang_toggleCrashRecovery_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT)
private val clang_toggleCrashRecovery_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_toggleCrashRecovery")
private val clang_toggleCrashRecovery_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_toggleCrashRecovery_ADDR, clang_toggleCrashRecovery_DESC)

fun clang_toggleCrashRecovery(arg0: Int): Unit {
    try {
        clang_toggleCrashRecovery_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : typedef (Void((Void)*,(Declared(CXSourceLocation))*,UNSIGNED = Int,(Void)*))* CXInclusionVisitor;}
 */
typealias CXInclusionVisitor = MemorySegment?

/**
 * {@snippet lang=c : clang_getInclusions Void(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*,typedef CXInclusionVisitor = (Void((Void)*,(Declared(CXSourceLocation))*,UNSIGNED = Int,(Void)*))*,typedef CXClientData = (Void)*)
 */
private val clang_getInclusions_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_getInclusions_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getInclusions")
private val clang_getInclusions_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getInclusions_ADDR, clang_getInclusions_DESC)

fun clang_getInclusions(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        clang_getInclusions_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXEval_Int 1
 */
fun CXEval_Int(): Int = 1

/**
 * {@snippet lang=c : #define CXEval_Float 2
 */
fun CXEval_Float(): Int = 2

/**
 * {@snippet lang=c : #define CXEval_ObjCStrLiteral 3
 */
fun CXEval_ObjCStrLiteral(): Int = 3

/**
 * {@snippet lang=c : #define CXEval_StrLiteral 4
 */
fun CXEval_StrLiteral(): Int = 4

/**
 * {@snippet lang=c : #define CXEval_CFStr 5
 */
fun CXEval_CFStr(): Int = 5

/**
 * {@snippet lang=c : #define CXEval_Other 6
 */
fun CXEval_Other(): Int = 6

/**
 * {@snippet lang=c : #define CXEval_UnExposed 0
 */
fun CXEval_UnExposed(): Int = 0

/**
 * {@snippet lang=c : typedef (Void)* CXEvalResult;}
 */
typealias CXEvalResult = MemorySegment?

/**
 * {@snippet lang=c : clang_Cursor_Evaluate typedef CXEvalResult = (Void)*(typedef CXCursor = Declared(CXCursor))
 */
private val clang_Cursor_Evaluate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, CXCursor.layout)
private val clang_Cursor_Evaluate_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Cursor_Evaluate")
private val clang_Cursor_Evaluate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Cursor_Evaluate_ADDR, clang_Cursor_Evaluate_DESC)

fun clang_Cursor_Evaluate(arg0: MemorySegment): MemorySegment {
    try {
        return clang_Cursor_Evaluate_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_EvalResult_getKind typedef CXEvalResultKind = Declared(CXEvalResultKind)(typedef CXEvalResult = (Void)*)
 */
private val clang_EvalResult_getKind_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_EvalResult_getKind_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_EvalResult_getKind")
private val clang_EvalResult_getKind_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_EvalResult_getKind_ADDR, clang_EvalResult_getKind_DESC)

fun clang_EvalResult_getKind(arg0: MemorySegment): Int {
    try {
        return clang_EvalResult_getKind_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_EvalResult_getAsInt Int(typedef CXEvalResult = (Void)*)
 */
private val clang_EvalResult_getAsInt_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_EvalResult_getAsInt_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_EvalResult_getAsInt")
private val clang_EvalResult_getAsInt_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_EvalResult_getAsInt_ADDR, clang_EvalResult_getAsInt_DESC)

fun clang_EvalResult_getAsInt(arg0: MemorySegment): Int {
    try {
        return clang_EvalResult_getAsInt_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_EvalResult_getAsLongLong LongLong(typedef CXEvalResult = (Void)*)
 */
private val clang_EvalResult_getAsLongLong_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val clang_EvalResult_getAsLongLong_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_EvalResult_getAsLongLong")
private val clang_EvalResult_getAsLongLong_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_EvalResult_getAsLongLong_ADDR, clang_EvalResult_getAsLongLong_DESC)

fun clang_EvalResult_getAsLongLong(arg0: MemorySegment): Long {
    try {
        return clang_EvalResult_getAsLongLong_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_EvalResult_isUnsignedInt UNSIGNED = Int(typedef CXEvalResult = (Void)*)
 */
private val clang_EvalResult_isUnsignedInt_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_EvalResult_isUnsignedInt_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_EvalResult_isUnsignedInt")
private val clang_EvalResult_isUnsignedInt_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_EvalResult_isUnsignedInt_ADDR, clang_EvalResult_isUnsignedInt_DESC)

fun clang_EvalResult_isUnsignedInt(arg0: MemorySegment): Int {
    try {
        return clang_EvalResult_isUnsignedInt_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_EvalResult_getAsUnsigned UNSIGNED = LongLong(typedef CXEvalResult = (Void)*)
 */
private val clang_EvalResult_getAsUnsigned_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val clang_EvalResult_getAsUnsigned_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_EvalResult_getAsUnsigned")
private val clang_EvalResult_getAsUnsigned_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_EvalResult_getAsUnsigned_ADDR, clang_EvalResult_getAsUnsigned_DESC)

fun clang_EvalResult_getAsUnsigned(arg0: MemorySegment): Long {
    try {
        return clang_EvalResult_getAsUnsigned_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_EvalResult_getAsDouble Double(typedef CXEvalResult = (Void)*)
 */
private val clang_EvalResult_getAsDouble_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS)
private val clang_EvalResult_getAsDouble_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_EvalResult_getAsDouble")
private val clang_EvalResult_getAsDouble_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_EvalResult_getAsDouble_ADDR, clang_EvalResult_getAsDouble_DESC)

fun clang_EvalResult_getAsDouble(arg0: MemorySegment): Double {
    try {
        return clang_EvalResult_getAsDouble_HANDLE.invokeExact(arg0) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_EvalResult_getAsStr (Char)*(typedef CXEvalResult = (Void)*)
 */
private val clang_EvalResult_getAsStr_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_EvalResult_getAsStr_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_EvalResult_getAsStr")
private val clang_EvalResult_getAsStr_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_EvalResult_getAsStr_ADDR, clang_EvalResult_getAsStr_DESC)

fun clang_EvalResult_getAsStr(arg0: MemorySegment): MemorySegment {
    try {
        return clang_EvalResult_getAsStr_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_EvalResult_dispose Void(typedef CXEvalResult = (Void)*)
 */
private val clang_EvalResult_dispose_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val clang_EvalResult_dispose_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_EvalResult_dispose")
private val clang_EvalResult_dispose_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_EvalResult_dispose_ADDR, clang_EvalResult_dispose_DESC)

fun clang_EvalResult_dispose(arg0: MemorySegment): Unit {
    try {
        clang_EvalResult_dispose_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXVisit_Break 0
 */
fun CXVisit_Break(): Int = 0

/**
 * {@snippet lang=c : #define CXVisit_Continue 1
 */
fun CXVisit_Continue(): Int = 1

/**
 * {@snippet lang=c : STRUCT CXCursorAndRangeVisitor
 */
class CXCursorAndRangeVisitor {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("context"),
            ValueLayout.ADDRESS.withName("visit")
        ).withName("CXCursorAndRangeVisitor")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val context_VH: VarHandle = layout.varHandle(groupElement("context"))
        
        @Suppress("UNCHECKED_CAST")
        fun context(segment: MemorySegment): MemorySegment? =
            context_VH.get(segment, 0L) as MemorySegment
        
        fun context(segment: MemorySegment, value: MemorySegment) =
            context_VH.set(segment, 0L, value)
        
        val visit_VH: VarHandle = layout.varHandle(groupElement("visit"))
        
        @Suppress("UNCHECKED_CAST")
        fun visit(segment: MemorySegment): MemorySegment? =
            visit_VH.get(segment, 0L) as MemorySegment
        
        fun visit(segment: MemorySegment, value: MemorySegment) =
            visit_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : #define CXResult_Success 0
 */
fun CXResult_Success(): Int = 0

/**
 * {@snippet lang=c : #define CXResult_Invalid 1
 */
fun CXResult_Invalid(): Int = 1

/**
 * {@snippet lang=c : #define CXResult_VisitBreak 2
 */
fun CXResult_VisitBreak(): Int = 2

/**
 * {@snippet lang=c : clang_findReferencesInFile typedef CXResult = Declared(CXResult)(typedef CXCursor = Declared(CXCursor),typedef CXFile = (Void)*,typedef CXCursorAndRangeVisitor = Declared(CXCursorAndRangeVisitor))
 */
private val clang_findReferencesInFile_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout, ValueLayout.ADDRESS, CXCursorAndRangeVisitor.layout)
private val clang_findReferencesInFile_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_findReferencesInFile")
private val clang_findReferencesInFile_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_findReferencesInFile_ADDR, clang_findReferencesInFile_DESC)

fun clang_findReferencesInFile(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Int {
    try {
        return clang_findReferencesInFile_HANDLE.invokeExact(arg0, arg1, arg2) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_findIncludesInFile typedef CXResult = Declared(CXResult)(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*,typedef CXFile = (Void)*,typedef CXCursorAndRangeVisitor = Declared(CXCursorAndRangeVisitor))
 */
private val clang_findIncludesInFile_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, CXCursorAndRangeVisitor.layout)
private val clang_findIncludesInFile_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_findIncludesInFile")
private val clang_findIncludesInFile_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_findIncludesInFile_ADDR, clang_findIncludesInFile_DESC)

fun clang_findIncludesInFile(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Int {
    try {
        return clang_findIncludesInFile_HANDLE.invokeExact(arg0, arg1, arg2) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : typedef (Declared(CXVisitorResult)(Declared(CXCursor),Declared(CXSourceRange)))* CXCursorAndRangeVisitorBlock;}
 */
typealias CXCursorAndRangeVisitorBlock = MemorySegment?

/**
 * {@snippet lang=c : clang_findReferencesInFileWithBlock typedef CXResult = Declared(CXResult)(typedef CXCursor = Declared(CXCursor),typedef CXFile = (Void)*,typedef CXCursorAndRangeVisitorBlock = (Declared(CXVisitorResult)(Declared(CXCursor),Declared(CXSourceRange)))*)
 */
private val clang_findReferencesInFileWithBlock_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_findReferencesInFileWithBlock_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_findReferencesInFileWithBlock")
private val clang_findReferencesInFileWithBlock_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_findReferencesInFileWithBlock_ADDR, clang_findReferencesInFileWithBlock_DESC)

fun clang_findReferencesInFileWithBlock(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Int {
    try {
        return clang_findReferencesInFileWithBlock_HANDLE.invokeExact(arg0, arg1, arg2) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_findIncludesInFileWithBlock typedef CXResult = Declared(CXResult)(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*,typedef CXFile = (Void)*,typedef CXCursorAndRangeVisitorBlock = (Declared(CXVisitorResult)(Declared(CXCursor),Declared(CXSourceRange)))*)
 */
private val clang_findIncludesInFileWithBlock_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_findIncludesInFileWithBlock_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_findIncludesInFileWithBlock")
private val clang_findIncludesInFileWithBlock_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_findIncludesInFileWithBlock_ADDR, clang_findIncludesInFileWithBlock_DESC)

fun clang_findIncludesInFileWithBlock(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Int {
    try {
        return clang_findIncludesInFileWithBlock_HANDLE.invokeExact(arg0, arg1, arg2) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : typedef (Void)* CXIdxClientFile;}
 */
typealias CXIdxClientFile = MemorySegment?

/**
 * {@snippet lang=c : typedef (Void)* CXIdxClientEntity;}
 */
typealias CXIdxClientEntity = MemorySegment?

/**
 * {@snippet lang=c : typedef (Void)* CXIdxClientContainer;}
 */
typealias CXIdxClientContainer = MemorySegment?

/**
 * {@snippet lang=c : typedef (Void)* CXIdxClientASTFile;}
 */
typealias CXIdxClientASTFile = MemorySegment?

/**
 * {@snippet lang=c : STRUCT CXIdxLoc
 */
class CXIdxLoc {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            MemoryLayout.sequenceLayout(2, ValueLayout.ADDRESS).withName("ptr_data"),
            ValueLayout.JAVA_INT.withName("int_data"),
            MemoryLayout.paddingLayout(4)
        ).withName("CXIdxLoc")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        
        fun ptr_data(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("ptr_data")), layout.select(groupElement("ptr_data")).byteSize())
        
        val int_data_VH: VarHandle = layout.varHandle(groupElement("int_data"))
        
        @Suppress("UNCHECKED_CAST")
        fun int_data(segment: MemorySegment): Int =
            int_data_VH.get(segment, 0L) as Int
        
        fun int_data(segment: MemorySegment, value: Int) =
            int_data_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : STRUCT CXIdxIncludedFileInfo
 */
class CXIdxIncludedFileInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            CXIdxLoc.layout.withName("hashLoc"),
            ValueLayout.ADDRESS.withName("filename"),
            ValueLayout.ADDRESS.withName("file"),
            ValueLayout.JAVA_INT.withName("isImport"),
            ValueLayout.JAVA_INT.withName("isAngled"),
            ValueLayout.JAVA_INT.withName("isModuleImport"),
            MemoryLayout.paddingLayout(4)
        ).withName("CXIdxIncludedFileInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val hashLoc_VH: VarHandle = layout.varHandle(groupElement("hashLoc"))
        
        fun hashLoc(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("hashLoc")), layout.select(groupElement("hashLoc")).byteSize())
        
        val filename_VH: VarHandle = layout.varHandle(groupElement("filename"))
        
        @Suppress("UNCHECKED_CAST")
        fun filename(segment: MemorySegment): MemorySegment? =
            filename_VH.get(segment, 0L) as MemorySegment
        
        fun filename(segment: MemorySegment, value: MemorySegment) =
            filename_VH.set(segment, 0L, value)
        
        val file__VH: VarHandle = layout.varHandle(groupElement("file"))
        
        @Suppress("UNCHECKED_CAST")
        fun file_(segment: MemorySegment): MemorySegment? =
            file__VH.get(segment, 0L) as MemorySegment
        
        fun file_(segment: MemorySegment, value: MemorySegment) =
            file__VH.set(segment, 0L, value)
        
        val isImport_VH: VarHandle = layout.varHandle(groupElement("isImport"))
        
        @Suppress("UNCHECKED_CAST")
        fun isImport(segment: MemorySegment): Int =
            isImport_VH.get(segment, 0L) as Int
        
        fun isImport(segment: MemorySegment, value: Int) =
            isImport_VH.set(segment, 0L, value)
        
        val isAngled_VH: VarHandle = layout.varHandle(groupElement("isAngled"))
        
        @Suppress("UNCHECKED_CAST")
        fun isAngled(segment: MemorySegment): Int =
            isAngled_VH.get(segment, 0L) as Int
        
        fun isAngled(segment: MemorySegment, value: Int) =
            isAngled_VH.set(segment, 0L, value)
        
        val isModuleImport_VH: VarHandle = layout.varHandle(groupElement("isModuleImport"))
        
        @Suppress("UNCHECKED_CAST")
        fun isModuleImport(segment: MemorySegment): Int =
            isModuleImport_VH.get(segment, 0L) as Int
        
        fun isModuleImport(segment: MemorySegment, value: Int) =
            isModuleImport_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : STRUCT CXIdxImportedASTFileInfo
 */
class CXIdxImportedASTFileInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("file"),
            ValueLayout.ADDRESS.withName("module"),
            CXIdxLoc.layout.withName("loc"),
            ValueLayout.JAVA_INT.withName("isImplicit"),
            MemoryLayout.paddingLayout(4)
        ).withName("CXIdxImportedASTFileInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val file__VH: VarHandle = layout.varHandle(groupElement("file"))
        
        @Suppress("UNCHECKED_CAST")
        fun file_(segment: MemorySegment): MemorySegment? =
            file__VH.get(segment, 0L) as MemorySegment
        
        fun file_(segment: MemorySegment, value: MemorySegment) =
            file__VH.set(segment, 0L, value)
        
        val module_VH: VarHandle = layout.varHandle(groupElement("module"))
        
        @Suppress("UNCHECKED_CAST")
        fun module(segment: MemorySegment): MemorySegment? =
            module_VH.get(segment, 0L) as MemorySegment
        
        fun module(segment: MemorySegment, value: MemorySegment) =
            module_VH.set(segment, 0L, value)
        
        val loc_VH: VarHandle = layout.varHandle(groupElement("loc"))
        
        fun loc(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("loc")), layout.select(groupElement("loc")).byteSize())
        
        val isImplicit_VH: VarHandle = layout.varHandle(groupElement("isImplicit"))
        
        @Suppress("UNCHECKED_CAST")
        fun isImplicit(segment: MemorySegment): Int =
            isImplicit_VH.get(segment, 0L) as Int
        
        fun isImplicit(segment: MemorySegment, value: Int) =
            isImplicit_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : #define CXIdxEntity_Unexposed 0
 */
fun CXIdxEntity_Unexposed(): Int = 0

/**
 * {@snippet lang=c : #define CXIdxEntity_Typedef 1
 */
fun CXIdxEntity_Typedef(): Int = 1

/**
 * {@snippet lang=c : #define CXIdxEntity_Function 2
 */
fun CXIdxEntity_Function(): Int = 2

/**
 * {@snippet lang=c : #define CXIdxEntity_Variable 3
 */
fun CXIdxEntity_Variable(): Int = 3

/**
 * {@snippet lang=c : #define CXIdxEntity_Field 4
 */
fun CXIdxEntity_Field(): Int = 4

/**
 * {@snippet lang=c : #define CXIdxEntity_EnumConstant 5
 */
fun CXIdxEntity_EnumConstant(): Int = 5

/**
 * {@snippet lang=c : #define CXIdxEntity_ObjCClass 6
 */
fun CXIdxEntity_ObjCClass(): Int = 6

/**
 * {@snippet lang=c : #define CXIdxEntity_ObjCProtocol 7
 */
fun CXIdxEntity_ObjCProtocol(): Int = 7

/**
 * {@snippet lang=c : #define CXIdxEntity_ObjCCategory 8
 */
fun CXIdxEntity_ObjCCategory(): Int = 8

/**
 * {@snippet lang=c : #define CXIdxEntity_ObjCInstanceMethod 9
 */
fun CXIdxEntity_ObjCInstanceMethod(): Int = 9

/**
 * {@snippet lang=c : #define CXIdxEntity_ObjCClassMethod 10
 */
fun CXIdxEntity_ObjCClassMethod(): Int = 10

/**
 * {@snippet lang=c : #define CXIdxEntity_ObjCProperty 11
 */
fun CXIdxEntity_ObjCProperty(): Int = 11

/**
 * {@snippet lang=c : #define CXIdxEntity_ObjCIvar 12
 */
fun CXIdxEntity_ObjCIvar(): Int = 12

/**
 * {@snippet lang=c : #define CXIdxEntity_Enum 13
 */
fun CXIdxEntity_Enum(): Int = 13

/**
 * {@snippet lang=c : #define CXIdxEntity_Struct 14
 */
fun CXIdxEntity_Struct(): Int = 14

/**
 * {@snippet lang=c : #define CXIdxEntity_Union 15
 */
fun CXIdxEntity_Union(): Int = 15

/**
 * {@snippet lang=c : #define CXIdxEntity_CXXClass 16
 */
fun CXIdxEntity_CXXClass(): Int = 16

/**
 * {@snippet lang=c : #define CXIdxEntity_CXXNamespace 17
 */
fun CXIdxEntity_CXXNamespace(): Int = 17

/**
 * {@snippet lang=c : #define CXIdxEntity_CXXNamespaceAlias 18
 */
fun CXIdxEntity_CXXNamespaceAlias(): Int = 18

/**
 * {@snippet lang=c : #define CXIdxEntity_CXXStaticVariable 19
 */
fun CXIdxEntity_CXXStaticVariable(): Int = 19

/**
 * {@snippet lang=c : #define CXIdxEntity_CXXStaticMethod 20
 */
fun CXIdxEntity_CXXStaticMethod(): Int = 20

/**
 * {@snippet lang=c : #define CXIdxEntity_CXXInstanceMethod 21
 */
fun CXIdxEntity_CXXInstanceMethod(): Int = 21

/**
 * {@snippet lang=c : #define CXIdxEntity_CXXConstructor 22
 */
fun CXIdxEntity_CXXConstructor(): Int = 22

/**
 * {@snippet lang=c : #define CXIdxEntity_CXXDestructor 23
 */
fun CXIdxEntity_CXXDestructor(): Int = 23

/**
 * {@snippet lang=c : #define CXIdxEntity_CXXConversionFunction 24
 */
fun CXIdxEntity_CXXConversionFunction(): Int = 24

/**
 * {@snippet lang=c : #define CXIdxEntity_CXXTypeAlias 25
 */
fun CXIdxEntity_CXXTypeAlias(): Int = 25

/**
 * {@snippet lang=c : #define CXIdxEntity_CXXInterface 26
 */
fun CXIdxEntity_CXXInterface(): Int = 26

/**
 * {@snippet lang=c : #define CXIdxEntity_CXXConcept 27
 */
fun CXIdxEntity_CXXConcept(): Int = 27

/**
 * {@snippet lang=c : #define CXIdxEntityLang_None 0
 */
fun CXIdxEntityLang_None(): Int = 0

/**
 * {@snippet lang=c : #define CXIdxEntityLang_C 1
 */
fun CXIdxEntityLang_C(): Int = 1

/**
 * {@snippet lang=c : #define CXIdxEntityLang_ObjC 2
 */
fun CXIdxEntityLang_ObjC(): Int = 2

/**
 * {@snippet lang=c : #define CXIdxEntityLang_CXX 3
 */
fun CXIdxEntityLang_CXX(): Int = 3

/**
 * {@snippet lang=c : #define CXIdxEntityLang_Swift 4
 */
fun CXIdxEntityLang_Swift(): Int = 4

/**
 * {@snippet lang=c : #define CXIdxEntity_NonTemplate 0
 */
fun CXIdxEntity_NonTemplate(): Int = 0

/**
 * {@snippet lang=c : #define CXIdxEntity_Template 1
 */
fun CXIdxEntity_Template(): Int = 1

/**
 * {@snippet lang=c : #define CXIdxEntity_TemplatePartialSpecialization 2
 */
fun CXIdxEntity_TemplatePartialSpecialization(): Int = 2

/**
 * {@snippet lang=c : #define CXIdxEntity_TemplateSpecialization 3
 */
fun CXIdxEntity_TemplateSpecialization(): Int = 3

/**
 * {@snippet lang=c : #define CXIdxAttr_Unexposed 0
 */
fun CXIdxAttr_Unexposed(): Int = 0

/**
 * {@snippet lang=c : #define CXIdxAttr_IBAction 1
 */
fun CXIdxAttr_IBAction(): Int = 1

/**
 * {@snippet lang=c : #define CXIdxAttr_IBOutlet 2
 */
fun CXIdxAttr_IBOutlet(): Int = 2

/**
 * {@snippet lang=c : #define CXIdxAttr_IBOutletCollection 3
 */
fun CXIdxAttr_IBOutletCollection(): Int = 3

/**
 * {@snippet lang=c : STRUCT CXIdxAttrInfo
 */
class CXIdxAttrInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("kind"),
            MemoryLayout.paddingLayout(4),
            CXCursor.layout.withName("cursor"),
            CXIdxLoc.layout.withName("loc")
        ).withName("CXIdxAttrInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val kind_VH: VarHandle = layout.varHandle(groupElement("kind"))
        
        @Suppress("UNCHECKED_CAST")
        fun kind(segment: MemorySegment): Int =
            kind_VH.get(segment, 0L) as Int
        
        fun kind(segment: MemorySegment, value: Int) =
            kind_VH.set(segment, 0L, value)
        
        val cursor_VH: VarHandle = layout.varHandle(groupElement("cursor"))
        
        fun cursor(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("cursor")), layout.select(groupElement("cursor")).byteSize())
        
        val loc_VH: VarHandle = layout.varHandle(groupElement("loc"))
        
        fun loc(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("loc")), layout.select(groupElement("loc")).byteSize())
    }
}

/**
 * {@snippet lang=c : STRUCT CXIdxEntityInfo
 */
class CXIdxEntityInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("kind"),
            ValueLayout.JAVA_INT.withName("templateKind"),
            ValueLayout.JAVA_INT.withName("lang"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.ADDRESS.withName("name"),
            ValueLayout.ADDRESS.withName("USR"),
            CXCursor.layout.withName("cursor"),
            ValueLayout.ADDRESS.withName("attributes"),
            ValueLayout.JAVA_INT.withName("numAttributes"),
            MemoryLayout.paddingLayout(4)
        ).withName("CXIdxEntityInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val kind_VH: VarHandle = layout.varHandle(groupElement("kind"))
        
        @Suppress("UNCHECKED_CAST")
        fun kind(segment: MemorySegment): Int =
            kind_VH.get(segment, 0L) as Int
        
        fun kind(segment: MemorySegment, value: Int) =
            kind_VH.set(segment, 0L, value)
        
        val templateKind_VH: VarHandle = layout.varHandle(groupElement("templateKind"))
        
        @Suppress("UNCHECKED_CAST")
        fun templateKind(segment: MemorySegment): Int =
            templateKind_VH.get(segment, 0L) as Int
        
        fun templateKind(segment: MemorySegment, value: Int) =
            templateKind_VH.set(segment, 0L, value)
        
        val lang_VH: VarHandle = layout.varHandle(groupElement("lang"))
        
        @Suppress("UNCHECKED_CAST")
        fun lang(segment: MemorySegment): Int =
            lang_VH.get(segment, 0L) as Int
        
        fun lang(segment: MemorySegment, value: Int) =
            lang_VH.set(segment, 0L, value)
        
        val name_VH: VarHandle = layout.varHandle(groupElement("name"))
        
        @Suppress("UNCHECKED_CAST")
        fun name(segment: MemorySegment): MemorySegment? =
            name_VH.get(segment, 0L) as MemorySegment
        
        fun name(segment: MemorySegment, value: MemorySegment) =
            name_VH.set(segment, 0L, value)
        
        val USR_VH: VarHandle = layout.varHandle(groupElement("USR"))
        
        @Suppress("UNCHECKED_CAST")
        fun USR(segment: MemorySegment): MemorySegment? =
            USR_VH.get(segment, 0L) as MemorySegment
        
        fun USR(segment: MemorySegment, value: MemorySegment) =
            USR_VH.set(segment, 0L, value)
        
        val cursor_VH: VarHandle = layout.varHandle(groupElement("cursor"))
        
        fun cursor(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("cursor")), layout.select(groupElement("cursor")).byteSize())
        
        val attributes_VH: VarHandle = layout.varHandle(groupElement("attributes"))
        
        @Suppress("UNCHECKED_CAST")
        fun attributes(segment: MemorySegment): MemorySegment? =
            attributes_VH.get(segment, 0L) as MemorySegment
        
        fun attributes(segment: MemorySegment, value: MemorySegment) =
            attributes_VH.set(segment, 0L, value)
        
        val numAttributes_VH: VarHandle = layout.varHandle(groupElement("numAttributes"))
        
        @Suppress("UNCHECKED_CAST")
        fun numAttributes(segment: MemorySegment): Int =
            numAttributes_VH.get(segment, 0L) as Int
        
        fun numAttributes(segment: MemorySegment, value: Int) =
            numAttributes_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : STRUCT CXIdxContainerInfo
 */
class CXIdxContainerInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            CXCursor.layout.withName("cursor")
        ).withName("CXIdxContainerInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val cursor_VH: VarHandle = layout.varHandle(groupElement("cursor"))
        
        fun cursor(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("cursor")), layout.select(groupElement("cursor")).byteSize())
    }
}

/**
 * {@snippet lang=c : STRUCT CXIdxIBOutletCollectionAttrInfo
 */
class CXIdxIBOutletCollectionAttrInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("attrInfo"),
            ValueLayout.ADDRESS.withName("objcClass"),
            CXCursor.layout.withName("classCursor"),
            CXIdxLoc.layout.withName("classLoc")
        ).withName("CXIdxIBOutletCollectionAttrInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val attrInfo_VH: VarHandle = layout.varHandle(groupElement("attrInfo"))
        
        @Suppress("UNCHECKED_CAST")
        fun attrInfo(segment: MemorySegment): MemorySegment? =
            attrInfo_VH.get(segment, 0L) as MemorySegment
        
        fun attrInfo(segment: MemorySegment, value: MemorySegment) =
            attrInfo_VH.set(segment, 0L, value)
        
        val objcClass_VH: VarHandle = layout.varHandle(groupElement("objcClass"))
        
        @Suppress("UNCHECKED_CAST")
        fun objcClass(segment: MemorySegment): MemorySegment? =
            objcClass_VH.get(segment, 0L) as MemorySegment
        
        fun objcClass(segment: MemorySegment, value: MemorySegment) =
            objcClass_VH.set(segment, 0L, value)
        
        val classCursor_VH: VarHandle = layout.varHandle(groupElement("classCursor"))
        
        fun classCursor(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("classCursor")), layout.select(groupElement("classCursor")).byteSize())
        
        val classLoc_VH: VarHandle = layout.varHandle(groupElement("classLoc"))
        
        fun classLoc(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("classLoc")), layout.select(groupElement("classLoc")).byteSize())
    }
}

/**
 * {@snippet lang=c : #define CXIdxDeclFlag_Skipped 1
 */
fun CXIdxDeclFlag_Skipped(): Int = 1

/**
 * {@snippet lang=c : STRUCT CXIdxDeclInfo
 */
class CXIdxDeclInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("entityInfo"),
            CXCursor.layout.withName("cursor"),
            CXIdxLoc.layout.withName("loc"),
            ValueLayout.ADDRESS.withName("semanticContainer"),
            ValueLayout.ADDRESS.withName("lexicalContainer"),
            ValueLayout.JAVA_INT.withName("isRedeclaration"),
            ValueLayout.JAVA_INT.withName("isDefinition"),
            ValueLayout.JAVA_INT.withName("isContainer"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.ADDRESS.withName("declAsContainer"),
            ValueLayout.JAVA_INT.withName("isImplicit"),
            MemoryLayout.paddingLayout(4),
            ValueLayout.ADDRESS.withName("attributes"),
            ValueLayout.JAVA_INT.withName("numAttributes"),
            ValueLayout.JAVA_INT.withName("flags")
        ).withName("CXIdxDeclInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val entityInfo_VH: VarHandle = layout.varHandle(groupElement("entityInfo"))
        
        @Suppress("UNCHECKED_CAST")
        fun entityInfo(segment: MemorySegment): MemorySegment? =
            entityInfo_VH.get(segment, 0L) as MemorySegment
        
        fun entityInfo(segment: MemorySegment, value: MemorySegment) =
            entityInfo_VH.set(segment, 0L, value)
        
        val cursor_VH: VarHandle = layout.varHandle(groupElement("cursor"))
        
        fun cursor(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("cursor")), layout.select(groupElement("cursor")).byteSize())
        
        val loc_VH: VarHandle = layout.varHandle(groupElement("loc"))
        
        fun loc(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("loc")), layout.select(groupElement("loc")).byteSize())
        
        val semanticContainer_VH: VarHandle = layout.varHandle(groupElement("semanticContainer"))
        
        @Suppress("UNCHECKED_CAST")
        fun semanticContainer(segment: MemorySegment): MemorySegment? =
            semanticContainer_VH.get(segment, 0L) as MemorySegment
        
        fun semanticContainer(segment: MemorySegment, value: MemorySegment) =
            semanticContainer_VH.set(segment, 0L, value)
        
        val lexicalContainer_VH: VarHandle = layout.varHandle(groupElement("lexicalContainer"))
        
        @Suppress("UNCHECKED_CAST")
        fun lexicalContainer(segment: MemorySegment): MemorySegment? =
            lexicalContainer_VH.get(segment, 0L) as MemorySegment
        
        fun lexicalContainer(segment: MemorySegment, value: MemorySegment) =
            lexicalContainer_VH.set(segment, 0L, value)
        
        val isRedeclaration_VH: VarHandle = layout.varHandle(groupElement("isRedeclaration"))
        
        @Suppress("UNCHECKED_CAST")
        fun isRedeclaration(segment: MemorySegment): Int =
            isRedeclaration_VH.get(segment, 0L) as Int
        
        fun isRedeclaration(segment: MemorySegment, value: Int) =
            isRedeclaration_VH.set(segment, 0L, value)
        
        val isDefinition_VH: VarHandle = layout.varHandle(groupElement("isDefinition"))
        
        @Suppress("UNCHECKED_CAST")
        fun isDefinition(segment: MemorySegment): Int =
            isDefinition_VH.get(segment, 0L) as Int
        
        fun isDefinition(segment: MemorySegment, value: Int) =
            isDefinition_VH.set(segment, 0L, value)
        
        val isContainer_VH: VarHandle = layout.varHandle(groupElement("isContainer"))
        
        @Suppress("UNCHECKED_CAST")
        fun isContainer(segment: MemorySegment): Int =
            isContainer_VH.get(segment, 0L) as Int
        
        fun isContainer(segment: MemorySegment, value: Int) =
            isContainer_VH.set(segment, 0L, value)
        
        val declAsContainer_VH: VarHandle = layout.varHandle(groupElement("declAsContainer"))
        
        @Suppress("UNCHECKED_CAST")
        fun declAsContainer(segment: MemorySegment): MemorySegment? =
            declAsContainer_VH.get(segment, 0L) as MemorySegment
        
        fun declAsContainer(segment: MemorySegment, value: MemorySegment) =
            declAsContainer_VH.set(segment, 0L, value)
        
        val isImplicit_VH: VarHandle = layout.varHandle(groupElement("isImplicit"))
        
        @Suppress("UNCHECKED_CAST")
        fun isImplicit(segment: MemorySegment): Int =
            isImplicit_VH.get(segment, 0L) as Int
        
        fun isImplicit(segment: MemorySegment, value: Int) =
            isImplicit_VH.set(segment, 0L, value)
        
        val attributes_VH: VarHandle = layout.varHandle(groupElement("attributes"))
        
        @Suppress("UNCHECKED_CAST")
        fun attributes(segment: MemorySegment): MemorySegment? =
            attributes_VH.get(segment, 0L) as MemorySegment
        
        fun attributes(segment: MemorySegment, value: MemorySegment) =
            attributes_VH.set(segment, 0L, value)
        
        val numAttributes_VH: VarHandle = layout.varHandle(groupElement("numAttributes"))
        
        @Suppress("UNCHECKED_CAST")
        fun numAttributes(segment: MemorySegment): Int =
            numAttributes_VH.get(segment, 0L) as Int
        
        fun numAttributes(segment: MemorySegment, value: Int) =
            numAttributes_VH.set(segment, 0L, value)
        
        val flags_VH: VarHandle = layout.varHandle(groupElement("flags"))
        
        @Suppress("UNCHECKED_CAST")
        fun flags(segment: MemorySegment): Int =
            flags_VH.get(segment, 0L) as Int
        
        fun flags(segment: MemorySegment, value: Int) =
            flags_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : #define CXIdxObjCContainer_ForwardRef 0
 */
fun CXIdxObjCContainer_ForwardRef(): Int = 0

/**
 * {@snippet lang=c : #define CXIdxObjCContainer_Interface 1
 */
fun CXIdxObjCContainer_Interface(): Int = 1

/**
 * {@snippet lang=c : #define CXIdxObjCContainer_Implementation 2
 */
fun CXIdxObjCContainer_Implementation(): Int = 2

/**
 * {@snippet lang=c : STRUCT CXIdxObjCContainerDeclInfo
 */
class CXIdxObjCContainerDeclInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("declInfo"),
            ValueLayout.JAVA_INT.withName("kind"),
            MemoryLayout.paddingLayout(4)
        ).withName("CXIdxObjCContainerDeclInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val declInfo_VH: VarHandle = layout.varHandle(groupElement("declInfo"))
        
        @Suppress("UNCHECKED_CAST")
        fun declInfo(segment: MemorySegment): MemorySegment? =
            declInfo_VH.get(segment, 0L) as MemorySegment
        
        fun declInfo(segment: MemorySegment, value: MemorySegment) =
            declInfo_VH.set(segment, 0L, value)
        
        val kind_VH: VarHandle = layout.varHandle(groupElement("kind"))
        
        @Suppress("UNCHECKED_CAST")
        fun kind(segment: MemorySegment): Int =
            kind_VH.get(segment, 0L) as Int
        
        fun kind(segment: MemorySegment, value: Int) =
            kind_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : STRUCT CXIdxBaseClassInfo
 */
class CXIdxBaseClassInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("base"),
            CXCursor.layout.withName("cursor"),
            CXIdxLoc.layout.withName("loc")
        ).withName("CXIdxBaseClassInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val base_VH: VarHandle = layout.varHandle(groupElement("base"))
        
        @Suppress("UNCHECKED_CAST")
        fun base(segment: MemorySegment): MemorySegment? =
            base_VH.get(segment, 0L) as MemorySegment
        
        fun base(segment: MemorySegment, value: MemorySegment) =
            base_VH.set(segment, 0L, value)
        
        val cursor_VH: VarHandle = layout.varHandle(groupElement("cursor"))
        
        fun cursor(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("cursor")), layout.select(groupElement("cursor")).byteSize())
        
        val loc_VH: VarHandle = layout.varHandle(groupElement("loc"))
        
        fun loc(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("loc")), layout.select(groupElement("loc")).byteSize())
    }
}

/**
 * {@snippet lang=c : STRUCT CXIdxObjCProtocolRefInfo
 */
class CXIdxObjCProtocolRefInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("protocol"),
            CXCursor.layout.withName("cursor"),
            CXIdxLoc.layout.withName("loc")
        ).withName("CXIdxObjCProtocolRefInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val protocol_VH: VarHandle = layout.varHandle(groupElement("protocol"))
        
        @Suppress("UNCHECKED_CAST")
        fun protocol(segment: MemorySegment): MemorySegment? =
            protocol_VH.get(segment, 0L) as MemorySegment
        
        fun protocol(segment: MemorySegment, value: MemorySegment) =
            protocol_VH.set(segment, 0L, value)
        
        val cursor_VH: VarHandle = layout.varHandle(groupElement("cursor"))
        
        fun cursor(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("cursor")), layout.select(groupElement("cursor")).byteSize())
        
        val loc_VH: VarHandle = layout.varHandle(groupElement("loc"))
        
        fun loc(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("loc")), layout.select(groupElement("loc")).byteSize())
    }
}

/**
 * {@snippet lang=c : STRUCT CXIdxObjCProtocolRefListInfo
 */
class CXIdxObjCProtocolRefListInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("protocols"),
            ValueLayout.JAVA_INT.withName("numProtocols"),
            MemoryLayout.paddingLayout(4)
        ).withName("CXIdxObjCProtocolRefListInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val protocols_VH: VarHandle = layout.varHandle(groupElement("protocols"))
        
        @Suppress("UNCHECKED_CAST")
        fun protocols(segment: MemorySegment): MemorySegment? =
            protocols_VH.get(segment, 0L) as MemorySegment
        
        fun protocols(segment: MemorySegment, value: MemorySegment) =
            protocols_VH.set(segment, 0L, value)
        
        val numProtocols_VH: VarHandle = layout.varHandle(groupElement("numProtocols"))
        
        @Suppress("UNCHECKED_CAST")
        fun numProtocols(segment: MemorySegment): Int =
            numProtocols_VH.get(segment, 0L) as Int
        
        fun numProtocols(segment: MemorySegment, value: Int) =
            numProtocols_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : STRUCT CXIdxObjCInterfaceDeclInfo
 */
class CXIdxObjCInterfaceDeclInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("containerInfo"),
            ValueLayout.ADDRESS.withName("superInfo"),
            ValueLayout.ADDRESS.withName("protocols")
        ).withName("CXIdxObjCInterfaceDeclInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val containerInfo_VH: VarHandle = layout.varHandle(groupElement("containerInfo"))
        
        @Suppress("UNCHECKED_CAST")
        fun containerInfo(segment: MemorySegment): MemorySegment? =
            containerInfo_VH.get(segment, 0L) as MemorySegment
        
        fun containerInfo(segment: MemorySegment, value: MemorySegment) =
            containerInfo_VH.set(segment, 0L, value)
        
        val superInfo_VH: VarHandle = layout.varHandle(groupElement("superInfo"))
        
        @Suppress("UNCHECKED_CAST")
        fun superInfo(segment: MemorySegment): MemorySegment? =
            superInfo_VH.get(segment, 0L) as MemorySegment
        
        fun superInfo(segment: MemorySegment, value: MemorySegment) =
            superInfo_VH.set(segment, 0L, value)
        
        val protocols_VH: VarHandle = layout.varHandle(groupElement("protocols"))
        
        @Suppress("UNCHECKED_CAST")
        fun protocols(segment: MemorySegment): MemorySegment? =
            protocols_VH.get(segment, 0L) as MemorySegment
        
        fun protocols(segment: MemorySegment, value: MemorySegment) =
            protocols_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : STRUCT CXIdxObjCCategoryDeclInfo
 */
class CXIdxObjCCategoryDeclInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("containerInfo"),
            ValueLayout.ADDRESS.withName("objcClass"),
            CXCursor.layout.withName("classCursor"),
            CXIdxLoc.layout.withName("classLoc"),
            ValueLayout.ADDRESS.withName("protocols")
        ).withName("CXIdxObjCCategoryDeclInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val containerInfo_VH: VarHandle = layout.varHandle(groupElement("containerInfo"))
        
        @Suppress("UNCHECKED_CAST")
        fun containerInfo(segment: MemorySegment): MemorySegment? =
            containerInfo_VH.get(segment, 0L) as MemorySegment
        
        fun containerInfo(segment: MemorySegment, value: MemorySegment) =
            containerInfo_VH.set(segment, 0L, value)
        
        val objcClass_VH: VarHandle = layout.varHandle(groupElement("objcClass"))
        
        @Suppress("UNCHECKED_CAST")
        fun objcClass(segment: MemorySegment): MemorySegment? =
            objcClass_VH.get(segment, 0L) as MemorySegment
        
        fun objcClass(segment: MemorySegment, value: MemorySegment) =
            objcClass_VH.set(segment, 0L, value)
        
        val classCursor_VH: VarHandle = layout.varHandle(groupElement("classCursor"))
        
        fun classCursor(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("classCursor")), layout.select(groupElement("classCursor")).byteSize())
        
        val classLoc_VH: VarHandle = layout.varHandle(groupElement("classLoc"))
        
        fun classLoc(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("classLoc")), layout.select(groupElement("classLoc")).byteSize())
        
        val protocols_VH: VarHandle = layout.varHandle(groupElement("protocols"))
        
        @Suppress("UNCHECKED_CAST")
        fun protocols(segment: MemorySegment): MemorySegment? =
            protocols_VH.get(segment, 0L) as MemorySegment
        
        fun protocols(segment: MemorySegment, value: MemorySegment) =
            protocols_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : STRUCT CXIdxObjCPropertyDeclInfo
 */
class CXIdxObjCPropertyDeclInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("declInfo"),
            ValueLayout.ADDRESS.withName("getter"),
            ValueLayout.ADDRESS.withName("setter")
        ).withName("CXIdxObjCPropertyDeclInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val declInfo_VH: VarHandle = layout.varHandle(groupElement("declInfo"))
        
        @Suppress("UNCHECKED_CAST")
        fun declInfo(segment: MemorySegment): MemorySegment? =
            declInfo_VH.get(segment, 0L) as MemorySegment
        
        fun declInfo(segment: MemorySegment, value: MemorySegment) =
            declInfo_VH.set(segment, 0L, value)
        
        val getter_VH: VarHandle = layout.varHandle(groupElement("getter"))
        
        @Suppress("UNCHECKED_CAST")
        fun getter(segment: MemorySegment): MemorySegment? =
            getter_VH.get(segment, 0L) as MemorySegment
        
        fun getter(segment: MemorySegment, value: MemorySegment) =
            getter_VH.set(segment, 0L, value)
        
        val setter_VH: VarHandle = layout.varHandle(groupElement("setter"))
        
        @Suppress("UNCHECKED_CAST")
        fun setter(segment: MemorySegment): MemorySegment? =
            setter_VH.get(segment, 0L) as MemorySegment
        
        fun setter(segment: MemorySegment, value: MemorySegment) =
            setter_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : STRUCT CXIdxCXXClassDeclInfo
 */
class CXIdxCXXClassDeclInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("declInfo"),
            ValueLayout.ADDRESS.withName("bases"),
            ValueLayout.JAVA_INT.withName("numBases"),
            MemoryLayout.paddingLayout(4)
        ).withName("CXIdxCXXClassDeclInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val declInfo_VH: VarHandle = layout.varHandle(groupElement("declInfo"))
        
        @Suppress("UNCHECKED_CAST")
        fun declInfo(segment: MemorySegment): MemorySegment? =
            declInfo_VH.get(segment, 0L) as MemorySegment
        
        fun declInfo(segment: MemorySegment, value: MemorySegment) =
            declInfo_VH.set(segment, 0L, value)
        
        val bases_VH: VarHandle = layout.varHandle(groupElement("bases"))
        
        @Suppress("UNCHECKED_CAST")
        fun bases(segment: MemorySegment): MemorySegment? =
            bases_VH.get(segment, 0L) as MemorySegment
        
        fun bases(segment: MemorySegment, value: MemorySegment) =
            bases_VH.set(segment, 0L, value)
        
        val numBases_VH: VarHandle = layout.varHandle(groupElement("numBases"))
        
        @Suppress("UNCHECKED_CAST")
        fun numBases(segment: MemorySegment): Int =
            numBases_VH.get(segment, 0L) as Int
        
        fun numBases(segment: MemorySegment, value: Int) =
            numBases_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : #define CXIdxEntityRef_Direct 1
 */
fun CXIdxEntityRef_Direct(): Int = 1

/**
 * {@snippet lang=c : #define CXIdxEntityRef_Implicit 2
 */
fun CXIdxEntityRef_Implicit(): Int = 2

/**
 * {@snippet lang=c : #define CXSymbolRole_None 0
 */
fun CXSymbolRole_None(): Int = 0

/**
 * {@snippet lang=c : #define CXSymbolRole_Declaration 1
 */
fun CXSymbolRole_Declaration(): Int = 1

/**
 * {@snippet lang=c : #define CXSymbolRole_Definition 2
 */
fun CXSymbolRole_Definition(): Int = 2

/**
 * {@snippet lang=c : #define CXSymbolRole_Reference 4
 */
fun CXSymbolRole_Reference(): Int = 4

/**
 * {@snippet lang=c : #define CXSymbolRole_Read 8
 */
fun CXSymbolRole_Read(): Int = 8

/**
 * {@snippet lang=c : #define CXSymbolRole_Write 16
 */
fun CXSymbolRole_Write(): Int = 16

/**
 * {@snippet lang=c : #define CXSymbolRole_Call 32
 */
fun CXSymbolRole_Call(): Int = 32

/**
 * {@snippet lang=c : #define CXSymbolRole_Dynamic 64
 */
fun CXSymbolRole_Dynamic(): Int = 64

/**
 * {@snippet lang=c : #define CXSymbolRole_AddressOf 128
 */
fun CXSymbolRole_AddressOf(): Int = 128

/**
 * {@snippet lang=c : #define CXSymbolRole_Implicit 256
 */
fun CXSymbolRole_Implicit(): Int = 256

/**
 * {@snippet lang=c : STRUCT CXIdxEntityRefInfo
 */
class CXIdxEntityRefInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("kind"),
            MemoryLayout.paddingLayout(4),
            CXCursor.layout.withName("cursor"),
            CXIdxLoc.layout.withName("loc"),
            ValueLayout.ADDRESS.withName("referencedEntity"),
            ValueLayout.ADDRESS.withName("parentEntity"),
            ValueLayout.ADDRESS.withName("container"),
            ValueLayout.JAVA_INT.withName("role"),
            MemoryLayout.paddingLayout(4)
        ).withName("CXIdxEntityRefInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val kind_VH: VarHandle = layout.varHandle(groupElement("kind"))
        
        @Suppress("UNCHECKED_CAST")
        fun kind(segment: MemorySegment): Int =
            kind_VH.get(segment, 0L) as Int
        
        fun kind(segment: MemorySegment, value: Int) =
            kind_VH.set(segment, 0L, value)
        
        val cursor_VH: VarHandle = layout.varHandle(groupElement("cursor"))
        
        fun cursor(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("cursor")), layout.select(groupElement("cursor")).byteSize())
        
        val loc_VH: VarHandle = layout.varHandle(groupElement("loc"))
        
        fun loc(segment: MemorySegment): MemorySegment =
            segment.asSlice(layout.byteOffset(groupElement("loc")), layout.select(groupElement("loc")).byteSize())
        
        val referencedEntity_VH: VarHandle = layout.varHandle(groupElement("referencedEntity"))
        
        @Suppress("UNCHECKED_CAST")
        fun referencedEntity(segment: MemorySegment): MemorySegment? =
            referencedEntity_VH.get(segment, 0L) as MemorySegment
        
        fun referencedEntity(segment: MemorySegment, value: MemorySegment) =
            referencedEntity_VH.set(segment, 0L, value)
        
        val parentEntity_VH: VarHandle = layout.varHandle(groupElement("parentEntity"))
        
        @Suppress("UNCHECKED_CAST")
        fun parentEntity(segment: MemorySegment): MemorySegment? =
            parentEntity_VH.get(segment, 0L) as MemorySegment
        
        fun parentEntity(segment: MemorySegment, value: MemorySegment) =
            parentEntity_VH.set(segment, 0L, value)
        
        val container_VH: VarHandle = layout.varHandle(groupElement("container"))
        
        @Suppress("UNCHECKED_CAST")
        fun container(segment: MemorySegment): MemorySegment? =
            container_VH.get(segment, 0L) as MemorySegment
        
        fun container(segment: MemorySegment, value: MemorySegment) =
            container_VH.set(segment, 0L, value)
        
        val role_VH: VarHandle = layout.varHandle(groupElement("role"))
        
        @Suppress("UNCHECKED_CAST")
        fun role(segment: MemorySegment): Int =
            role_VH.get(segment, 0L) as Int
        
        fun role(segment: MemorySegment, value: Int) =
            role_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : STRUCT IndexerCallbacks
 */
class IndexerCallbacks {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("abortQuery"),
            ValueLayout.ADDRESS.withName("diagnostic"),
            ValueLayout.ADDRESS.withName("enteredMainFile"),
            ValueLayout.ADDRESS.withName("ppIncludedFile"),
            ValueLayout.ADDRESS.withName("importedASTFile"),
            ValueLayout.ADDRESS.withName("startedTranslationUnit"),
            ValueLayout.ADDRESS.withName("indexDeclaration"),
            ValueLayout.ADDRESS.withName("indexEntityReference")
        ).withName("IndexerCallbacks")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(layout.byteSize() * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(layout.byteSize())
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(layout.byteSize() * elementCount)
        
        val abortQuery_VH: VarHandle = layout.varHandle(groupElement("abortQuery"))
        
        @Suppress("UNCHECKED_CAST")
        fun abortQuery(segment: MemorySegment): MemorySegment? =
            abortQuery_VH.get(segment, 0L) as MemorySegment
        
        fun abortQuery(segment: MemorySegment, value: MemorySegment) =
            abortQuery_VH.set(segment, 0L, value)
        
        val diagnostic_VH: VarHandle = layout.varHandle(groupElement("diagnostic"))
        
        @Suppress("UNCHECKED_CAST")
        fun diagnostic(segment: MemorySegment): MemorySegment? =
            diagnostic_VH.get(segment, 0L) as MemorySegment
        
        fun diagnostic(segment: MemorySegment, value: MemorySegment) =
            diagnostic_VH.set(segment, 0L, value)
        
        val enteredMainFile_VH: VarHandle = layout.varHandle(groupElement("enteredMainFile"))
        
        @Suppress("UNCHECKED_CAST")
        fun enteredMainFile(segment: MemorySegment): MemorySegment? =
            enteredMainFile_VH.get(segment, 0L) as MemorySegment
        
        fun enteredMainFile(segment: MemorySegment, value: MemorySegment) =
            enteredMainFile_VH.set(segment, 0L, value)
        
        val ppIncludedFile_VH: VarHandle = layout.varHandle(groupElement("ppIncludedFile"))
        
        @Suppress("UNCHECKED_CAST")
        fun ppIncludedFile(segment: MemorySegment): MemorySegment? =
            ppIncludedFile_VH.get(segment, 0L) as MemorySegment
        
        fun ppIncludedFile(segment: MemorySegment, value: MemorySegment) =
            ppIncludedFile_VH.set(segment, 0L, value)
        
        val importedASTFile_VH: VarHandle = layout.varHandle(groupElement("importedASTFile"))
        
        @Suppress("UNCHECKED_CAST")
        fun importedASTFile(segment: MemorySegment): MemorySegment? =
            importedASTFile_VH.get(segment, 0L) as MemorySegment
        
        fun importedASTFile(segment: MemorySegment, value: MemorySegment) =
            importedASTFile_VH.set(segment, 0L, value)
        
        val startedTranslationUnit_VH: VarHandle = layout.varHandle(groupElement("startedTranslationUnit"))
        
        @Suppress("UNCHECKED_CAST")
        fun startedTranslationUnit(segment: MemorySegment): MemorySegment? =
            startedTranslationUnit_VH.get(segment, 0L) as MemorySegment
        
        fun startedTranslationUnit(segment: MemorySegment, value: MemorySegment) =
            startedTranslationUnit_VH.set(segment, 0L, value)
        
        val indexDeclaration_VH: VarHandle = layout.varHandle(groupElement("indexDeclaration"))
        
        @Suppress("UNCHECKED_CAST")
        fun indexDeclaration(segment: MemorySegment): MemorySegment? =
            indexDeclaration_VH.get(segment, 0L) as MemorySegment
        
        fun indexDeclaration(segment: MemorySegment, value: MemorySegment) =
            indexDeclaration_VH.set(segment, 0L, value)
        
        val indexEntityReference_VH: VarHandle = layout.varHandle(groupElement("indexEntityReference"))
        
        @Suppress("UNCHECKED_CAST")
        fun indexEntityReference(segment: MemorySegment): MemorySegment? =
            indexEntityReference_VH.get(segment, 0L) as MemorySegment
        
        fun indexEntityReference(segment: MemorySegment, value: MemorySegment) =
            indexEntityReference_VH.set(segment, 0L, value)
    }
}

/**
 * {@snippet lang=c : clang_index_isEntityObjCContainerKind Int(typedef CXIdxEntityKind = Declared(CXIdxEntityKind))
 */
private val clang_index_isEntityObjCContainerKind_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val clang_index_isEntityObjCContainerKind_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_index_isEntityObjCContainerKind")
private val clang_index_isEntityObjCContainerKind_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_index_isEntityObjCContainerKind_ADDR, clang_index_isEntityObjCContainerKind_DESC)

fun clang_index_isEntityObjCContainerKind(arg0: Int): Int {
    try {
        return clang_index_isEntityObjCContainerKind_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_index_getObjCContainerDeclInfo (typedef CXIdxObjCContainerDeclInfo = Declared(CXIdxObjCContainerDeclInfo))*((typedef CXIdxDeclInfo = Declared(CXIdxDeclInfo))*)
 */
private val clang_index_getObjCContainerDeclInfo_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_index_getObjCContainerDeclInfo_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_index_getObjCContainerDeclInfo")
private val clang_index_getObjCContainerDeclInfo_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_index_getObjCContainerDeclInfo_ADDR, clang_index_getObjCContainerDeclInfo_DESC)

fun clang_index_getObjCContainerDeclInfo(arg0: MemorySegment): MemorySegment {
    try {
        return clang_index_getObjCContainerDeclInfo_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_index_getObjCInterfaceDeclInfo (typedef CXIdxObjCInterfaceDeclInfo = Declared(CXIdxObjCInterfaceDeclInfo))*((typedef CXIdxDeclInfo = Declared(CXIdxDeclInfo))*)
 */
private val clang_index_getObjCInterfaceDeclInfo_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_index_getObjCInterfaceDeclInfo_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_index_getObjCInterfaceDeclInfo")
private val clang_index_getObjCInterfaceDeclInfo_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_index_getObjCInterfaceDeclInfo_ADDR, clang_index_getObjCInterfaceDeclInfo_DESC)

fun clang_index_getObjCInterfaceDeclInfo(arg0: MemorySegment): MemorySegment {
    try {
        return clang_index_getObjCInterfaceDeclInfo_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_index_getObjCCategoryDeclInfo (typedef CXIdxObjCCategoryDeclInfo = Declared(CXIdxObjCCategoryDeclInfo))*((typedef CXIdxDeclInfo = Declared(CXIdxDeclInfo))*)
 */
private val clang_index_getObjCCategoryDeclInfo_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_index_getObjCCategoryDeclInfo_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_index_getObjCCategoryDeclInfo")
private val clang_index_getObjCCategoryDeclInfo_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_index_getObjCCategoryDeclInfo_ADDR, clang_index_getObjCCategoryDeclInfo_DESC)

fun clang_index_getObjCCategoryDeclInfo(arg0: MemorySegment): MemorySegment {
    try {
        return clang_index_getObjCCategoryDeclInfo_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_index_getObjCProtocolRefListInfo (typedef CXIdxObjCProtocolRefListInfo = Declared(CXIdxObjCProtocolRefListInfo))*((typedef CXIdxDeclInfo = Declared(CXIdxDeclInfo))*)
 */
private val clang_index_getObjCProtocolRefListInfo_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_index_getObjCProtocolRefListInfo_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_index_getObjCProtocolRefListInfo")
private val clang_index_getObjCProtocolRefListInfo_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_index_getObjCProtocolRefListInfo_ADDR, clang_index_getObjCProtocolRefListInfo_DESC)

fun clang_index_getObjCProtocolRefListInfo(arg0: MemorySegment): MemorySegment {
    try {
        return clang_index_getObjCProtocolRefListInfo_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_index_getObjCPropertyDeclInfo (typedef CXIdxObjCPropertyDeclInfo = Declared(CXIdxObjCPropertyDeclInfo))*((typedef CXIdxDeclInfo = Declared(CXIdxDeclInfo))*)
 */
private val clang_index_getObjCPropertyDeclInfo_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_index_getObjCPropertyDeclInfo_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_index_getObjCPropertyDeclInfo")
private val clang_index_getObjCPropertyDeclInfo_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_index_getObjCPropertyDeclInfo_ADDR, clang_index_getObjCPropertyDeclInfo_DESC)

fun clang_index_getObjCPropertyDeclInfo(arg0: MemorySegment): MemorySegment {
    try {
        return clang_index_getObjCPropertyDeclInfo_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_index_getIBOutletCollectionAttrInfo (typedef CXIdxIBOutletCollectionAttrInfo = Declared(CXIdxIBOutletCollectionAttrInfo))*((typedef CXIdxAttrInfo = Declared(CXIdxAttrInfo))*)
 */
private val clang_index_getIBOutletCollectionAttrInfo_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_index_getIBOutletCollectionAttrInfo_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_index_getIBOutletCollectionAttrInfo")
private val clang_index_getIBOutletCollectionAttrInfo_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_index_getIBOutletCollectionAttrInfo_ADDR, clang_index_getIBOutletCollectionAttrInfo_DESC)

fun clang_index_getIBOutletCollectionAttrInfo(arg0: MemorySegment): MemorySegment {
    try {
        return clang_index_getIBOutletCollectionAttrInfo_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_index_getCXXClassDeclInfo (typedef CXIdxCXXClassDeclInfo = Declared(CXIdxCXXClassDeclInfo))*((typedef CXIdxDeclInfo = Declared(CXIdxDeclInfo))*)
 */
private val clang_index_getCXXClassDeclInfo_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_index_getCXXClassDeclInfo_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_index_getCXXClassDeclInfo")
private val clang_index_getCXXClassDeclInfo_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_index_getCXXClassDeclInfo_ADDR, clang_index_getCXXClassDeclInfo_DESC)

fun clang_index_getCXXClassDeclInfo(arg0: MemorySegment): MemorySegment {
    try {
        return clang_index_getCXXClassDeclInfo_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_index_getClientContainer typedef CXIdxClientContainer = (Void)*((typedef CXIdxContainerInfo = Declared(CXIdxContainerInfo))*)
 */
private val clang_index_getClientContainer_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_index_getClientContainer_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_index_getClientContainer")
private val clang_index_getClientContainer_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_index_getClientContainer_ADDR, clang_index_getClientContainer_DESC)

fun clang_index_getClientContainer(arg0: MemorySegment): MemorySegment {
    try {
        return clang_index_getClientContainer_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_index_setClientContainer Void((typedef CXIdxContainerInfo = Declared(CXIdxContainerInfo))*,typedef CXIdxClientContainer = (Void)*)
 */
private val clang_index_setClientContainer_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_index_setClientContainer_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_index_setClientContainer")
private val clang_index_setClientContainer_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_index_setClientContainer_ADDR, clang_index_setClientContainer_DESC)

fun clang_index_setClientContainer(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        clang_index_setClientContainer_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_index_getClientEntity typedef CXIdxClientEntity = (Void)*((typedef CXIdxEntityInfo = Declared(CXIdxEntityInfo))*)
 */
private val clang_index_getClientEntity_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_index_getClientEntity_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_index_getClientEntity")
private val clang_index_getClientEntity_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_index_getClientEntity_ADDR, clang_index_getClientEntity_DESC)

fun clang_index_getClientEntity(arg0: MemorySegment): MemorySegment {
    try {
        return clang_index_getClientEntity_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_index_setClientEntity Void((typedef CXIdxEntityInfo = Declared(CXIdxEntityInfo))*,typedef CXIdxClientEntity = (Void)*)
 */
private val clang_index_setClientEntity_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_index_setClientEntity_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_index_setClientEntity")
private val clang_index_setClientEntity_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_index_setClientEntity_ADDR, clang_index_setClientEntity_DESC)

fun clang_index_setClientEntity(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        clang_index_setClientEntity_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : typedef (Void)* CXIndexAction;}
 */
typealias CXIndexAction = MemorySegment?

/**
 * {@snippet lang=c : clang_IndexAction_create typedef CXIndexAction = (Void)*(typedef CXIndex = (Void)*)
 */
private val clang_IndexAction_create_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_IndexAction_create_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_IndexAction_create")
private val clang_IndexAction_create_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_IndexAction_create_ADDR, clang_IndexAction_create_DESC)

fun clang_IndexAction_create(arg0: MemorySegment): MemorySegment {
    try {
        return clang_IndexAction_create_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_IndexAction_dispose Void(typedef CXIndexAction = (Void)*)
 */
private val clang_IndexAction_dispose_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val clang_IndexAction_dispose_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_IndexAction_dispose")
private val clang_IndexAction_dispose_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_IndexAction_dispose_ADDR, clang_IndexAction_dispose_DESC)

fun clang_IndexAction_dispose(arg0: MemorySegment): Unit {
    try {
        clang_IndexAction_dispose_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXIndexOpt_None 0
 */
fun CXIndexOpt_None(): Int = 0

/**
 * {@snippet lang=c : #define CXIndexOpt_SuppressRedundantRefs 1
 */
fun CXIndexOpt_SuppressRedundantRefs(): Int = 1

/**
 * {@snippet lang=c : #define CXIndexOpt_IndexFunctionLocalSymbols 2
 */
fun CXIndexOpt_IndexFunctionLocalSymbols(): Int = 2

/**
 * {@snippet lang=c : #define CXIndexOpt_IndexImplicitTemplateInstantiations 4
 */
fun CXIndexOpt_IndexImplicitTemplateInstantiations(): Int = 4

/**
 * {@snippet lang=c : #define CXIndexOpt_SuppressWarnings 8
 */
fun CXIndexOpt_SuppressWarnings(): Int = 8

/**
 * {@snippet lang=c : #define CXIndexOpt_SkipParsedBodiesInSession 16
 */
fun CXIndexOpt_SkipParsedBodiesInSession(): Int = 16

/**
 * {@snippet lang=c : clang_indexSourceFile Int(typedef CXIndexAction = (Void)*,typedef CXClientData = (Void)*,(typedef IndexerCallbacks = Declared(IndexerCallbacks))*,UNSIGNED = Int,UNSIGNED = Int,(Char)*,((Char)*)*,Int,(Declared(CXUnsavedFile))*,UNSIGNED = Int,(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*)*,UNSIGNED = Int)
 */
private val clang_indexSourceFile_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_indexSourceFile_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_indexSourceFile")
private val clang_indexSourceFile_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_indexSourceFile_ADDR, clang_indexSourceFile_DESC)

fun clang_indexSourceFile(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Int, arg4: Int, arg5: MemorySegment, arg6: MemorySegment, arg7: Int, arg8: MemorySegment, arg9: Int, arg10: MemorySegment, arg11: Int): Int {
    try {
        return clang_indexSourceFile_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_indexSourceFileFullArgv Int(typedef CXIndexAction = (Void)*,typedef CXClientData = (Void)*,(typedef IndexerCallbacks = Declared(IndexerCallbacks))*,UNSIGNED = Int,UNSIGNED = Int,(Char)*,((Char)*)*,Int,(Declared(CXUnsavedFile))*,UNSIGNED = Int,(typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*)*,UNSIGNED = Int)
 */
private val clang_indexSourceFileFullArgv_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_indexSourceFileFullArgv_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_indexSourceFileFullArgv")
private val clang_indexSourceFileFullArgv_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_indexSourceFileFullArgv_ADDR, clang_indexSourceFileFullArgv_DESC)

fun clang_indexSourceFileFullArgv(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Int, arg4: Int, arg5: MemorySegment, arg6: MemorySegment, arg7: Int, arg8: MemorySegment, arg9: Int, arg10: MemorySegment, arg11: Int): Int {
    try {
        return clang_indexSourceFileFullArgv_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_indexTranslationUnit Int(typedef CXIndexAction = (Void)*,typedef CXClientData = (Void)*,(typedef IndexerCallbacks = Declared(IndexerCallbacks))*,UNSIGNED = Int,UNSIGNED = Int,typedef CXTranslationUnit = (Declared(CXTranslationUnitImpl))*)
 */
private val clang_indexTranslationUnit_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_indexTranslationUnit_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_indexTranslationUnit")
private val clang_indexTranslationUnit_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_indexTranslationUnit_ADDR, clang_indexTranslationUnit_DESC)

fun clang_indexTranslationUnit(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Int, arg4: Int, arg5: MemorySegment): Int {
    try {
        return clang_indexTranslationUnit_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_indexLoc_getFileLocation Void(typedef CXIdxLoc = Declared(CXIdxLoc),(typedef CXIdxClientFile = (Void)*)*,(typedef CXFile = (Void)*)*,(UNSIGNED = Int)*,(UNSIGNED = Int)*,(UNSIGNED = Int)*)
 */
private val clang_indexLoc_getFileLocation_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CXIdxLoc.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_indexLoc_getFileLocation_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_indexLoc_getFileLocation")
private val clang_indexLoc_getFileLocation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_indexLoc_getFileLocation_ADDR, clang_indexLoc_getFileLocation_DESC)

fun clang_indexLoc_getFileLocation(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment, arg5: MemorySegment): Unit {
    try {
        clang_indexLoc_getFileLocation_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_indexLoc_getCXSourceLocation typedef CXSourceLocation = Declared(CXSourceLocation)(typedef CXIdxLoc = Declared(CXIdxLoc))
 */
private val clang_indexLoc_getCXSourceLocation_DESC: FunctionDescriptor = FunctionDescriptor.of(CXSourceLocation.layout, CXIdxLoc.layout)
private val clang_indexLoc_getCXSourceLocation_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_indexLoc_getCXSourceLocation")
private val clang_indexLoc_getCXSourceLocation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_indexLoc_getCXSourceLocation_ADDR, clang_indexLoc_getCXSourceLocation_DESC)

fun clang_indexLoc_getCXSourceLocation(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return clang_indexLoc_getCXSourceLocation_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : typedef (Declared(CXVisitorResult)(Declared(CXCursor),(Void)*))* CXFieldVisitor;}
 */
typealias CXFieldVisitor = MemorySegment?

/**
 * {@snippet lang=c : clang_Type_visitFields UNSIGNED = Int(typedef CXType = Declared(CXType),typedef CXFieldVisitor = (Declared(CXVisitorResult)(Declared(CXCursor),(Void)*))*,typedef CXClientData = (Void)*)
 */
private val clang_Type_visitFields_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXType.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_Type_visitFields_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_Type_visitFields")
private val clang_Type_visitFields_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_Type_visitFields_ADDR, clang_Type_visitFields_DESC)

fun clang_Type_visitFields(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Int {
    try {
        return clang_Type_visitFields_HANDLE.invokeExact(arg0, arg1, arg2) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_visitCXXBaseClasses UNSIGNED = Int(typedef CXType = Declared(CXType),typedef CXFieldVisitor = (Declared(CXVisitorResult)(Declared(CXCursor),(Void)*))*,typedef CXClientData = (Void)*)
 */
private val clang_visitCXXBaseClasses_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXType.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_visitCXXBaseClasses_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_visitCXXBaseClasses")
private val clang_visitCXXBaseClasses_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_visitCXXBaseClasses_ADDR, clang_visitCXXBaseClasses_DESC)

fun clang_visitCXXBaseClasses(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Int {
    try {
        return clang_visitCXXBaseClasses_HANDLE.invokeExact(arg0, arg1, arg2) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_visitCXXMethods UNSIGNED = Int(typedef CXType = Declared(CXType),typedef CXFieldVisitor = (Declared(CXVisitorResult)(Declared(CXCursor),(Void)*))*,typedef CXClientData = (Void)*)
 */
private val clang_visitCXXMethods_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXType.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_visitCXXMethods_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_visitCXXMethods")
private val clang_visitCXXMethods_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_visitCXXMethods_ADDR, clang_visitCXXMethods_DESC)

fun clang_visitCXXMethods(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Int {
    try {
        return clang_visitCXXMethods_HANDLE.invokeExact(arg0, arg1, arg2) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXBinaryOperator_Invalid 0
 */
fun CXBinaryOperator_Invalid(): Int = 0

/**
 * {@snippet lang=c : #define CXBinaryOperator_PtrMemD 1
 */
fun CXBinaryOperator_PtrMemD(): Int = 1

/**
 * {@snippet lang=c : #define CXBinaryOperator_PtrMemI 2
 */
fun CXBinaryOperator_PtrMemI(): Int = 2

/**
 * {@snippet lang=c : #define CXBinaryOperator_Mul 3
 */
fun CXBinaryOperator_Mul(): Int = 3

/**
 * {@snippet lang=c : #define CXBinaryOperator_Div 4
 */
fun CXBinaryOperator_Div(): Int = 4

/**
 * {@snippet lang=c : #define CXBinaryOperator_Rem 5
 */
fun CXBinaryOperator_Rem(): Int = 5

/**
 * {@snippet lang=c : #define CXBinaryOperator_Add 6
 */
fun CXBinaryOperator_Add(): Int = 6

/**
 * {@snippet lang=c : #define CXBinaryOperator_Sub 7
 */
fun CXBinaryOperator_Sub(): Int = 7

/**
 * {@snippet lang=c : #define CXBinaryOperator_Shl 8
 */
fun CXBinaryOperator_Shl(): Int = 8

/**
 * {@snippet lang=c : #define CXBinaryOperator_Shr 9
 */
fun CXBinaryOperator_Shr(): Int = 9

/**
 * {@snippet lang=c : #define CXBinaryOperator_Cmp 10
 */
fun CXBinaryOperator_Cmp(): Int = 10

/**
 * {@snippet lang=c : #define CXBinaryOperator_LT 11
 */
fun CXBinaryOperator_LT(): Int = 11

/**
 * {@snippet lang=c : #define CXBinaryOperator_GT 12
 */
fun CXBinaryOperator_GT(): Int = 12

/**
 * {@snippet lang=c : #define CXBinaryOperator_LE 13
 */
fun CXBinaryOperator_LE(): Int = 13

/**
 * {@snippet lang=c : #define CXBinaryOperator_GE 14
 */
fun CXBinaryOperator_GE(): Int = 14

/**
 * {@snippet lang=c : #define CXBinaryOperator_EQ 15
 */
fun CXBinaryOperator_EQ(): Int = 15

/**
 * {@snippet lang=c : #define CXBinaryOperator_NE 16
 */
fun CXBinaryOperator_NE(): Int = 16

/**
 * {@snippet lang=c : #define CXBinaryOperator_And 17
 */
fun CXBinaryOperator_And(): Int = 17

/**
 * {@snippet lang=c : #define CXBinaryOperator_Xor 18
 */
fun CXBinaryOperator_Xor(): Int = 18

/**
 * {@snippet lang=c : #define CXBinaryOperator_Or 19
 */
fun CXBinaryOperator_Or(): Int = 19

/**
 * {@snippet lang=c : #define CXBinaryOperator_LAnd 20
 */
fun CXBinaryOperator_LAnd(): Int = 20

/**
 * {@snippet lang=c : #define CXBinaryOperator_LOr 21
 */
fun CXBinaryOperator_LOr(): Int = 21

/**
 * {@snippet lang=c : #define CXBinaryOperator_Assign 22
 */
fun CXBinaryOperator_Assign(): Int = 22

/**
 * {@snippet lang=c : #define CXBinaryOperator_MulAssign 23
 */
fun CXBinaryOperator_MulAssign(): Int = 23

/**
 * {@snippet lang=c : #define CXBinaryOperator_DivAssign 24
 */
fun CXBinaryOperator_DivAssign(): Int = 24

/**
 * {@snippet lang=c : #define CXBinaryOperator_RemAssign 25
 */
fun CXBinaryOperator_RemAssign(): Int = 25

/**
 * {@snippet lang=c : #define CXBinaryOperator_AddAssign 26
 */
fun CXBinaryOperator_AddAssign(): Int = 26

/**
 * {@snippet lang=c : #define CXBinaryOperator_SubAssign 27
 */
fun CXBinaryOperator_SubAssign(): Int = 27

/**
 * {@snippet lang=c : #define CXBinaryOperator_ShlAssign 28
 */
fun CXBinaryOperator_ShlAssign(): Int = 28

/**
 * {@snippet lang=c : #define CXBinaryOperator_ShrAssign 29
 */
fun CXBinaryOperator_ShrAssign(): Int = 29

/**
 * {@snippet lang=c : #define CXBinaryOperator_AndAssign 30
 */
fun CXBinaryOperator_AndAssign(): Int = 30

/**
 * {@snippet lang=c : #define CXBinaryOperator_XorAssign 31
 */
fun CXBinaryOperator_XorAssign(): Int = 31

/**
 * {@snippet lang=c : #define CXBinaryOperator_OrAssign 32
 */
fun CXBinaryOperator_OrAssign(): Int = 32

/**
 * {@snippet lang=c : #define CXBinaryOperator_Comma 33
 */
fun CXBinaryOperator_Comma(): Int = 33

/**
 * {@snippet lang=c : #define CXBinaryOperator_Last 33
 */
fun CXBinaryOperator_Last(): Int = 33

/**
 * {@snippet lang=c : clang_getBinaryOperatorKindSpelling typedef CXString = Declared(CXString)(Declared(CXBinaryOperatorKind))
 */
private val clang_getBinaryOperatorKindSpelling_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.JAVA_INT)
private val clang_getBinaryOperatorKindSpelling_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getBinaryOperatorKindSpelling")
private val clang_getBinaryOperatorKindSpelling_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getBinaryOperatorKindSpelling_ADDR, clang_getBinaryOperatorKindSpelling_DESC)

fun clang_getBinaryOperatorKindSpelling(allocator: SegmentAllocator, arg0: Int): MemorySegment {
    try {
        return clang_getBinaryOperatorKindSpelling_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCursorBinaryOperatorKind Declared(CXBinaryOperatorKind)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getCursorBinaryOperatorKind_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_getCursorBinaryOperatorKind_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorBinaryOperatorKind")
private val clang_getCursorBinaryOperatorKind_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorBinaryOperatorKind_ADDR, clang_getCursorBinaryOperatorKind_DESC)

fun clang_getCursorBinaryOperatorKind(arg0: MemorySegment): Int {
    try {
        return clang_getCursorBinaryOperatorKind_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define CXUnaryOperator_Invalid 0
 */
fun CXUnaryOperator_Invalid(): Int = 0

/**
 * {@snippet lang=c : #define CXUnaryOperator_PostInc 1
 */
fun CXUnaryOperator_PostInc(): Int = 1

/**
 * {@snippet lang=c : #define CXUnaryOperator_PostDec 2
 */
fun CXUnaryOperator_PostDec(): Int = 2

/**
 * {@snippet lang=c : #define CXUnaryOperator_PreInc 3
 */
fun CXUnaryOperator_PreInc(): Int = 3

/**
 * {@snippet lang=c : #define CXUnaryOperator_PreDec 4
 */
fun CXUnaryOperator_PreDec(): Int = 4

/**
 * {@snippet lang=c : #define CXUnaryOperator_AddrOf 5
 */
fun CXUnaryOperator_AddrOf(): Int = 5

/**
 * {@snippet lang=c : #define CXUnaryOperator_Deref 6
 */
fun CXUnaryOperator_Deref(): Int = 6

/**
 * {@snippet lang=c : #define CXUnaryOperator_Plus 7
 */
fun CXUnaryOperator_Plus(): Int = 7

/**
 * {@snippet lang=c : #define CXUnaryOperator_Minus 8
 */
fun CXUnaryOperator_Minus(): Int = 8

/**
 * {@snippet lang=c : #define CXUnaryOperator_Not 9
 */
fun CXUnaryOperator_Not(): Int = 9

/**
 * {@snippet lang=c : #define CXUnaryOperator_LNot 10
 */
fun CXUnaryOperator_LNot(): Int = 10

/**
 * {@snippet lang=c : #define CXUnaryOperator_Real 11
 */
fun CXUnaryOperator_Real(): Int = 11

/**
 * {@snippet lang=c : #define CXUnaryOperator_Imag 12
 */
fun CXUnaryOperator_Imag(): Int = 12

/**
 * {@snippet lang=c : #define CXUnaryOperator_Extension 13
 */
fun CXUnaryOperator_Extension(): Int = 13

/**
 * {@snippet lang=c : #define CXUnaryOperator_Coawait 14
 */
fun CXUnaryOperator_Coawait(): Int = 14

/**
 * {@snippet lang=c : clang_getUnaryOperatorKindSpelling typedef CXString = Declared(CXString)(Declared(CXUnaryOperatorKind))
 */
private val clang_getUnaryOperatorKindSpelling_DESC: FunctionDescriptor = FunctionDescriptor.of(CXString.layout, ValueLayout.JAVA_INT)
private val clang_getUnaryOperatorKindSpelling_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getUnaryOperatorKindSpelling")
private val clang_getUnaryOperatorKindSpelling_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getUnaryOperatorKindSpelling_ADDR, clang_getUnaryOperatorKindSpelling_DESC)

fun clang_getUnaryOperatorKindSpelling(allocator: SegmentAllocator, arg0: Int): MemorySegment {
    try {
        return clang_getUnaryOperatorKindSpelling_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getCursorUnaryOperatorKind Declared(CXUnaryOperatorKind)(typedef CXCursor = Declared(CXCursor))
 */
private val clang_getCursorUnaryOperatorKind_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, CXCursor.layout)
private val clang_getCursorUnaryOperatorKind_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getCursorUnaryOperatorKind")
private val clang_getCursorUnaryOperatorKind_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getCursorUnaryOperatorKind_ADDR, clang_getCursorUnaryOperatorKind_DESC)

fun clang_getCursorUnaryOperatorKind(arg0: MemorySegment): Int {
    try {
        return clang_getCursorUnaryOperatorKind_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : typedef (Void)* CXRemapping;}
 */
typealias CXRemapping = MemorySegment?

/**
 * {@snippet lang=c : clang_getRemappings typedef CXRemapping = (Void)*((Char)*)
 */
private val clang_getRemappings_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_getRemappings_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getRemappings")
private val clang_getRemappings_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getRemappings_ADDR, clang_getRemappings_DESC)

fun clang_getRemappings(arg0: MemorySegment): MemorySegment {
    try {
        return clang_getRemappings_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_getRemappingsFromFileList typedef CXRemapping = (Void)*(((Char)*)*,UNSIGNED = Int)
 */
private val clang_getRemappingsFromFileList_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val clang_getRemappingsFromFileList_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_getRemappingsFromFileList")
private val clang_getRemappingsFromFileList_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_getRemappingsFromFileList_ADDR, clang_getRemappingsFromFileList_DESC)

fun clang_getRemappingsFromFileList(arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return clang_getRemappingsFromFileList_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_remap_getNumFiles UNSIGNED = Int(typedef CXRemapping = (Void)*)
 */
private val clang_remap_getNumFiles_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val clang_remap_getNumFiles_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_remap_getNumFiles")
private val clang_remap_getNumFiles_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_remap_getNumFiles_ADDR, clang_remap_getNumFiles_DESC)

fun clang_remap_getNumFiles(arg0: MemorySegment): Int {
    try {
        return clang_remap_getNumFiles_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_remap_getFilenames Void(typedef CXRemapping = (Void)*,UNSIGNED = Int,(typedef CXString = Declared(CXString))*,(typedef CXString = Declared(CXString))*)
 */
private val clang_remap_getFilenames_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val clang_remap_getFilenames_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_remap_getFilenames")
private val clang_remap_getFilenames_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_remap_getFilenames_ADDR, clang_remap_getFilenames_DESC)

fun clang_remap_getFilenames(arg0: MemorySegment, arg1: Int, arg2: MemorySegment, arg3: MemorySegment): Unit {
    try {
        clang_remap_getFilenames_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : clang_remap_dispose Void(typedef CXRemapping = (Void)*)
 */
private val clang_remap_dispose_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val clang_remap_dispose_ADDR: MemorySegment = SymbolLookup.loaderLookup().findOrThrow("clang_remap_dispose")
private val clang_remap_dispose_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(clang_remap_dispose_ADDR, clang_remap_dispose_DESC)

fun clang_remap_dispose(arg0: MemorySegment): Unit {
    try {
        clang_remap_dispose_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : #define __DARWIN_SUF_EXTSN $DARWIN_EXTSN
 */
// Skipped: macro value references another macro with $ prefix which is not representable in Kotlin

/**
 * {@snippet lang=c : #define __DARWIN_C_ANSI 4096
 */
fun _DARWIN_C_ANSI(): Long = 4096

/**
 * {@snippet lang=c : #define __DARWIN_C_FULL 900000
 */
fun _DARWIN_C_FULL(): Long = 900000

/**
 * {@snippet lang=c : #define __DARWIN_C_LEVEL 900000
 */
fun _DARWIN_C_LEVEL(): Long = 900000

/**
 * {@snippet lang=c : #define __DARWIN_NULL 0
 */
fun _DARWIN_NULL(): Long = 0

/**
 * {@snippet lang=c : #define __DARWIN_WCHAR_MAX 2147483647
 */
fun _DARWIN_WCHAR_MAX(): Int = 2147483647

/**
 * {@snippet lang=c : #define __DARWIN_WCHAR_MIN -2147483648
 */
fun _DARWIN_WCHAR_MIN(): Int = -2147483648

/**
 * {@snippet lang=c : #define __DARWIN_WEOF -1
 */
fun _DARWIN_WEOF(): Int = -1

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_0 1000
 */
fun MAC_OS_X_VERSION_10_0(): Int = 1000

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_1 1010
 */
fun MAC_OS_X_VERSION_10_1(): Int = 1010

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_2 1020
 */
fun MAC_OS_X_VERSION_10_2(): Int = 1020

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_3 1030
 */
fun MAC_OS_X_VERSION_10_3(): Int = 1030

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_4 1040
 */
fun MAC_OS_X_VERSION_10_4(): Int = 1040

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_5 1050
 */
fun MAC_OS_X_VERSION_10_5(): Int = 1050

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_6 1060
 */
fun MAC_OS_X_VERSION_10_6(): Int = 1060

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_7 1070
 */
fun MAC_OS_X_VERSION_10_7(): Int = 1070

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_8 1080
 */
fun MAC_OS_X_VERSION_10_8(): Int = 1080

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_9 1090
 */
fun MAC_OS_X_VERSION_10_9(): Int = 1090

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_10 101000
 */
fun MAC_OS_X_VERSION_10_10(): Int = 101000

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_10_2 101002
 */
fun MAC_OS_X_VERSION_10_10_2(): Int = 101002

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_10_3 101003
 */
fun MAC_OS_X_VERSION_10_10_3(): Int = 101003

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_11 101100
 */
fun MAC_OS_X_VERSION_10_11(): Int = 101100

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_11_2 101102
 */
fun MAC_OS_X_VERSION_10_11_2(): Int = 101102

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_11_3 101103
 */
fun MAC_OS_X_VERSION_10_11_3(): Int = 101103

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_11_4 101104
 */
fun MAC_OS_X_VERSION_10_11_4(): Int = 101104

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_12 101200
 */
fun MAC_OS_X_VERSION_10_12(): Int = 101200

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_12_1 101201
 */
fun MAC_OS_X_VERSION_10_12_1(): Int = 101201

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_12_2 101202
 */
fun MAC_OS_X_VERSION_10_12_2(): Int = 101202

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_12_4 101204
 */
fun MAC_OS_X_VERSION_10_12_4(): Int = 101204

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_13 101300
 */
fun MAC_OS_X_VERSION_10_13(): Int = 101300

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_13_1 101301
 */
fun MAC_OS_X_VERSION_10_13_1(): Int = 101301

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_13_2 101302
 */
fun MAC_OS_X_VERSION_10_13_2(): Int = 101302

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_13_4 101304
 */
fun MAC_OS_X_VERSION_10_13_4(): Int = 101304

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_14 101400
 */
fun MAC_OS_X_VERSION_10_14(): Int = 101400

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_14_1 101401
 */
fun MAC_OS_X_VERSION_10_14_1(): Int = 101401

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_14_4 101404
 */
fun MAC_OS_X_VERSION_10_14_4(): Int = 101404

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_14_5 101405
 */
fun MAC_OS_X_VERSION_10_14_5(): Int = 101405

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_14_6 101406
 */
fun MAC_OS_X_VERSION_10_14_6(): Int = 101406

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_15 101500
 */
fun MAC_OS_X_VERSION_10_15(): Int = 101500

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_15_1 101501
 */
fun MAC_OS_X_VERSION_10_15_1(): Int = 101501

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_15_4 101504
 */
fun MAC_OS_X_VERSION_10_15_4(): Int = 101504

/**
 * {@snippet lang=c : #define MAC_OS_X_VERSION_10_16 101600
 */
fun MAC_OS_X_VERSION_10_16(): Int = 101600

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_11_0 110000
 */
fun MAC_OS_VERSION_11_0(): Int = 110000

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_11_1 110100
 */
fun MAC_OS_VERSION_11_1(): Int = 110100

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_11_3 110300
 */
fun MAC_OS_VERSION_11_3(): Int = 110300

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_11_4 110400
 */
fun MAC_OS_VERSION_11_4(): Int = 110400

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_11_5 110500
 */
fun MAC_OS_VERSION_11_5(): Int = 110500

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_11_6 110600
 */
fun MAC_OS_VERSION_11_6(): Int = 110600

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_12_0 120000
 */
fun MAC_OS_VERSION_12_0(): Int = 120000

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_12_1 120100
 */
fun MAC_OS_VERSION_12_1(): Int = 120100

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_12_2 120200
 */
fun MAC_OS_VERSION_12_2(): Int = 120200

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_12_3 120300
 */
fun MAC_OS_VERSION_12_3(): Int = 120300

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_12_4 120400
 */
fun MAC_OS_VERSION_12_4(): Int = 120400

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_12_5 120500
 */
fun MAC_OS_VERSION_12_5(): Int = 120500

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_12_6 120600
 */
fun MAC_OS_VERSION_12_6(): Int = 120600

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_12_7 120700
 */
fun MAC_OS_VERSION_12_7(): Int = 120700

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_13_0 130000
 */
fun MAC_OS_VERSION_13_0(): Int = 130000

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_13_1 130100
 */
fun MAC_OS_VERSION_13_1(): Int = 130100

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_13_2 130200
 */
fun MAC_OS_VERSION_13_2(): Int = 130200

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_13_3 130300
 */
fun MAC_OS_VERSION_13_3(): Int = 130300

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_13_4 130400
 */
fun MAC_OS_VERSION_13_4(): Int = 130400

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_13_5 130500
 */
fun MAC_OS_VERSION_13_5(): Int = 130500

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_13_6 130600
 */
fun MAC_OS_VERSION_13_6(): Int = 130600

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_13_7 130700
 */
fun MAC_OS_VERSION_13_7(): Int = 130700

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_14_0 140000
 */
fun MAC_OS_VERSION_14_0(): Int = 140000

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_14_1 140100
 */
fun MAC_OS_VERSION_14_1(): Int = 140100

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_14_2 140200
 */
fun MAC_OS_VERSION_14_2(): Int = 140200

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_14_3 140300
 */
fun MAC_OS_VERSION_14_3(): Int = 140300

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_14_4 140400
 */
fun MAC_OS_VERSION_14_4(): Int = 140400

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_14_5 140500
 */
fun MAC_OS_VERSION_14_5(): Int = 140500

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_14_6 140600
 */
fun MAC_OS_VERSION_14_6(): Int = 140600

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_14_7 140700
 */
fun MAC_OS_VERSION_14_7(): Int = 140700

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_15_0 150000
 */
fun MAC_OS_VERSION_15_0(): Int = 150000

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_15_1 150100
 */
fun MAC_OS_VERSION_15_1(): Int = 150100

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_15_2 150200
 */
fun MAC_OS_VERSION_15_2(): Int = 150200

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_15_3 150300
 */
fun MAC_OS_VERSION_15_3(): Int = 150300

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_15_4 150400
 */
fun MAC_OS_VERSION_15_4(): Int = 150400

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_15_5 150500
 */
fun MAC_OS_VERSION_15_5(): Int = 150500

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_15_6 150600
 */
fun MAC_OS_VERSION_15_6(): Int = 150600

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_16_0 160000
 */
fun MAC_OS_VERSION_16_0(): Int = 160000

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_26_0 260000
 */
fun MAC_OS_VERSION_26_0(): Int = 260000

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_26_1 260100
 */
fun MAC_OS_VERSION_26_1(): Int = 260100

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_26_2 260200
 */
fun MAC_OS_VERSION_26_2(): Int = 260200

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_26_3 260300
 */
fun MAC_OS_VERSION_26_3(): Int = 260300

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_26_4 260400
 */
fun MAC_OS_VERSION_26_4(): Int = 260400

/**
 * {@snippet lang=c : #define MAC_OS_VERSION_26_5 260500
 */
fun MAC_OS_VERSION_26_5(): Int = 260500

/**
 * {@snippet lang=c : #define __AVAILABILITY_VERSIONS_VERSION_HASH 93585900
 */
fun _AVAILABILITY_VERSIONS_VERSION_HASH(): Int = 93585900

/**
 * {@snippet lang=c : #define __AVAILABILITY_VERSIONS_VERSION_STRING Local
 */
// Skipped: macro value 'Local' is not representable as a valid Kotlin expression

/**
 * {@snippet lang=c : #define __AVAILABILITY_FILE AvailabilityVersions.h
 */
// Skipped: macro value 'AvailabilityVersions.h' is not representable as a valid Kotlin expression

/**
 * {@snippet lang=c : #define __MAC_OS_X_VERSION_MIN_REQUIRED 260000
 */
fun _MAC_OS_X_VERSION_MIN_REQUIRED(): Int = 260000

/**
 * {@snippet lang=c : #define __MAC_OS_X_VERSION_MAX_ALLOWED 260500
 */
fun _MAC_OS_X_VERSION_MAX_ALLOWED(): Int = 260500

/**
 * {@snippet lang=c : #define USER_ADDR_NULL 0
 */
fun USER_ADDR_NULL(): Long = 0

/**
 * {@snippet lang=c : #define NULL 0
 */
fun NULL(): Long = 0

/**
 * {@snippet lang=c : #define CLOCKS_PER_SEC 1000000
 */
fun CLOCKS_PER_SEC(): Long = 1000000

/**
 * {@snippet lang=c : #define CLOCK_REALTIME 0
 */
fun CLOCK_REALTIME(): Int = 0

/**
 * {@snippet lang=c : #define CLOCK_MONOTONIC 6
 */
fun CLOCK_MONOTONIC(): Int = 6

/**
 * {@snippet lang=c : #define CLOCK_MONOTONIC_RAW 4
 */
fun CLOCK_MONOTONIC_RAW(): Int = 4

/**
 * {@snippet lang=c : #define CLOCK_MONOTONIC_RAW_APPROX 5
 */
fun CLOCK_MONOTONIC_RAW_APPROX(): Int = 5

/**
 * {@snippet lang=c : #define CLOCK_UPTIME_RAW 8
 */
fun CLOCK_UPTIME_RAW(): Int = 8

/**
 * {@snippet lang=c : #define CLOCK_UPTIME_RAW_APPROX 9
 */
fun CLOCK_UPTIME_RAW_APPROX(): Int = 9

/**
 * {@snippet lang=c : #define CLOCK_PROCESS_CPUTIME_ID 12
 */
fun CLOCK_PROCESS_CPUTIME_ID(): Int = 12

/**
 * {@snippet lang=c : #define CLOCK_THREAD_CPUTIME_ID 16
 */
fun CLOCK_THREAD_CPUTIME_ID(): Int = 16

/**
 * {@snippet lang=c : #define CINDEX_VERSION 64
 */
fun CINDEX_VERSION(): Int = 64

/**
 * {@snippet lang=c : #define CINDEX_VERSION_STRING 0.64
 */
fun CINDEX_VERSION_STRING(): String = "0.64"

