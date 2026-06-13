package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

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
 * {@snippet lang=c : typedef Long __darwin_ptrdiff_t;}
 */
typealias _darwin_ptrdiff_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long __darwin_size_t;}
 */
typealias _darwin_size_t = Long

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
 * {@snippet lang=c : typedef UNSIGNED = Long __darwin_pthread_key_t;}
 */
typealias _darwin_pthread_key_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Char u_char;}
 */
typealias u_char = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = Short u_short;}
 */
typealias u_short = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int u_int;}
 */
typealias u_int = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Long u_long;}
 */
typealias u_long = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Short ushort;}
 */
typealias ushort = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int uint;}
 */
typealias uint = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong u_quad_t;}
 */
typealias u_quad_t = Long

/**
 * {@snippet lang=c : typedef LongLong quad_t;}
 */
typealias quad_t = Long

/**
 * {@snippet lang=c : typedef Int daddr_t;}
 */
typealias daddr_t = Int

/**
 * {@snippet lang=c : typedef Int dev_t;}
 */
typealias dev_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int fixpt_t;}
 */
typealias fixpt_t = Int

/**
 * {@snippet lang=c : typedef LongLong blkcnt_t;}
 */
typealias blkcnt_t = Long

/**
 * {@snippet lang=c : typedef Int blksize_t;}
 */
typealias blksize_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int gid_t;}
 */
typealias gid_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int in_addr_t;}
 */
typealias in_addr_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short in_port_t;}
 */
typealias in_port_t = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong ino_t;}
 */
typealias ino_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong ino64_t;}
 */
typealias ino64_t = Long

/**
 * {@snippet lang=c : typedef Int key_t;}
 */
typealias key_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short mode_t;}
 */
typealias mode_t = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short nlink_t;}
 */
typealias nlink_t = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int id_t;}
 */
typealias id_t = Int

/**
 * {@snippet lang=c : typedef Int pid_t;}
 */
typealias pid_t = Int

/**
 * {@snippet lang=c : typedef LongLong off_t;}
 */
typealias off_t = Long

/**
 * {@snippet lang=c : typedef Int segsz_t;}
 */
typealias segsz_t = Int

/**
 * {@snippet lang=c : typedef Int swblk_t;}
 */
typealias swblk_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int uid_t;}
 */
typealias uid_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Long clock_t;}
 */
typealias clock_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long size_t;}
 */
typealias size_t = Long

/**
 * {@snippet lang=c : typedef Long ssize_t;}
 */
typealias ssize_t = Long

/**
 * {@snippet lang=c : typedef Long time_t;}
 */
typealias time_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int useconds_t;}
 */
typealias useconds_t = Int

/**
 * {@snippet lang=c : typedef Int suseconds_t;}
 */
typealias suseconds_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Long rsize_t;}
 */
typealias rsize_t = Long

/**
 * {@snippet lang=c : typedef Int errno_t;}
 */
typealias errno_t = Int

/**
 * {@snippet lang=c : typedef Int fd_mask;}
 */
typealias fd_mask = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Long pthread_key_t;}
 */
typealias pthread_key_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int fsblkcnt_t;}
 */
typealias fsblkcnt_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int fsfilcnt_t;}
 */
typealias fsfilcnt_t = Int

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
 * {@snippet lang=c : typedef Int ct_rune_t;}
 */
typealias ct_rune_t = Int

/**
 * {@snippet lang=c : typedef Int rune_t;}
 */
typealias rune_t = Int

/**
 * {@snippet lang=c : typedef Int wchar_t;}
 */
typealias wchar_t = Int

/**
 * {@snippet lang=c : typedef Int wint_t;}
 */
typealias wint_t = Int

/**
 * {@snippet lang=c : typedef Float float_t;}
 */
typealias float_t = Float

/**
 * {@snippet lang=c : typedef Double double_t;}
 */
typealias double_t = Double

/**
 * {@snippet lang=c : typedef Int sig_atomic_t;}
 */
typealias sig_atomic_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int sigset_t;}
 */
typealias sigset_t = Int

/**
 * {@snippet lang=c : typedef Long ptrdiff_t;}
 */
typealias ptrdiff_t = Long

/**
 * {@snippet lang=c : typedef LongLong fpos_t;}
 */
typealias fpos_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Char uint8_t;}
 */
typealias uint8_t = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = Short uint16_t;}
 */
typealias uint16_t = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int uint32_t;}
 */
typealias uint32_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong uint64_t;}
 */
typealias uint64_t = Long

/**
 * {@snippet lang=c : typedef SIGNED = Char int_least8_t;}
 */
typealias int_least8_t = Byte

/**
 * {@snippet lang=c : typedef Short int_least16_t;}
 */
typealias int_least16_t = Short

/**
 * {@snippet lang=c : typedef Int int_least32_t;}
 */
typealias int_least32_t = Int

/**
 * {@snippet lang=c : typedef LongLong int_least64_t;}
 */
typealias int_least64_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Char uint_least8_t;}
 */
typealias uint_least8_t = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = Short uint_least16_t;}
 */
typealias uint_least16_t = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int uint_least32_t;}
 */
typealias uint_least32_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong uint_least64_t;}
 */
typealias uint_least64_t = Long

/**
 * {@snippet lang=c : typedef SIGNED = Char int_fast8_t;}
 */
typealias int_fast8_t = Byte

/**
 * {@snippet lang=c : typedef Short int_fast16_t;}
 */
typealias int_fast16_t = Short

/**
 * {@snippet lang=c : typedef Int int_fast32_t;}
 */
typealias int_fast32_t = Int

/**
 * {@snippet lang=c : typedef LongLong int_fast64_t;}
 */
typealias int_fast64_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Char uint_fast8_t;}
 */
typealias uint_fast8_t = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = Short uint_fast16_t;}
 */
typealias uint_fast16_t = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int uint_fast32_t;}
 */
typealias uint_fast32_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong uint_fast64_t;}
 */
typealias uint_fast64_t = Long

/**
 * {@snippet lang=c : typedef Long intmax_t;}
 */
typealias intmax_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long uintmax_t;}
 */
typealias uintmax_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong rlim_t;}
 */
typealias rlim_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong malloc_type_id_t;}
 */
typealias malloc_type_id_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Char UInt8;}
 */
typealias UInt8 = Byte

/**
 * {@snippet lang=c : typedef SIGNED = Char SInt8;}
 */
typealias SInt8 = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = Short UInt16;}
 */
typealias UInt16 = Short

/**
 * {@snippet lang=c : typedef Short SInt16;}
 */
typealias SInt16 = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int UInt32;}
 */
typealias UInt32 = Int

/**
 * {@snippet lang=c : typedef Int SInt32;}
 */
typealias SInt32 = Int

/**
 * {@snippet lang=c : typedef LongLong SInt64;}
 */
typealias SInt64 = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong UInt64;}
 */
typealias UInt64 = Long

/**
 * {@snippet lang=c : typedef Int Fixed;}
 */
typealias Fixed = Int

/**
 * {@snippet lang=c : typedef Int Fract;}
 */
typealias Fract = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int UnsignedFixed;}
 */
typealias UnsignedFixed = Int

/**
 * {@snippet lang=c : typedef Short ShortFixed;}
 */
typealias ShortFixed = Short

/**
 * {@snippet lang=c : typedef Float Float32;}
 */
typealias Float32 = Float

/**
 * {@snippet lang=c : typedef Double Float64;}
 */
typealias Float64 = Double

/**
 * {@snippet lang=c : typedef Long Size;}
 */
typealias Size = Long

/**
 * {@snippet lang=c : typedef Short OSErr;}
 */
typealias OSErr = Short

/**
 * {@snippet lang=c : typedef Int OSStatus;}
 */
typealias OSStatus = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Long ByteCount;}
 */
typealias ByteCount = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long ByteOffset;}
 */
typealias ByteOffset = Long

/**
 * {@snippet lang=c : typedef Int Duration;}
 */
typealias Duration = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int OptionBits;}
 */
typealias OptionBits = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Long ItemCount;}
 */
typealias ItemCount = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int PBVersion;}
 */
typealias PBVersion = Int

/**
 * {@snippet lang=c : typedef Short ScriptCode;}
 */
typealias ScriptCode = Short

/**
 * {@snippet lang=c : typedef Short LangCode;}
 */
typealias LangCode = Short

/**
 * {@snippet lang=c : typedef Short RegionCode;}
 */
typealias RegionCode = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int FourCharCode;}
 */
typealias FourCharCode = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int OSType;}
 */
typealias OSType = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ResType;}
 */
typealias ResType = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Char Boolean;}
 */
typealias Boolean = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = Int UnicodeScalarValue;}
 */
typealias UnicodeScalarValue = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int UTF32Char;}
 */
typealias UTF32Char = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short UniChar;}
 */
typealias UniChar = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short UTF16Char;}
 */
typealias UTF16Char = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Char UTF8Char;}
 */
typealias UTF8Char = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = Long UniCharCount;}
 */
typealias UniCharCount = Long

/**
 * {@snippet lang=c : typedef Short CharParameter;}
 */
typealias CharParameter = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Char Style;}
 */
typealias Style = Byte

/**
 * {@snippet lang=c : typedef Short StyleParameter;}
 */
typealias StyleParameter = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Char StyleField;}
 */
typealias StyleField = Byte

/**
 * {@snippet lang=c : typedef Int TimeValue;}
 */
typealias TimeValue = Int

/**
 * {@snippet lang=c : typedef Int TimeScale;}
 */
typealias TimeScale = Int

/**
 * {@snippet lang=c : typedef LongLong TimeValue64;}
 */
typealias TimeValue64 = Long

/**
 * {@snippet lang=c : typedef SIGNED = Char SignedByte;}
 */
typealias SignedByte = Byte

/**
 * {@snippet lang=c : typedef SIGNED = Char VHSelect;}
 */
typealias VHSelect = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong CFAllocatorTypeID;}
 */
typealias CFAllocatorTypeID = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long CFTypeID;}
 */
typealias CFTypeID = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long CFOptionFlags;}
 */
typealias CFOptionFlags = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long CFHashCode;}
 */
typealias CFHashCode = Long

/**
 * {@snippet lang=c : typedef Long CFIndex;}
 */
typealias CFIndex = Long

/**
 * {@snippet lang=c : typedef (Void)* CFTypeRef;}
 */
typealias CFTypeRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFString))* CFStringRef;}
 */
typealias CFStringRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFString))* CFMutableStringRef;}
 */
typealias CFMutableStringRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Void)* CFPropertyListRef;}
 */
typealias CFPropertyListRef = MemorySegment

/**
 * {@snippet lang=c : STRUCT CFRange
 */
class CFRange {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("location"),
            ValueLayout.JAVA_LONG.withName("length")
        ).withName("CFRange")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val location_VH: VarHandle = layout.varHandle(groupElement("location"))
    
    @Suppress("UNCHECKED_CAST")
    fun location(segment: MemorySegment): Long =
        location_VH.get(segment, 0L) as Long
    
    fun location(segment: MemorySegment, value: Long) =
        location_VH.set(segment, 0L, value)
    
    val length_VH: VarHandle = layout.varHandle(groupElement("length"))
    
    @Suppress("UNCHECKED_CAST")
    fun length(segment: MemorySegment): Long =
        length_VH.get(segment, 0L) as Long
    
    fun length(segment: MemorySegment, value: Long) =
        length_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Declared(__CFNull))* CFNullRef;}
 */
typealias CFNullRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFAllocator))* CFAllocatorRef;}
 */
typealias CFAllocatorRef = MemorySegment

/**
 * {@snippet lang=c : typedef ((Void)*((Void)*))* CFAllocatorRetainCallBack;}
 */
typealias CFAllocatorRetainCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Void)*))* CFAllocatorReleaseCallBack;}
 */
typealias CFAllocatorReleaseCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef ((Declared(__CFString))*((Void)*))* CFAllocatorCopyDescriptionCallBack;}
 */
typealias CFAllocatorCopyDescriptionCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef ((Void)*(Long,UNSIGNED = Long,(Void)*))* CFAllocatorAllocateCallBack;}
 */
typealias CFAllocatorAllocateCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef ((Void)*((Void)*,Long,UNSIGNED = Long,(Void)*))* CFAllocatorReallocateCallBack;}
 */
typealias CFAllocatorReallocateCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Void)*,(Void)*))* CFAllocatorDeallocateCallBack;}
 */
typealias CFAllocatorDeallocateCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef (Long(Long,UNSIGNED = Long,(Void)*))* CFAllocatorPreferredSizeCallBack;}
 */
typealias CFAllocatorPreferredSizeCallBack = MemorySegment

/**
 * {@snippet lang=c : STRUCT CFAllocatorContext
 */
class CFAllocatorContext {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("version"),
            ValueLayout.ADDRESS.withName("info"),
            ValueLayout.ADDRESS.withName("retain"),
            ValueLayout.ADDRESS.withName("release"),
            ValueLayout.ADDRESS.withName("copyDescription"),
            ValueLayout.ADDRESS.withName("allocate"),
            ValueLayout.ADDRESS.withName("reallocate"),
            ValueLayout.ADDRESS.withName("deallocate"),
            ValueLayout.ADDRESS.withName("preferredSize")
        ).withName("CFAllocatorContext")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Long =
        version_VH.get(segment, 0L) as Long
    
    fun version(segment: MemorySegment, value: Long) =
        version_VH.set(segment, 0L, value)
    
    val info_VH: VarHandle = layout.varHandle(groupElement("info"))
    
    @Suppress("UNCHECKED_CAST")
    fun info(segment: MemorySegment): MemorySegment =
        info_VH.get(segment, 0L) as MemorySegment
    
    fun info(segment: MemorySegment, value: MemorySegment) =
        info_VH.set(segment, 0L, value)
    
    val retain_VH: VarHandle = layout.varHandle(groupElement("retain"))
    
    @Suppress("UNCHECKED_CAST")
    fun retain(segment: MemorySegment): MemorySegment =
        retain_VH.get(segment, 0L) as MemorySegment
    
    fun retain(segment: MemorySegment, value: MemorySegment) =
        retain_VH.set(segment, 0L, value)
    
    val release_VH: VarHandle = layout.varHandle(groupElement("release"))
    
    @Suppress("UNCHECKED_CAST")
    fun release(segment: MemorySegment): MemorySegment =
        release_VH.get(segment, 0L) as MemorySegment
    
    fun release(segment: MemorySegment, value: MemorySegment) =
        release_VH.set(segment, 0L, value)
    
    val copyDescription_VH: VarHandle = layout.varHandle(groupElement("copyDescription"))
    
    @Suppress("UNCHECKED_CAST")
    fun copyDescription(segment: MemorySegment): MemorySegment =
        copyDescription_VH.get(segment, 0L) as MemorySegment
    
    fun copyDescription(segment: MemorySegment, value: MemorySegment) =
        copyDescription_VH.set(segment, 0L, value)
    
    val allocate_VH: VarHandle = layout.varHandle(groupElement("allocate"))
    
    @Suppress("UNCHECKED_CAST")
    fun allocate(segment: MemorySegment): MemorySegment =
        allocate_VH.get(segment, 0L) as MemorySegment
    
    fun allocate(segment: MemorySegment, value: MemorySegment) =
        allocate_VH.set(segment, 0L, value)
    
    val reallocate_VH: VarHandle = layout.varHandle(groupElement("reallocate"))
    
    @Suppress("UNCHECKED_CAST")
    fun reallocate(segment: MemorySegment): MemorySegment =
        reallocate_VH.get(segment, 0L) as MemorySegment
    
    fun reallocate(segment: MemorySegment, value: MemorySegment) =
        reallocate_VH.set(segment, 0L, value)
    
    val deallocate_VH: VarHandle = layout.varHandle(groupElement("deallocate"))
    
    @Suppress("UNCHECKED_CAST")
    fun deallocate(segment: MemorySegment): MemorySegment =
        deallocate_VH.get(segment, 0L) as MemorySegment
    
    fun deallocate(segment: MemorySegment, value: MemorySegment) =
        deallocate_VH.set(segment, 0L, value)
    
    val preferredSize_VH: VarHandle = layout.varHandle(groupElement("preferredSize"))
    
    @Suppress("UNCHECKED_CAST")
    fun preferredSize(segment: MemorySegment): MemorySegment =
        preferredSize_VH.get(segment, 0L) as MemorySegment
    
    fun preferredSize(segment: MemorySegment, value: MemorySegment) =
        preferredSize_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef UNSIGNED = Long ptrauth_extra_data_t;}
 */
typealias ptrauth_extra_data_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long ptrauth_generic_signature_t;}
 */
typealias ptrauth_generic_signature_t = Long

/**
 * {@snippet lang=c : typedef ((Void)*((Declared(__CFAllocator))*,(Void)*))* CFArrayRetainCallBack;}
 */
typealias CFArrayRetainCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Declared(__CFAllocator))*,(Void)*))* CFArrayReleaseCallBack;}
 */
typealias CFArrayReleaseCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef ((Declared(__CFString))*((Void)*))* CFArrayCopyDescriptionCallBack;}
 */
typealias CFArrayCopyDescriptionCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef (UNSIGNED = Char((Void)*,(Void)*))* CFArrayEqualCallBack;}
 */
typealias CFArrayEqualCallBack = MemorySegment

/**
 * {@snippet lang=c : STRUCT CFArrayCallBacks
 */
class CFArrayCallBacks {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("version"),
            ValueLayout.ADDRESS.withName("retain"),
            ValueLayout.ADDRESS.withName("release"),
            ValueLayout.ADDRESS.withName("copyDescription"),
            ValueLayout.ADDRESS.withName("equal")
        ).withName("CFArrayCallBacks")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Long =
        version_VH.get(segment, 0L) as Long
    
    fun version(segment: MemorySegment, value: Long) =
        version_VH.set(segment, 0L, value)
    
    val retain_VH: VarHandle = layout.varHandle(groupElement("retain"))
    
    @Suppress("UNCHECKED_CAST")
    fun retain(segment: MemorySegment): MemorySegment =
        retain_VH.get(segment, 0L) as MemorySegment
    
    fun retain(segment: MemorySegment, value: MemorySegment) =
        retain_VH.set(segment, 0L, value)
    
    val release_VH: VarHandle = layout.varHandle(groupElement("release"))
    
    @Suppress("UNCHECKED_CAST")
    fun release(segment: MemorySegment): MemorySegment =
        release_VH.get(segment, 0L) as MemorySegment
    
    fun release(segment: MemorySegment, value: MemorySegment) =
        release_VH.set(segment, 0L, value)
    
    val copyDescription_VH: VarHandle = layout.varHandle(groupElement("copyDescription"))
    
    @Suppress("UNCHECKED_CAST")
    fun copyDescription(segment: MemorySegment): MemorySegment =
        copyDescription_VH.get(segment, 0L) as MemorySegment
    
    fun copyDescription(segment: MemorySegment, value: MemorySegment) =
        copyDescription_VH.set(segment, 0L, value)
    
    val equal_VH: VarHandle = layout.varHandle(groupElement("equal"))
    
    @Suppress("UNCHECKED_CAST")
    fun equal(segment: MemorySegment): MemorySegment =
        equal_VH.get(segment, 0L) as MemorySegment
    
    fun equal(segment: MemorySegment, value: MemorySegment) =
        equal_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Void((Void)*,(Void)*))* CFArrayApplierFunction;}
 */
typealias CFArrayApplierFunction = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFArray))* CFArrayRef;}
 */
typealias CFArrayRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFArray))* CFMutableArrayRef;}
 */
typealias CFMutableArrayRef = MemorySegment

/**
 * {@snippet lang=c : typedef ((Void)*((Declared(__CFAllocator))*,(Void)*))* CFBagRetainCallBack;}
 */
typealias CFBagRetainCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Declared(__CFAllocator))*,(Void)*))* CFBagReleaseCallBack;}
 */
typealias CFBagReleaseCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef ((Declared(__CFString))*((Void)*))* CFBagCopyDescriptionCallBack;}
 */
typealias CFBagCopyDescriptionCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef (UNSIGNED = Char((Void)*,(Void)*))* CFBagEqualCallBack;}
 */
typealias CFBagEqualCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef (UNSIGNED = Long((Void)*))* CFBagHashCallBack;}
 */
typealias CFBagHashCallBack = MemorySegment

/**
 * {@snippet lang=c : STRUCT CFBagCallBacks
 */
class CFBagCallBacks {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("version"),
            ValueLayout.ADDRESS.withName("retain"),
            ValueLayout.ADDRESS.withName("release"),
            ValueLayout.ADDRESS.withName("copyDescription"),
            ValueLayout.ADDRESS.withName("equal"),
            ValueLayout.ADDRESS.withName("hash")
        ).withName("CFBagCallBacks")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Long =
        version_VH.get(segment, 0L) as Long
    
    fun version(segment: MemorySegment, value: Long) =
        version_VH.set(segment, 0L, value)
    
    val retain_VH: VarHandle = layout.varHandle(groupElement("retain"))
    
    @Suppress("UNCHECKED_CAST")
    fun retain(segment: MemorySegment): MemorySegment =
        retain_VH.get(segment, 0L) as MemorySegment
    
    fun retain(segment: MemorySegment, value: MemorySegment) =
        retain_VH.set(segment, 0L, value)
    
    val release_VH: VarHandle = layout.varHandle(groupElement("release"))
    
    @Suppress("UNCHECKED_CAST")
    fun release(segment: MemorySegment): MemorySegment =
        release_VH.get(segment, 0L) as MemorySegment
    
    fun release(segment: MemorySegment, value: MemorySegment) =
        release_VH.set(segment, 0L, value)
    
    val copyDescription_VH: VarHandle = layout.varHandle(groupElement("copyDescription"))
    
    @Suppress("UNCHECKED_CAST")
    fun copyDescription(segment: MemorySegment): MemorySegment =
        copyDescription_VH.get(segment, 0L) as MemorySegment
    
    fun copyDescription(segment: MemorySegment, value: MemorySegment) =
        copyDescription_VH.set(segment, 0L, value)
    
    val equal_VH: VarHandle = layout.varHandle(groupElement("equal"))
    
    @Suppress("UNCHECKED_CAST")
    fun equal(segment: MemorySegment): MemorySegment =
        equal_VH.get(segment, 0L) as MemorySegment
    
    fun equal(segment: MemorySegment, value: MemorySegment) =
        equal_VH.set(segment, 0L, value)
    
    val hash_VH: VarHandle = layout.varHandle(groupElement("hash"))
    
    @Suppress("UNCHECKED_CAST")
    fun hash(segment: MemorySegment): MemorySegment =
        hash_VH.get(segment, 0L) as MemorySegment
    
    fun hash(segment: MemorySegment, value: MemorySegment) =
        hash_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Void((Void)*,(Void)*))* CFBagApplierFunction;}
 */
typealias CFBagApplierFunction = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFBag))* CFBagRef;}
 */
typealias CFBagRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFBag))* CFMutableBagRef;}
 */
typealias CFMutableBagRef = MemorySegment

/**
 * {@snippet lang=c : STRUCT CFBinaryHeapCompareContext
 */
class CFBinaryHeapCompareContext {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("version"),
            ValueLayout.ADDRESS.withName("info"),
            ValueLayout.ADDRESS.withName("retain"),
            ValueLayout.ADDRESS.withName("release"),
            ValueLayout.ADDRESS.withName("copyDescription")
        ).withName("CFBinaryHeapCompareContext")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Long =
        version_VH.get(segment, 0L) as Long
    
    fun version(segment: MemorySegment, value: Long) =
        version_VH.set(segment, 0L, value)
    
    val info_VH: VarHandle = layout.varHandle(groupElement("info"))
    
    @Suppress("UNCHECKED_CAST")
    fun info(segment: MemorySegment): MemorySegment =
        info_VH.get(segment, 0L) as MemorySegment
    
    fun info(segment: MemorySegment, value: MemorySegment) =
        info_VH.set(segment, 0L, value)
    
    val retain_VH: VarHandle = layout.varHandle(groupElement("retain"))
    
    @Suppress("UNCHECKED_CAST")
    fun retain(segment: MemorySegment): MemorySegment =
        retain_VH.get(segment, 0L) as MemorySegment
    
    fun retain(segment: MemorySegment, value: MemorySegment) =
        retain_VH.set(segment, 0L, value)
    
    val release_VH: VarHandle = layout.varHandle(groupElement("release"))
    
    @Suppress("UNCHECKED_CAST")
    fun release(segment: MemorySegment): MemorySegment =
        release_VH.get(segment, 0L) as MemorySegment
    
    fun release(segment: MemorySegment, value: MemorySegment) =
        release_VH.set(segment, 0L, value)
    
    val copyDescription_VH: VarHandle = layout.varHandle(groupElement("copyDescription"))
    
    @Suppress("UNCHECKED_CAST")
    fun copyDescription(segment: MemorySegment): MemorySegment =
        copyDescription_VH.get(segment, 0L) as MemorySegment
    
    fun copyDescription(segment: MemorySegment, value: MemorySegment) =
        copyDescription_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CFBinaryHeapCallBacks
 */
class CFBinaryHeapCallBacks {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("version"),
            ValueLayout.ADDRESS.withName("retain"),
            ValueLayout.ADDRESS.withName("release"),
            ValueLayout.ADDRESS.withName("copyDescription"),
            ValueLayout.ADDRESS.withName("compare")
        ).withName("CFBinaryHeapCallBacks")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Long =
        version_VH.get(segment, 0L) as Long
    
    fun version(segment: MemorySegment, value: Long) =
        version_VH.set(segment, 0L, value)
    
    val retain_VH: VarHandle = layout.varHandle(groupElement("retain"))
    
    @Suppress("UNCHECKED_CAST")
    fun retain(segment: MemorySegment): MemorySegment =
        retain_VH.get(segment, 0L) as MemorySegment
    
    fun retain(segment: MemorySegment, value: MemorySegment) =
        retain_VH.set(segment, 0L, value)
    
    val release_VH: VarHandle = layout.varHandle(groupElement("release"))
    
    @Suppress("UNCHECKED_CAST")
    fun release(segment: MemorySegment): MemorySegment =
        release_VH.get(segment, 0L) as MemorySegment
    
    fun release(segment: MemorySegment, value: MemorySegment) =
        release_VH.set(segment, 0L, value)
    
    val copyDescription_VH: VarHandle = layout.varHandle(groupElement("copyDescription"))
    
    @Suppress("UNCHECKED_CAST")
    fun copyDescription(segment: MemorySegment): MemorySegment =
        copyDescription_VH.get(segment, 0L) as MemorySegment
    
    fun copyDescription(segment: MemorySegment, value: MemorySegment) =
        copyDescription_VH.set(segment, 0L, value)
    
    val compare_VH: VarHandle = layout.varHandle(groupElement("compare"))
    
    @Suppress("UNCHECKED_CAST")
    fun compare(segment: MemorySegment): MemorySegment =
        compare_VH.get(segment, 0L) as MemorySegment
    
    fun compare(segment: MemorySegment, value: MemorySegment) =
        compare_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Void((Void)*,(Void)*))* CFBinaryHeapApplierFunction;}
 */
typealias CFBinaryHeapApplierFunction = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFBinaryHeap))* CFBinaryHeapRef;}
 */
typealias CFBinaryHeapRef = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CFBit;}
 */
typealias CFBit = Int

/**
 * {@snippet lang=c : typedef (Declared(__CFBitVector))* CFBitVectorRef;}
 */
typealias CFBitVectorRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFBitVector))* CFMutableBitVectorRef;}
 */
typealias CFMutableBitVectorRef = MemorySegment

/**
 * {@snippet lang=c : typedef Long CFByteOrder;}
 */
typealias CFByteOrder = Long

/**
 * {@snippet lang=c : STRUCT CFSwappedFloat32
 */
class CFSwappedFloat32 {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("v")
        ).withName("CFSwappedFloat32")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val v_VH: VarHandle = layout.varHandle(groupElement("v"))
    
    @Suppress("UNCHECKED_CAST")
    fun v(segment: MemorySegment): Int =
        v_VH.get(segment, 0L) as Int
    
    fun v(segment: MemorySegment, value: Int) =
        v_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CFSwappedFloat64
 */
class CFSwappedFloat64 {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("v")
        ).withName("CFSwappedFloat64")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val v_VH: VarHandle = layout.varHandle(groupElement("v"))
    
    @Suppress("UNCHECKED_CAST")
    fun v(segment: MemorySegment): Long =
        v_VH.get(segment, 0L) as Long
    
    fun v(segment: MemorySegment, value: Long) =
        v_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef ((Void)*((Declared(__CFAllocator))*,(Void)*))* CFDictionaryRetainCallBack;}
 */
typealias CFDictionaryRetainCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Declared(__CFAllocator))*,(Void)*))* CFDictionaryReleaseCallBack;}
 */
typealias CFDictionaryReleaseCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef ((Declared(__CFString))*((Void)*))* CFDictionaryCopyDescriptionCallBack;}
 */
typealias CFDictionaryCopyDescriptionCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef (UNSIGNED = Char((Void)*,(Void)*))* CFDictionaryEqualCallBack;}
 */
typealias CFDictionaryEqualCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef (UNSIGNED = Long((Void)*))* CFDictionaryHashCallBack;}
 */
typealias CFDictionaryHashCallBack = MemorySegment

/**
 * {@snippet lang=c : STRUCT CFDictionaryKeyCallBacks
 */
class CFDictionaryKeyCallBacks {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("version"),
            ValueLayout.ADDRESS.withName("retain"),
            ValueLayout.ADDRESS.withName("release"),
            ValueLayout.ADDRESS.withName("copyDescription"),
            ValueLayout.ADDRESS.withName("equal"),
            ValueLayout.ADDRESS.withName("hash")
        ).withName("CFDictionaryKeyCallBacks")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Long =
        version_VH.get(segment, 0L) as Long
    
    fun version(segment: MemorySegment, value: Long) =
        version_VH.set(segment, 0L, value)
    
    val retain_VH: VarHandle = layout.varHandle(groupElement("retain"))
    
    @Suppress("UNCHECKED_CAST")
    fun retain(segment: MemorySegment): MemorySegment =
        retain_VH.get(segment, 0L) as MemorySegment
    
    fun retain(segment: MemorySegment, value: MemorySegment) =
        retain_VH.set(segment, 0L, value)
    
    val release_VH: VarHandle = layout.varHandle(groupElement("release"))
    
    @Suppress("UNCHECKED_CAST")
    fun release(segment: MemorySegment): MemorySegment =
        release_VH.get(segment, 0L) as MemorySegment
    
    fun release(segment: MemorySegment, value: MemorySegment) =
        release_VH.set(segment, 0L, value)
    
    val copyDescription_VH: VarHandle = layout.varHandle(groupElement("copyDescription"))
    
    @Suppress("UNCHECKED_CAST")
    fun copyDescription(segment: MemorySegment): MemorySegment =
        copyDescription_VH.get(segment, 0L) as MemorySegment
    
    fun copyDescription(segment: MemorySegment, value: MemorySegment) =
        copyDescription_VH.set(segment, 0L, value)
    
    val equal_VH: VarHandle = layout.varHandle(groupElement("equal"))
    
    @Suppress("UNCHECKED_CAST")
    fun equal(segment: MemorySegment): MemorySegment =
        equal_VH.get(segment, 0L) as MemorySegment
    
    fun equal(segment: MemorySegment, value: MemorySegment) =
        equal_VH.set(segment, 0L, value)
    
    val hash_VH: VarHandle = layout.varHandle(groupElement("hash"))
    
    @Suppress("UNCHECKED_CAST")
    fun hash(segment: MemorySegment): MemorySegment =
        hash_VH.get(segment, 0L) as MemorySegment
    
    fun hash(segment: MemorySegment, value: MemorySegment) =
        hash_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CFDictionaryValueCallBacks
 */
class CFDictionaryValueCallBacks {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("version"),
            ValueLayout.ADDRESS.withName("retain"),
            ValueLayout.ADDRESS.withName("release"),
            ValueLayout.ADDRESS.withName("copyDescription"),
            ValueLayout.ADDRESS.withName("equal")
        ).withName("CFDictionaryValueCallBacks")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Long =
        version_VH.get(segment, 0L) as Long
    
    fun version(segment: MemorySegment, value: Long) =
        version_VH.set(segment, 0L, value)
    
    val retain_VH: VarHandle = layout.varHandle(groupElement("retain"))
    
    @Suppress("UNCHECKED_CAST")
    fun retain(segment: MemorySegment): MemorySegment =
        retain_VH.get(segment, 0L) as MemorySegment
    
    fun retain(segment: MemorySegment, value: MemorySegment) =
        retain_VH.set(segment, 0L, value)
    
    val release_VH: VarHandle = layout.varHandle(groupElement("release"))
    
    @Suppress("UNCHECKED_CAST")
    fun release(segment: MemorySegment): MemorySegment =
        release_VH.get(segment, 0L) as MemorySegment
    
    fun release(segment: MemorySegment, value: MemorySegment) =
        release_VH.set(segment, 0L, value)
    
    val copyDescription_VH: VarHandle = layout.varHandle(groupElement("copyDescription"))
    
    @Suppress("UNCHECKED_CAST")
    fun copyDescription(segment: MemorySegment): MemorySegment =
        copyDescription_VH.get(segment, 0L) as MemorySegment
    
    fun copyDescription(segment: MemorySegment, value: MemorySegment) =
        copyDescription_VH.set(segment, 0L, value)
    
    val equal_VH: VarHandle = layout.varHandle(groupElement("equal"))
    
    @Suppress("UNCHECKED_CAST")
    fun equal(segment: MemorySegment): MemorySegment =
        equal_VH.get(segment, 0L) as MemorySegment
    
    fun equal(segment: MemorySegment, value: MemorySegment) =
        equal_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Void((Void)*,(Void)*,(Void)*))* CFDictionaryApplierFunction;}
 */
typealias CFDictionaryApplierFunction = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFDictionary))* CFDictionaryRef;}
 */
typealias CFDictionaryRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFDictionary))* CFMutableDictionaryRef;}
 */
typealias CFMutableDictionaryRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFString))* CFNotificationName;}
 */
typealias CFNotificationName = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFNotificationCenter))* CFNotificationCenterRef;}
 */
typealias CFNotificationCenterRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Declared(__CFNotificationCenter))*,(Void)*,(Declared(__CFString))*,(Void)*,(Declared(__CFDictionary))*))* CFNotificationCallback;}
 */
typealias CFNotificationCallback = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFString))* CFLocaleIdentifier;}
 */
typealias CFLocaleIdentifier = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFString))* CFLocaleKey;}
 */
typealias CFLocaleKey = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFLocale))* CFLocaleRef;}
 */
typealias CFLocaleRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFString))* CFCalendarIdentifier;}
 */
typealias CFCalendarIdentifier = MemorySegment

/**
 * {@snippet lang=c : typedef Double CFTimeInterval;}
 */
typealias CFTimeInterval = Double

/**
 * {@snippet lang=c : typedef Double CFAbsoluteTime;}
 */
typealias CFAbsoluteTime = Double

/**
 * {@snippet lang=c : typedef (Declared(__CFDate))* CFDateRef;}
 */
typealias CFDateRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFTimeZone))* CFTimeZoneRef;}
 */
typealias CFTimeZoneRef = MemorySegment

/**
 * {@snippet lang=c : STRUCT CFGregorianDate
 */
class CFGregorianDate {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("year"),
            ValueLayout.JAVA_BYTE.withName("month"),
            ValueLayout.JAVA_BYTE.withName("day"),
            ValueLayout.JAVA_BYTE.withName("hour"),
            ValueLayout.JAVA_BYTE.withName("minute"),
            ValueLayout.JAVA_DOUBLE.withName("second")
        ).withName("CFGregorianDate")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val year_VH: VarHandle = layout.varHandle(groupElement("year"))
    
    @Suppress("UNCHECKED_CAST")
    fun year(segment: MemorySegment): Int =
        year_VH.get(segment, 0L) as Int
    
    fun year(segment: MemorySegment, value: Int) =
        year_VH.set(segment, 0L, value)
    
    val month_VH: VarHandle = layout.varHandle(groupElement("month"))
    
    @Suppress("UNCHECKED_CAST")
    fun month(segment: MemorySegment): Byte =
        month_VH.get(segment, 0L) as Byte
    
    fun month(segment: MemorySegment, value: Byte) =
        month_VH.set(segment, 0L, value)
    
    val day_VH: VarHandle = layout.varHandle(groupElement("day"))
    
    @Suppress("UNCHECKED_CAST")
    fun day(segment: MemorySegment): Byte =
        day_VH.get(segment, 0L) as Byte
    
    fun day(segment: MemorySegment, value: Byte) =
        day_VH.set(segment, 0L, value)
    
    val hour_VH: VarHandle = layout.varHandle(groupElement("hour"))
    
    @Suppress("UNCHECKED_CAST")
    fun hour(segment: MemorySegment): Byte =
        hour_VH.get(segment, 0L) as Byte
    
    fun hour(segment: MemorySegment, value: Byte) =
        hour_VH.set(segment, 0L, value)
    
    val minute_VH: VarHandle = layout.varHandle(groupElement("minute"))
    
    @Suppress("UNCHECKED_CAST")
    fun minute(segment: MemorySegment): Byte =
        minute_VH.get(segment, 0L) as Byte
    
    fun minute(segment: MemorySegment, value: Byte) =
        minute_VH.set(segment, 0L, value)
    
    val second_VH: VarHandle = layout.varHandle(groupElement("second"))
    
    @Suppress("UNCHECKED_CAST")
    fun second(segment: MemorySegment): Double =
        second_VH.get(segment, 0L) as Double
    
    fun second(segment: MemorySegment, value: Double) =
        second_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CFGregorianUnits
 */
class CFGregorianUnits {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("years"),
            ValueLayout.JAVA_INT.withName("months"),
            ValueLayout.JAVA_INT.withName("days"),
            ValueLayout.JAVA_INT.withName("hours"),
            ValueLayout.JAVA_INT.withName("minutes"),
            ValueLayout.JAVA_DOUBLE.withName("seconds")
        ).withName("CFGregorianUnits")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val years_VH: VarHandle = layout.varHandle(groupElement("years"))
    
    @Suppress("UNCHECKED_CAST")
    fun years(segment: MemorySegment): Int =
        years_VH.get(segment, 0L) as Int
    
    fun years(segment: MemorySegment, value: Int) =
        years_VH.set(segment, 0L, value)
    
    val months_VH: VarHandle = layout.varHandle(groupElement("months"))
    
    @Suppress("UNCHECKED_CAST")
    fun months(segment: MemorySegment): Int =
        months_VH.get(segment, 0L) as Int
    
    fun months(segment: MemorySegment, value: Int) =
        months_VH.set(segment, 0L, value)
    
    val days_VH: VarHandle = layout.varHandle(groupElement("days"))
    
    @Suppress("UNCHECKED_CAST")
    fun days(segment: MemorySegment): Int =
        days_VH.get(segment, 0L) as Int
    
    fun days(segment: MemorySegment, value: Int) =
        days_VH.set(segment, 0L, value)
    
    val hours_VH: VarHandle = layout.varHandle(groupElement("hours"))
    
    @Suppress("UNCHECKED_CAST")
    fun hours(segment: MemorySegment): Int =
        hours_VH.get(segment, 0L) as Int
    
    fun hours(segment: MemorySegment, value: Int) =
        hours_VH.set(segment, 0L, value)
    
    val minutes_VH: VarHandle = layout.varHandle(groupElement("minutes"))
    
    @Suppress("UNCHECKED_CAST")
    fun minutes(segment: MemorySegment): Int =
        minutes_VH.get(segment, 0L) as Int
    
    fun minutes(segment: MemorySegment, value: Int) =
        minutes_VH.set(segment, 0L, value)
    
    val seconds_VH: VarHandle = layout.varHandle(groupElement("seconds"))
    
    @Suppress("UNCHECKED_CAST")
    fun seconds(segment: MemorySegment): Double =
        seconds_VH.get(segment, 0L) as Double
    
    fun seconds(segment: MemorySegment, value: Double) =
        seconds_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Declared(__CFData))* CFDataRef;}
 */
typealias CFDataRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFData))* CFMutableDataRef;}
 */
typealias CFMutableDataRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFCharacterSet))* CFCharacterSetRef;}
 */
typealias CFCharacterSetRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFCharacterSet))* CFMutableCharacterSetRef;}
 */
typealias CFMutableCharacterSetRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFString))* CFErrorDomain;}
 */
typealias CFErrorDomain = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFError))* CFErrorRef;}
 */
typealias CFErrorRef = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CFStringEncoding;}
 */
typealias CFStringEncoding = Int

/**
 * {@snippet lang=c : STRUCT CFStringInlineBuffer
 */
class CFStringInlineBuffer {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            MemoryLayout.sequenceLayout(64, ValueLayout.JAVA_SHORT).withName("buffer"),
            ValueLayout.ADDRESS.withName("theString"),
            ValueLayout.ADDRESS.withName("directUniCharBuffer"),
            ValueLayout.ADDRESS.withName("directCStringBuffer"),
            CFRange.layout.withName("rangeToBuffer"),
            ValueLayout.JAVA_LONG.withName("bufferedRangeStart"),
            ValueLayout.JAVA_LONG.withName("bufferedRangeEnd")
        ).withName("CFStringInlineBuffer")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    fun buffer(segment: MemorySegment): MemorySegment =
        segment.asSlice(layout.byteOffset(groupElement("buffer")), layout.select(groupElement("buffer")).byteSize())
    
    val theString_VH: VarHandle = layout.varHandle(groupElement("theString"))
    
    @Suppress("UNCHECKED_CAST")
    fun theString(segment: MemorySegment): MemorySegment =
        theString_VH.get(segment, 0L) as MemorySegment
    
    fun theString(segment: MemorySegment, value: MemorySegment) =
        theString_VH.set(segment, 0L, value)
    
    val directUniCharBuffer_VH: VarHandle = layout.varHandle(groupElement("directUniCharBuffer"))
    
    @Suppress("UNCHECKED_CAST")
    fun directUniCharBuffer(segment: MemorySegment): MemorySegment =
        directUniCharBuffer_VH.get(segment, 0L) as MemorySegment
    
    fun directUniCharBuffer(segment: MemorySegment, value: MemorySegment) =
        directUniCharBuffer_VH.set(segment, 0L, value)
    
    val directCStringBuffer_VH: VarHandle = layout.varHandle(groupElement("directCStringBuffer"))
    
    @Suppress("UNCHECKED_CAST")
    fun directCStringBuffer(segment: MemorySegment): MemorySegment =
        directCStringBuffer_VH.get(segment, 0L) as MemorySegment
    
    fun directCStringBuffer(segment: MemorySegment, value: MemorySegment) =
        directCStringBuffer_VH.set(segment, 0L, value)
    
    val rangeToBuffer_VH: VarHandle = layout.varHandle(groupElement("rangeToBuffer"))
    
    @Suppress("UNCHECKED_CAST")
    fun rangeToBuffer(segment: MemorySegment): MemorySegment =
        rangeToBuffer_VH.get(segment, 0L) as MemorySegment
    
    fun rangeToBuffer(segment: MemorySegment, value: MemorySegment) =
        rangeToBuffer_VH.set(segment, 0L, value)
    
    val bufferedRangeStart_VH: VarHandle = layout.varHandle(groupElement("bufferedRangeStart"))
    
    @Suppress("UNCHECKED_CAST")
    fun bufferedRangeStart(segment: MemorySegment): Long =
        bufferedRangeStart_VH.get(segment, 0L) as Long
    
    fun bufferedRangeStart(segment: MemorySegment, value: Long) =
        bufferedRangeStart_VH.set(segment, 0L, value)
    
    val bufferedRangeEnd_VH: VarHandle = layout.varHandle(groupElement("bufferedRangeEnd"))
    
    @Suppress("UNCHECKED_CAST")
    fun bufferedRangeEnd(segment: MemorySegment): Long =
        bufferedRangeEnd_VH.get(segment, 0L) as Long
    
    fun bufferedRangeEnd(segment: MemorySegment, value: Long) =
        bufferedRangeEnd_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Declared(__CFCalendar))* CFCalendarRef;}
 */
typealias CFCalendarRef = MemorySegment

/**
 * {@snippet lang=c : typedef Double CGFloat;}
 */
typealias CGFloat = Double

/**
 * {@snippet lang=c : STRUCT CGPoint
 */
class CGPoint {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_DOUBLE.withName("x"),
            ValueLayout.JAVA_DOUBLE.withName("y")
        ).withName("CGPoint")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val x_VH: VarHandle = layout.varHandle(groupElement("x"))
    
    @Suppress("UNCHECKED_CAST")
    fun x(segment: MemorySegment): Double =
        x_VH.get(segment, 0L) as Double
    
    fun x(segment: MemorySegment, value: Double) =
        x_VH.set(segment, 0L, value)
    
    val y_VH: VarHandle = layout.varHandle(groupElement("y"))
    
    @Suppress("UNCHECKED_CAST")
    fun y(segment: MemorySegment): Double =
        y_VH.get(segment, 0L) as Double
    
    fun y(segment: MemorySegment, value: Double) =
        y_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CGSize
 */
class CGSize {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_DOUBLE.withName("width"),
            ValueLayout.JAVA_DOUBLE.withName("height")
        ).withName("CGSize")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val width_VH: VarHandle = layout.varHandle(groupElement("width"))
    
    @Suppress("UNCHECKED_CAST")
    fun width(segment: MemorySegment): Double =
        width_VH.get(segment, 0L) as Double
    
    fun width(segment: MemorySegment, value: Double) =
        width_VH.set(segment, 0L, value)
    
    val height_VH: VarHandle = layout.varHandle(groupElement("height"))
    
    @Suppress("UNCHECKED_CAST")
    fun height(segment: MemorySegment): Double =
        height_VH.get(segment, 0L) as Double
    
    fun height(segment: MemorySegment, value: Double) =
        height_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CGVector
 */
class CGVector {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_DOUBLE.withName("dx"),
            ValueLayout.JAVA_DOUBLE.withName("dy")
        ).withName("CGVector")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val dx_VH: VarHandle = layout.varHandle(groupElement("dx"))
    
    @Suppress("UNCHECKED_CAST")
    fun dx(segment: MemorySegment): Double =
        dx_VH.get(segment, 0L) as Double
    
    fun dx(segment: MemorySegment, value: Double) =
        dx_VH.set(segment, 0L, value)
    
    val dy_VH: VarHandle = layout.varHandle(groupElement("dy"))
    
    @Suppress("UNCHECKED_CAST")
    fun dy(segment: MemorySegment): Double =
        dy_VH.get(segment, 0L) as Double
    
    fun dy(segment: MemorySegment, value: Double) =
        dy_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CGRect
 */
class CGRect {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            CGPoint.layout.withName("origin"),
            CGSize.layout.withName("size")
        ).withName("CGRect")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val origin_VH: VarHandle = layout.varHandle(groupElement("origin"))
    
    @Suppress("UNCHECKED_CAST")
    fun origin(segment: MemorySegment): MemorySegment =
        origin_VH.get(segment, 0L) as MemorySegment
    
    fun origin(segment: MemorySegment, value: MemorySegment) =
        origin_VH.set(segment, 0L, value)
    
    val size_VH: VarHandle = layout.varHandle(groupElement("size"))
    
    @Suppress("UNCHECKED_CAST")
    fun size(segment: MemorySegment): MemorySegment =
        size_VH.get(segment, 0L) as MemorySegment
    
    fun size(segment: MemorySegment, value: MemorySegment) =
        size_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CGAffineTransform
 */
class CGAffineTransform {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_DOUBLE.withName("a"),
            ValueLayout.JAVA_DOUBLE.withName("b"),
            ValueLayout.JAVA_DOUBLE.withName("c"),
            ValueLayout.JAVA_DOUBLE.withName("d"),
            ValueLayout.JAVA_DOUBLE.withName("tx"),
            ValueLayout.JAVA_DOUBLE.withName("ty")
        ).withName("CGAffineTransform")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val a_VH: VarHandle = layout.varHandle(groupElement("a"))
    
    @Suppress("UNCHECKED_CAST")
    fun a(segment: MemorySegment): Double =
        a_VH.get(segment, 0L) as Double
    
    fun a(segment: MemorySegment, value: Double) =
        a_VH.set(segment, 0L, value)
    
    val b_VH: VarHandle = layout.varHandle(groupElement("b"))
    
    @Suppress("UNCHECKED_CAST")
    fun b(segment: MemorySegment): Double =
        b_VH.get(segment, 0L) as Double
    
    fun b(segment: MemorySegment, value: Double) =
        b_VH.set(segment, 0L, value)
    
    val c_VH: VarHandle = layout.varHandle(groupElement("c"))
    
    @Suppress("UNCHECKED_CAST")
    fun c(segment: MemorySegment): Double =
        c_VH.get(segment, 0L) as Double
    
    fun c(segment: MemorySegment, value: Double) =
        c_VH.set(segment, 0L, value)
    
    val d_VH: VarHandle = layout.varHandle(groupElement("d"))
    
    @Suppress("UNCHECKED_CAST")
    fun d(segment: MemorySegment): Double =
        d_VH.get(segment, 0L) as Double
    
    fun d(segment: MemorySegment, value: Double) =
        d_VH.set(segment, 0L, value)
    
    val tx_VH: VarHandle = layout.varHandle(groupElement("tx"))
    
    @Suppress("UNCHECKED_CAST")
    fun tx(segment: MemorySegment): Double =
        tx_VH.get(segment, 0L) as Double
    
    fun tx(segment: MemorySegment, value: Double) =
        tx_VH.set(segment, 0L, value)
    
    val ty_VH: VarHandle = layout.varHandle(groupElement("ty"))
    
    @Suppress("UNCHECKED_CAST")
    fun ty(segment: MemorySegment): Double =
        ty_VH.get(segment, 0L) as Double
    
    fun ty(segment: MemorySegment, value: Double) =
        ty_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CGAffineTransformComponents
 */
class CGAffineTransformComponents {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            CGSize.layout.withName("scale"),
            ValueLayout.JAVA_DOUBLE.withName("horizontalShear"),
            ValueLayout.JAVA_DOUBLE.withName("rotation"),
            CGVector.layout.withName("translation")
        ).withName("CGAffineTransformComponents")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val scale_VH: VarHandle = layout.varHandle(groupElement("scale"))
    
    @Suppress("UNCHECKED_CAST")
    fun scale(segment: MemorySegment): MemorySegment =
        scale_VH.get(segment, 0L) as MemorySegment
    
    fun scale(segment: MemorySegment, value: MemorySegment) =
        scale_VH.set(segment, 0L, value)
    
    val horizontalShear_VH: VarHandle = layout.varHandle(groupElement("horizontalShear"))
    
    @Suppress("UNCHECKED_CAST")
    fun horizontalShear(segment: MemorySegment): Double =
        horizontalShear_VH.get(segment, 0L) as Double
    
    fun horizontalShear(segment: MemorySegment, value: Double) =
        horizontalShear_VH.set(segment, 0L, value)
    
    val rotation_VH: VarHandle = layout.varHandle(groupElement("rotation"))
    
    @Suppress("UNCHECKED_CAST")
    fun rotation(segment: MemorySegment): Double =
        rotation_VH.get(segment, 0L) as Double
    
    fun rotation(segment: MemorySegment, value: Double) =
        rotation_VH.set(segment, 0L, value)
    
    val translation_VH: VarHandle = layout.varHandle(groupElement("translation"))
    
    @Suppress("UNCHECKED_CAST")
    fun translation(segment: MemorySegment): MemorySegment =
        translation_VH.get(segment, 0L) as MemorySegment
    
    fun translation(segment: MemorySegment, value: MemorySegment) =
        translation_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Declared(__CFString))* CFDateFormatterKey;}
 */
typealias CFDateFormatterKey = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFDateFormatter))* CFDateFormatterRef;}
 */
typealias CFDateFormatterRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFBoolean))* CFBooleanRef;}
 */
typealias CFBooleanRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFNumber))* CFNumberRef;}
 */
typealias CFNumberRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFString))* CFNumberFormatterKey;}
 */
typealias CFNumberFormatterKey = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFNumberFormatter))* CFNumberFormatterRef;}
 */
typealias CFNumberFormatterRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFURL))* CFURLRef;}
 */
typealias CFURLRef = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Long CFURLBookmarkFileCreationOptions;}
 */
typealias CFURLBookmarkFileCreationOptions = Long

/**
 * {@snippet lang=c : typedef Int boolean_t;}
 */
typealias boolean_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int natural_t;}
 */
typealias natural_t = Int

/**
 * {@snippet lang=c : typedef Int integer_t;}
 */
typealias integer_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Long vm_offset_t;}
 */
typealias vm_offset_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long vm_size_t;}
 */
typealias vm_size_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong mach_vm_address_t;}
 */
typealias mach_vm_address_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong mach_vm_offset_t;}
 */
typealias mach_vm_offset_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong mach_vm_size_t;}
 */
typealias mach_vm_size_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong vm_map_offset_t;}
 */
typealias vm_map_offset_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong vm_map_address_t;}
 */
typealias vm_map_address_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong vm_map_size_t;}
 */
typealias vm_map_size_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int vm32_offset_t;}
 */
typealias vm32_offset_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int vm32_address_t;}
 */
typealias vm32_address_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int vm32_size_t;}
 */
typealias vm32_size_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Long mach_port_context_t;}
 */
typealias mach_port_context_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_port_name_t;}
 */
typealias mach_port_name_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_port_t;}
 */
typealias mach_port_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_port_right_t;}
 */
typealias mach_port_right_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_port_type_t;}
 */
typealias mach_port_type_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_port_urefs_t;}
 */
typealias mach_port_urefs_t = Int

/**
 * {@snippet lang=c : typedef Int mach_port_delta_t;}
 */
typealias mach_port_delta_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_port_seqno_t;}
 */
typealias mach_port_seqno_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_port_mscount_t;}
 */
typealias mach_port_mscount_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_port_msgcount_t;}
 */
typealias mach_port_msgcount_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_port_rights_t;}
 */
typealias mach_port_rights_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_port_srights_t;}
 */
typealias mach_port_srights_t = Int

/**
 * {@snippet lang=c : typedef Int mach_port_flavor_t;}
 */
typealias mach_port_flavor_t = Int

/**
 * {@snippet lang=c : typedef (Declared(__CFString))* CFRunLoopMode;}
 */
typealias CFRunLoopMode = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFRunLoop))* CFRunLoopRef;}
 */
typealias CFRunLoopRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFRunLoopSource))* CFRunLoopSourceRef;}
 */
typealias CFRunLoopSourceRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFRunLoopObserver))* CFRunLoopObserverRef;}
 */
typealias CFRunLoopObserverRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFRunLoopTimer))* CFRunLoopTimerRef;}
 */
typealias CFRunLoopTimerRef = MemorySegment

/**
 * {@snippet lang=c : STRUCT CFRunLoopSourceContext
 */
class CFRunLoopSourceContext {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("version"),
            ValueLayout.ADDRESS.withName("info"),
            ValueLayout.ADDRESS.withName("retain"),
            ValueLayout.ADDRESS.withName("release"),
            ValueLayout.ADDRESS.withName("copyDescription"),
            ValueLayout.ADDRESS.withName("equal"),
            ValueLayout.ADDRESS.withName("hash"),
            ValueLayout.ADDRESS.withName("schedule"),
            ValueLayout.ADDRESS.withName("cancel"),
            ValueLayout.ADDRESS.withName("perform")
        ).withName("CFRunLoopSourceContext")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Long =
        version_VH.get(segment, 0L) as Long
    
    fun version(segment: MemorySegment, value: Long) =
        version_VH.set(segment, 0L, value)
    
    val info_VH: VarHandle = layout.varHandle(groupElement("info"))
    
    @Suppress("UNCHECKED_CAST")
    fun info(segment: MemorySegment): MemorySegment =
        info_VH.get(segment, 0L) as MemorySegment
    
    fun info(segment: MemorySegment, value: MemorySegment) =
        info_VH.set(segment, 0L, value)
    
    val retain_VH: VarHandle = layout.varHandle(groupElement("retain"))
    
    @Suppress("UNCHECKED_CAST")
    fun retain(segment: MemorySegment): MemorySegment =
        retain_VH.get(segment, 0L) as MemorySegment
    
    fun retain(segment: MemorySegment, value: MemorySegment) =
        retain_VH.set(segment, 0L, value)
    
    val release_VH: VarHandle = layout.varHandle(groupElement("release"))
    
    @Suppress("UNCHECKED_CAST")
    fun release(segment: MemorySegment): MemorySegment =
        release_VH.get(segment, 0L) as MemorySegment
    
    fun release(segment: MemorySegment, value: MemorySegment) =
        release_VH.set(segment, 0L, value)
    
    val copyDescription_VH: VarHandle = layout.varHandle(groupElement("copyDescription"))
    
    @Suppress("UNCHECKED_CAST")
    fun copyDescription(segment: MemorySegment): MemorySegment =
        copyDescription_VH.get(segment, 0L) as MemorySegment
    
    fun copyDescription(segment: MemorySegment, value: MemorySegment) =
        copyDescription_VH.set(segment, 0L, value)
    
    val equal_VH: VarHandle = layout.varHandle(groupElement("equal"))
    
    @Suppress("UNCHECKED_CAST")
    fun equal(segment: MemorySegment): MemorySegment =
        equal_VH.get(segment, 0L) as MemorySegment
    
    fun equal(segment: MemorySegment, value: MemorySegment) =
        equal_VH.set(segment, 0L, value)
    
    val hash_VH: VarHandle = layout.varHandle(groupElement("hash"))
    
    @Suppress("UNCHECKED_CAST")
    fun hash(segment: MemorySegment): MemorySegment =
        hash_VH.get(segment, 0L) as MemorySegment
    
    fun hash(segment: MemorySegment, value: MemorySegment) =
        hash_VH.set(segment, 0L, value)
    
    val schedule_VH: VarHandle = layout.varHandle(groupElement("schedule"))
    
    @Suppress("UNCHECKED_CAST")
    fun schedule(segment: MemorySegment): MemorySegment =
        schedule_VH.get(segment, 0L) as MemorySegment
    
    fun schedule(segment: MemorySegment, value: MemorySegment) =
        schedule_VH.set(segment, 0L, value)
    
    val cancel_VH: VarHandle = layout.varHandle(groupElement("cancel"))
    
    @Suppress("UNCHECKED_CAST")
    fun cancel(segment: MemorySegment): MemorySegment =
        cancel_VH.get(segment, 0L) as MemorySegment
    
    fun cancel(segment: MemorySegment, value: MemorySegment) =
        cancel_VH.set(segment, 0L, value)
    
    val perform_VH: VarHandle = layout.varHandle(groupElement("perform"))
    
    @Suppress("UNCHECKED_CAST")
    fun perform(segment: MemorySegment): MemorySegment =
        perform_VH.get(segment, 0L) as MemorySegment
    
    fun perform(segment: MemorySegment, value: MemorySegment) =
        perform_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CFRunLoopSourceContext1
 */
class CFRunLoopSourceContext1 {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("version"),
            ValueLayout.ADDRESS.withName("info"),
            ValueLayout.ADDRESS.withName("retain"),
            ValueLayout.ADDRESS.withName("release"),
            ValueLayout.ADDRESS.withName("copyDescription"),
            ValueLayout.ADDRESS.withName("equal"),
            ValueLayout.ADDRESS.withName("hash"),
            ValueLayout.ADDRESS.withName("getPort"),
            ValueLayout.ADDRESS.withName("perform")
        ).withName("CFRunLoopSourceContext1")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Long =
        version_VH.get(segment, 0L) as Long
    
    fun version(segment: MemorySegment, value: Long) =
        version_VH.set(segment, 0L, value)
    
    val info_VH: VarHandle = layout.varHandle(groupElement("info"))
    
    @Suppress("UNCHECKED_CAST")
    fun info(segment: MemorySegment): MemorySegment =
        info_VH.get(segment, 0L) as MemorySegment
    
    fun info(segment: MemorySegment, value: MemorySegment) =
        info_VH.set(segment, 0L, value)
    
    val retain_VH: VarHandle = layout.varHandle(groupElement("retain"))
    
    @Suppress("UNCHECKED_CAST")
    fun retain(segment: MemorySegment): MemorySegment =
        retain_VH.get(segment, 0L) as MemorySegment
    
    fun retain(segment: MemorySegment, value: MemorySegment) =
        retain_VH.set(segment, 0L, value)
    
    val release_VH: VarHandle = layout.varHandle(groupElement("release"))
    
    @Suppress("UNCHECKED_CAST")
    fun release(segment: MemorySegment): MemorySegment =
        release_VH.get(segment, 0L) as MemorySegment
    
    fun release(segment: MemorySegment, value: MemorySegment) =
        release_VH.set(segment, 0L, value)
    
    val copyDescription_VH: VarHandle = layout.varHandle(groupElement("copyDescription"))
    
    @Suppress("UNCHECKED_CAST")
    fun copyDescription(segment: MemorySegment): MemorySegment =
        copyDescription_VH.get(segment, 0L) as MemorySegment
    
    fun copyDescription(segment: MemorySegment, value: MemorySegment) =
        copyDescription_VH.set(segment, 0L, value)
    
    val equal_VH: VarHandle = layout.varHandle(groupElement("equal"))
    
    @Suppress("UNCHECKED_CAST")
    fun equal(segment: MemorySegment): MemorySegment =
        equal_VH.get(segment, 0L) as MemorySegment
    
    fun equal(segment: MemorySegment, value: MemorySegment) =
        equal_VH.set(segment, 0L, value)
    
    val hash_VH: VarHandle = layout.varHandle(groupElement("hash"))
    
    @Suppress("UNCHECKED_CAST")
    fun hash(segment: MemorySegment): MemorySegment =
        hash_VH.get(segment, 0L) as MemorySegment
    
    fun hash(segment: MemorySegment, value: MemorySegment) =
        hash_VH.set(segment, 0L, value)
    
    val getPort_VH: VarHandle = layout.varHandle(groupElement("getPort"))
    
    @Suppress("UNCHECKED_CAST")
    fun getPort(segment: MemorySegment): MemorySegment =
        getPort_VH.get(segment, 0L) as MemorySegment
    
    fun getPort(segment: MemorySegment, value: MemorySegment) =
        getPort_VH.set(segment, 0L, value)
    
    val perform_VH: VarHandle = layout.varHandle(groupElement("perform"))
    
    @Suppress("UNCHECKED_CAST")
    fun perform(segment: MemorySegment): MemorySegment =
        perform_VH.get(segment, 0L) as MemorySegment
    
    fun perform(segment: MemorySegment, value: MemorySegment) =
        perform_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CFRunLoopObserverContext
 */
class CFRunLoopObserverContext {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("version"),
            ValueLayout.ADDRESS.withName("info"),
            ValueLayout.ADDRESS.withName("retain"),
            ValueLayout.ADDRESS.withName("release"),
            ValueLayout.ADDRESS.withName("copyDescription")
        ).withName("CFRunLoopObserverContext")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Long =
        version_VH.get(segment, 0L) as Long
    
    fun version(segment: MemorySegment, value: Long) =
        version_VH.set(segment, 0L, value)
    
    val info_VH: VarHandle = layout.varHandle(groupElement("info"))
    
    @Suppress("UNCHECKED_CAST")
    fun info(segment: MemorySegment): MemorySegment =
        info_VH.get(segment, 0L) as MemorySegment
    
    fun info(segment: MemorySegment, value: MemorySegment) =
        info_VH.set(segment, 0L, value)
    
    val retain_VH: VarHandle = layout.varHandle(groupElement("retain"))
    
    @Suppress("UNCHECKED_CAST")
    fun retain(segment: MemorySegment): MemorySegment =
        retain_VH.get(segment, 0L) as MemorySegment
    
    fun retain(segment: MemorySegment, value: MemorySegment) =
        retain_VH.set(segment, 0L, value)
    
    val release_VH: VarHandle = layout.varHandle(groupElement("release"))
    
    @Suppress("UNCHECKED_CAST")
    fun release(segment: MemorySegment): MemorySegment =
        release_VH.get(segment, 0L) as MemorySegment
    
    fun release(segment: MemorySegment, value: MemorySegment) =
        release_VH.set(segment, 0L, value)
    
    val copyDescription_VH: VarHandle = layout.varHandle(groupElement("copyDescription"))
    
    @Suppress("UNCHECKED_CAST")
    fun copyDescription(segment: MemorySegment): MemorySegment =
        copyDescription_VH.get(segment, 0L) as MemorySegment
    
    fun copyDescription(segment: MemorySegment, value: MemorySegment) =
        copyDescription_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CFRunLoopTimerContext
 */
class CFRunLoopTimerContext {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("version"),
            ValueLayout.ADDRESS.withName("info"),
            ValueLayout.ADDRESS.withName("retain"),
            ValueLayout.ADDRESS.withName("release"),
            ValueLayout.ADDRESS.withName("copyDescription")
        ).withName("CFRunLoopTimerContext")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Long =
        version_VH.get(segment, 0L) as Long
    
    fun version(segment: MemorySegment, value: Long) =
        version_VH.set(segment, 0L, value)
    
    val info_VH: VarHandle = layout.varHandle(groupElement("info"))
    
    @Suppress("UNCHECKED_CAST")
    fun info(segment: MemorySegment): MemorySegment =
        info_VH.get(segment, 0L) as MemorySegment
    
    fun info(segment: MemorySegment, value: MemorySegment) =
        info_VH.set(segment, 0L, value)
    
    val retain_VH: VarHandle = layout.varHandle(groupElement("retain"))
    
    @Suppress("UNCHECKED_CAST")
    fun retain(segment: MemorySegment): MemorySegment =
        retain_VH.get(segment, 0L) as MemorySegment
    
    fun retain(segment: MemorySegment, value: MemorySegment) =
        retain_VH.set(segment, 0L, value)
    
    val release_VH: VarHandle = layout.varHandle(groupElement("release"))
    
    @Suppress("UNCHECKED_CAST")
    fun release(segment: MemorySegment): MemorySegment =
        release_VH.get(segment, 0L) as MemorySegment
    
    fun release(segment: MemorySegment, value: MemorySegment) =
        release_VH.set(segment, 0L, value)
    
    val copyDescription_VH: VarHandle = layout.varHandle(groupElement("copyDescription"))
    
    @Suppress("UNCHECKED_CAST")
    fun copyDescription(segment: MemorySegment): MemorySegment =
        copyDescription_VH.get(segment, 0L) as MemorySegment
    
    fun copyDescription(segment: MemorySegment, value: MemorySegment) =
        copyDescription_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Void((Declared(__CFRunLoopTimer))*,(Void)*))* CFRunLoopTimerCallBack;}
 */
typealias CFRunLoopTimerCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFSocket))* CFSocketRef;}
 */
typealias CFSocketRef = MemorySegment

/**
 * {@snippet lang=c : STRUCT CFSocketSignature
 */
class CFSocketSignature {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("protocolFamily"),
            ValueLayout.JAVA_INT.withName("socketType"),
            ValueLayout.JAVA_INT.withName("protocol"),
            ValueLayout.ADDRESS.withName("address")
        ).withName("CFSocketSignature")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val protocolFamily_VH: VarHandle = layout.varHandle(groupElement("protocolFamily"))
    
    @Suppress("UNCHECKED_CAST")
    fun protocolFamily(segment: MemorySegment): Int =
        protocolFamily_VH.get(segment, 0L) as Int
    
    fun protocolFamily(segment: MemorySegment, value: Int) =
        protocolFamily_VH.set(segment, 0L, value)
    
    val socketType_VH: VarHandle = layout.varHandle(groupElement("socketType"))
    
    @Suppress("UNCHECKED_CAST")
    fun socketType(segment: MemorySegment): Int =
        socketType_VH.get(segment, 0L) as Int
    
    fun socketType(segment: MemorySegment, value: Int) =
        socketType_VH.set(segment, 0L, value)
    
    val protocol_VH: VarHandle = layout.varHandle(groupElement("protocol"))
    
    @Suppress("UNCHECKED_CAST")
    fun protocol(segment: MemorySegment): Int =
        protocol_VH.get(segment, 0L) as Int
    
    fun protocol(segment: MemorySegment, value: Int) =
        protocol_VH.set(segment, 0L, value)
    
    val address_VH: VarHandle = layout.varHandle(groupElement("address"))
    
    @Suppress("UNCHECKED_CAST")
    fun address(segment: MemorySegment): MemorySegment =
        address_VH.get(segment, 0L) as MemorySegment
    
    fun address(segment: MemorySegment, value: MemorySegment) =
        address_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CFSocketContext
 */
class CFSocketContext {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("version"),
            ValueLayout.ADDRESS.withName("info"),
            ValueLayout.ADDRESS.withName("retain"),
            ValueLayout.ADDRESS.withName("release"),
            ValueLayout.ADDRESS.withName("copyDescription")
        ).withName("CFSocketContext")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Long =
        version_VH.get(segment, 0L) as Long
    
    fun version(segment: MemorySegment, value: Long) =
        version_VH.set(segment, 0L, value)
    
    val info_VH: VarHandle = layout.varHandle(groupElement("info"))
    
    @Suppress("UNCHECKED_CAST")
    fun info(segment: MemorySegment): MemorySegment =
        info_VH.get(segment, 0L) as MemorySegment
    
    fun info(segment: MemorySegment, value: MemorySegment) =
        info_VH.set(segment, 0L, value)
    
    val retain_VH: VarHandle = layout.varHandle(groupElement("retain"))
    
    @Suppress("UNCHECKED_CAST")
    fun retain(segment: MemorySegment): MemorySegment =
        retain_VH.get(segment, 0L) as MemorySegment
    
    fun retain(segment: MemorySegment, value: MemorySegment) =
        retain_VH.set(segment, 0L, value)
    
    val release_VH: VarHandle = layout.varHandle(groupElement("release"))
    
    @Suppress("UNCHECKED_CAST")
    fun release(segment: MemorySegment): MemorySegment =
        release_VH.get(segment, 0L) as MemorySegment
    
    fun release(segment: MemorySegment, value: MemorySegment) =
        release_VH.set(segment, 0L, value)
    
    val copyDescription_VH: VarHandle = layout.varHandle(groupElement("copyDescription"))
    
    @Suppress("UNCHECKED_CAST")
    fun copyDescription(segment: MemorySegment): MemorySegment =
        copyDescription_VH.get(segment, 0L) as MemorySegment
    
    fun copyDescription(segment: MemorySegment, value: MemorySegment) =
        copyDescription_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef Int CFSocketNativeHandle;}
 */
typealias CFSocketNativeHandle = Int

/**
 * {@snippet lang=c : typedef Bool BOOL;}
 */
typealias BOOL = Boolean

/**
 * {@snippet lang=c : typedef Long NSInteger;}
 */
typealias NSInteger = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long NSUInteger;}
 */
typealias NSUInteger = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int os_workgroup_index;}
 */
typealias os_workgroup_index = Int

/**
 * {@snippet lang=c : typedef Int alarm_type_t;}
 */
typealias alarm_type_t = Int

/**
 * {@snippet lang=c : typedef Int sleep_type_t;}
 */
typealias sleep_type_t = Int

/**
 * {@snippet lang=c : typedef Int clock_id_t;}
 */
typealias clock_id_t = Int

/**
 * {@snippet lang=c : typedef Int clock_flavor_t;}
 */
typealias clock_flavor_t = Int

/**
 * {@snippet lang=c : typedef Int clock_res_t;}
 */
typealias clock_res_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong dispatch_time_t;}
 */
typealias dispatch_time_t = Long

/**
 * {@snippet lang=c : typedef Long dispatch_queue_priority_t;}
 */
typealias dispatch_queue_priority_t = Long

/**
 * {@snippet lang=c : typedef Int kern_return_t;}
 */
typealias kern_return_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_msg_timeout_t;}
 */
typealias mach_msg_timeout_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_msg_bits_t;}
 */
typealias mach_msg_bits_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_msg_size_t;}
 */
typealias mach_msg_size_t = Int

/**
 * {@snippet lang=c : typedef Int mach_msg_id_t;}
 */
typealias mach_msg_id_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_msg_priority_t;}
 */
typealias mach_msg_priority_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_msg_type_name_t;}
 */
typealias mach_msg_type_name_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_msg_copy_options_t;}
 */
typealias mach_msg_copy_options_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_msg_guard_flags_t;}
 */
typealias mach_msg_guard_flags_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_msg_descriptor_type_t;}
 */
typealias mach_msg_descriptor_type_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_msg_trailer_type_t;}
 */
typealias mach_msg_trailer_type_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_msg_trailer_size_t;}
 */
typealias mach_msg_trailer_size_t = Int

/**
 * {@snippet lang=c : typedef Int mach_msg_filter_id;}
 */
typealias mach_msg_filter_id = Int

/**
 * {@snippet lang=c : typedef Int mach_msg_options_t;}
 */
typealias mach_msg_options_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_msg_type_size_t;}
 */
typealias mach_msg_type_size_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_msg_type_number_t;}
 */
typealias mach_msg_type_number_t = Int

/**
 * {@snippet lang=c : typedef Int mach_msg_option_t;}
 */
typealias mach_msg_option_t = Int

/**
 * {@snippet lang=c : typedef Int mach_msg_return_t;}
 */
typealias mach_msg_return_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Long dispatch_source_mach_send_flags_t;}
 */
typealias dispatch_source_mach_send_flags_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long dispatch_source_mach_recv_flags_t;}
 */
typealias dispatch_source_mach_recv_flags_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long dispatch_source_memorypressure_flags_t;}
 */
typealias dispatch_source_memorypressure_flags_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long dispatch_source_proc_flags_t;}
 */
typealias dispatch_source_proc_flags_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long dispatch_source_vnode_flags_t;}
 */
typealias dispatch_source_vnode_flags_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long dispatch_source_timer_flags_t;}
 */
typealias dispatch_source_timer_flags_t = Long

/**
 * {@snippet lang=c : typedef Long dispatch_once_t;}
 */
typealias dispatch_once_t = Long

/**
 * {@snippet lang=c : typedef Int dispatch_fd_t;}
 */
typealias dispatch_fd_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Long dispatch_io_type_t;}
 */
typealias dispatch_io_type_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long dispatch_io_close_flags_t;}
 */
typealias dispatch_io_close_flags_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long dispatch_io_interval_flags_t;}
 */
typealias dispatch_io_interval_flags_t = Long

/**
 * {@snippet lang=c : STRUCT CFStreamError
 */
class CFStreamError {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("domain"),
            ValueLayout.JAVA_INT.withName("error")
        ).withName("CFStreamError")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val domain_VH: VarHandle = layout.varHandle(groupElement("domain"))
    
    @Suppress("UNCHECKED_CAST")
    fun domain(segment: MemorySegment): Long =
        domain_VH.get(segment, 0L) as Long
    
    fun domain(segment: MemorySegment, value: Long) =
        domain_VH.set(segment, 0L, value)
    
    val error_VH: VarHandle = layout.varHandle(groupElement("error"))
    
    @Suppress("UNCHECKED_CAST")
    fun error(segment: MemorySegment): Int =
        error_VH.get(segment, 0L) as Int
    
    fun error(segment: MemorySegment, value: Int) =
        error_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Declared(__CFString))* CFStreamPropertyKey;}
 */
typealias CFStreamPropertyKey = MemorySegment

/**
 * {@snippet lang=c : STRUCT CFStreamClientContext
 */
class CFStreamClientContext {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("version"),
            ValueLayout.ADDRESS.withName("info"),
            ValueLayout.ADDRESS.withName("retain"),
            ValueLayout.ADDRESS.withName("release"),
            ValueLayout.ADDRESS.withName("copyDescription")
        ).withName("CFStreamClientContext")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Long =
        version_VH.get(segment, 0L) as Long
    
    fun version(segment: MemorySegment, value: Long) =
        version_VH.set(segment, 0L, value)
    
    val info_VH: VarHandle = layout.varHandle(groupElement("info"))
    
    @Suppress("UNCHECKED_CAST")
    fun info(segment: MemorySegment): MemorySegment =
        info_VH.get(segment, 0L) as MemorySegment
    
    fun info(segment: MemorySegment, value: MemorySegment) =
        info_VH.set(segment, 0L, value)
    
    val retain_VH: VarHandle = layout.varHandle(groupElement("retain"))
    
    @Suppress("UNCHECKED_CAST")
    fun retain(segment: MemorySegment): MemorySegment =
        retain_VH.get(segment, 0L) as MemorySegment
    
    fun retain(segment: MemorySegment, value: MemorySegment) =
        retain_VH.set(segment, 0L, value)
    
    val release_VH: VarHandle = layout.varHandle(groupElement("release"))
    
    @Suppress("UNCHECKED_CAST")
    fun release(segment: MemorySegment): MemorySegment =
        release_VH.get(segment, 0L) as MemorySegment
    
    fun release(segment: MemorySegment, value: MemorySegment) =
        release_VH.set(segment, 0L, value)
    
    val copyDescription_VH: VarHandle = layout.varHandle(groupElement("copyDescription"))
    
    @Suppress("UNCHECKED_CAST")
    fun copyDescription(segment: MemorySegment): MemorySegment =
        copyDescription_VH.get(segment, 0L) as MemorySegment
    
    fun copyDescription(segment: MemorySegment, value: MemorySegment) =
        copyDescription_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Declared(__CFReadStream))* CFReadStreamRef;}
 */
typealias CFReadStreamRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFWriteStream))* CFWriteStreamRef;}
 */
typealias CFWriteStreamRef = MemorySegment

/**
 * {@snippet lang=c : typedef ((Void)*((Declared(__CFAllocator))*,(Void)*))* CFSetRetainCallBack;}
 */
typealias CFSetRetainCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Declared(__CFAllocator))*,(Void)*))* CFSetReleaseCallBack;}
 */
typealias CFSetReleaseCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef ((Declared(__CFString))*((Void)*))* CFSetCopyDescriptionCallBack;}
 */
typealias CFSetCopyDescriptionCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef (UNSIGNED = Char((Void)*,(Void)*))* CFSetEqualCallBack;}
 */
typealias CFSetEqualCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef (UNSIGNED = Long((Void)*))* CFSetHashCallBack;}
 */
typealias CFSetHashCallBack = MemorySegment

/**
 * {@snippet lang=c : STRUCT CFSetCallBacks
 */
class CFSetCallBacks {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("version"),
            ValueLayout.ADDRESS.withName("retain"),
            ValueLayout.ADDRESS.withName("release"),
            ValueLayout.ADDRESS.withName("copyDescription"),
            ValueLayout.ADDRESS.withName("equal"),
            ValueLayout.ADDRESS.withName("hash")
        ).withName("CFSetCallBacks")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Long =
        version_VH.get(segment, 0L) as Long
    
    fun version(segment: MemorySegment, value: Long) =
        version_VH.set(segment, 0L, value)
    
    val retain_VH: VarHandle = layout.varHandle(groupElement("retain"))
    
    @Suppress("UNCHECKED_CAST")
    fun retain(segment: MemorySegment): MemorySegment =
        retain_VH.get(segment, 0L) as MemorySegment
    
    fun retain(segment: MemorySegment, value: MemorySegment) =
        retain_VH.set(segment, 0L, value)
    
    val release_VH: VarHandle = layout.varHandle(groupElement("release"))
    
    @Suppress("UNCHECKED_CAST")
    fun release(segment: MemorySegment): MemorySegment =
        release_VH.get(segment, 0L) as MemorySegment
    
    fun release(segment: MemorySegment, value: MemorySegment) =
        release_VH.set(segment, 0L, value)
    
    val copyDescription_VH: VarHandle = layout.varHandle(groupElement("copyDescription"))
    
    @Suppress("UNCHECKED_CAST")
    fun copyDescription(segment: MemorySegment): MemorySegment =
        copyDescription_VH.get(segment, 0L) as MemorySegment
    
    fun copyDescription(segment: MemorySegment, value: MemorySegment) =
        copyDescription_VH.set(segment, 0L, value)
    
    val equal_VH: VarHandle = layout.varHandle(groupElement("equal"))
    
    @Suppress("UNCHECKED_CAST")
    fun equal(segment: MemorySegment): MemorySegment =
        equal_VH.get(segment, 0L) as MemorySegment
    
    fun equal(segment: MemorySegment, value: MemorySegment) =
        equal_VH.set(segment, 0L, value)
    
    val hash_VH: VarHandle = layout.varHandle(groupElement("hash"))
    
    @Suppress("UNCHECKED_CAST")
    fun hash(segment: MemorySegment): MemorySegment =
        hash_VH.get(segment, 0L) as MemorySegment
    
    fun hash(segment: MemorySegment, value: MemorySegment) =
        hash_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Void((Void)*,(Void)*))* CFSetApplierFunction;}
 */
typealias CFSetApplierFunction = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFSet))* CFSetRef;}
 */
typealias CFSetRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFSet))* CFMutableSetRef;}
 */
typealias CFMutableSetRef = MemorySegment

/**
 * {@snippet lang=c : typedef ((Void)*((Void)*))* CFTreeRetainCallBack;}
 */
typealias CFTreeRetainCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Void)*))* CFTreeReleaseCallBack;}
 */
typealias CFTreeReleaseCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef ((Declared(__CFString))*((Void)*))* CFTreeCopyDescriptionCallBack;}
 */
typealias CFTreeCopyDescriptionCallBack = MemorySegment

/**
 * {@snippet lang=c : STRUCT CFTreeContext
 */
class CFTreeContext {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("version"),
            ValueLayout.ADDRESS.withName("info"),
            ValueLayout.ADDRESS.withName("retain"),
            ValueLayout.ADDRESS.withName("release"),
            ValueLayout.ADDRESS.withName("copyDescription")
        ).withName("CFTreeContext")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Long =
        version_VH.get(segment, 0L) as Long
    
    fun version(segment: MemorySegment, value: Long) =
        version_VH.set(segment, 0L, value)
    
    val info_VH: VarHandle = layout.varHandle(groupElement("info"))
    
    @Suppress("UNCHECKED_CAST")
    fun info(segment: MemorySegment): MemorySegment =
        info_VH.get(segment, 0L) as MemorySegment
    
    fun info(segment: MemorySegment, value: MemorySegment) =
        info_VH.set(segment, 0L, value)
    
    val retain_VH: VarHandle = layout.varHandle(groupElement("retain"))
    
    @Suppress("UNCHECKED_CAST")
    fun retain(segment: MemorySegment): MemorySegment =
        retain_VH.get(segment, 0L) as MemorySegment
    
    fun retain(segment: MemorySegment, value: MemorySegment) =
        retain_VH.set(segment, 0L, value)
    
    val release_VH: VarHandle = layout.varHandle(groupElement("release"))
    
    @Suppress("UNCHECKED_CAST")
    fun release(segment: MemorySegment): MemorySegment =
        release_VH.get(segment, 0L) as MemorySegment
    
    fun release(segment: MemorySegment, value: MemorySegment) =
        release_VH.set(segment, 0L, value)
    
    val copyDescription_VH: VarHandle = layout.varHandle(groupElement("copyDescription"))
    
    @Suppress("UNCHECKED_CAST")
    fun copyDescription(segment: MemorySegment): MemorySegment =
        copyDescription_VH.get(segment, 0L) as MemorySegment
    
    fun copyDescription(segment: MemorySegment, value: MemorySegment) =
        copyDescription_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Void((Void)*,(Void)*))* CFTreeApplierFunction;}
 */
typealias CFTreeApplierFunction = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFTree))* CFTreeRef;}
 */
typealias CFTreeRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFUUID))* CFUUIDRef;}
 */
typealias CFUUIDRef = MemorySegment

/**
 * {@snippet lang=c : STRUCT CFUUIDBytes
 */
class CFUUIDBytes {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_BYTE.withName("byte0"),
            ValueLayout.JAVA_BYTE.withName("byte1"),
            ValueLayout.JAVA_BYTE.withName("byte2"),
            ValueLayout.JAVA_BYTE.withName("byte3"),
            ValueLayout.JAVA_BYTE.withName("byte4"),
            ValueLayout.JAVA_BYTE.withName("byte5"),
            ValueLayout.JAVA_BYTE.withName("byte6"),
            ValueLayout.JAVA_BYTE.withName("byte7"),
            ValueLayout.JAVA_BYTE.withName("byte8"),
            ValueLayout.JAVA_BYTE.withName("byte9"),
            ValueLayout.JAVA_BYTE.withName("byte10"),
            ValueLayout.JAVA_BYTE.withName("byte11"),
            ValueLayout.JAVA_BYTE.withName("byte12"),
            ValueLayout.JAVA_BYTE.withName("byte13"),
            ValueLayout.JAVA_BYTE.withName("byte14"),
            ValueLayout.JAVA_BYTE.withName("byte15")
        ).withName("CFUUIDBytes")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val byte0_VH: VarHandle = layout.varHandle(groupElement("byte0"))
    
    @Suppress("UNCHECKED_CAST")
    fun byte0(segment: MemorySegment): Byte =
        byte0_VH.get(segment, 0L) as Byte
    
    fun byte0(segment: MemorySegment, value: Byte) =
        byte0_VH.set(segment, 0L, value)
    
    val byte1_VH: VarHandle = layout.varHandle(groupElement("byte1"))
    
    @Suppress("UNCHECKED_CAST")
    fun byte1(segment: MemorySegment): Byte =
        byte1_VH.get(segment, 0L) as Byte
    
    fun byte1(segment: MemorySegment, value: Byte) =
        byte1_VH.set(segment, 0L, value)
    
    val byte2_VH: VarHandle = layout.varHandle(groupElement("byte2"))
    
    @Suppress("UNCHECKED_CAST")
    fun byte2(segment: MemorySegment): Byte =
        byte2_VH.get(segment, 0L) as Byte
    
    fun byte2(segment: MemorySegment, value: Byte) =
        byte2_VH.set(segment, 0L, value)
    
    val byte3_VH: VarHandle = layout.varHandle(groupElement("byte3"))
    
    @Suppress("UNCHECKED_CAST")
    fun byte3(segment: MemorySegment): Byte =
        byte3_VH.get(segment, 0L) as Byte
    
    fun byte3(segment: MemorySegment, value: Byte) =
        byte3_VH.set(segment, 0L, value)
    
    val byte4_VH: VarHandle = layout.varHandle(groupElement("byte4"))
    
    @Suppress("UNCHECKED_CAST")
    fun byte4(segment: MemorySegment): Byte =
        byte4_VH.get(segment, 0L) as Byte
    
    fun byte4(segment: MemorySegment, value: Byte) =
        byte4_VH.set(segment, 0L, value)
    
    val byte5_VH: VarHandle = layout.varHandle(groupElement("byte5"))
    
    @Suppress("UNCHECKED_CAST")
    fun byte5(segment: MemorySegment): Byte =
        byte5_VH.get(segment, 0L) as Byte
    
    fun byte5(segment: MemorySegment, value: Byte) =
        byte5_VH.set(segment, 0L, value)
    
    val byte6_VH: VarHandle = layout.varHandle(groupElement("byte6"))
    
    @Suppress("UNCHECKED_CAST")
    fun byte6(segment: MemorySegment): Byte =
        byte6_VH.get(segment, 0L) as Byte
    
    fun byte6(segment: MemorySegment, value: Byte) =
        byte6_VH.set(segment, 0L, value)
    
    val byte7_VH: VarHandle = layout.varHandle(groupElement("byte7"))
    
    @Suppress("UNCHECKED_CAST")
    fun byte7(segment: MemorySegment): Byte =
        byte7_VH.get(segment, 0L) as Byte
    
    fun byte7(segment: MemorySegment, value: Byte) =
        byte7_VH.set(segment, 0L, value)
    
    val byte8_VH: VarHandle = layout.varHandle(groupElement("byte8"))
    
    @Suppress("UNCHECKED_CAST")
    fun byte8(segment: MemorySegment): Byte =
        byte8_VH.get(segment, 0L) as Byte
    
    fun byte8(segment: MemorySegment, value: Byte) =
        byte8_VH.set(segment, 0L, value)
    
    val byte9_VH: VarHandle = layout.varHandle(groupElement("byte9"))
    
    @Suppress("UNCHECKED_CAST")
    fun byte9(segment: MemorySegment): Byte =
        byte9_VH.get(segment, 0L) as Byte
    
    fun byte9(segment: MemorySegment, value: Byte) =
        byte9_VH.set(segment, 0L, value)
    
    val byte10_VH: VarHandle = layout.varHandle(groupElement("byte10"))
    
    @Suppress("UNCHECKED_CAST")
    fun byte10(segment: MemorySegment): Byte =
        byte10_VH.get(segment, 0L) as Byte
    
    fun byte10(segment: MemorySegment, value: Byte) =
        byte10_VH.set(segment, 0L, value)
    
    val byte11_VH: VarHandle = layout.varHandle(groupElement("byte11"))
    
    @Suppress("UNCHECKED_CAST")
    fun byte11(segment: MemorySegment): Byte =
        byte11_VH.get(segment, 0L) as Byte
    
    fun byte11(segment: MemorySegment, value: Byte) =
        byte11_VH.set(segment, 0L, value)
    
    val byte12_VH: VarHandle = layout.varHandle(groupElement("byte12"))
    
    @Suppress("UNCHECKED_CAST")
    fun byte12(segment: MemorySegment): Byte =
        byte12_VH.get(segment, 0L) as Byte
    
    fun byte12(segment: MemorySegment, value: Byte) =
        byte12_VH.set(segment, 0L, value)
    
    val byte13_VH: VarHandle = layout.varHandle(groupElement("byte13"))
    
    @Suppress("UNCHECKED_CAST")
    fun byte13(segment: MemorySegment): Byte =
        byte13_VH.get(segment, 0L) as Byte
    
    fun byte13(segment: MemorySegment, value: Byte) =
        byte13_VH.set(segment, 0L, value)
    
    val byte14_VH: VarHandle = layout.varHandle(groupElement("byte14"))
    
    @Suppress("UNCHECKED_CAST")
    fun byte14(segment: MemorySegment): Byte =
        byte14_VH.get(segment, 0L) as Byte
    
    fun byte14(segment: MemorySegment, value: Byte) =
        byte14_VH.set(segment, 0L, value)
    
    val byte15_VH: VarHandle = layout.varHandle(groupElement("byte15"))
    
    @Suppress("UNCHECKED_CAST")
    fun byte15(segment: MemorySegment): Byte =
        byte15_VH.get(segment, 0L) as Byte
    
    fun byte15(segment: MemorySegment, value: Byte) =
        byte15_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef Int cpu_type_t;}
 */
typealias cpu_type_t = Int

/**
 * {@snippet lang=c : typedef Int cpu_subtype_t;}
 */
typealias cpu_subtype_t = Int

/**
 * {@snippet lang=c : typedef Int cpu_threadtype_t;}
 */
typealias cpu_threadtype_t = Int

/**
 * {@snippet lang=c : typedef (Declared(__CFBundle))* CFBundleRef;}
 */
typealias CFBundleRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFBundle))* CFPlugInRef;}
 */
typealias CFPlugInRef = MemorySegment

/**
 * {@snippet lang=c : typedef Int CFBundleRefNum;}
 */
typealias CFBundleRefNum = Int

/**
 * {@snippet lang=c : typedef (Declared(__CFMessagePort))* CFMessagePortRef;}
 */
typealias CFMessagePortRef = MemorySegment

/**
 * {@snippet lang=c : STRUCT CFMessagePortContext
 */
class CFMessagePortContext {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("version"),
            ValueLayout.ADDRESS.withName("info"),
            ValueLayout.ADDRESS.withName("retain"),
            ValueLayout.ADDRESS.withName("release"),
            ValueLayout.ADDRESS.withName("copyDescription")
        ).withName("CFMessagePortContext")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Long =
        version_VH.get(segment, 0L) as Long
    
    fun version(segment: MemorySegment, value: Long) =
        version_VH.set(segment, 0L, value)
    
    val info_VH: VarHandle = layout.varHandle(groupElement("info"))
    
    @Suppress("UNCHECKED_CAST")
    fun info(segment: MemorySegment): MemorySegment =
        info_VH.get(segment, 0L) as MemorySegment
    
    fun info(segment: MemorySegment, value: MemorySegment) =
        info_VH.set(segment, 0L, value)
    
    val retain_VH: VarHandle = layout.varHandle(groupElement("retain"))
    
    @Suppress("UNCHECKED_CAST")
    fun retain(segment: MemorySegment): MemorySegment =
        retain_VH.get(segment, 0L) as MemorySegment
    
    fun retain(segment: MemorySegment, value: MemorySegment) =
        retain_VH.set(segment, 0L, value)
    
    val release_VH: VarHandle = layout.varHandle(groupElement("release"))
    
    @Suppress("UNCHECKED_CAST")
    fun release(segment: MemorySegment): MemorySegment =
        release_VH.get(segment, 0L) as MemorySegment
    
    fun release(segment: MemorySegment, value: MemorySegment) =
        release_VH.set(segment, 0L, value)
    
    val copyDescription_VH: VarHandle = layout.varHandle(groupElement("copyDescription"))
    
    @Suppress("UNCHECKED_CAST")
    fun copyDescription(segment: MemorySegment): MemorySegment =
        copyDescription_VH.get(segment, 0L) as MemorySegment
    
    fun copyDescription(segment: MemorySegment, value: MemorySegment) =
        copyDescription_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef ((Declared(__CFData))*((Declared(__CFMessagePort))*,Int,(Declared(__CFData))*,(Void)*))* CFMessagePortCallBack;}
 */
typealias CFMessagePortCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Declared(__CFMessagePort))*,(Void)*))* CFMessagePortInvalidationCallBack;}
 */
typealias CFMessagePortInvalidationCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Declared(__CFBundle))*))* CFPlugInDynamicRegisterFunction;}
 */
typealias CFPlugInDynamicRegisterFunction = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Declared(__CFBundle))*))* CFPlugInUnloadFunction;}
 */
typealias CFPlugInUnloadFunction = MemorySegment

/**
 * {@snippet lang=c : typedef ((Void)*((Declared(__CFAllocator))*,(Declared(__CFUUID))*))* CFPlugInFactoryFunction;}
 */
typealias CFPlugInFactoryFunction = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFPlugInInstance))* CFPlugInInstanceRef;}
 */
typealias CFPlugInInstanceRef = MemorySegment

/**
 * {@snippet lang=c : typedef (UNSIGNED = Char((Declared(__CFPlugInInstance))*,(Declared(__CFString))*,((Void)*)*))* CFPlugInInstanceGetInterfaceFunction;}
 */
typealias CFPlugInInstanceGetInterfaceFunction = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Void)*))* CFPlugInInstanceDeallocateInstanceDataFunction;}
 */
typealias CFPlugInInstanceDeallocateInstanceDataFunction = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFMachPort))* CFMachPortRef;}
 */
typealias CFMachPortRef = MemorySegment

/**
 * {@snippet lang=c : STRUCT CFMachPortContext
 */
class CFMachPortContext {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("version"),
            ValueLayout.ADDRESS.withName("info"),
            ValueLayout.ADDRESS.withName("retain"),
            ValueLayout.ADDRESS.withName("release"),
            ValueLayout.ADDRESS.withName("copyDescription")
        ).withName("CFMachPortContext")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Long =
        version_VH.get(segment, 0L) as Long
    
    fun version(segment: MemorySegment, value: Long) =
        version_VH.set(segment, 0L, value)
    
    val info_VH: VarHandle = layout.varHandle(groupElement("info"))
    
    @Suppress("UNCHECKED_CAST")
    fun info(segment: MemorySegment): MemorySegment =
        info_VH.get(segment, 0L) as MemorySegment
    
    fun info(segment: MemorySegment, value: MemorySegment) =
        info_VH.set(segment, 0L, value)
    
    val retain_VH: VarHandle = layout.varHandle(groupElement("retain"))
    
    @Suppress("UNCHECKED_CAST")
    fun retain(segment: MemorySegment): MemorySegment =
        retain_VH.get(segment, 0L) as MemorySegment
    
    fun retain(segment: MemorySegment, value: MemorySegment) =
        retain_VH.set(segment, 0L, value)
    
    val release_VH: VarHandle = layout.varHandle(groupElement("release"))
    
    @Suppress("UNCHECKED_CAST")
    fun release(segment: MemorySegment): MemorySegment =
        release_VH.get(segment, 0L) as MemorySegment
    
    fun release(segment: MemorySegment, value: MemorySegment) =
        release_VH.set(segment, 0L, value)
    
    val copyDescription_VH: VarHandle = layout.varHandle(groupElement("copyDescription"))
    
    @Suppress("UNCHECKED_CAST")
    fun copyDescription(segment: MemorySegment): MemorySegment =
        copyDescription_VH.get(segment, 0L) as MemorySegment
    
    fun copyDescription(segment: MemorySegment, value: MemorySegment) =
        copyDescription_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Void((Declared(__CFMachPort))*,(Void)*,Long,(Void)*))* CFMachPortCallBack;}
 */
typealias CFMachPortCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Declared(__CFMachPort))*,(Void)*))* CFMachPortInvalidationCallBack;}
 */
typealias CFMachPortInvalidationCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFAttributedString))* CFAttributedStringRef;}
 */
typealias CFAttributedStringRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFAttributedString))* CFMutableAttributedStringRef;}
 */
typealias CFMutableAttributedStringRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFURLEnumerator))* CFURLEnumeratorRef;}
 */
typealias CFURLEnumeratorRef = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Int kauth_ace_rights_t;}
 */
typealias kauth_ace_rights_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong acl_permset_mask_t;}
 */
typealias acl_permset_mask_t = Long

/**
 * {@snippet lang=c : typedef (Declared(__CFFileSecurity))* CFFileSecurityRef;}
 */
typealias CFFileSecurityRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFStringTokenizer))* CFStringTokenizerRef;}
 */
typealias CFStringTokenizerRef = MemorySegment

/**
 * {@snippet lang=c : typedef Int CFFileDescriptorNativeDescriptor;}
 */
typealias CFFileDescriptorNativeDescriptor = Int

/**
 * {@snippet lang=c : typedef (Declared(__CFFileDescriptor))* CFFileDescriptorRef;}
 */
typealias CFFileDescriptorRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Declared(__CFFileDescriptor))*,UNSIGNED = Long,(Void)*))* CFFileDescriptorCallBack;}
 */
typealias CFFileDescriptorCallBack = MemorySegment

/**
 * {@snippet lang=c : STRUCT CFFileDescriptorContext
 */
class CFFileDescriptorContext {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("version"),
            ValueLayout.ADDRESS.withName("info"),
            ValueLayout.ADDRESS.withName("retain"),
            ValueLayout.ADDRESS.withName("release"),
            ValueLayout.ADDRESS.withName("copyDescription")
        ).withName("CFFileDescriptorContext")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Long =
        version_VH.get(segment, 0L) as Long
    
    fun version(segment: MemorySegment, value: Long) =
        version_VH.set(segment, 0L, value)
    
    val info_VH: VarHandle = layout.varHandle(groupElement("info"))
    
    @Suppress("UNCHECKED_CAST")
    fun info(segment: MemorySegment): MemorySegment =
        info_VH.get(segment, 0L) as MemorySegment
    
    fun info(segment: MemorySegment, value: MemorySegment) =
        info_VH.set(segment, 0L, value)
    
    val retain_VH: VarHandle = layout.varHandle(groupElement("retain"))
    
    @Suppress("UNCHECKED_CAST")
    fun retain(segment: MemorySegment): MemorySegment =
        retain_VH.get(segment, 0L) as MemorySegment
    
    fun retain(segment: MemorySegment, value: MemorySegment) =
        retain_VH.set(segment, 0L, value)
    
    val release_VH: VarHandle = layout.varHandle(groupElement("release"))
    
    @Suppress("UNCHECKED_CAST")
    fun release(segment: MemorySegment): MemorySegment =
        release_VH.get(segment, 0L) as MemorySegment
    
    fun release(segment: MemorySegment, value: MemorySegment) =
        release_VH.set(segment, 0L, value)
    
    val copyDescription_VH: VarHandle = layout.varHandle(groupElement("copyDescription"))
    
    @Suppress("UNCHECKED_CAST")
    fun copyDescription(segment: MemorySegment): MemorySegment =
        copyDescription_VH.get(segment, 0L) as MemorySegment
    
    fun copyDescription(segment: MemorySegment, value: MemorySegment) =
        copyDescription_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Declared(__CFUserNotification))* CFUserNotificationRef;}
 */
typealias CFUserNotificationRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Declared(__CFUserNotification))*,UNSIGNED = Long))* CFUserNotificationCallBack;}
 */
typealias CFUserNotificationCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFXMLNode))* CFXMLNodeRef;}
 */
typealias CFXMLNodeRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFTree))* CFXMLTreeRef;}
 */
typealias CFXMLTreeRef = MemorySegment

/**
 * {@snippet lang=c : STRUCT CFXMLElementInfo
 */
class CFXMLElementInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("attributes"),
            ValueLayout.ADDRESS.withName("attributeOrder"),
            ValueLayout.JAVA_BYTE.withName("isEmpty"),
            MemoryLayout.sequenceLayout(3, ValueLayout.JAVA_BYTE).withName("_reserved")
        ).withName("CFXMLElementInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val attributes_VH: VarHandle = layout.varHandle(groupElement("attributes"))
    
    @Suppress("UNCHECKED_CAST")
    fun attributes(segment: MemorySegment): MemorySegment =
        attributes_VH.get(segment, 0L) as MemorySegment
    
    fun attributes(segment: MemorySegment, value: MemorySegment) =
        attributes_VH.set(segment, 0L, value)
    
    val attributeOrder_VH: VarHandle = layout.varHandle(groupElement("attributeOrder"))
    
    @Suppress("UNCHECKED_CAST")
    fun attributeOrder(segment: MemorySegment): MemorySegment =
        attributeOrder_VH.get(segment, 0L) as MemorySegment
    
    fun attributeOrder(segment: MemorySegment, value: MemorySegment) =
        attributeOrder_VH.set(segment, 0L, value)
    
    val isEmpty_VH: VarHandle = layout.varHandle(groupElement("isEmpty"))
    
    @Suppress("UNCHECKED_CAST")
    fun isEmpty(segment: MemorySegment): Byte =
        isEmpty_VH.get(segment, 0L) as Byte
    
    fun isEmpty(segment: MemorySegment, value: Byte) =
        isEmpty_VH.set(segment, 0L, value)
    
    fun _reserved(segment: MemorySegment): MemorySegment =
        segment.asSlice(layout.byteOffset(groupElement("_reserved")), layout.select(groupElement("_reserved")).byteSize())
} // End class

/**
 * {@snippet lang=c : STRUCT CFXMLProcessingInstructionInfo
 */
class CFXMLProcessingInstructionInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("dataString")
        ).withName("CFXMLProcessingInstructionInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val dataString_VH: VarHandle = layout.varHandle(groupElement("dataString"))
    
    @Suppress("UNCHECKED_CAST")
    fun dataString(segment: MemorySegment): MemorySegment =
        dataString_VH.get(segment, 0L) as MemorySegment
    
    fun dataString(segment: MemorySegment, value: MemorySegment) =
        dataString_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CFXMLDocumentInfo
 */
class CFXMLDocumentInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("sourceURL"),
            ValueLayout.JAVA_INT.withName("encoding")
        ).withName("CFXMLDocumentInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val sourceURL_VH: VarHandle = layout.varHandle(groupElement("sourceURL"))
    
    @Suppress("UNCHECKED_CAST")
    fun sourceURL(segment: MemorySegment): MemorySegment =
        sourceURL_VH.get(segment, 0L) as MemorySegment
    
    fun sourceURL(segment: MemorySegment, value: MemorySegment) =
        sourceURL_VH.set(segment, 0L, value)
    
    val encoding_VH: VarHandle = layout.varHandle(groupElement("encoding"))
    
    @Suppress("UNCHECKED_CAST")
    fun encoding(segment: MemorySegment): Int =
        encoding_VH.get(segment, 0L) as Int
    
    fun encoding(segment: MemorySegment, value: Int) =
        encoding_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CFXMLExternalID
 */
class CFXMLExternalID {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("systemID"),
            ValueLayout.ADDRESS.withName("publicID")
        ).withName("CFXMLExternalID")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val systemID_VH: VarHandle = layout.varHandle(groupElement("systemID"))
    
    @Suppress("UNCHECKED_CAST")
    fun systemID(segment: MemorySegment): MemorySegment =
        systemID_VH.get(segment, 0L) as MemorySegment
    
    fun systemID(segment: MemorySegment, value: MemorySegment) =
        systemID_VH.set(segment, 0L, value)
    
    val publicID_VH: VarHandle = layout.varHandle(groupElement("publicID"))
    
    @Suppress("UNCHECKED_CAST")
    fun publicID(segment: MemorySegment): MemorySegment =
        publicID_VH.get(segment, 0L) as MemorySegment
    
    fun publicID(segment: MemorySegment, value: MemorySegment) =
        publicID_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CFXMLDocumentTypeInfo
 */
class CFXMLDocumentTypeInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            CFXMLExternalID.layout.withName("externalID")
        ).withName("CFXMLDocumentTypeInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val externalID_VH: VarHandle = layout.varHandle(groupElement("externalID"))
    
    @Suppress("UNCHECKED_CAST")
    fun externalID(segment: MemorySegment): MemorySegment =
        externalID_VH.get(segment, 0L) as MemorySegment
    
    fun externalID(segment: MemorySegment, value: MemorySegment) =
        externalID_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CFXMLNotationInfo
 */
class CFXMLNotationInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            CFXMLExternalID.layout.withName("externalID")
        ).withName("CFXMLNotationInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val externalID_VH: VarHandle = layout.varHandle(groupElement("externalID"))
    
    @Suppress("UNCHECKED_CAST")
    fun externalID(segment: MemorySegment): MemorySegment =
        externalID_VH.get(segment, 0L) as MemorySegment
    
    fun externalID(segment: MemorySegment, value: MemorySegment) =
        externalID_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CFXMLElementTypeDeclarationInfo
 */
class CFXMLElementTypeDeclarationInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("contentDescription")
        ).withName("CFXMLElementTypeDeclarationInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val contentDescription_VH: VarHandle = layout.varHandle(groupElement("contentDescription"))
    
    @Suppress("UNCHECKED_CAST")
    fun contentDescription(segment: MemorySegment): MemorySegment =
        contentDescription_VH.get(segment, 0L) as MemorySegment
    
    fun contentDescription(segment: MemorySegment, value: MemorySegment) =
        contentDescription_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CFXMLAttributeDeclarationInfo
 */
class CFXMLAttributeDeclarationInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("attributeName"),
            ValueLayout.ADDRESS.withName("typeString"),
            ValueLayout.ADDRESS.withName("defaultString")
        ).withName("CFXMLAttributeDeclarationInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val attributeName_VH: VarHandle = layout.varHandle(groupElement("attributeName"))
    
    @Suppress("UNCHECKED_CAST")
    fun attributeName(segment: MemorySegment): MemorySegment =
        attributeName_VH.get(segment, 0L) as MemorySegment
    
    fun attributeName(segment: MemorySegment, value: MemorySegment) =
        attributeName_VH.set(segment, 0L, value)
    
    val typeString_VH: VarHandle = layout.varHandle(groupElement("typeString"))
    
    @Suppress("UNCHECKED_CAST")
    fun typeString(segment: MemorySegment): MemorySegment =
        typeString_VH.get(segment, 0L) as MemorySegment
    
    fun typeString(segment: MemorySegment, value: MemorySegment) =
        typeString_VH.set(segment, 0L, value)
    
    val defaultString_VH: VarHandle = layout.varHandle(groupElement("defaultString"))
    
    @Suppress("UNCHECKED_CAST")
    fun defaultString(segment: MemorySegment): MemorySegment =
        defaultString_VH.get(segment, 0L) as MemorySegment
    
    fun defaultString(segment: MemorySegment, value: MemorySegment) =
        defaultString_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CFXMLAttributeListDeclarationInfo
 */
class CFXMLAttributeListDeclarationInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("numberOfAttributes"),
            ValueLayout.ADDRESS.withName("attributes")
        ).withName("CFXMLAttributeListDeclarationInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val numberOfAttributes_VH: VarHandle = layout.varHandle(groupElement("numberOfAttributes"))
    
    @Suppress("UNCHECKED_CAST")
    fun numberOfAttributes(segment: MemorySegment): Long =
        numberOfAttributes_VH.get(segment, 0L) as Long
    
    fun numberOfAttributes(segment: MemorySegment, value: Long) =
        numberOfAttributes_VH.set(segment, 0L, value)
    
    val attributes_VH: VarHandle = layout.varHandle(groupElement("attributes"))
    
    @Suppress("UNCHECKED_CAST")
    fun attributes(segment: MemorySegment): MemorySegment =
        attributes_VH.get(segment, 0L) as MemorySegment
    
    fun attributes(segment: MemorySegment, value: MemorySegment) =
        attributes_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CFXMLEntityInfo
 */
class CFXMLEntityInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("entityType"),
            ValueLayout.ADDRESS.withName("replacementText"),
            CFXMLExternalID.layout.withName("entityID"),
            ValueLayout.ADDRESS.withName("notationName")
        ).withName("CFXMLEntityInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val entityType_VH: VarHandle = layout.varHandle(groupElement("entityType"))
    
    @Suppress("UNCHECKED_CAST")
    fun entityType(segment: MemorySegment): MemorySegment =
        entityType_VH.get(segment, 0L) as MemorySegment
    
    fun entityType(segment: MemorySegment, value: MemorySegment) =
        entityType_VH.set(segment, 0L, value)
    
    val replacementText_VH: VarHandle = layout.varHandle(groupElement("replacementText"))
    
    @Suppress("UNCHECKED_CAST")
    fun replacementText(segment: MemorySegment): MemorySegment =
        replacementText_VH.get(segment, 0L) as MemorySegment
    
    fun replacementText(segment: MemorySegment, value: MemorySegment) =
        replacementText_VH.set(segment, 0L, value)
    
    val entityID_VH: VarHandle = layout.varHandle(groupElement("entityID"))
    
    @Suppress("UNCHECKED_CAST")
    fun entityID(segment: MemorySegment): MemorySegment =
        entityID_VH.get(segment, 0L) as MemorySegment
    
    fun entityID(segment: MemorySegment, value: MemorySegment) =
        entityID_VH.set(segment, 0L, value)
    
    val notationName_VH: VarHandle = layout.varHandle(groupElement("notationName"))
    
    @Suppress("UNCHECKED_CAST")
    fun notationName(segment: MemorySegment): MemorySegment =
        notationName_VH.get(segment, 0L) as MemorySegment
    
    fun notationName(segment: MemorySegment, value: MemorySegment) =
        notationName_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CFXMLEntityReferenceInfo
 */
class CFXMLEntityReferenceInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("entityType")
        ).withName("CFXMLEntityReferenceInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val entityType_VH: VarHandle = layout.varHandle(groupElement("entityType"))
    
    @Suppress("UNCHECKED_CAST")
    fun entityType(segment: MemorySegment): MemorySegment =
        entityType_VH.get(segment, 0L) as MemorySegment
    
    fun entityType(segment: MemorySegment, value: MemorySegment) =
        entityType_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Declared(__CFXMLParser))* CFXMLParserRef;}
 */
typealias CFXMLParserRef = MemorySegment

/**
 * {@snippet lang=c : typedef ((Void)*((Declared(__CFXMLParser))*,(Declared(__CFXMLNode))*,(Void)*))* CFXMLParserCreateXMLStructureCallBack;}
 */
typealias CFXMLParserCreateXMLStructureCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Declared(__CFXMLParser))*,(Void)*,(Void)*,(Void)*))* CFXMLParserAddChildCallBack;}
 */
typealias CFXMLParserAddChildCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Declared(__CFXMLParser))*,(Void)*,(Void)*))* CFXMLParserEndXMLStructureCallBack;}
 */
typealias CFXMLParserEndXMLStructureCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef ((Declared(__CFData))*((Declared(__CFXMLParser))*,(Declared(CFXMLExternalID))*,(Void)*))* CFXMLParserResolveExternalEntityCallBack;}
 */
typealias CFXMLParserResolveExternalEntityCallBack = MemorySegment

/**
 * {@snippet lang=c : STRUCT CFXMLParserCallBacks
 */
class CFXMLParserCallBacks {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("version"),
            ValueLayout.ADDRESS.withName("createXMLStructure"),
            ValueLayout.ADDRESS.withName("addChild"),
            ValueLayout.ADDRESS.withName("endXMLStructure"),
            ValueLayout.ADDRESS.withName("resolveExternalEntity"),
            ValueLayout.ADDRESS.withName("handleError")
        ).withName("CFXMLParserCallBacks")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Long =
        version_VH.get(segment, 0L) as Long
    
    fun version(segment: MemorySegment, value: Long) =
        version_VH.set(segment, 0L, value)
    
    val createXMLStructure_VH: VarHandle = layout.varHandle(groupElement("createXMLStructure"))
    
    @Suppress("UNCHECKED_CAST")
    fun createXMLStructure(segment: MemorySegment): MemorySegment =
        createXMLStructure_VH.get(segment, 0L) as MemorySegment
    
    fun createXMLStructure(segment: MemorySegment, value: MemorySegment) =
        createXMLStructure_VH.set(segment, 0L, value)
    
    val addChild_VH: VarHandle = layout.varHandle(groupElement("addChild"))
    
    @Suppress("UNCHECKED_CAST")
    fun addChild(segment: MemorySegment): MemorySegment =
        addChild_VH.get(segment, 0L) as MemorySegment
    
    fun addChild(segment: MemorySegment, value: MemorySegment) =
        addChild_VH.set(segment, 0L, value)
    
    val endXMLStructure_VH: VarHandle = layout.varHandle(groupElement("endXMLStructure"))
    
    @Suppress("UNCHECKED_CAST")
    fun endXMLStructure(segment: MemorySegment): MemorySegment =
        endXMLStructure_VH.get(segment, 0L) as MemorySegment
    
    fun endXMLStructure(segment: MemorySegment, value: MemorySegment) =
        endXMLStructure_VH.set(segment, 0L, value)
    
    val resolveExternalEntity_VH: VarHandle = layout.varHandle(groupElement("resolveExternalEntity"))
    
    @Suppress("UNCHECKED_CAST")
    fun resolveExternalEntity(segment: MemorySegment): MemorySegment =
        resolveExternalEntity_VH.get(segment, 0L) as MemorySegment
    
    fun resolveExternalEntity(segment: MemorySegment, value: MemorySegment) =
        resolveExternalEntity_VH.set(segment, 0L, value)
    
    val handleError_VH: VarHandle = layout.varHandle(groupElement("handleError"))
    
    @Suppress("UNCHECKED_CAST")
    fun handleError(segment: MemorySegment): MemorySegment =
        handleError_VH.get(segment, 0L) as MemorySegment
    
    fun handleError(segment: MemorySegment, value: MemorySegment) =
        handleError_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef ((Void)*((Void)*))* CFXMLParserRetainCallBack;}
 */
typealias CFXMLParserRetainCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Void)*))* CFXMLParserReleaseCallBack;}
 */
typealias CFXMLParserReleaseCallBack = MemorySegment

/**
 * {@snippet lang=c : typedef ((Declared(__CFString))*((Void)*))* CFXMLParserCopyDescriptionCallBack;}
 */
typealias CFXMLParserCopyDescriptionCallBack = MemorySegment

/**
 * {@snippet lang=c : STRUCT CFXMLParserContext
 */
class CFXMLParserContext {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("version"),
            ValueLayout.ADDRESS.withName("info"),
            ValueLayout.ADDRESS.withName("retain"),
            ValueLayout.ADDRESS.withName("release"),
            ValueLayout.ADDRESS.withName("copyDescription")
        ).withName("CFXMLParserContext")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Long =
        version_VH.get(segment, 0L) as Long
    
    fun version(segment: MemorySegment, value: Long) =
        version_VH.set(segment, 0L, value)
    
    val info_VH: VarHandle = layout.varHandle(groupElement("info"))
    
    @Suppress("UNCHECKED_CAST")
    fun info(segment: MemorySegment): MemorySegment =
        info_VH.get(segment, 0L) as MemorySegment
    
    fun info(segment: MemorySegment, value: MemorySegment) =
        info_VH.set(segment, 0L, value)
    
    val retain_VH: VarHandle = layout.varHandle(groupElement("retain"))
    
    @Suppress("UNCHECKED_CAST")
    fun retain(segment: MemorySegment): MemorySegment =
        retain_VH.get(segment, 0L) as MemorySegment
    
    fun retain(segment: MemorySegment, value: MemorySegment) =
        retain_VH.set(segment, 0L, value)
    
    val release_VH: VarHandle = layout.varHandle(groupElement("release"))
    
    @Suppress("UNCHECKED_CAST")
    fun release(segment: MemorySegment): MemorySegment =
        release_VH.get(segment, 0L) as MemorySegment
    
    fun release(segment: MemorySegment, value: MemorySegment) =
        release_VH.set(segment, 0L, value)
    
    val copyDescription_VH: VarHandle = layout.varHandle(groupElement("copyDescription"))
    
    @Suppress("UNCHECKED_CAST")
    fun copyDescription(segment: MemorySegment): MemorySegment =
        copyDescription_VH.get(segment, 0L) as MemorySegment
    
    fun copyDescription(segment: MemorySegment, value: MemorySegment) =
        copyDescription_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSExceptionName;}
 */
typealias NSExceptionName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSRunLoopMode;}
 */
typealias NSRunLoopMode = MemorySegment

/**
 * {@snippet lang=c : typedef (Void)* NSComparator;}
 */
typealias NSComparator = MemorySegment

/**
 * {@snippet lang=c : STRUCT NSFastEnumerationState
 */
class NSFastEnumerationState {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("state"),
            ValueLayout.ADDRESS.withName("itemsPtr"),
            ValueLayout.ADDRESS.withName("mutationsPtr"),
            MemoryLayout.sequenceLayout(5, ValueLayout.JAVA_LONG).withName("extra")
        ).withName("NSFastEnumerationState")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val state_VH: VarHandle = layout.varHandle(groupElement("state"))
    
    @Suppress("UNCHECKED_CAST")
    fun state(segment: MemorySegment): Long =
        state_VH.get(segment, 0L) as Long
    
    fun state(segment: MemorySegment, value: Long) =
        state_VH.set(segment, 0L, value)
    
    val itemsPtr_VH: VarHandle = layout.varHandle(groupElement("itemsPtr"))
    
    @Suppress("UNCHECKED_CAST")
    fun itemsPtr(segment: MemorySegment): MemorySegment =
        itemsPtr_VH.get(segment, 0L) as MemorySegment
    
    fun itemsPtr(segment: MemorySegment, value: MemorySegment) =
        itemsPtr_VH.set(segment, 0L, value)
    
    val mutationsPtr_VH: VarHandle = layout.varHandle(groupElement("mutationsPtr"))
    
    @Suppress("UNCHECKED_CAST")
    fun mutationsPtr(segment: MemorySegment): MemorySegment =
        mutationsPtr_VH.get(segment, 0L) as MemorySegment
    
    fun mutationsPtr(segment: MemorySegment, value: MemorySegment) =
        mutationsPtr_VH.set(segment, 0L, value)
    
    fun extra(segment: MemorySegment): MemorySegment =
        segment.asSlice(layout.byteOffset(groupElement("extra")), layout.select(groupElement("extra")).byteSize())
} // End class

/**
 * {@snippet lang=c : STRUCT _NSRange
 */
class _NSRange {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("location"),
            ValueLayout.JAVA_LONG.withName("length")
        ).withName("_NSRange")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val location_VH: VarHandle = layout.varHandle(groupElement("location"))
    
    @Suppress("UNCHECKED_CAST")
    fun location(segment: MemorySegment): Long =
        location_VH.get(segment, 0L) as Long
    
    fun location(segment: MemorySegment, value: Long) =
        location_VH.set(segment, 0L, value)
    
    val length_VH: VarHandle = layout.varHandle(groupElement("length"))
    
    @Suppress("UNCHECKED_CAST")
    fun length(segment: MemorySegment): Long =
        length_VH.get(segment, 0L) as Long
    
    fun length(segment: MemorySegment, value: Long) =
        length_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef Declared(_NSRange) NSRange;}
 */
typealias NSRange = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(_NSRange))* NSRangePointer;}
 */
typealias NSRangePointer = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Short unichar;}
 */
typealias unichar = Short

/**
 * {@snippet lang=c : typedef (Void)* NSItemProviderCompletionHandler;}
 */
typealias NSItemProviderCompletionHandler = MemorySegment

/**
 * {@snippet lang=c : typedef (Void)* NSItemProviderLoadHandler;}
 */
typealias NSItemProviderLoadHandler = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Long NSStringEncoding;}
 */
typealias NSStringEncoding = Long

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSStringTransform;}
 */
typealias NSStringTransform = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSStringEncodingDetectionOptionsKey;}
 */
typealias NSStringEncodingDetectionOptionsKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSProgressKind;}
 */
typealias NSProgressKind = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSProgressUserInfoKey;}
 */
typealias NSProgressUserInfoKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSProgressFileOperationKind;}
 */
typealias NSProgressFileOperationKind = MemorySegment

/**
 * {@snippet lang=c : typedef (Void)* NSProgressUnpublishingHandler;}
 */
typealias NSProgressUnpublishingHandler = MemorySegment

/**
 * {@snippet lang=c : typedef (Void)* NSProgressPublishingHandler;}
 */
typealias NSProgressPublishingHandler = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSNotificationName;}
 */
typealias NSNotificationName = MemorySegment

/**
 * {@snippet lang=c : STRUCT NSSwappedFloat
 */
class NSSwappedFloat {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("v")
        ).withName("NSSwappedFloat")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val v_VH: VarHandle = layout.varHandle(groupElement("v"))
    
    @Suppress("UNCHECKED_CAST")
    fun v(segment: MemorySegment): Int =
        v_VH.get(segment, 0L) as Int
    
    fun v(segment: MemorySegment, value: Int) =
        v_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT NSSwappedDouble
 */
class NSSwappedDouble {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("v")
        ).withName("NSSwappedDouble")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val v_VH: VarHandle = layout.varHandle(groupElement("v"))
    
    @Suppress("UNCHECKED_CAST")
    fun v(segment: MemorySegment): Long =
        v_VH.get(segment, 0L) as Long
    
    fun v(segment: MemorySegment, value: Long) =
        v_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef Double NSTimeInterval;}
 */
typealias NSTimeInterval = Double

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSCalendarIdentifier;}
 */
typealias NSCalendarIdentifier = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSAttributedStringKey;}
 */
typealias NSAttributedStringKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSAttributedStringFormattingContextKey;}
 */
typealias NSAttributedStringFormattingContextKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSLocaleKey;}
 */
typealias NSLocaleKey = MemorySegment

/**
 * {@snippet lang=c : STRUCT NSDecimal
 */
class NSDecimal {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            MemoryLayout.sequenceLayout(8, ValueLayout.JAVA_SHORT).withName("_mantissa")
        ).withName("NSDecimal")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    fun _mantissa(segment: MemorySegment): MemorySegment =
        segment.asSlice(layout.byteOffset(groupElement("_mantissa")), layout.select(groupElement("_mantissa")).byteSize())
} // End class

/**
 * {@snippet lang=c : typedef Void(typedef NSException = (Void)*) NSUncaughtExceptionHandler;}
 */
typealias NSUncaughtExceptionHandler = Unit

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSErrorDomain;}
 */
typealias NSErrorDomain = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSErrorUserInfoKey;}
 */
typealias NSErrorUserInfoKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSURLResourceKey;}
 */
typealias NSURLResourceKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSURLFileResourceType;}
 */
typealias NSURLFileResourceType = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSURLThumbnailDictionaryItem;}
 */
typealias NSURLThumbnailDictionaryItem = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSURLFileProtectionType;}
 */
typealias NSURLFileProtectionType = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSURLUbiquitousItemDownloadingStatus;}
 */
typealias NSURLUbiquitousItemDownloadingStatus = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSURLUbiquitousSharedItemRole;}
 */
typealias NSURLUbiquitousSharedItemRole = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSURLUbiquitousSharedItemPermissions;}
 */
typealias NSURLUbiquitousSharedItemPermissions = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Long NSURLBookmarkFileCreationOptions;}
 */
typealias NSURLBookmarkFileCreationOptions = Long

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSFileAttributeKey;}
 */
typealias NSFileAttributeKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSFileAttributeType;}
 */
typealias NSFileAttributeType = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSFileProtectionType;}
 */
typealias NSFileProtectionType = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSFileProviderServiceName;}
 */
typealias NSFileProviderServiceName = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Long NSHashTableOptions;}
 */
typealias NSHashTableOptions = Long

/**
 * {@snippet lang=c : STRUCT NSHashEnumerator
 */
class NSHashEnumerator {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("_pi"),
            ValueLayout.JAVA_LONG.withName("_si"),
            ValueLayout.ADDRESS.withName("_bs")
        ).withName("NSHashEnumerator")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val _pi_VH: VarHandle = layout.varHandle(groupElement("_pi"))
    
    @Suppress("UNCHECKED_CAST")
    fun _pi(segment: MemorySegment): Long =
        _pi_VH.get(segment, 0L) as Long
    
    fun _pi(segment: MemorySegment, value: Long) =
        _pi_VH.set(segment, 0L, value)
    
    val _si_VH: VarHandle = layout.varHandle(groupElement("_si"))
    
    @Suppress("UNCHECKED_CAST")
    fun _si(segment: MemorySegment): Long =
        _si_VH.get(segment, 0L) as Long
    
    fun _si(segment: MemorySegment, value: Long) =
        _si_VH.set(segment, 0L, value)
    
    val _bs_VH: VarHandle = layout.varHandle(groupElement("_bs"))
    
    @Suppress("UNCHECKED_CAST")
    fun _bs(segment: MemorySegment): MemorySegment =
        _bs_VH.get(segment, 0L) as MemorySegment
    
    fun _bs(segment: MemorySegment, value: MemorySegment) =
        _bs_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT NSHashTableCallBacks
 */
class NSHashTableCallBacks {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("hash"),
            ValueLayout.ADDRESS.withName("isEqual"),
            ValueLayout.ADDRESS.withName("retain"),
            ValueLayout.ADDRESS.withName("release"),
            ValueLayout.ADDRESS.withName("describe")
        ).withName("NSHashTableCallBacks")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val hash_VH: VarHandle = layout.varHandle(groupElement("hash"))
    
    @Suppress("UNCHECKED_CAST")
    fun hash(segment: MemorySegment): MemorySegment =
        hash_VH.get(segment, 0L) as MemorySegment
    
    fun hash(segment: MemorySegment, value: MemorySegment) =
        hash_VH.set(segment, 0L, value)
    
    val isEqual_VH: VarHandle = layout.varHandle(groupElement("isEqual"))
    
    @Suppress("UNCHECKED_CAST")
    fun isEqual(segment: MemorySegment): MemorySegment =
        isEqual_VH.get(segment, 0L) as MemorySegment
    
    fun isEqual(segment: MemorySegment, value: MemorySegment) =
        isEqual_VH.set(segment, 0L, value)
    
    val retain_VH: VarHandle = layout.varHandle(groupElement("retain"))
    
    @Suppress("UNCHECKED_CAST")
    fun retain(segment: MemorySegment): MemorySegment =
        retain_VH.get(segment, 0L) as MemorySegment
    
    fun retain(segment: MemorySegment, value: MemorySegment) =
        retain_VH.set(segment, 0L, value)
    
    val release_VH: VarHandle = layout.varHandle(groupElement("release"))
    
    @Suppress("UNCHECKED_CAST")
    fun release(segment: MemorySegment): MemorySegment =
        release_VH.get(segment, 0L) as MemorySegment
    
    fun release(segment: MemorySegment, value: MemorySegment) =
        release_VH.set(segment, 0L, value)
    
    val describe_VH: VarHandle = layout.varHandle(groupElement("describe"))
    
    @Suppress("UNCHECKED_CAST")
    fun describe(segment: MemorySegment): MemorySegment =
        describe_VH.get(segment, 0L) as MemorySegment
    
    fun describe(segment: MemorySegment, value: MemorySegment) =
        describe_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSHTTPCookiePropertyKey;}
 */
typealias NSHTTPCookiePropertyKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSHTTPCookieStringPolicy;}
 */
typealias NSHTTPCookieStringPolicy = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSKeyValueOperator;}
 */
typealias NSKeyValueOperator = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSKeyValueChangeKey;}
 */
typealias NSKeyValueChangeKey = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Long NSPropertyListWriteOptions;}
 */
typealias NSPropertyListWriteOptions = Long

/**
 * {@snippet lang=c : typedef (Declared(__IOSurface))* IOSurfaceRef;}
 */
typealias IOSurfaceRef = MemorySegment

/**
 * {@snippet lang=c : typedef Declared(CGPoint) NSPoint;}
 */
typealias NSPoint = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGPoint))* NSPointPointer;}
 */
typealias NSPointPointer = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGPoint))* NSPointArray;}
 */
typealias NSPointArray = MemorySegment

/**
 * {@snippet lang=c : typedef Declared(CGSize) NSSize;}
 */
typealias NSSize = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGSize))* NSSizePointer;}
 */
typealias NSSizePointer = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGSize))* NSSizeArray;}
 */
typealias NSSizeArray = MemorySegment

/**
 * {@snippet lang=c : typedef Declared(CGRect) NSRect;}
 */
typealias NSRect = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGRect))* NSRectPointer;}
 */
typealias NSRectPointer = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGRect))* NSRectArray;}
 */
typealias NSRectArray = MemorySegment

/**
 * {@snippet lang=c : STRUCT NSEdgeInsets
 */
class NSEdgeInsets {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_DOUBLE.withName("top"),
            ValueLayout.JAVA_DOUBLE.withName("left"),
            ValueLayout.JAVA_DOUBLE.withName("bottom"),
            ValueLayout.JAVA_DOUBLE.withName("right")
        ).withName("NSEdgeInsets")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val top_VH: VarHandle = layout.varHandle(groupElement("top"))
    
    @Suppress("UNCHECKED_CAST")
    fun top(segment: MemorySegment): Double =
        top_VH.get(segment, 0L) as Double
    
    fun top(segment: MemorySegment, value: Double) =
        top_VH.set(segment, 0L, value)
    
    val left_VH: VarHandle = layout.varHandle(groupElement("left"))
    
    @Suppress("UNCHECKED_CAST")
    fun left(segment: MemorySegment): Double =
        left_VH.get(segment, 0L) as Double
    
    fun left(segment: MemorySegment, value: Double) =
        left_VH.set(segment, 0L, value)
    
    val bottom_VH: VarHandle = layout.varHandle(groupElement("bottom"))
    
    @Suppress("UNCHECKED_CAST")
    fun bottom(segment: MemorySegment): Double =
        bottom_VH.get(segment, 0L) as Double
    
    fun bottom(segment: MemorySegment, value: Double) =
        bottom_VH.set(segment, 0L, value)
    
    val right_VH: VarHandle = layout.varHandle(groupElement("right"))
    
    @Suppress("UNCHECKED_CAST")
    fun right(segment: MemorySegment): Double =
        right_VH.get(segment, 0L) as Double
    
    fun right(segment: MemorySegment, value: Double) =
        right_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef UNSIGNED = Long NSMapTableOptions;}
 */
typealias NSMapTableOptions = Long

/**
 * {@snippet lang=c : STRUCT NSMapEnumerator
 */
class NSMapEnumerator {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("_pi"),
            ValueLayout.JAVA_LONG.withName("_si"),
            ValueLayout.ADDRESS.withName("_bs")
        ).withName("NSMapEnumerator")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val _pi_VH: VarHandle = layout.varHandle(groupElement("_pi"))
    
    @Suppress("UNCHECKED_CAST")
    fun _pi(segment: MemorySegment): Long =
        _pi_VH.get(segment, 0L) as Long
    
    fun _pi(segment: MemorySegment, value: Long) =
        _pi_VH.set(segment, 0L, value)
    
    val _si_VH: VarHandle = layout.varHandle(groupElement("_si"))
    
    @Suppress("UNCHECKED_CAST")
    fun _si(segment: MemorySegment): Long =
        _si_VH.get(segment, 0L) as Long
    
    fun _si(segment: MemorySegment, value: Long) =
        _si_VH.set(segment, 0L, value)
    
    val _bs_VH: VarHandle = layout.varHandle(groupElement("_bs"))
    
    @Suppress("UNCHECKED_CAST")
    fun _bs(segment: MemorySegment): MemorySegment =
        _bs_VH.get(segment, 0L) as MemorySegment
    
    fun _bs(segment: MemorySegment, value: MemorySegment) =
        _bs_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT NSMapTableKeyCallBacks
 */
class NSMapTableKeyCallBacks {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("hash"),
            ValueLayout.ADDRESS.withName("isEqual"),
            ValueLayout.ADDRESS.withName("retain"),
            ValueLayout.ADDRESS.withName("release"),
            ValueLayout.ADDRESS.withName("describe"),
            ValueLayout.ADDRESS.withName("notAKeyMarker")
        ).withName("NSMapTableKeyCallBacks")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val hash_VH: VarHandle = layout.varHandle(groupElement("hash"))
    
    @Suppress("UNCHECKED_CAST")
    fun hash(segment: MemorySegment): MemorySegment =
        hash_VH.get(segment, 0L) as MemorySegment
    
    fun hash(segment: MemorySegment, value: MemorySegment) =
        hash_VH.set(segment, 0L, value)
    
    val isEqual_VH: VarHandle = layout.varHandle(groupElement("isEqual"))
    
    @Suppress("UNCHECKED_CAST")
    fun isEqual(segment: MemorySegment): MemorySegment =
        isEqual_VH.get(segment, 0L) as MemorySegment
    
    fun isEqual(segment: MemorySegment, value: MemorySegment) =
        isEqual_VH.set(segment, 0L, value)
    
    val retain_VH: VarHandle = layout.varHandle(groupElement("retain"))
    
    @Suppress("UNCHECKED_CAST")
    fun retain(segment: MemorySegment): MemorySegment =
        retain_VH.get(segment, 0L) as MemorySegment
    
    fun retain(segment: MemorySegment, value: MemorySegment) =
        retain_VH.set(segment, 0L, value)
    
    val release_VH: VarHandle = layout.varHandle(groupElement("release"))
    
    @Suppress("UNCHECKED_CAST")
    fun release(segment: MemorySegment): MemorySegment =
        release_VH.get(segment, 0L) as MemorySegment
    
    fun release(segment: MemorySegment, value: MemorySegment) =
        release_VH.set(segment, 0L, value)
    
    val describe_VH: VarHandle = layout.varHandle(groupElement("describe"))
    
    @Suppress("UNCHECKED_CAST")
    fun describe(segment: MemorySegment): MemorySegment =
        describe_VH.get(segment, 0L) as MemorySegment
    
    fun describe(segment: MemorySegment, value: MemorySegment) =
        describe_VH.set(segment, 0L, value)
    
    val notAKeyMarker_VH: VarHandle = layout.varHandle(groupElement("notAKeyMarker"))
    
    @Suppress("UNCHECKED_CAST")
    fun notAKeyMarker(segment: MemorySegment): MemorySegment =
        notAKeyMarker_VH.get(segment, 0L) as MemorySegment
    
    fun notAKeyMarker(segment: MemorySegment, value: MemorySegment) =
        notAKeyMarker_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT NSMapTableValueCallBacks
 */
class NSMapTableValueCallBacks {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("retain"),
            ValueLayout.ADDRESS.withName("release"),
            ValueLayout.ADDRESS.withName("describe")
        ).withName("NSMapTableValueCallBacks")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val retain_VH: VarHandle = layout.varHandle(groupElement("retain"))
    
    @Suppress("UNCHECKED_CAST")
    fun retain(segment: MemorySegment): MemorySegment =
        retain_VH.get(segment, 0L) as MemorySegment
    
    fun retain(segment: MemorySegment, value: MemorySegment) =
        retain_VH.set(segment, 0L, value)
    
    val release_VH: VarHandle = layout.varHandle(groupElement("release"))
    
    @Suppress("UNCHECKED_CAST")
    fun release(segment: MemorySegment): MemorySegment =
        release_VH.get(segment, 0L) as MemorySegment
    
    fun release(segment: MemorySegment, value: MemorySegment) =
        release_VH.set(segment, 0L, value)
    
    val describe_VH: VarHandle = layout.varHandle(groupElement("describe"))
    
    @Suppress("UNCHECKED_CAST")
    fun describe(segment: MemorySegment): MemorySegment =
        describe_VH.get(segment, 0L) as MemorySegment
    
    fun describe(segment: MemorySegment, value: MemorySegment) =
        describe_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef Int NSSocketNativeHandle;}
 */
typealias NSSocketNativeHandle = Int

/**
 * {@snippet lang=c : STRUCT NSOperatingSystemVersion
 */
class NSOperatingSystemVersion {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("majorVersion"),
            ValueLayout.JAVA_LONG.withName("minorVersion"),
            ValueLayout.JAVA_LONG.withName("patchVersion")
        ).withName("NSOperatingSystemVersion")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val majorVersion_VH: VarHandle = layout.varHandle(groupElement("majorVersion"))
    
    @Suppress("UNCHECKED_CAST")
    fun majorVersion(segment: MemorySegment): Long =
        majorVersion_VH.get(segment, 0L) as Long
    
    fun majorVersion(segment: MemorySegment, value: Long) =
        majorVersion_VH.set(segment, 0L, value)
    
    val minorVersion_VH: VarHandle = layout.varHandle(groupElement("minorVersion"))
    
    @Suppress("UNCHECKED_CAST")
    fun minorVersion(segment: MemorySegment): Long =
        minorVersion_VH.get(segment, 0L) as Long
    
    fun minorVersion(segment: MemorySegment, value: Long) =
        minorVersion_VH.set(segment, 0L, value)
    
    val patchVersion_VH: VarHandle = layout.varHandle(groupElement("patchVersion"))
    
    @Suppress("UNCHECKED_CAST")
    fun patchVersion(segment: MemorySegment): Long =
        patchVersion_VH.get(segment, 0L) as Long
    
    fun patchVersion(segment: MemorySegment, value: Long) =
        patchVersion_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong NSTextCheckingTypes;}
 */
typealias NSTextCheckingTypes = Long

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSTextCheckingKey;}
 */
typealias NSTextCheckingKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSStreamPropertyKey;}
 */
typealias NSStreamPropertyKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSStreamSocketSecurityLevel;}
 */
typealias NSStreamSocketSecurityLevel = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSStreamSOCKSProxyConfiguration;}
 */
typealias NSStreamSOCKSProxyConfiguration = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSStreamSOCKSProxyVersion;}
 */
typealias NSStreamSOCKSProxyVersion = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSStreamNetworkServiceTypeValue;}
 */
typealias NSStreamNetworkServiceTypeValue = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Int SecKeychainAttrType;}
 */
typealias SecKeychainAttrType = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int SecKeychainStatus;}
 */
typealias SecKeychainStatus = Int

/**
 * {@snippet lang=c : typedef LongLong sint64;}
 */
typealias sint64 = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong uint64;}
 */
typealias uint64 = Long

/**
 * {@snippet lang=c : typedef Int sint32;}
 */
typealias sint32 = Int

/**
 * {@snippet lang=c : typedef Short sint16;}
 */
typealias sint16 = Short

/**
 * {@snippet lang=c : typedef SIGNED = Char sint8;}
 */
typealias sint8 = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = Int uint32;}
 */
typealias uint32 = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short uint16;}
 */
typealias uint16 = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Char uint8;}
 */
typealias uint8 = Byte

/**
 * {@snippet lang=c : typedef Long CSSM_INTPTR;}
 */
typealias CSSM_INTPTR = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long CSSM_SIZE;}
 */
typealias CSSM_SIZE = Long

/**
 * {@snippet lang=c : typedef Long CSSM_HANDLE;}
 */
typealias CSSM_HANDLE = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong CSSM_LONG_HANDLE;}
 */
typealias CSSM_LONG_HANDLE = Long

/**
 * {@snippet lang=c : typedef Long CSSM_MODULE_HANDLE;}
 */
typealias CSSM_MODULE_HANDLE = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong CSSM_CC_HANDLE;}
 */
typealias CSSM_CC_HANDLE = Long

/**
 * {@snippet lang=c : typedef Long CSSM_CSP_HANDLE;}
 */
typealias CSSM_CSP_HANDLE = Long

/**
 * {@snippet lang=c : typedef Long CSSM_TP_HANDLE;}
 */
typealias CSSM_TP_HANDLE = Long

/**
 * {@snippet lang=c : typedef Long CSSM_AC_HANDLE;}
 */
typealias CSSM_AC_HANDLE = Long

/**
 * {@snippet lang=c : typedef Long CSSM_CL_HANDLE;}
 */
typealias CSSM_CL_HANDLE = Long

/**
 * {@snippet lang=c : typedef Long CSSM_DL_HANDLE;}
 */
typealias CSSM_DL_HANDLE = Long

/**
 * {@snippet lang=c : typedef Long CSSM_DB_HANDLE;}
 */
typealias CSSM_DB_HANDLE = Long

/**
 * {@snippet lang=c : typedef Int CSSM_BOOL;}
 */
typealias CSSM_BOOL = Int

/**
 * {@snippet lang=c : typedef Int CSSM_RETURN;}
 */
typealias CSSM_RETURN = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_BITMASK;}
 */
typealias CSSM_BITMASK = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_KEY_HIERARCHY;}
 */
typealias CSSM_KEY_HIERARCHY = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_PVC_MODE;}
 */
typealias CSSM_PVC_MODE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_PRIVILEGE_SCOPE;}
 */
typealias CSSM_PRIVILEGE_SCOPE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_SERVICE_MASK;}
 */
typealias CSSM_SERVICE_MASK = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_SERVICE_TYPE;}
 */
typealias CSSM_SERVICE_TYPE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_MODULE_EVENT;}
 */
typealias CSSM_MODULE_EVENT = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_ATTACH_FLAGS;}
 */
typealias CSSM_ATTACH_FLAGS = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong CSSM_PRIVILEGE;}
 */
typealias CSSM_PRIVILEGE = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong CSSM_USEE_TAG;}
 */
typealias CSSM_USEE_TAG = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_NET_ADDRESS_TYPE;}
 */
typealias CSSM_NET_ADDRESS_TYPE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_NET_PROTOCOL;}
 */
typealias CSSM_NET_PROTOCOL = Int

/**
 * {@snippet lang=c : typedef Int CSSM_WORDID_TYPE;}
 */
typealias CSSM_WORDID_TYPE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_LIST_ELEMENT_TYPE;}
 */
typealias CSSM_LIST_ELEMENT_TYPE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_LIST_TYPE;}
 */
typealias CSSM_LIST_TYPE = Int

/**
 * {@snippet lang=c : typedef Int CSSM_SAMPLE_TYPE;}
 */
typealias CSSM_SAMPLE_TYPE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_CERT_TYPE;}
 */
typealias CSSM_CERT_TYPE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_CERT_ENCODING;}
 */
typealias CSSM_CERT_ENCODING = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_CERT_PARSE_FORMAT;}
 */
typealias CSSM_CERT_PARSE_FORMAT = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_CERTGROUP_TYPE;}
 */
typealias CSSM_CERTGROUP_TYPE = Int

/**
 * {@snippet lang=c : typedef Int CSSM_ACL_SUBJECT_TYPE;}
 */
typealias CSSM_ACL_SUBJECT_TYPE = Int

/**
 * {@snippet lang=c : typedef Int CSSM_ACL_AUTHORIZATION_TAG;}
 */
typealias CSSM_ACL_AUTHORIZATION_TAG = Int

/**
 * {@snippet lang=c : typedef Long CSSM_ACL_HANDLE;}
 */
typealias CSSM_ACL_HANDLE = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_ACL_EDIT_MODE;}
 */
typealias CSSM_ACL_EDIT_MODE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_HEADERVERSION;}
 */
typealias CSSM_HEADERVERSION = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_KEYBLOB_TYPE;}
 */
typealias CSSM_KEYBLOB_TYPE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_KEYBLOB_FORMAT;}
 */
typealias CSSM_KEYBLOB_FORMAT = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_KEYCLASS;}
 */
typealias CSSM_KEYCLASS = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_KEYATTR_FLAGS;}
 */
typealias CSSM_KEYATTR_FLAGS = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_KEYUSE;}
 */
typealias CSSM_KEYUSE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_ALGORITHMS;}
 */
typealias CSSM_ALGORITHMS = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_ENCRYPT_MODE;}
 */
typealias CSSM_ENCRYPT_MODE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_CSPTYPE;}
 */
typealias CSSM_CSPTYPE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_CONTEXT_TYPE;}
 */
typealias CSSM_CONTEXT_TYPE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_ATTRIBUTE_TYPE;}
 */
typealias CSSM_ATTRIBUTE_TYPE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_PADDING;}
 */
typealias CSSM_PADDING = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_KEY_TYPE;}
 */
typealias CSSM_KEY_TYPE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_SC_FLAGS;}
 */
typealias CSSM_SC_FLAGS = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_CSP_READER_FLAGS;}
 */
typealias CSSM_CSP_READER_FLAGS = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_CSP_FLAGS;}
 */
typealias CSSM_CSP_FLAGS = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_PKCS_OAEP_MGF;}
 */
typealias CSSM_PKCS_OAEP_MGF = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_PKCS_OAEP_PSOURCE;}
 */
typealias CSSM_PKCS_OAEP_PSOURCE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_PKCS5_PBKDF2_PRF;}
 */
typealias CSSM_PKCS5_PBKDF2_PRF = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_TP_AUTHORITY_REQUEST_TYPE;}
 */
typealias CSSM_TP_AUTHORITY_REQUEST_TYPE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_TP_SERVICES;}
 */
typealias CSSM_TP_SERVICES = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_TP_ACTION;}
 */
typealias CSSM_TP_ACTION = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_TP_STOP_ON;}
 */
typealias CSSM_TP_STOP_ON = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_CRL_PARSE_FORMAT;}
 */
typealias CSSM_CRL_PARSE_FORMAT = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_CRL_TYPE;}
 */
typealias CSSM_CRL_TYPE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_CRL_ENCODING;}
 */
typealias CSSM_CRL_ENCODING = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_CRLGROUP_TYPE;}
 */
typealias CSSM_CRLGROUP_TYPE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_EVIDENCE_FORM;}
 */
typealias CSSM_EVIDENCE_FORM = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_TP_CONFIRM_STATUS;}
 */
typealias CSSM_TP_CONFIRM_STATUS = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_TP_CERTISSUE_STATUS;}
 */
typealias CSSM_TP_CERTISSUE_STATUS = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_TP_CERTCHANGE_ACTION;}
 */
typealias CSSM_TP_CERTCHANGE_ACTION = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_TP_CERTCHANGE_REASON;}
 */
typealias CSSM_TP_CERTCHANGE_REASON = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_TP_CERTCHANGE_STATUS;}
 */
typealias CSSM_TP_CERTCHANGE_STATUS = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_TP_CERTVERIFY_STATUS;}
 */
typealias CSSM_TP_CERTVERIFY_STATUS = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_TP_CERTNOTARIZE_STATUS;}
 */
typealias CSSM_TP_CERTNOTARIZE_STATUS = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_TP_CERTRECLAIM_STATUS;}
 */
typealias CSSM_TP_CERTRECLAIM_STATUS = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_TP_CRLISSUE_STATUS;}
 */
typealias CSSM_TP_CRLISSUE_STATUS = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_TP_FORM_TYPE;}
 */
typealias CSSM_TP_FORM_TYPE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_CL_TEMPLATE_TYPE;}
 */
typealias CSSM_CL_TEMPLATE_TYPE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_CERT_BUNDLE_TYPE;}
 */
typealias CSSM_CERT_BUNDLE_TYPE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_CERT_BUNDLE_ENCODING;}
 */
typealias CSSM_CERT_BUNDLE_ENCODING = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_DB_ATTRIBUTE_NAME_FORMAT;}
 */
typealias CSSM_DB_ATTRIBUTE_NAME_FORMAT = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_DB_ATTRIBUTE_FORMAT;}
 */
typealias CSSM_DB_ATTRIBUTE_FORMAT = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_DB_RECORDTYPE;}
 */
typealias CSSM_DB_RECORDTYPE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_DB_INDEX_TYPE;}
 */
typealias CSSM_DB_INDEX_TYPE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_DB_INDEXED_DATA_LOCATION;}
 */
typealias CSSM_DB_INDEXED_DATA_LOCATION = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_DB_ACCESS_TYPE;}
 */
typealias CSSM_DB_ACCESS_TYPE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_DB_MODIFY_MODE;}
 */
typealias CSSM_DB_MODIFY_MODE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_DB_OPERATOR;}
 */
typealias CSSM_DB_OPERATOR = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_DB_CONJUNCTIVE;}
 */
typealias CSSM_DB_CONJUNCTIVE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_QUERY_FLAGS;}
 */
typealias CSSM_QUERY_FLAGS = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_DLTYPE;}
 */
typealias CSSM_DLTYPE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_DB_RETRIEVAL_MODES;}
 */
typealias CSSM_DB_RETRIEVAL_MODES = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Char CSSM_BER_TAG;}
 */
typealias CSSM_BER_TAG = Byte

/**
 * {@snippet lang=c : typedef Int CSSM_X509_OPTION;}
 */
typealias CSSM_X509_OPTION = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int SecAccessOwnerType;}
 */
typealias SecAccessOwnerType = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short CE_KeyUsage;}
 */
typealias CE_KeyUsage = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CE_CrlReason;}
 */
typealias CE_CrlReason = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short CE_NetscapeCertType;}
 */
typealias CE_NetscapeCertType = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Char CE_CrlDistReasonFlags;}
 */
typealias CE_CrlDistReasonFlags = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CE_CrlNumber;}
 */
typealias CE_CrlNumber = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CE_DeltaCrl;}
 */
typealias CE_DeltaCrl = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CE_InhibitAnyPolicy;}
 */
typealias CE_InhibitAnyPolicy = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_ACL_PREAUTH_TRACKING_STATE;}
 */
typealias CSSM_ACL_PREAUTH_TRACKING_STATE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_APPLE_TP_CRL_OPT_FLAGS;}
 */
typealias CSSM_APPLE_TP_CRL_OPT_FLAGS = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_APPLE_TP_ACTION_FLAGS;}
 */
typealias CSSM_APPLE_TP_ACTION_FLAGS = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_TP_APPLE_CERT_STATUS;}
 */
typealias CSSM_TP_APPLE_CERT_STATUS = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short SSLCipherSuite;}
 */
typealias SSLCipherSuite = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int SecuritySessionId;}
 */
typealias SecuritySessionId = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_MANAGER_EVENT_TYPES;}
 */
typealias CSSM_MANAGER_EVENT_TYPES = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_CONTEXT_EVENT;}
 */
typealias CSSM_CONTEXT_EVENT = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_KRSP_HANDLE;}
 */
typealias CSSM_KRSP_HANDLE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_KR_POLICY_TYPE;}
 */
typealias CSSM_KR_POLICY_TYPE = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSSM_KR_POLICY_FLAGS;}
 */
typealias CSSM_KR_POLICY_FLAGS = Int

/**
 * {@snippet lang=c : typedef Long MDS_HANDLE;}
 */
typealias MDS_HANDLE = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int SecGuestRef;}
 */
typealias SecGuestRef = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int au_id_t;}
 */
typealias au_id_t = Int

/**
 * {@snippet lang=c : typedef Int au_asid_t;}
 */
typealias au_asid_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short au_event_t;}
 */
typealias au_event_t = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short au_emod_t;}
 */
typealias au_emod_t = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int au_class_t;}
 */
typealias au_class_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong au_asflgs_t;}
 */
typealias au_asflgs_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Char au_ctlmode_t;}
 */
typealias au_ctlmode_t = Byte

/**
 * {@snippet lang=c : typedef Long xpc_activity_state_t;}
 */
typealias xpc_activity_state_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long pointer_t;}
 */
typealias pointer_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long vm_address_t;}
 */
typealias vm_address_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong addr64_t;}
 */
typealias addr64_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int reg64_t;}
 */
typealias reg64_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ppnum_t;}
 */
typealias ppnum_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int vm_map_t;}
 */
typealias vm_map_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int vm_map_read_t;}
 */
typealias vm_map_read_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int vm_map_inspect_t;}
 */
typealias vm_map_inspect_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int upl_t;}
 */
typealias upl_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int vm_named_entry_t;}
 */
typealias vm_named_entry_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong vm_object_offset_t;}
 */
typealias vm_object_offset_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong vm_object_size_t;}
 */
typealias vm_object_size_t = Long

/**
 * {@snippet lang=c : typedef Int host_flavor_t;}
 */
typealias host_flavor_t = Int

/**
 * {@snippet lang=c : typedef Int vm_prot_t;}
 */
typealias vm_prot_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int vm_sync_t;}
 */
typealias vm_sync_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong memory_object_offset_t;}
 */
typealias memory_object_offset_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong memory_object_size_t;}
 */
typealias memory_object_size_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int memory_object_cluster_size_t;}
 */
typealias memory_object_cluster_size_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong vm_object_id_t;}
 */
typealias vm_object_id_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int memory_object_t;}
 */
typealias memory_object_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int memory_object_control_t;}
 */
typealias memory_object_control_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int memory_object_name_t;}
 */
typealias memory_object_name_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int memory_object_default_t;}
 */
typealias memory_object_default_t = Int

/**
 * {@snippet lang=c : typedef Int memory_object_copy_strategy_t;}
 */
typealias memory_object_copy_strategy_t = Int

/**
 * {@snippet lang=c : typedef Int memory_object_return_t;}
 */
typealias memory_object_return_t = Int

/**
 * {@snippet lang=c : typedef Int memory_object_flavor_t;}
 */
typealias memory_object_flavor_t = Int

/**
 * {@snippet lang=c : typedef Int thread_state_flavor_t;}
 */
typealias thread_state_flavor_t = Int

/**
 * {@snippet lang=c : typedef Int exception_type_t;}
 */
typealias exception_type_t = Int

/**
 * {@snippet lang=c : typedef Int exception_data_type_t;}
 */
typealias exception_data_type_t = Int

/**
 * {@snippet lang=c : typedef LongLong mach_exception_data_type_t;}
 */
typealias mach_exception_data_type_t = Long

/**
 * {@snippet lang=c : typedef Int exception_behavior_t;}
 */
typealias exception_behavior_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int exception_mask_t;}
 */
typealias exception_mask_t = Int

/**
 * {@snippet lang=c : typedef LongLong mach_exception_code_t;}
 */
typealias mach_exception_code_t = Long

/**
 * {@snippet lang=c : typedef LongLong mach_exception_subcode_t;}
 */
typealias mach_exception_subcode_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_voucher_t;}
 */
typealias mach_voucher_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_voucher_name_t;}
 */
typealias mach_voucher_name_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ipc_voucher_t;}
 */
typealias ipc_voucher_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_voucher_selector_t;}
 */
typealias mach_voucher_selector_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_voucher_attr_key_t;}
 */
typealias mach_voucher_attr_key_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_voucher_attr_content_size_t;}
 */
typealias mach_voucher_attr_content_size_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_voucher_attr_command_t;}
 */
typealias mach_voucher_attr_command_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_voucher_attr_recipe_command_t;}
 */
typealias mach_voucher_attr_recipe_command_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_voucher_attr_recipe_size_t;}
 */
typealias mach_voucher_attr_recipe_size_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_voucher_attr_raw_recipe_size_t;}
 */
typealias mach_voucher_attr_raw_recipe_size_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_voucher_attr_raw_recipe_array_size_t;}
 */
typealias mach_voucher_attr_raw_recipe_array_size_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_voucher_attr_manager_t;}
 */
typealias mach_voucher_attr_manager_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_voucher_attr_control_t;}
 */
typealias mach_voucher_attr_control_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ipc_voucher_attr_manager_t;}
 */
typealias ipc_voucher_attr_manager_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ipc_voucher_attr_control_t;}
 */
typealias ipc_voucher_attr_control_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong mach_voucher_attr_value_handle_t;}
 */
typealias mach_voucher_attr_value_handle_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_voucher_attr_value_handle_array_size_t;}
 */
typealias mach_voucher_attr_value_handle_array_size_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_voucher_attr_value_reference_t;}
 */
typealias mach_voucher_attr_value_reference_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_voucher_attr_value_flags_t;}
 */
typealias mach_voucher_attr_value_flags_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_voucher_attr_control_flags_t;}
 */
typealias mach_voucher_attr_control_flags_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_voucher_attr_importance_refs;}
 */
typealias mach_voucher_attr_importance_refs = Int

/**
 * {@snippet lang=c : typedef Int processor_flavor_t;}
 */
typealias processor_flavor_t = Int

/**
 * {@snippet lang=c : typedef Int processor_set_flavor_t;}
 */
typealias processor_set_flavor_t = Int

/**
 * {@snippet lang=c : typedef Int policy_t;}
 */
typealias policy_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int task_flavor_t;}
 */
typealias task_flavor_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int task_exc_guard_behavior_t;}
 */
typealias task_exc_guard_behavior_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int task_corpse_forking_behavior_t;}
 */
typealias task_corpse_forking_behavior_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int task_inspect_flavor_t;}
 */
typealias task_inspect_flavor_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int task_policy_flavor_t;}
 */
typealias task_policy_flavor_t = Int

/**
 * {@snippet lang=c : typedef Int task_latency_qos_t;}
 */
typealias task_latency_qos_t = Int

/**
 * {@snippet lang=c : typedef Int task_throughput_qos_t;}
 */
typealias task_throughput_qos_t = Int

/**
 * {@snippet lang=c : typedef Int task_special_port_t;}
 */
typealias task_special_port_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int thread_flavor_t;}
 */
typealias thread_flavor_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int thread_policy_flavor_t;}
 */
typealias thread_policy_flavor_t = Int

/**
 * {@snippet lang=c : typedef Int thread_latency_qos_t;}
 */
typealias thread_latency_qos_t = Int

/**
 * {@snippet lang=c : typedef Int thread_throughput_qos_t;}
 */
typealias thread_throughput_qos_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int vm_machine_attribute_t;}
 */
typealias vm_machine_attribute_t = Int

/**
 * {@snippet lang=c : typedef Int vm_machine_attribute_val_t;}
 */
typealias vm_machine_attribute_val_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int vm_inherit_t;}
 */
typealias vm_inherit_t = Int

/**
 * {@snippet lang=c : typedef Int vm_purgable_t;}
 */
typealias vm_purgable_t = Int

/**
 * {@snippet lang=c : typedef Int vm_behavior_t;}
 */
typealias vm_behavior_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int vm32_object_id_t;}
 */
typealias vm32_object_id_t = Int

/**
 * {@snippet lang=c : typedef Int vm_region_flavor_t;}
 */
typealias vm_region_flavor_t = Int

/**
 * {@snippet lang=c : typedef Int vm_page_info_flavor_t;}
 */
typealias vm_page_info_flavor_t = Int

/**
 * {@snippet lang=c : typedef Int kmod_t;}
 */
typealias kmod_t = Int

/**
 * {@snippet lang=c : typedef Int kmod_control_flavor_t;}
 */
typealias kmod_control_flavor_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int task_t;}
 */
typealias task_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int task_name_t;}
 */
typealias task_name_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int task_policy_set_t;}
 */
typealias task_policy_set_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int task_policy_get_t;}
 */
typealias task_policy_get_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int task_inspect_t;}
 */
typealias task_inspect_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int task_read_t;}
 */
typealias task_read_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int task_suspension_token_t;}
 */
typealias task_suspension_token_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int thread_t;}
 */
typealias thread_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int thread_act_t;}
 */
typealias thread_act_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int thread_inspect_t;}
 */
typealias thread_inspect_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int thread_read_t;}
 */
typealias thread_read_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ipc_space_t;}
 */
typealias ipc_space_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ipc_space_read_t;}
 */
typealias ipc_space_read_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ipc_space_inspect_t;}
 */
typealias ipc_space_inspect_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int coalition_t;}
 */
typealias coalition_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int host_t;}
 */
typealias host_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int host_priv_t;}
 */
typealias host_priv_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int host_security_t;}
 */
typealias host_security_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int processor_t;}
 */
typealias processor_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int processor_set_t;}
 */
typealias processor_set_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int processor_set_control_t;}
 */
typealias processor_set_control_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int semaphore_t;}
 */
typealias semaphore_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int lock_set_t;}
 */
typealias lock_set_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ledger_t;}
 */
typealias ledger_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int alarm_t;}
 */
typealias alarm_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int clock_serv_t;}
 */
typealias clock_serv_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int clock_ctrl_t;}
 */
typealias clock_ctrl_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int arcade_register_t;}
 */
typealias arcade_register_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ipc_eventlink_t;}
 */
typealias ipc_eventlink_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int task_id_token_t;}
 */
typealias task_id_token_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int kcdata_object_t;}
 */
typealias kcdata_object_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int processor_set_name_t;}
 */
typealias processor_set_name_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int clock_reply_t;}
 */
typealias clock_reply_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int bootstrap_t;}
 */
typealias bootstrap_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mem_entry_name_port_t;}
 */
typealias mem_entry_name_port_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int exception_handler_t;}
 */
typealias exception_handler_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int vm_task_entry_t;}
 */
typealias vm_task_entry_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int io_main_t;}
 */
typealias io_main_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int UNDServerRef;}
 */
typealias UNDServerRef = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_eventlink_t;}
 */
typealias mach_eventlink_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int task_port_t;}
 */
typealias task_port_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int thread_port_t;}
 */
typealias thread_port_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ipc_space_port_t;}
 */
typealias ipc_space_port_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int host_name_t;}
 */
typealias host_name_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int host_name_port_t;}
 */
typealias host_name_port_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int processor_set_port_t;}
 */
typealias processor_set_port_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int processor_set_name_port_t;}
 */
typealias processor_set_name_port_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int processor_set_control_port_t;}
 */
typealias processor_set_control_port_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int processor_port_t;}
 */
typealias processor_port_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int thread_act_port_t;}
 */
typealias thread_act_port_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int semaphore_port_t;}
 */
typealias semaphore_port_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int lock_set_port_t;}
 */
typealias lock_set_port_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ledger_port_t;}
 */
typealias ledger_port_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int alarm_port_t;}
 */
typealias alarm_port_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int clock_serv_port_t;}
 */
typealias clock_serv_port_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int clock_ctrl_port_t;}
 */
typealias clock_ctrl_port_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int exception_port_t;}
 */
typealias exception_port_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_task_flavor_t;}
 */
typealias mach_task_flavor_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int mach_thread_flavor_t;}
 */
typealias mach_thread_flavor_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ledger_item_t;}
 */
typealias ledger_item_t = Int

/**
 * {@snippet lang=c : typedef LongLong ledger_amount_t;}
 */
typealias ledger_amount_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int notify_port_t;}
 */
typealias notify_port_t = Int

/**
 * {@snippet lang=c : typedef Int sync_policy_t;}
 */
typealias sync_policy_t = Int

/**
 * {@snippet lang=c : typedef Int mach_error_t;}
 */
typealias mach_error_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int routine_arg_type;}
 */
typealias routine_arg_type = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int routine_arg_offset;}
 */
typealias routine_arg_offset = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int routine_arg_size;}
 */
typealias routine_arg_size = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Char DERByte;}
 */
typealias DERByte = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = Short DERShort;}
 */
typealias DERShort = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int DERInt;}
 */
typealias DERInt = Int

/**
 * {@snippet lang=c : typedef Int DERSignedInt;}
 */
typealias DERSignedInt = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong DERLong;}
 */
typealias DERLong = Long

/**
 * {@snippet lang=c : typedef LongLong DERSignedLong;}
 */
typealias DERSignedLong = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long DERSize;}
 */
typealias DERSize = Long

/**
 * {@snippet lang=c : typedef Bool DERBool;}
 */
typealias DERBool = Boolean

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong DERTag;}
 */
typealias DERTag = Long

/**
 * {@snippet lang=c : typedef SIGNED = Char DateOrders;}
 */
typealias DateOrders = Byte

/**
 * {@snippet lang=c : typedef SIGNED = Char TokenResults;}
 */
typealias TokenResults = Byte

/**
 * {@snippet lang=c : typedef Short ScriptTokenType;}
 */
typealias ScriptTokenType = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int TextEncodingBase;}
 */
typealias TextEncodingBase = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int TextEncodingVariant;}
 */
typealias TextEncodingVariant = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int TextEncodingFormat;}
 */
typealias TextEncodingFormat = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int TextEncoding;}
 */
typealias TextEncoding = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int TextEncodingNameSelector;}
 */
typealias TextEncodingNameSelector = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Long UniCharArrayOffset;}
 */
typealias UniCharArrayOffset = Long

/**
 * {@snippet lang=c : typedef Int UCCharPropertyType;}
 */
typealias UCCharPropertyType = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int UCCharPropertyValue;}
 */
typealias UCCharPropertyValue = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short CallingConventionType;}
 */
typealias CallingConventionType = Short

/**
 * {@snippet lang=c : typedef SIGNED = Char ISAType;}
 */
typealias ISAType = Byte

/**
 * {@snippet lang=c : typedef SIGNED = Char RTAType;}
 */
typealias RTAType = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = Short registerSelectorType;}
 */
typealias registerSelectorType = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Long ProcInfoType;}
 */
typealias ProcInfoType = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Short RoutineFlagsType;}
 */
typealias RoutineFlagsType = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Char RDFlagsType;}
 */
typealias RDFlagsType = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CollectionTag;}
 */
typealias CollectionTag = Int

/**
 * {@snippet lang=c : typedef Int CSDiskSpaceRecoveryOptions;}
 */
typealias CSDiskSpaceRecoveryOptions = Int

/**
 * {@snippet lang=c : typedef Short ToggleResults;}
 */
typealias ToggleResults = Short

/**
 * {@snippet lang=c : typedef SIGNED = Char LongDateField;}
 */
typealias LongDateField = Byte

/**
 * {@snippet lang=c : typedef SIGNED = Char DateForm;}
 */
typealias DateForm = Byte

/**
 * {@snippet lang=c : typedef Short StringToDateStatus;}
 */
typealias StringToDateStatus = Short

/**
 * {@snippet lang=c : typedef Short String2DateStatus;}
 */
typealias String2DateStatus = Short

/**
 * {@snippet lang=c : typedef LongLong LongDateTime;}
 */
typealias LongDateTime = Long

/**
 * {@snippet lang=c : typedef SIGNED = Char DateDelta;}
 */
typealias DateDelta = Byte

/**
 * {@snippet lang=c : typedef SIGNED = Char QTypes;}
 */
typealias QTypes = Byte

/**
 * {@snippet lang=c : typedef Int IOReturn;}
 */
typealias IOReturn = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int UInt;}
 */
typealias UInt = Int

/**
 * {@snippet lang=c : typedef Int SInt;}
 */
typealias SInt = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int IOOptionBits;}
 */
typealias IOOptionBits = Int

/**
 * {@snippet lang=c : typedef Int IOFixed;}
 */
typealias IOFixed = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int IOVersion;}
 */
typealias IOVersion = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int IOItemCount;}
 */
typealias IOItemCount = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int IOCacheMode;}
 */
typealias IOCacheMode = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int IOByteCount32;}
 */
typealias IOByteCount32 = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong IOByteCount64;}
 */
typealias IOByteCount64 = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int IOPhysicalAddress32;}
 */
typealias IOPhysicalAddress32 = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong IOPhysicalAddress64;}
 */
typealias IOPhysicalAddress64 = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int IOPhysicalLength32;}
 */
typealias IOPhysicalLength32 = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong IOPhysicalLength64;}
 */
typealias IOPhysicalLength64 = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong IOVirtualAddress;}
 */
typealias IOVirtualAddress = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong IOByteCount;}
 */
typealias IOByteCount = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong IOLogicalAddress;}
 */
typealias IOLogicalAddress = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong IOPhysicalAddress;}
 */
typealias IOPhysicalAddress = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong IOPhysicalLength;}
 */
typealias IOPhysicalLength = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int IOAlignment;}
 */
typealias IOAlignment = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int io_object_t;}
 */
typealias io_object_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong io_user_scalar_t;}
 */
typealias io_user_scalar_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong io_user_reference_t;}
 */
typealias io_user_reference_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int io_connect_t;}
 */
typealias io_connect_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int io_enumerator_t;}
 */
typealias io_enumerator_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int io_ident_t;}
 */
typealias io_ident_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int io_iterator_t;}
 */
typealias io_iterator_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int io_registry_entry_t;}
 */
typealias io_registry_entry_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int io_service_t;}
 */
typealias io_service_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int uext_object_t;}
 */
typealias uext_object_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int IODeviceNumber;}
 */
typealias IODeviceNumber = Int

/**
 * {@snippet lang=c : typedef Short FSVolumeRefNum;}
 */
typealias FSVolumeRefNum = Short

/**
 * {@snippet lang=c : typedef Int FSIORefNum;}
 */
typealias FSIORefNum = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int FSCatalogInfoBitmap;}
 */
typealias FSCatalogInfoBitmap = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int FSIteratorFlags;}
 */
typealias FSIteratorFlags = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short FSAllocationFlags;}
 */
typealias FSAllocationFlags = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Char FSForkInfoFlags;}
 */
typealias FSForkInfoFlags = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = Int FSVolumeInfoBitmap;}
 */
typealias FSVolumeInfoBitmap = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int VolumeType;}
 */
typealias VolumeType = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int FNMessage;}
 */
typealias FNMessage = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int FSMountStatus;}
 */
typealias FSMountStatus = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int FSEjectStatus;}
 */
typealias FSEjectStatus = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int FSUnmountStatus;}
 */
typealias FSUnmountStatus = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int FSFileOperationStage;}
 */
typealias FSFileOperationStage = Int

/**
 * {@snippet lang=c : typedef Short ResID;}
 */
typealias ResID = Short

/**
 * {@snippet lang=c : typedef Short ResAttributes;}
 */
typealias ResAttributes = Short

/**
 * {@snippet lang=c : typedef Short ResFileAttributes;}
 */
typealias ResFileAttributes = Short

/**
 * {@snippet lang=c : typedef Short ResourceCount;}
 */
typealias ResourceCount = Short

/**
 * {@snippet lang=c : typedef Short ResourceIndex;}
 */
typealias ResourceIndex = Short

/**
 * {@snippet lang=c : typedef Int ResFileRefNum;}
 */
typealias ResFileRefNum = Int

/**
 * {@snippet lang=c : typedef Short RsrcChainLocation;}
 */
typealias RsrcChainLocation = Short

/**
 * {@snippet lang=c : typedef Int ComponentResult;}
 */
typealias ComponentResult = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CSComponentsThreadMode;}
 */
typealias CSComponentsThreadMode = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int MPOpaqueIDClass;}
 */
typealias MPOpaqueIDClass = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int MPTaskOptions;}
 */
typealias MPTaskOptions = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Long TaskStorageIndex;}
 */
typealias TaskStorageIndex = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long MPSemaphoreCount;}
 */
typealias MPSemaphoreCount = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int MPTaskWeight;}
 */
typealias MPTaskWeight = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int MPEventFlags;}
 */
typealias MPEventFlags = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int MPExceptionKind;}
 */
typealias MPExceptionKind = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int MPTaskStateKind;}
 */
typealias MPTaskStateKind = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int MPPageSizeClass;}
 */
typealias MPPageSizeClass = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int MPDebuggerLevel;}
 */
typealias MPDebuggerLevel = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Char MPRemoteContext;}
 */
typealias MPRemoteContext = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = Int FSAliasInfoBitmap;}
 */
typealias FSAliasInfoBitmap = Int

/**
 * {@snippet lang=c : typedef Short AliasInfoType;}
 */
typealias AliasInfoType = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int LocalePartMask;}
 */
typealias LocalePartMask = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int LocaleOperationClass;}
 */
typealias LocaleOperationClass = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int LocaleOperationVariant;}
 */
typealias LocaleOperationVariant = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int LocaleNameMask;}
 */
typealias LocaleNameMask = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Long MemoryReferenceKind;}
 */
typealias MemoryReferenceKind = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long ExceptionKind;}
 */
typealias ExceptionKind = Long

/**
 * {@snippet lang=c : typedef Short FormatStatus;}
 */
typealias FormatStatus = Short

/**
 * {@snippet lang=c : typedef SIGNED = Char FormatClass;}
 */
typealias FormatClass = Byte

/**
 * {@snippet lang=c : typedef SIGNED = Char FormatResultType;}
 */
typealias FormatResultType = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = Short UCKeyOutput;}
 */
typealias UCKeyOutput = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short UCKeyCharSeq;}
 */
typealias UCKeyCharSeq = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int UCCollateOptions;}
 */
typealias UCCollateOptions = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int UCCollationValue;}
 */
typealias UCCollationValue = Int

/**
 * {@snippet lang=c : typedef Int UCTypeSelectCompareResult;}
 */
typealias UCTypeSelectCompareResult = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short UCTSWalkDirection;}
 */
typealias UCTSWalkDirection = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short UCTypeSelectOptions;}
 */
typealias UCTypeSelectOptions = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int UCTextBreakType;}
 */
typealias UCTextBreakType = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int UCTextBreakOptions;}
 */
typealias UCTextBreakOptions = Int

/**
 * {@snippet lang=c : typedef Short relop;}
 */
typealias relop = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short fexcept_t;}
 */
typealias fexcept_t = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int TECPluginSignature;}
 */
typealias TECPluginSignature = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int TECPluginVersion;}
 */
typealias TECPluginVersion = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int TECPluginSig;}
 */
typealias TECPluginSig = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int TECInternetNameUsageMask;}
 */
typealias TECInternetNameUsageMask = Int

/**
 * {@snippet lang=c : typedef Int UnicodeMapVersion;}
 */
typealias UnicodeMapVersion = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short ThreadState;}
 */
typealias ThreadState = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ThreadStyle;}
 */
typealias ThreadStyle = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Long ThreadID;}
 */
typealias ThreadID = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ThreadOptions;}
 */
typealias ThreadOptions = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int FolderDescFlags;}
 */
typealias FolderDescFlags = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int FolderClass;}
 */
typealias FolderClass = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int FolderType;}
 */
typealias FolderType = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int FolderLocation;}
 */
typealias FolderLocation = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int RoutingFlags;}
 */
typealias RoutingFlags = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short PEFRelocChunk;}
 */
typealias PEFRelocChunk = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int HFSCatalogNodeID;}
 */
typealias HFSCatalogNodeID = Int

/**
 * {@snippet lang=c : typedef Short MarkerIdType;}
 */
typealias MarkerIdType = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int DescType;}
 */
typealias DescType = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int AEKeyword;}
 */
typealias AEKeyword = Int

/**
 * {@snippet lang=c : typedef Short AEReturnID;}
 */
typealias AEReturnID = Short

/**
 * {@snippet lang=c : typedef Int AETransactionID;}
 */
typealias AETransactionID = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int AEEventClass;}
 */
typealias AEEventClass = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int AEEventID;}
 */
typealias AEEventID = Int

/**
 * {@snippet lang=c : typedef SIGNED = Char AEArrayType;}
 */
typealias AEArrayType = Byte

/**
 * {@snippet lang=c : typedef Short AESendPriority;}
 */
typealias AESendPriority = Short

/**
 * {@snippet lang=c : typedef Int AESendMode;}
 */
typealias AESendMode = Int

/**
 * {@snippet lang=c : typedef SIGNED = Char AEEventSource;}
 */
typealias AEEventSource = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = Int AEBuildErrorCode;}
 */
typealias AEBuildErrorCode = Int

/**
 * {@snippet lang=c : typedef Long CFNetDiagnosticStatus;}
 */
typealias CFNetDiagnosticStatus = Long

/**
 * {@snippet lang=c : typedef Long CSIdentityClass;}
 */
typealias CSIdentityClass = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long CSIdentityFlags;}
 */
typealias CSIdentityFlags = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long CSIdentityQueryFlags;}
 */
typealias CSIdentityQueryFlags = Long

/**
 * {@snippet lang=c : typedef Long CSIdentityQueryStringComparisonMethod;}
 */
typealias CSIdentityQueryStringComparisonMethod = Long

/**
 * {@snippet lang=c : typedef Long CSIdentityQueryEvent;}
 */
typealias CSIdentityQueryEvent = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int KCAttrType;}
 */
typealias KCAttrType = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int KCStatus;}
 */
typealias KCStatus = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short KCEvent;}
 */
typealias KCEvent = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short KCEventMask;}
 */
typealias KCEventMask = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int KCItemClass;}
 */
typealias KCItemClass = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int KCItemAttr;}
 */
typealias KCItemAttr = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int KCAuthType;}
 */
typealias KCAuthType = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int KCProtocolType;}
 */
typealias KCProtocolType = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int KCCertAddOptions;}
 */
typealias KCCertAddOptions = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short KCVerifyStopOn;}
 */
typealias KCVerifyStopOn = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int KCCertSearchOptions;}
 */
typealias KCCertSearchOptions = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int IconServicesUsageFlags;}
 */
typealias IconServicesUsageFlags = Int

/**
 * {@snippet lang=c : typedef Long SKDocumentID;}
 */
typealias SKDocumentID = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int SKSearchOptions;}
 */
typealias SKSearchOptions = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int FSEventStreamCreateFlags;}
 */
typealias FSEventStreamCreateFlags = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int FSEventStreamEventFlags;}
 */
typealias FSEventStreamEventFlags = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong FSEventStreamEventId;}
 */
typealias FSEventStreamEventId = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int LSSharedFileListResolutionFlags;}
 */
typealias LSSharedFileListResolutionFlags = Int

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSValueTransformerName;}
 */
typealias NSValueTransformerName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSLinguisticTagScheme;}
 */
typealias NSLinguisticTagScheme = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSLinguisticTag;}
 */
typealias NSLinguisticTag = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSUndoManagerUserInfoKey;}
 */
typealias NSUndoManagerUserInfoKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSUserActivityPersistentIdentifier;}
 */
typealias NSUserActivityPersistentIdentifier = MemorySegment

/**
 * {@snippet lang=c : STRUCT NSAffineTransformStruct
 */
class NSAffineTransformStruct {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_DOUBLE.withName("m11"),
            ValueLayout.JAVA_DOUBLE.withName("m12"),
            ValueLayout.JAVA_DOUBLE.withName("m21"),
            ValueLayout.JAVA_DOUBLE.withName("m22"),
            ValueLayout.JAVA_DOUBLE.withName("tX"),
            ValueLayout.JAVA_DOUBLE.withName("tY")
        ).withName("NSAffineTransformStruct")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val m11_VH: VarHandle = layout.varHandle(groupElement("m11"))
    
    @Suppress("UNCHECKED_CAST")
    fun m11(segment: MemorySegment): Double =
        m11_VH.get(segment, 0L) as Double
    
    fun m11(segment: MemorySegment, value: Double) =
        m11_VH.set(segment, 0L, value)
    
    val m12_VH: VarHandle = layout.varHandle(groupElement("m12"))
    
    @Suppress("UNCHECKED_CAST")
    fun m12(segment: MemorySegment): Double =
        m12_VH.get(segment, 0L) as Double
    
    fun m12(segment: MemorySegment, value: Double) =
        m12_VH.set(segment, 0L, value)
    
    val m21_VH: VarHandle = layout.varHandle(groupElement("m21"))
    
    @Suppress("UNCHECKED_CAST")
    fun m21(segment: MemorySegment): Double =
        m21_VH.get(segment, 0L) as Double
    
    fun m21(segment: MemorySegment, value: Double) =
        m21_VH.set(segment, 0L, value)
    
    val m22_VH: VarHandle = layout.varHandle(groupElement("m22"))
    
    @Suppress("UNCHECKED_CAST")
    fun m22(segment: MemorySegment): Double =
        m22_VH.get(segment, 0L) as Double
    
    fun m22(segment: MemorySegment, value: Double) =
        m22_VH.set(segment, 0L, value)
    
    val tX_VH: VarHandle = layout.varHandle(groupElement("tX"))
    
    @Suppress("UNCHECKED_CAST")
    fun tX(segment: MemorySegment): Double =
        tX_VH.get(segment, 0L) as Double
    
    fun tX(segment: MemorySegment, value: Double) =
        tX_VH.set(segment, 0L, value)
    
    val tY_VH: VarHandle = layout.varHandle(groupElement("tY"))
    
    @Suppress("UNCHECKED_CAST")
    fun tY(segment: MemorySegment): Double =
        tY_VH.get(segment, 0L) as Double
    
    fun tY(segment: MemorySegment, value: Double) =
        tY_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Void)* NSBackgroundActivityCompletionHandler;}
 */
typealias NSBackgroundActivityCompletionHandler = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSDistributedNotificationCenterType;}
 */
typealias NSDistributedNotificationCenterType = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__NSAppleEventManagerSuspension))* NSAppleEventManagerSuspensionID;}
 */
typealias NSAppleEventManagerSuspensionID = MemorySegment

/**
 * {@snippet lang=c : typedef (Void)* NSUserScriptTaskCompletionHandler;}
 */
typealias NSUserScriptTaskCompletionHandler = MemorySegment

/**
 * {@snippet lang=c : typedef (Void)* NSUserUnixTaskCompletionHandler;}
 */
typealias NSUserUnixTaskCompletionHandler = MemorySegment

/**
 * {@snippet lang=c : typedef (Void)* NSUserAppleScriptTaskCompletionHandler;}
 */
typealias NSUserAppleScriptTaskCompletionHandler = MemorySegment

/**
 * {@snippet lang=c : typedef (Void)* NSUserAutomatorTaskCompletionHandler;}
 */
typealias NSUserAutomatorTaskCompletionHandler = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSColorSpaceName;}
 */
typealias NSColorSpaceName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSDeviceDescriptionKey;}
 */
typealias NSDeviceDescriptionKey = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGContext))* CGContextRef;}
 */
typealias CGContextRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGColor))* CGColorRef;}
 */
typealias CGColorRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGColorSpace))* CGColorSpaceRef;}
 */
typealias CGColorSpaceRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGDataProvider))* CGDataProviderRef;}
 */
typealias CGDataProviderRef = MemorySegment

/**
 * {@snippet lang=c : typedef (UNSIGNED = Long((Void)*,(Void)*,UNSIGNED = Long))* CGDataProviderGetBytesCallback;}
 */
typealias CGDataProviderGetBytesCallback = MemorySegment

/**
 * {@snippet lang=c : typedef (LongLong((Void)*,LongLong))* CGDataProviderSkipForwardCallback;}
 */
typealias CGDataProviderSkipForwardCallback = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Void)*))* CGDataProviderRewindCallback;}
 */
typealias CGDataProviderRewindCallback = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Void)*))* CGDataProviderReleaseInfoCallback;}
 */
typealias CGDataProviderReleaseInfoCallback = MemorySegment

/**
 * {@snippet lang=c : STRUCT CGDataProviderSequentialCallbacks
 */
class CGDataProviderSequentialCallbacks {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("version"),
            ValueLayout.ADDRESS.withName("getBytes"),
            ValueLayout.ADDRESS.withName("skipForward"),
            ValueLayout.ADDRESS.withName("rewind"),
            ValueLayout.ADDRESS.withName("releaseInfo")
        ).withName("CGDataProviderSequentialCallbacks")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Int =
        version_VH.get(segment, 0L) as Int
    
    fun version(segment: MemorySegment, value: Int) =
        version_VH.set(segment, 0L, value)
    
    val getBytes_VH: VarHandle = layout.varHandle(groupElement("getBytes"))
    
    @Suppress("UNCHECKED_CAST")
    fun getBytes(segment: MemorySegment): MemorySegment =
        getBytes_VH.get(segment, 0L) as MemorySegment
    
    fun getBytes(segment: MemorySegment, value: MemorySegment) =
        getBytes_VH.set(segment, 0L, value)
    
    val skipForward_VH: VarHandle = layout.varHandle(groupElement("skipForward"))
    
    @Suppress("UNCHECKED_CAST")
    fun skipForward(segment: MemorySegment): MemorySegment =
        skipForward_VH.get(segment, 0L) as MemorySegment
    
    fun skipForward(segment: MemorySegment, value: MemorySegment) =
        skipForward_VH.set(segment, 0L, value)
    
    val rewind_VH: VarHandle = layout.varHandle(groupElement("rewind"))
    
    @Suppress("UNCHECKED_CAST")
    fun rewind(segment: MemorySegment): MemorySegment =
        rewind_VH.get(segment, 0L) as MemorySegment
    
    fun rewind(segment: MemorySegment, value: MemorySegment) =
        rewind_VH.set(segment, 0L, value)
    
    val releaseInfo_VH: VarHandle = layout.varHandle(groupElement("releaseInfo"))
    
    @Suppress("UNCHECKED_CAST")
    fun releaseInfo(segment: MemorySegment): MemorySegment =
        releaseInfo_VH.get(segment, 0L) as MemorySegment
    
    fun releaseInfo(segment: MemorySegment, value: MemorySegment) =
        releaseInfo_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef ((Void)*((Void)*))* CGDataProviderGetBytePointerCallback;}
 */
typealias CGDataProviderGetBytePointerCallback = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Void)*,(Void)*))* CGDataProviderReleaseBytePointerCallback;}
 */
typealias CGDataProviderReleaseBytePointerCallback = MemorySegment

/**
 * {@snippet lang=c : typedef (UNSIGNED = Long((Void)*,(Void)*,LongLong,UNSIGNED = Long))* CGDataProviderGetBytesAtPositionCallback;}
 */
typealias CGDataProviderGetBytesAtPositionCallback = MemorySegment

/**
 * {@snippet lang=c : STRUCT CGDataProviderDirectCallbacks
 */
class CGDataProviderDirectCallbacks {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("version"),
            ValueLayout.ADDRESS.withName("getBytePointer"),
            ValueLayout.ADDRESS.withName("releaseBytePointer"),
            ValueLayout.ADDRESS.withName("getBytesAtPosition"),
            ValueLayout.ADDRESS.withName("releaseInfo")
        ).withName("CGDataProviderDirectCallbacks")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Int =
        version_VH.get(segment, 0L) as Int
    
    fun version(segment: MemorySegment, value: Int) =
        version_VH.set(segment, 0L, value)
    
    val getBytePointer_VH: VarHandle = layout.varHandle(groupElement("getBytePointer"))
    
    @Suppress("UNCHECKED_CAST")
    fun getBytePointer(segment: MemorySegment): MemorySegment =
        getBytePointer_VH.get(segment, 0L) as MemorySegment
    
    fun getBytePointer(segment: MemorySegment, value: MemorySegment) =
        getBytePointer_VH.set(segment, 0L, value)
    
    val releaseBytePointer_VH: VarHandle = layout.varHandle(groupElement("releaseBytePointer"))
    
    @Suppress("UNCHECKED_CAST")
    fun releaseBytePointer(segment: MemorySegment): MemorySegment =
        releaseBytePointer_VH.get(segment, 0L) as MemorySegment
    
    fun releaseBytePointer(segment: MemorySegment, value: MemorySegment) =
        releaseBytePointer_VH.set(segment, 0L, value)
    
    val getBytesAtPosition_VH: VarHandle = layout.varHandle(groupElement("getBytesAtPosition"))
    
    @Suppress("UNCHECKED_CAST")
    fun getBytesAtPosition(segment: MemorySegment): MemorySegment =
        getBytesAtPosition_VH.get(segment, 0L) as MemorySegment
    
    fun getBytesAtPosition(segment: MemorySegment, value: MemorySegment) =
        getBytesAtPosition_VH.set(segment, 0L, value)
    
    val releaseInfo_VH: VarHandle = layout.varHandle(groupElement("releaseInfo"))
    
    @Suppress("UNCHECKED_CAST")
    fun releaseInfo(segment: MemorySegment): MemorySegment =
        releaseInfo_VH.get(segment, 0L) as MemorySegment
    
    fun releaseInfo(segment: MemorySegment, value: MemorySegment) =
        releaseInfo_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Void((Void)*,(Void)*,UNSIGNED = Long))* CGDataProviderReleaseDataCallback;}
 */
typealias CGDataProviderReleaseDataCallback = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(ColorSyncProfile))* ColorSyncProfileRef;}
 */
typealias ColorSyncProfileRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGPattern))* CGPatternRef;}
 */
typealias CGPatternRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Void)*,(Declared(CGContext))*))* CGPatternDrawPatternCallback;}
 */
typealias CGPatternDrawPatternCallback = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Void)*))* CGPatternReleaseInfoCallback;}
 */
typealias CGPatternReleaseInfoCallback = MemorySegment

/**
 * {@snippet lang=c : STRUCT CGPatternCallbacks
 */
class CGPatternCallbacks {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("version"),
            ValueLayout.ADDRESS.withName("drawPattern"),
            ValueLayout.ADDRESS.withName("releaseInfo")
        ).withName("CGPatternCallbacks")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Int =
        version_VH.get(segment, 0L) as Int
    
    fun version(segment: MemorySegment, value: Int) =
        version_VH.set(segment, 0L, value)
    
    val drawPattern_VH: VarHandle = layout.varHandle(groupElement("drawPattern"))
    
    @Suppress("UNCHECKED_CAST")
    fun drawPattern(segment: MemorySegment): MemorySegment =
        drawPattern_VH.get(segment, 0L) as MemorySegment
    
    fun drawPattern(segment: MemorySegment, value: MemorySegment) =
        drawPattern_VH.set(segment, 0L, value)
    
    val releaseInfo_VH: VarHandle = layout.varHandle(groupElement("releaseInfo"))
    
    @Suppress("UNCHECKED_CAST")
    fun releaseInfo(segment: MemorySegment): MemorySegment =
        releaseInfo_VH.get(segment, 0L) as MemorySegment
    
    fun releaseInfo(segment: MemorySegment, value: MemorySegment) =
        releaseInfo_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Declared(CGFont))* CGFontRef;}
 */
typealias CGFontRef = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Short CGFontIndex;}
 */
typealias CGFontIndex = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short CGGlyph;}
 */
typealias CGGlyph = Short

/**
 * {@snippet lang=c : typedef (Declared(CGGradient))* CGGradientRef;}
 */
typealias CGGradientRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGImage))* CGImageRef;}
 */
typealias CGImageRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGPath))* CGMutablePathRef;}
 */
typealias CGMutablePathRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGPath))* CGPathRef;}
 */
typealias CGPathRef = MemorySegment

/**
 * {@snippet lang=c : STRUCT CGPathElement
 */
class CGPathElement {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("type"),
            ValueLayout.ADDRESS.withName("points")
        ).withName("CGPathElement")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val type_VH: VarHandle = layout.varHandle(groupElement("type"))
    
    @Suppress("UNCHECKED_CAST")
    fun type(segment: MemorySegment): MemorySegment =
        type_VH.get(segment, 0L) as MemorySegment
    
    fun type(segment: MemorySegment, value: MemorySegment) =
        type_VH.set(segment, 0L, value)
    
    val points_VH: VarHandle = layout.varHandle(groupElement("points"))
    
    @Suppress("UNCHECKED_CAST")
    fun points(segment: MemorySegment): MemorySegment =
        points_VH.get(segment, 0L) as MemorySegment
    
    fun points(segment: MemorySegment, value: MemorySegment) =
        points_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Void((Void)*,(Declared(CGPathElement))*))* CGPathApplierFunction;}
 */
typealias CGPathApplierFunction = MemorySegment

/**
 * {@snippet lang=c : typedef (Void)* CGPathApplyBlock;}
 */
typealias CGPathApplyBlock = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGPDFDocument))* CGPDFDocumentRef;}
 */
typealias CGPDFDocumentRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGPDFPage))* CGPDFPageRef;}
 */
typealias CGPDFPageRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGPDFDictionary))* CGPDFDictionaryRef;}
 */
typealias CGPDFDictionaryRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGPDFArray))* CGPDFArrayRef;}
 */
typealias CGPDFArrayRef = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Char CGPDFBoolean;}
 */
typealias CGPDFBoolean = Byte

/**
 * {@snippet lang=c : typedef Long CGPDFInteger;}
 */
typealias CGPDFInteger = Long

/**
 * {@snippet lang=c : typedef Double CGPDFReal;}
 */
typealias CGPDFReal = Double

/**
 * {@snippet lang=c : typedef (Declared(CGPDFObject))* CGPDFObjectRef;}
 */
typealias CGPDFObjectRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGPDFStream))* CGPDFStreamRef;}
 */
typealias CGPDFStreamRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGPDFString))* CGPDFStringRef;}
 */
typealias CGPDFStringRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Void)* CGPDFArrayApplierBlock;}
 */
typealias CGPDFArrayApplierBlock = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Char)*,(Declared(CGPDFObject))*,(Void)*))* CGPDFDictionaryApplierFunction;}
 */
typealias CGPDFDictionaryApplierFunction = MemorySegment

/**
 * {@snippet lang=c : typedef (Void)* CGPDFDictionaryApplierBlock;}
 */
typealias CGPDFDictionaryApplierBlock = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGShading))* CGShadingRef;}
 */
typealias CGShadingRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGFunction))* CGFunctionRef;}
 */
typealias CGFunctionRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Void)*,(Double)*,(Double)*))* CGFunctionEvaluateCallback;}
 */
typealias CGFunctionEvaluateCallback = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Void)*))* CGFunctionReleaseInfoCallback;}
 */
typealias CGFunctionReleaseInfoCallback = MemorySegment

/**
 * {@snippet lang=c : STRUCT CGFunctionCallbacks
 */
class CGFunctionCallbacks {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("version"),
            ValueLayout.ADDRESS.withName("evaluate"),
            ValueLayout.ADDRESS.withName("releaseInfo")
        ).withName("CGFunctionCallbacks")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Int =
        version_VH.get(segment, 0L) as Int
    
    fun version(segment: MemorySegment, value: Int) =
        version_VH.set(segment, 0L, value)
    
    val evaluate_VH: VarHandle = layout.varHandle(groupElement("evaluate"))
    
    @Suppress("UNCHECKED_CAST")
    fun evaluate(segment: MemorySegment): MemorySegment =
        evaluate_VH.get(segment, 0L) as MemorySegment
    
    fun evaluate(segment: MemorySegment, value: MemorySegment) =
        evaluate_VH.set(segment, 0L, value)
    
    val releaseInfo_VH: VarHandle = layout.varHandle(groupElement("releaseInfo"))
    
    @Suppress("UNCHECKED_CAST")
    fun releaseInfo(segment: MemorySegment): MemorySegment =
        releaseInfo_VH.get(segment, 0L) as MemorySegment
    
    fun releaseInfo(segment: MemorySegment, value: MemorySegment) =
        releaseInfo_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CGContentToneMappingInfo
 */
class CGContentToneMappingInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("method"),
            ValueLayout.ADDRESS.withName("options")
        ).withName("CGContentToneMappingInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val method_VH: VarHandle = layout.varHandle(groupElement("method"))
    
    @Suppress("UNCHECKED_CAST")
    fun method(segment: MemorySegment): MemorySegment =
        method_VH.get(segment, 0L) as MemorySegment
    
    fun method(segment: MemorySegment, value: MemorySegment) =
        method_VH.set(segment, 0L, value)
    
    val options_VH: VarHandle = layout.varHandle(groupElement("options"))
    
    @Suppress("UNCHECKED_CAST")
    fun options(segment: MemorySegment): MemorySegment =
        options_VH.get(segment, 0L) as MemorySegment
    
    fun options(segment: MemorySegment, value: MemorySegment) =
        options_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSGraphicsContextAttributeKey;}
 */
typealias NSGraphicsContextAttributeKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSGraphicsContextRepresentationFormatName;}
 */
typealias NSGraphicsContextRepresentationFormatName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSAccessibilityAttributeName;}
 */
typealias NSAccessibilityAttributeName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSAccessibilityParameterizedAttributeName;}
 */
typealias NSAccessibilityParameterizedAttributeName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSAccessibilityAnnotationAttributeKey;}
 */
typealias NSAccessibilityAnnotationAttributeKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSAccessibilityFontAttributeKey;}
 */
typealias NSAccessibilityFontAttributeKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSAccessibilityOrientationValue;}
 */
typealias NSAccessibilityOrientationValue = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSAccessibilitySortDirectionValue;}
 */
typealias NSAccessibilitySortDirectionValue = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSAccessibilityRulerMarkerTypeValue;}
 */
typealias NSAccessibilityRulerMarkerTypeValue = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSAccessibilityRulerUnitValue;}
 */
typealias NSAccessibilityRulerUnitValue = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSAccessibilityActionName;}
 */
typealias NSAccessibilityActionName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSAccessibilityNotificationName;}
 */
typealias NSAccessibilityNotificationName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSAccessibilityRole;}
 */
typealias NSAccessibilityRole = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSAccessibilitySubrole;}
 */
typealias NSAccessibilitySubrole = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSAccessibilityNotificationUserInfoKey;}
 */
typealias NSAccessibilityNotificationUserInfoKey = MemorySegment

/**
 * {@snippet lang=c : typedef (Void)* NSAccessibilityLoadingToken;}
 */
typealias NSAccessibilityLoadingToken = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSAccessibilitySearchKey;}
 */
typealias NSAccessibilitySearchKey = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Long NSAccessibilityDateTimeComponentsFlags;}
 */
typealias NSAccessibilityDateTimeComponentsFlags = Long

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSWorkspaceDesktopImageOptionKey;}
 */
typealias NSWorkspaceDesktopImageOptionKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSWorkspaceFileOperationName;}
 */
typealias NSWorkspaceFileOperationName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSWorkspaceLaunchConfigurationKey;}
 */
typealias NSWorkspaceLaunchConfigurationKey = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Short SFNTLookupTableFormat;}
 */
typealias SFNTLookupTableFormat = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short SFNTLookupValue;}
 */
typealias SFNTLookupValue = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short SFNTLookupOffset;}
 */
typealias SFNTLookupOffset = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int SFNTLookupKind;}
 */
typealias SFNTLookupKind = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Char STClass;}
 */
typealias STClass = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = Char STEntryIndex;}
 */
typealias STEntryIndex = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = Short STXClass;}
 */
typealias STXClass = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short STXStateIndex;}
 */
typealias STXStateIndex = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short STXEntryIndex;}
 */
typealias STXEntryIndex = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short JustPCActionType;}
 */
typealias JustPCActionType = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short JustificationFlags;}
 */
typealias JustificationFlags = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short JustPCUnconditionalAddAction;}
 */
typealias JustPCUnconditionalAddAction = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short OpbdTableFormat;}
 */
typealias OpbdTableFormat = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int MortSubtableMaskFlags;}
 */
typealias MortSubtableMaskFlags = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int MortLigatureActionEntry;}
 */
typealias MortLigatureActionEntry = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short PropCharProperties;}
 */
typealias PropCharProperties = Short

/**
 * {@snippet lang=c : typedef Short TrakValue;}
 */
typealias TrakValue = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Char KernTableFormat;}
 */
typealias KernTableFormat = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = Short KernSubtableInfo;}
 */
typealias KernSubtableInfo = Short

/**
 * {@snippet lang=c : typedef Short KernKerningValue;}
 */
typealias KernKerningValue = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short KernArrayOffset;}
 */
typealias KernArrayOffset = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int KerxSubtableCoverage;}
 */
typealias KerxSubtableCoverage = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int KerxArrayOffset;}
 */
typealias KerxArrayOffset = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int BslnBaselineClass;}
 */
typealias BslnBaselineClass = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short BslnTableFormat;}
 */
typealias BslnTableFormat = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int FMGeneration;}
 */
typealias FMGeneration = Int

/**
 * {@snippet lang=c : typedef Short FMFontFamily;}
 */
typealias FMFontFamily = Short

/**
 * {@snippet lang=c : typedef Short FMFontStyle;}
 */
typealias FMFontStyle = Short

/**
 * {@snippet lang=c : typedef Short FMFontSize;}
 */
typealias FMFontSize = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int FMFont;}
 */
typealias FMFont = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int FMFilterSelector;}
 */
typealias FMFilterSelector = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ATSOptionFlags;}
 */
typealias ATSOptionFlags = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ATSGeneration;}
 */
typealias ATSGeneration = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ATSFontContainerRef;}
 */
typealias ATSFontContainerRef = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ATSFontFamilyRef;}
 */
typealias ATSFontFamilyRef = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ATSFontRef;}
 */
typealias ATSFontRef = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short ATSGlyphRef;}
 */
typealias ATSGlyphRef = Short

/**
 * {@snippet lang=c : typedef Double ATSFontSize;}
 */
typealias ATSFontSize = Double

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ATSFontFormat;}
 */
typealias ATSFontFormat = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short ATSCurveType;}
 */
typealias ATSCurveType = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short GlyphID;}
 */
typealias GlyphID = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ATSULayoutOperationSelector;}
 */
typealias ATSULayoutOperationSelector = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ATSULayoutOperationCallbackStatus;}
 */
typealias ATSULayoutOperationCallbackStatus = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ATSLineLayoutOptions;}
 */
typealias ATSLineLayoutOptions = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ATSStyleRenderingOptions;}
 */
typealias ATSStyleRenderingOptions = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ATSGlyphInfoFlags;}
 */
typealias ATSGlyphInfoFlags = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int FontNameCode;}
 */
typealias FontNameCode = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int FontPlatformCode;}
 */
typealias FontPlatformCode = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int FontScriptCode;}
 */
typealias FontScriptCode = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int FontLanguageCode;}
 */
typealias FontLanguageCode = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ATSFontContext;}
 */
typealias ATSFontContext = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ATSFontAutoActivationSetting;}
 */
typealias ATSFontAutoActivationSetting = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ColorSyncDataLayout;}
 */
typealias ColorSyncDataLayout = Int

/**
 * {@snippet lang=c : typedef (Declared(CGRenderingBufferProvider))* CGRenderingBufferProviderRef;}
 */
typealias CGRenderingBufferProviderRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Void)*,(Void)*))* CGBitmapContextReleaseDataCallback;}
 */
typealias CGBitmapContextReleaseDataCallback = MemorySegment

/**
 * {@snippet lang=c : STRUCT CGContentInfo
 */
class CGContentInfo {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("deepestImageComponent"),
            ValueLayout.ADDRESS.withName("contentColorModels"),
            ValueLayout.JAVA_BOOLEAN.withName("hasWideGamut"),
            ValueLayout.JAVA_BOOLEAN.withName("hasTransparency"),
            ValueLayout.JAVA_FLOAT.withName("largestContentHeadroom")
        ).withName("CGContentInfo")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val deepestImageComponent_VH: VarHandle = layout.varHandle(groupElement("deepestImageComponent"))
    
    @Suppress("UNCHECKED_CAST")
    fun deepestImageComponent(segment: MemorySegment): MemorySegment =
        deepestImageComponent_VH.get(segment, 0L) as MemorySegment
    
    fun deepestImageComponent(segment: MemorySegment, value: MemorySegment) =
        deepestImageComponent_VH.set(segment, 0L, value)
    
    val contentColorModels_VH: VarHandle = layout.varHandle(groupElement("contentColorModels"))
    
    @Suppress("UNCHECKED_CAST")
    fun contentColorModels(segment: MemorySegment): MemorySegment =
        contentColorModels_VH.get(segment, 0L) as MemorySegment
    
    fun contentColorModels(segment: MemorySegment, value: MemorySegment) =
        contentColorModels_VH.set(segment, 0L, value)
    
    val hasWideGamut_VH: VarHandle = layout.varHandle(groupElement("hasWideGamut"))
    
    @Suppress("UNCHECKED_CAST")
    fun hasWideGamut(segment: MemorySegment): Boolean =
        hasWideGamut_VH.get(segment, 0L) as Boolean
    
    fun hasWideGamut(segment: MemorySegment, value: Boolean) =
        hasWideGamut_VH.set(segment, 0L, value)
    
    val hasTransparency_VH: VarHandle = layout.varHandle(groupElement("hasTransparency"))
    
    @Suppress("UNCHECKED_CAST")
    fun hasTransparency(segment: MemorySegment): Boolean =
        hasTransparency_VH.get(segment, 0L) as Boolean
    
    fun hasTransparency(segment: MemorySegment, value: Boolean) =
        hasTransparency_VH.set(segment, 0L, value)
    
    val largestContentHeadroom_VH: VarHandle = layout.varHandle(groupElement("largestContentHeadroom"))
    
    @Suppress("UNCHECKED_CAST")
    fun largestContentHeadroom(segment: MemorySegment): Float =
        largestContentHeadroom_VH.get(segment, 0L) as Float
    
    fun largestContentHeadroom(segment: MemorySegment, value: Float) =
        largestContentHeadroom_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CGBitmapParameters
 */
class CGBitmapParameters {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_LONG.withName("width"),
            ValueLayout.JAVA_LONG.withName("height"),
            ValueLayout.JAVA_LONG.withName("bytesPerPixel"),
            ValueLayout.JAVA_LONG.withName("alignedBytesPerRow"),
            ValueLayout.ADDRESS.withName("component"),
            ValueLayout.ADDRESS.withName("layout"),
            ValueLayout.ADDRESS.withName("format"),
            ValueLayout.ADDRESS.withName("colorSpace"),
            ValueLayout.JAVA_BOOLEAN.withName("hasPremultipliedAlpha"),
            ValueLayout.JAVA_LONG.withName("byteOrder"),
            ValueLayout.JAVA_FLOAT.withName("edrTargetHeadroom")
        ).withName("CGBitmapParameters")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val width_VH: VarHandle = layout.varHandle(groupElement("width"))
    
    @Suppress("UNCHECKED_CAST")
    fun width(segment: MemorySegment): Long =
        width_VH.get(segment, 0L) as Long
    
    fun width(segment: MemorySegment, value: Long) =
        width_VH.set(segment, 0L, value)
    
    val height_VH: VarHandle = layout.varHandle(groupElement("height"))
    
    @Suppress("UNCHECKED_CAST")
    fun height(segment: MemorySegment): Long =
        height_VH.get(segment, 0L) as Long
    
    fun height(segment: MemorySegment, value: Long) =
        height_VH.set(segment, 0L, value)
    
    val bytesPerPixel_VH: VarHandle = layout.varHandle(groupElement("bytesPerPixel"))
    
    @Suppress("UNCHECKED_CAST")
    fun bytesPerPixel(segment: MemorySegment): Long =
        bytesPerPixel_VH.get(segment, 0L) as Long
    
    fun bytesPerPixel(segment: MemorySegment, value: Long) =
        bytesPerPixel_VH.set(segment, 0L, value)
    
    val alignedBytesPerRow_VH: VarHandle = layout.varHandle(groupElement("alignedBytesPerRow"))
    
    @Suppress("UNCHECKED_CAST")
    fun alignedBytesPerRow(segment: MemorySegment): Long =
        alignedBytesPerRow_VH.get(segment, 0L) as Long
    
    fun alignedBytesPerRow(segment: MemorySegment, value: Long) =
        alignedBytesPerRow_VH.set(segment, 0L, value)
    
    val component_VH: VarHandle = layout.varHandle(groupElement("component"))
    
    @Suppress("UNCHECKED_CAST")
    fun component(segment: MemorySegment): MemorySegment =
        component_VH.get(segment, 0L) as MemorySegment
    
    fun component(segment: MemorySegment, value: MemorySegment) =
        component_VH.set(segment, 0L, value)
    
    val layout_VH: VarHandle = layout.varHandle(groupElement("layout"))
    
    @Suppress("UNCHECKED_CAST")
    fun layout(segment: MemorySegment): MemorySegment =
        layout_VH.get(segment, 0L) as MemorySegment
    
    fun layout(segment: MemorySegment, value: MemorySegment) =
        layout_VH.set(segment, 0L, value)
    
    val format_VH: VarHandle = layout.varHandle(groupElement("format"))
    
    @Suppress("UNCHECKED_CAST")
    fun format(segment: MemorySegment): MemorySegment =
        format_VH.get(segment, 0L) as MemorySegment
    
    fun format(segment: MemorySegment, value: MemorySegment) =
        format_VH.set(segment, 0L, value)
    
    val colorSpace_VH: VarHandle = layout.varHandle(groupElement("colorSpace"))
    
    @Suppress("UNCHECKED_CAST")
    fun colorSpace(segment: MemorySegment): MemorySegment =
        colorSpace_VH.get(segment, 0L) as MemorySegment
    
    fun colorSpace(segment: MemorySegment, value: MemorySegment) =
        colorSpace_VH.set(segment, 0L, value)
    
    val hasPremultipliedAlpha_VH: VarHandle = layout.varHandle(groupElement("hasPremultipliedAlpha"))
    
    @Suppress("UNCHECKED_CAST")
    fun hasPremultipliedAlpha(segment: MemorySegment): Boolean =
        hasPremultipliedAlpha_VH.get(segment, 0L) as Boolean
    
    fun hasPremultipliedAlpha(segment: MemorySegment, value: Boolean) =
        hasPremultipliedAlpha_VH.set(segment, 0L, value)
    
    val byteOrder_VH: VarHandle = layout.varHandle(groupElement("byteOrder"))
    
    @Suppress("UNCHECKED_CAST")
    fun byteOrder(segment: MemorySegment): Long =
        byteOrder_VH.get(segment, 0L) as Long
    
    fun byteOrder(segment: MemorySegment, value: Long) =
        byteOrder_VH.set(segment, 0L, value)
    
    val edrTargetHeadroom_VH: VarHandle = layout.varHandle(groupElement("edrTargetHeadroom"))
    
    @Suppress("UNCHECKED_CAST")
    fun edrTargetHeadroom(segment: MemorySegment): Float =
        edrTargetHeadroom_VH.get(segment, 0L) as Float
    
    fun edrTargetHeadroom(segment: MemorySegment, value: Float) =
        edrTargetHeadroom_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Declared(CGColorConversionInfo))* CGColorConversionInfoRef;}
 */
typealias CGColorConversionInfoRef = MemorySegment

/**
 * {@snippet lang=c : STRUCT CGColorBufferFormat
 */
class CGColorBufferFormat {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("version"),
            ValueLayout.ADDRESS.withName("bitmapInfo"),
            ValueLayout.JAVA_LONG.withName("bitsPerComponent"),
            ValueLayout.JAVA_LONG.withName("bitsPerPixel"),
            ValueLayout.JAVA_LONG.withName("bytesPerRow")
        ).withName("CGColorBufferFormat")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Int =
        version_VH.get(segment, 0L) as Int
    
    fun version(segment: MemorySegment, value: Int) =
        version_VH.set(segment, 0L, value)
    
    val bitmapInfo_VH: VarHandle = layout.varHandle(groupElement("bitmapInfo"))
    
    @Suppress("UNCHECKED_CAST")
    fun bitmapInfo(segment: MemorySegment): MemorySegment =
        bitmapInfo_VH.get(segment, 0L) as MemorySegment
    
    fun bitmapInfo(segment: MemorySegment, value: MemorySegment) =
        bitmapInfo_VH.set(segment, 0L, value)
    
    val bitsPerComponent_VH: VarHandle = layout.varHandle(groupElement("bitsPerComponent"))
    
    @Suppress("UNCHECKED_CAST")
    fun bitsPerComponent(segment: MemorySegment): Long =
        bitsPerComponent_VH.get(segment, 0L) as Long
    
    fun bitsPerComponent(segment: MemorySegment, value: Long) =
        bitsPerComponent_VH.set(segment, 0L, value)
    
    val bitsPerPixel_VH: VarHandle = layout.varHandle(groupElement("bitsPerPixel"))
    
    @Suppress("UNCHECKED_CAST")
    fun bitsPerPixel(segment: MemorySegment): Long =
        bitsPerPixel_VH.get(segment, 0L) as Long
    
    fun bitsPerPixel(segment: MemorySegment, value: Long) =
        bitsPerPixel_VH.set(segment, 0L, value)
    
    val bytesPerRow_VH: VarHandle = layout.varHandle(groupElement("bytesPerRow"))
    
    @Suppress("UNCHECKED_CAST")
    fun bytesPerRow(segment: MemorySegment): Long =
        bytesPerRow_VH.get(segment, 0L) as Long
    
    fun bytesPerRow(segment: MemorySegment, value: Long) =
        bytesPerRow_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT CGColorDataFormat
 */
class CGColorDataFormat {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("version"),
            ValueLayout.ADDRESS.withName("colorspace_info"),
            ValueLayout.ADDRESS.withName("bitmap_info"),
            ValueLayout.JAVA_LONG.withName("bits_per_component"),
            ValueLayout.JAVA_LONG.withName("bytes_per_row"),
            ValueLayout.ADDRESS.withName("intent"),
            ValueLayout.ADDRESS.withName("decode")
        ).withName("CGColorDataFormat")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Int =
        version_VH.get(segment, 0L) as Int
    
    fun version(segment: MemorySegment, value: Int) =
        version_VH.set(segment, 0L, value)
    
    val colorspace_info_VH: VarHandle = layout.varHandle(groupElement("colorspace_info"))
    
    @Suppress("UNCHECKED_CAST")
    fun colorspace_info(segment: MemorySegment): MemorySegment =
        colorspace_info_VH.get(segment, 0L) as MemorySegment
    
    fun colorspace_info(segment: MemorySegment, value: MemorySegment) =
        colorspace_info_VH.set(segment, 0L, value)
    
    val bitmap_info_VH: VarHandle = layout.varHandle(groupElement("bitmap_info"))
    
    @Suppress("UNCHECKED_CAST")
    fun bitmap_info(segment: MemorySegment): MemorySegment =
        bitmap_info_VH.get(segment, 0L) as MemorySegment
    
    fun bitmap_info(segment: MemorySegment, value: MemorySegment) =
        bitmap_info_VH.set(segment, 0L, value)
    
    val bits_per_component_VH: VarHandle = layout.varHandle(groupElement("bits_per_component"))
    
    @Suppress("UNCHECKED_CAST")
    fun bits_per_component(segment: MemorySegment): Long =
        bits_per_component_VH.get(segment, 0L) as Long
    
    fun bits_per_component(segment: MemorySegment, value: Long) =
        bits_per_component_VH.set(segment, 0L, value)
    
    val bytes_per_row_VH: VarHandle = layout.varHandle(groupElement("bytes_per_row"))
    
    @Suppress("UNCHECKED_CAST")
    fun bytes_per_row(segment: MemorySegment): Long =
        bytes_per_row_VH.get(segment, 0L) as Long
    
    fun bytes_per_row(segment: MemorySegment, value: Long) =
        bytes_per_row_VH.set(segment, 0L, value)
    
    val intent_VH: VarHandle = layout.varHandle(groupElement("intent"))
    
    @Suppress("UNCHECKED_CAST")
    fun intent(segment: MemorySegment): MemorySegment =
        intent_VH.get(segment, 0L) as MemorySegment
    
    fun intent(segment: MemorySegment, value: MemorySegment) =
        intent_VH.set(segment, 0L, value)
    
    val decode_VH: VarHandle = layout.varHandle(groupElement("decode"))
    
    @Suppress("UNCHECKED_CAST")
    fun decode(segment: MemorySegment): MemorySegment =
        decode_VH.get(segment, 0L) as MemorySegment
    
    fun decode(segment: MemorySegment, value: MemorySegment) =
        decode_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Declared(CGDataConsumer))* CGDataConsumerRef;}
 */
typealias CGDataConsumerRef = MemorySegment

/**
 * {@snippet lang=c : typedef (UNSIGNED = Long((Void)*,(Void)*,UNSIGNED = Long))* CGDataConsumerPutBytesCallback;}
 */
typealias CGDataConsumerPutBytesCallback = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Void)*))* CGDataConsumerReleaseInfoCallback;}
 */
typealias CGDataConsumerReleaseInfoCallback = MemorySegment

/**
 * {@snippet lang=c : STRUCT CGDataConsumerCallbacks
 */
class CGDataConsumerCallbacks {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("putBytes"),
            ValueLayout.ADDRESS.withName("releaseConsumer")
        ).withName("CGDataConsumerCallbacks")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val putBytes_VH: VarHandle = layout.varHandle(groupElement("putBytes"))
    
    @Suppress("UNCHECKED_CAST")
    fun putBytes(segment: MemorySegment): MemorySegment =
        putBytes_VH.get(segment, 0L) as MemorySegment
    
    fun putBytes(segment: MemorySegment, value: MemorySegment) =
        putBytes_VH.set(segment, 0L, value)
    
    val releaseConsumer_VH: VarHandle = layout.varHandle(groupElement("releaseConsumer"))
    
    @Suppress("UNCHECKED_CAST")
    fun releaseConsumer(segment: MemorySegment): MemorySegment =
        releaseConsumer_VH.get(segment, 0L) as MemorySegment
    
    fun releaseConsumer(segment: MemorySegment, value: MemorySegment) =
        releaseConsumer_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Void())* CGErrorCallback;}
 */
typealias CGErrorCallback = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGLayer))* CGLayerRef;}
 */
typealias CGLayerRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGPDFContentStream))* CGPDFContentStreamRef;}
 */
typealias CGPDFContentStreamRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CFString))* CGPDFTagProperty;}
 */
typealias CGPDFTagProperty = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGPDFOperatorTable))* CGPDFOperatorTableRef;}
 */
typealias CGPDFOperatorTableRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGPDFScanner))* CGPDFScannerRef;}
 */
typealias CGPDFScannerRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Declared(CGPDFScanner))*,(Void)*))* CGPDFOperatorCallback;}
 */
typealias CGPDFOperatorCallback = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CGWindowID;}
 */
typealias CGWindowID = Int

/**
 * {@snippet lang=c : typedef Int CGWindowLevel;}
 */
typealias CGWindowLevel = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CGDirectDisplayID;}
 */
typealias CGDirectDisplayID = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CGOpenGLDisplayMask;}
 */
typealias CGOpenGLDisplayMask = Int

/**
 * {@snippet lang=c : typedef Double CGRefreshRate;}
 */
typealias CGRefreshRate = Double

/**
 * {@snippet lang=c : typedef (Declared(CGDisplayMode))* CGDisplayModeRef;}
 */
typealias CGDisplayModeRef = MemorySegment

/**
 * {@snippet lang=c : typedef Float CGGammaValue;}
 */
typealias CGGammaValue = Float

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CGDisplayCount;}
 */
typealias CGDisplayCount = Int

/**
 * {@snippet lang=c : STRUCT CGDeviceColor
 */
class CGDeviceColor {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_FLOAT.withName("red"),
            ValueLayout.JAVA_FLOAT.withName("green"),
            ValueLayout.JAVA_FLOAT.withName("blue")
        ).withName("CGDeviceColor")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val red_VH: VarHandle = layout.varHandle(groupElement("red"))
    
    @Suppress("UNCHECKED_CAST")
    fun red(segment: MemorySegment): Float =
        red_VH.get(segment, 0L) as Float
    
    fun red(segment: MemorySegment, value: Float) =
        red_VH.set(segment, 0L, value)
    
    val green_VH: VarHandle = layout.varHandle(groupElement("green"))
    
    @Suppress("UNCHECKED_CAST")
    fun green(segment: MemorySegment): Float =
        green_VH.get(segment, 0L) as Float
    
    fun green(segment: MemorySegment, value: Float) =
        green_VH.set(segment, 0L, value)
    
    val blue_VH: VarHandle = layout.varHandle(groupElement("blue"))
    
    @Suppress("UNCHECKED_CAST")
    fun blue(segment: MemorySegment): Float =
        blue_VH.get(segment, 0L) as Float
    
    fun blue(segment: MemorySegment, value: Float) =
        blue_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Declared(_CGDisplayConfigRef))* CGDisplayConfigRef;}
 */
typealias CGDisplayConfigRef = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CGDisplayFadeReservationToken;}
 */
typealias CGDisplayFadeReservationToken = Int

/**
 * {@snippet lang=c : typedef Float CGDisplayBlendFraction;}
 */
typealias CGDisplayBlendFraction = Float

/**
 * {@snippet lang=c : typedef Float CGDisplayFadeInterval;}
 */
typealias CGDisplayFadeInterval = Float

/**
 * {@snippet lang=c : typedef Float CGDisplayReservationInterval;}
 */
typealias CGDisplayReservationInterval = Float

/**
 * {@snippet lang=c : typedef (Declared(CGDisplayStream))* CGDisplayStreamRef;}
 */
typealias CGDisplayStreamRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(CGDisplayStreamUpdate))* CGDisplayStreamUpdateRef;}
 */
typealias CGDisplayStreamUpdateRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Void)* CGDisplayStreamFrameAvailableHandler;}
 */
typealias CGDisplayStreamFrameAvailableHandler = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CGButtonCount;}
 */
typealias CGButtonCount = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CGWheelCount;}
 */
typealias CGWheelCount = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short CGCharCode;}
 */
typealias CGCharCode = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short CGKeyCode;}
 */
typealias CGKeyCode = Short

/**
 * {@snippet lang=c : typedef (Void(UNSIGNED = Int,(Declared(CGRect))*,(Void)*))* CGScreenRefreshCallback;}
 */
typealias CGScreenRefreshCallback = MemorySegment

/**
 * {@snippet lang=c : STRUCT CGScreenUpdateMoveDelta
 */
class CGScreenUpdateMoveDelta {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("dX"),
            ValueLayout.JAVA_INT.withName("dY")
        ).withName("CGScreenUpdateMoveDelta")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val dX_VH: VarHandle = layout.varHandle(groupElement("dX"))
    
    @Suppress("UNCHECKED_CAST")
    fun dX(segment: MemorySegment): Int =
        dX_VH.get(segment, 0L) as Int
    
    fun dX(segment: MemorySegment, value: Int) =
        dX_VH.set(segment, 0L, value)
    
    val dY_VH: VarHandle = layout.varHandle(groupElement("dY"))
    
    @Suppress("UNCHECKED_CAST")
    fun dY(segment: MemorySegment): Int =
        dY_VH.get(segment, 0L) as Int
    
    fun dY(segment: MemorySegment, value: Int) =
        dY_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Void(Declared(CGScreenUpdateMoveDelta),UNSIGNED = Long,(Declared(CGRect))*,(Void)*))* CGScreenUpdateMoveCallback;}
 */
typealias CGScreenUpdateMoveCallback = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CGRectCount;}
 */
typealias CGRectCount = Int

/**
 * {@snippet lang=c : typedef LongLong OSAtomic_int64_aligned64_t;}
 */
typealias OSAtomic_int64_aligned64_t = Long

/**
 * {@snippet lang=c : typedef Int OSSpinLock;}
 */
typealias OSSpinLock = Int

/**
 * {@snippet lang=c : typedef Int IOIndex;}
 */
typealias IOIndex = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int IOSelect;}
 */
typealias IOSelect = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int IOFixed1616;}
 */
typealias IOFixed1616 = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int IODisplayVendorID;}
 */
typealias IODisplayVendorID = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int IODisplayProductID;}
 */
typealias IODisplayProductID = Int

/**
 * {@snippet lang=c : typedef Int IODisplayModeID;}
 */
typealias IODisplayModeID = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int IOAppleTimingID;}
 */
typealias IOAppleTimingID = Int

/**
 * {@snippet lang=c : typedef Int IOPixelAperture;}
 */
typealias IOPixelAperture = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short IOColorComponent;}
 */
typealias IOColorComponent = Short

/**
 * {@snippet lang=c : typedef Float NXCoord;}
 */
typealias NXCoord = Float

/**
 * {@snippet lang=c : typedef (Declared(__CGEvent))* CGEventRef;}
 */
typealias CGEventRef = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong CGEventTimestamp;}
 */
typealias CGEventTimestamp = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong CGEventMask;}
 */
typealias CGEventMask = Long

/**
 * {@snippet lang=c : typedef (Declared(__CGEventTapProxy))* CGEventTapProxy;}
 */
typealias CGEventTapProxy = MemorySegment

/**
 * {@snippet lang=c : STRUCT __CGEventTapInformation
 */
class _CGEventTapInformation {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("eventTapID"),
            ValueLayout.ADDRESS.withName("tapPoint"),
            ValueLayout.ADDRESS.withName("options"),
            ValueLayout.JAVA_LONG.withName("eventsOfInterest"),
            ValueLayout.JAVA_INT.withName("tappingProcess"),
            ValueLayout.JAVA_INT.withName("processBeingTapped"),
            ValueLayout.JAVA_BOOLEAN.withName("enabled"),
            ValueLayout.JAVA_FLOAT.withName("minUsecLatency"),
            ValueLayout.JAVA_FLOAT.withName("avgUsecLatency"),
            ValueLayout.JAVA_FLOAT.withName("maxUsecLatency")
        ).withName("__CGEventTapInformation")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val eventTapID_VH: VarHandle = layout.varHandle(groupElement("eventTapID"))
    
    @Suppress("UNCHECKED_CAST")
    fun eventTapID(segment: MemorySegment): Int =
        eventTapID_VH.get(segment, 0L) as Int
    
    fun eventTapID(segment: MemorySegment, value: Int) =
        eventTapID_VH.set(segment, 0L, value)
    
    val tapPoint_VH: VarHandle = layout.varHandle(groupElement("tapPoint"))
    
    @Suppress("UNCHECKED_CAST")
    fun tapPoint(segment: MemorySegment): MemorySegment =
        tapPoint_VH.get(segment, 0L) as MemorySegment
    
    fun tapPoint(segment: MemorySegment, value: MemorySegment) =
        tapPoint_VH.set(segment, 0L, value)
    
    val options_VH: VarHandle = layout.varHandle(groupElement("options"))
    
    @Suppress("UNCHECKED_CAST")
    fun options(segment: MemorySegment): MemorySegment =
        options_VH.get(segment, 0L) as MemorySegment
    
    fun options(segment: MemorySegment, value: MemorySegment) =
        options_VH.set(segment, 0L, value)
    
    val eventsOfInterest_VH: VarHandle = layout.varHandle(groupElement("eventsOfInterest"))
    
    @Suppress("UNCHECKED_CAST")
    fun eventsOfInterest(segment: MemorySegment): Long =
        eventsOfInterest_VH.get(segment, 0L) as Long
    
    fun eventsOfInterest(segment: MemorySegment, value: Long) =
        eventsOfInterest_VH.set(segment, 0L, value)
    
    val tappingProcess_VH: VarHandle = layout.varHandle(groupElement("tappingProcess"))
    
    @Suppress("UNCHECKED_CAST")
    fun tappingProcess(segment: MemorySegment): Int =
        tappingProcess_VH.get(segment, 0L) as Int
    
    fun tappingProcess(segment: MemorySegment, value: Int) =
        tappingProcess_VH.set(segment, 0L, value)
    
    val processBeingTapped_VH: VarHandle = layout.varHandle(groupElement("processBeingTapped"))
    
    @Suppress("UNCHECKED_CAST")
    fun processBeingTapped(segment: MemorySegment): Int =
        processBeingTapped_VH.get(segment, 0L) as Int
    
    fun processBeingTapped(segment: MemorySegment, value: Int) =
        processBeingTapped_VH.set(segment, 0L, value)
    
    val enabled_VH: VarHandle = layout.varHandle(groupElement("enabled"))
    
    @Suppress("UNCHECKED_CAST")
    fun enabled(segment: MemorySegment): Boolean =
        enabled_VH.get(segment, 0L) as Boolean
    
    fun enabled(segment: MemorySegment, value: Boolean) =
        enabled_VH.set(segment, 0L, value)
    
    val minUsecLatency_VH: VarHandle = layout.varHandle(groupElement("minUsecLatency"))
    
    @Suppress("UNCHECKED_CAST")
    fun minUsecLatency(segment: MemorySegment): Float =
        minUsecLatency_VH.get(segment, 0L) as Float
    
    fun minUsecLatency(segment: MemorySegment, value: Float) =
        minUsecLatency_VH.set(segment, 0L, value)
    
    val avgUsecLatency_VH: VarHandle = layout.varHandle(groupElement("avgUsecLatency"))
    
    @Suppress("UNCHECKED_CAST")
    fun avgUsecLatency(segment: MemorySegment): Float =
        avgUsecLatency_VH.get(segment, 0L) as Float
    
    fun avgUsecLatency(segment: MemorySegment, value: Float) =
        avgUsecLatency_VH.set(segment, 0L, value)
    
    val maxUsecLatency_VH: VarHandle = layout.varHandle(groupElement("maxUsecLatency"))
    
    @Suppress("UNCHECKED_CAST")
    fun maxUsecLatency(segment: MemorySegment): Float =
        maxUsecLatency_VH.get(segment, 0L) as Float
    
    fun maxUsecLatency(segment: MemorySegment, value: Float) =
        maxUsecLatency_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef Declared(__CGEventTapInformation) CGEventTapInformation;}
 */
typealias CGEventTapInformation = MemorySegment

/**
 * {@snippet lang=c : typedef (Declared(__CGEventSource))* CGEventSourceRef;}
 */
typealias CGEventSourceRef = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CGEventSourceKeyboardType;}
 */
typealias CGEventSourceKeyboardType = Int

/**
 * {@snippet lang=c : typedef (Declared(CGPSConverter))* CGPSConverterRef;}
 */
typealias CGPSConverterRef = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Void)*))* CGPSConverterBeginDocumentCallback;}
 */
typealias CGPSConverterBeginDocumentCallback = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Void)*,Bool))* CGPSConverterEndDocumentCallback;}
 */
typealias CGPSConverterEndDocumentCallback = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Void)*,UNSIGNED = Long,(Declared(__CFDictionary))*))* CGPSConverterBeginPageCallback;}
 */
typealias CGPSConverterBeginPageCallback = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Void)*,UNSIGNED = Long,(Declared(__CFDictionary))*))* CGPSConverterEndPageCallback;}
 */
typealias CGPSConverterEndPageCallback = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Void)*))* CGPSConverterProgressCallback;}
 */
typealias CGPSConverterProgressCallback = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Void)*,(Declared(__CFString))*))* CGPSConverterMessageCallback;}
 */
typealias CGPSConverterMessageCallback = MemorySegment

/**
 * {@snippet lang=c : typedef (Void((Void)*))* CGPSConverterReleaseInfoCallback;}
 */
typealias CGPSConverterReleaseInfoCallback = MemorySegment

/**
 * {@snippet lang=c : STRUCT CGPSConverterCallbacks
 */
class CGPSConverterCallbacks {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("version"),
            ValueLayout.ADDRESS.withName("beginDocument"),
            ValueLayout.ADDRESS.withName("endDocument"),
            ValueLayout.ADDRESS.withName("beginPage"),
            ValueLayout.ADDRESS.withName("endPage"),
            ValueLayout.ADDRESS.withName("noteProgress"),
            ValueLayout.ADDRESS.withName("noteMessage"),
            ValueLayout.ADDRESS.withName("releaseInfo")
        ).withName("CGPSConverterCallbacks")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Int =
        version_VH.get(segment, 0L) as Int
    
    fun version(segment: MemorySegment, value: Int) =
        version_VH.set(segment, 0L, value)
    
    val beginDocument_VH: VarHandle = layout.varHandle(groupElement("beginDocument"))
    
    @Suppress("UNCHECKED_CAST")
    fun beginDocument(segment: MemorySegment): MemorySegment =
        beginDocument_VH.get(segment, 0L) as MemorySegment
    
    fun beginDocument(segment: MemorySegment, value: MemorySegment) =
        beginDocument_VH.set(segment, 0L, value)
    
    val endDocument_VH: VarHandle = layout.varHandle(groupElement("endDocument"))
    
    @Suppress("UNCHECKED_CAST")
    fun endDocument(segment: MemorySegment): MemorySegment =
        endDocument_VH.get(segment, 0L) as MemorySegment
    
    fun endDocument(segment: MemorySegment, value: MemorySegment) =
        endDocument_VH.set(segment, 0L, value)
    
    val beginPage_VH: VarHandle = layout.varHandle(groupElement("beginPage"))
    
    @Suppress("UNCHECKED_CAST")
    fun beginPage(segment: MemorySegment): MemorySegment =
        beginPage_VH.get(segment, 0L) as MemorySegment
    
    fun beginPage(segment: MemorySegment, value: MemorySegment) =
        beginPage_VH.set(segment, 0L, value)
    
    val endPage_VH: VarHandle = layout.varHandle(groupElement("endPage"))
    
    @Suppress("UNCHECKED_CAST")
    fun endPage(segment: MemorySegment): MemorySegment =
        endPage_VH.get(segment, 0L) as MemorySegment
    
    fun endPage(segment: MemorySegment, value: MemorySegment) =
        endPage_VH.set(segment, 0L, value)
    
    val noteProgress_VH: VarHandle = layout.varHandle(groupElement("noteProgress"))
    
    @Suppress("UNCHECKED_CAST")
    fun noteProgress(segment: MemorySegment): MemorySegment =
        noteProgress_VH.get(segment, 0L) as MemorySegment
    
    fun noteProgress(segment: MemorySegment, value: MemorySegment) =
        noteProgress_VH.set(segment, 0L, value)
    
    val noteMessage_VH: VarHandle = layout.varHandle(groupElement("noteMessage"))
    
    @Suppress("UNCHECKED_CAST")
    fun noteMessage(segment: MemorySegment): MemorySegment =
        noteMessage_VH.get(segment, 0L) as MemorySegment
    
    fun noteMessage(segment: MemorySegment, value: MemorySegment) =
        noteMessage_VH.set(segment, 0L, value)
    
    val releaseInfo_VH: VarHandle = layout.varHandle(groupElement("releaseInfo"))
    
    @Suppress("UNCHECKED_CAST")
    fun releaseInfo(segment: MemorySegment): MemorySegment =
        releaseInfo_VH.get(segment, 0L) as MemorySegment
    
    fun releaseInfo(segment: MemorySegment, value: MemorySegment) =
        releaseInfo_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CTFontPriority;}
 */
typealias CTFontPriority = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CTFontTableTag;}
 */
typealias CTFontTableTag = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short CMXYZComponent;}
 */
typealias CMXYZComponent = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CMDisplayIDType;}
 */
typealias CMDisplayIDType = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CMChromaticAdaptation;}
 */
typealias CMChromaticAdaptation = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CMBitmapColorSpace;}
 */
typealias CMBitmapColorSpace = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CMDeviceState;}
 */
typealias CMDeviceState = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CMDeviceID;}
 */
typealias CMDeviceID = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CMDeviceProfileID;}
 */
typealias CMDeviceProfileID = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int CMDeviceClass;}
 */
typealias CMDeviceClass = Int

/**
 * {@snippet lang=c : typedef Short QDErr;}
 */
typealias QDErr = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Long GWorldFlags;}
 */
typealias GWorldFlags = Long

/**
 * {@snippet lang=c : typedef Int QDRegionParseDirection;}
 */
typealias QDRegionParseDirection = Int

/**
 * {@snippet lang=c : typedef Short TruncCode;}
 */
typealias TruncCode = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short DragConstraint;}
 */
typealias DragConstraint = Short

/**
 * {@snippet lang=c : typedef SIGNED = Char GrafVerb;}
 */
typealias GrafVerb = Byte

/**
 * {@snippet lang=c : typedef Int PrinterStatusOpcode;}
 */
typealias PrinterStatusOpcode = Int

/**
 * {@snippet lang=c : typedef Short IconAlignmentType;}
 */
typealias IconAlignmentType = Short

/**
 * {@snippet lang=c : typedef Short IconTransformType;}
 */
typealias IconTransformType = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int IconSelectorValue;}
 */
typealias IconSelectorValue = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int PlotIconRefFlags;}
 */
typealias PlotIconRefFlags = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ICAttr;}
 */
typealias ICAttr = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Char ICPerm;}
 */
typealias ICPerm = Byte

/**
 * {@snippet lang=c : typedef Int ICProfileID;}
 */
typealias ICProfileID = Int

/**
 * {@snippet lang=c : typedef Int ICMapEntryFlags;}
 */
typealias ICMapEntryFlags = Int

/**
 * {@snippet lang=c : typedef Short ICFixedLength;}
 */
typealias ICFixedLength = Short

/**
 * {@snippet lang=c : typedef Short ICServiceEntryFlags;}
 */
typealias ICServiceEntryFlags = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short LaunchFlags;}
 */
typealias LaunchFlags = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ProcessApplicationTransformState;}
 */
typealias ProcessApplicationTransformState = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int TranslationFlags;}
 */
typealias TranslationFlags = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int UAZoomChangeFocusType;}
 */
typealias UAZoomChangeFocusType = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short PMDestinationType;}
 */
typealias PMDestinationType = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short PMOrientation;}
 */
typealias PMOrientation = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short PMPrinterState;}
 */
typealias PMPrinterState = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int PMColorSpaceModel;}
 */
typealias PMColorSpaceModel = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int PMQualityMode;}
 */
typealias PMQualityMode = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int PMPaperType;}
 */
typealias PMPaperType = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short PMScalingAlignment;}
 */
typealias PMScalingAlignment = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int PMDuplexMode;}
 */
typealias PMDuplexMode = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short PMLayoutDirection;}
 */
typealias PMLayoutDirection = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short PMBorderType;}
 */
typealias PMBorderType = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int PMPrintDialogOptionFlags;}
 */
typealias PMPrintDialogOptionFlags = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short PMPPDDomain;}
 */
typealias PMPPDDomain = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Char sa_family_t;}
 */
typealias sa_family_t = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = Int socklen_t;}
 */
typealias socklen_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int sae_associd_t;}
 */
typealias sae_associd_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int sae_connid_t;}
 */
typealias sae_connid_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short n_short;}
 */
typealias n_short = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int n_long;}
 */
typealias n_long = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int n_time;}
 */
typealias n_time = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int tcp_seq;}
 */
typealias tcp_seq = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int tcp_cc;}
 */
typealias tcp_cc = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Char ipp_uchar_t;}
 */
typealias ipp_uchar_t = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = Int cups_ptype_t;}
 */
typealias cups_ptype_t = Int

/**
 * {@snippet lang=c : typedef Int ATSUTextMeasurement;}
 */
typealias ATSUTextMeasurement = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ATSUFontID;}
 */
typealias ATSUFontID = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short ATSUFontFeatureType;}
 */
typealias ATSUFontFeatureType = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short ATSUFontFeatureSelector;}
 */
typealias ATSUFontFeatureSelector = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ATSUFontVariationAxis;}
 */
typealias ATSUFontVariationAxis = Int

/**
 * {@snippet lang=c : typedef Int ATSUFontVariationValue;}
 */
typealias ATSUFontVariationValue = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ATSUAttributeTag;}
 */
typealias ATSUAttributeTag = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short ATSUCursorMovementType;}
 */
typealias ATSUCursorMovementType = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ATSULineTruncation;}
 */
typealias ATSULineTruncation = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short ATSUStyleLineCountType;}
 */
typealias ATSUStyleLineCountType = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short ATSUVerticalCharacterType;}
 */
typealias ATSUVerticalCharacterType = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short ATSUStyleComparison;}
 */
typealias ATSUStyleComparison = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short ATSUFontFallbackMethod;}
 */
typealias ATSUFontFallbackMethod = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short ATSUTabType;}
 */
typealias ATSUTabType = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short GlyphCollection;}
 */
typealias GlyphCollection = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ATSUHighlightMethod;}
 */
typealias ATSUHighlightMethod = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ATSUBackgroundDataType;}
 */
typealias ATSUBackgroundDataType = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ATSUFlattenedDataStreamFormat;}
 */
typealias ATSUFlattenedDataStreamFormat = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ATSUFlattenStyleRunOptions;}
 */
typealias ATSUFlattenStyleRunOptions = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ATSUUnFlattenStyleRunOptions;}
 */
typealias ATSUUnFlattenStyleRunOptions = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ATSFlatDataFontSpeciferType;}
 */
typealias ATSFlatDataFontSpeciferType = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int ATSUDirectDataSelector;}
 */
typealias ATSUDirectDataSelector = Int

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSPasteboardType;}
 */
typealias NSPasteboardType = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSPasteboardName;}
 */
typealias NSPasteboardName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSPasteboardDetectionPattern;}
 */
typealias NSPasteboardDetectionPattern = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSPasteboardMetadataType;}
 */
typealias NSPasteboardMetadataType = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSPasteboardReadingOptionKey;}
 */
typealias NSPasteboardReadingOptionKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSNibName;}
 */
typealias NSNibName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSUserInterfaceItemIdentifier;}
 */
typealias NSUserInterfaceItemIdentifier = MemorySegment

/**
 * {@snippet lang=c : typedef Float NSAnimationProgress;}
 */
typealias NSAnimationProgress = Float

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSViewAnimationKey;}
 */
typealias NSViewAnimationKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSViewAnimationEffectName;}
 */
typealias NSViewAnimationEffectName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSAnimatablePropertyKey;}
 */
typealias NSAnimatablePropertyKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSAppearanceName;}
 */
typealias NSAppearanceName = MemorySegment

/**
 * {@snippet lang=c : typedef Long NSTrackingRectTag;}
 */
typealias NSTrackingRectTag = Long

/**
 * {@snippet lang=c : typedef Long NSToolTipTag;}
 */
typealias NSToolTipTag = Long

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSViewFullScreenModeOptionKey;}
 */
typealias NSViewFullScreenModeOptionKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSDefinitionOptionKey;}
 */
typealias NSDefinitionOptionKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSDefinitionPresentationType;}
 */
typealias NSDefinitionPresentationType = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSTextTabOptionKey;}
 */
typealias NSTextTabOptionKey = MemorySegment

/**
 * {@snippet lang=c : typedef Long NSControlStateValue;}
 */
typealias NSControlStateValue = Long

/**
 * {@snippet lang=c : typedef Long NSCellStateValue;}
 */
typealias NSCellStateValue = Long

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSPrinterTypeName;}
 */
typealias NSPrinterTypeName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSPrinterPaperName;}
 */
typealias NSPrinterPaperName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSPrintInfoAttributeKey;}
 */
typealias NSPrintInfoAttributeKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSPrintJobDispositionValue;}
 */
typealias NSPrintJobDispositionValue = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSPrintInfoSettingKey;}
 */
typealias NSPrintInfoSettingKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSBindingName;}
 */
typealias NSBindingName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSBindingOption;}
 */
typealias NSBindingOption = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSBindingInfoKey;}
 */
typealias NSBindingInfoKey = MemorySegment

/**
 * {@snippet lang=c : typedef Double NSAppKitVersion;}
 */
typealias NSAppKitVersion = Double

/**
 * {@snippet lang=c : typedef Long NSModalResponse;}
 */
typealias NSModalResponse = Long

/**
 * {@snippet lang=c : typedef (Declared(_NSModalSession))* NSModalSession;}
 */
typealias NSModalSession = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSAboutPanelOptionKey;}
 */
typealias NSAboutPanelOptionKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSServiceProviderName;}
 */
typealias NSServiceProviderName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSColorListName;}
 */
typealias NSColorListName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSColorName;}
 */
typealias NSColorName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSHelpBookName;}
 */
typealias NSHelpBookName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSHelpAnchorName;}
 */
typealias NSHelpAnchorName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSHelpManagerContextHelpKey;}
 */
typealias NSHelpManagerContextHelpKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSTouchBarItemIdentifier;}
 */
typealias NSTouchBarItemIdentifier = MemorySegment

/**
 * {@snippet lang=c : typedef Float NSTouchBarItemPriority;}
 */
typealias NSTouchBarItemPriority = Float

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSTouchBarCustomizationIdentifier;}
 */
typealias NSTouchBarCustomizationIdentifier = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSPopoverCloseReasonValue;}
 */
typealias NSPopoverCloseReasonValue = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSStoryboardName;}
 */
typealias NSStoryboardName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSStoryboardSceneIdentifier;}
 */
typealias NSStoryboardSceneIdentifier = MemorySegment

/**
 * {@snippet lang=c : typedef (Void)* NSStoryboardControllerCreator;}
 */
typealias NSStoryboardControllerCreator = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSStoryboardSegueIdentifier;}
 */
typealias NSStoryboardSegueIdentifier = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSCollectionViewSupplementaryElementKind;}
 */
typealias NSCollectionViewSupplementaryElementKind = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSCollectionViewDecorationElementKind;}
 */
typealias NSCollectionViewDecorationElementKind = MemorySegment

/**
 * {@snippet lang=c : STRUCT NSDirectionalEdgeInsets
 */
class NSDirectionalEdgeInsets {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_DOUBLE.withName("top"),
            ValueLayout.JAVA_DOUBLE.withName("leading"),
            ValueLayout.JAVA_DOUBLE.withName("bottom"),
            ValueLayout.JAVA_DOUBLE.withName("trailing")
        ).withName("NSDirectionalEdgeInsets")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val top_VH: VarHandle = layout.varHandle(groupElement("top"))
    
    @Suppress("UNCHECKED_CAST")
    fun top(segment: MemorySegment): Double =
        top_VH.get(segment, 0L) as Double
    
    fun top(segment: MemorySegment, value: Double) =
        top_VH.set(segment, 0L, value)
    
    val leading_VH: VarHandle = layout.varHandle(groupElement("leading"))
    
    @Suppress("UNCHECKED_CAST")
    fun leading(segment: MemorySegment): Double =
        leading_VH.get(segment, 0L) as Double
    
    fun leading(segment: MemorySegment, value: Double) =
        leading_VH.set(segment, 0L, value)
    
    val bottom_VH: VarHandle = layout.varHandle(groupElement("bottom"))
    
    @Suppress("UNCHECKED_CAST")
    fun bottom(segment: MemorySegment): Double =
        bottom_VH.get(segment, 0L) as Double
    
    fun bottom(segment: MemorySegment, value: Double) =
        bottom_VH.set(segment, 0L, value)
    
    val trailing_VH: VarHandle = layout.varHandle(groupElement("trailing"))
    
    @Suppress("UNCHECKED_CAST")
    fun trailing(segment: MemorySegment): Double =
        trailing_VH.get(segment, 0L) as Double
    
    fun trailing(segment: MemorySegment, value: Double) =
        trailing_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef (Void)* NSCollectionViewCompositionalLayoutSectionProvider;}
 */
typealias NSCollectionViewCompositionalLayoutSectionProvider = MemorySegment

/**
 * {@snippet lang=c : typedef (Void)* NSCollectionLayoutSectionVisibleItemsInvalidationHandler;}
 */
typealias NSCollectionLayoutSectionVisibleItemsInvalidationHandler = MemorySegment

/**
 * {@snippet lang=c : typedef (Void)* NSCollectionLayoutGroupCustomItemProvider;}
 */
typealias NSCollectionLayoutGroupCustomItemProvider = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSCollectionViewTransitionLayoutAnimatedKey;}
 */
typealias NSCollectionViewTransitionLayoutAnimatedKey = MemorySegment

/**
 * {@snippet lang=c : typedef (Void)* NSCollectionViewDiffableDataSourceItemProvider;}
 */
typealias NSCollectionViewDiffableDataSourceItemProvider = MemorySegment

/**
 * {@snippet lang=c : typedef (Void)* NSCollectionViewDiffableDataSourceSupplementaryViewProvider;}
 */
typealias NSCollectionViewDiffableDataSourceSupplementaryViewProvider = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Int NSFontSymbolicTraits;}
 */
typealias NSFontSymbolicTraits = Int

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSFontDescriptorAttributeName;}
 */
typealias NSFontDescriptorAttributeName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSFontDescriptorTraitKey;}
 */
typealias NSFontDescriptorTraitKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSFontDescriptorVariationKey;}
 */
typealias NSFontDescriptorVariationKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSFontDescriptorFeatureKey;}
 */
typealias NSFontDescriptorFeatureKey = MemorySegment

/**
 * {@snippet lang=c : typedef Double NSFontWeight;}
 */
typealias NSFontWeight = Double

/**
 * {@snippet lang=c : typedef Double NSFontWidth;}
 */
typealias NSFontWidth = Double

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSFontDescriptorSystemDesign;}
 */
typealias NSFontDescriptorSystemDesign = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSFontTextStyle;}
 */
typealias NSFontTextStyle = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSFontTextStyleOptionKey;}
 */
typealias NSFontTextStyleOptionKey = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Int NSFontFamilyClass;}
 */
typealias NSFontFamilyClass = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int NSGlyph;}
 */
typealias NSGlyph = Int

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSFontCollectionMatchingOptionKey;}
 */
typealias NSFontCollectionMatchingOptionKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSFontCollectionName;}
 */
typealias NSFontCollectionName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSFontCollectionUserInfoKey;}
 */
typealias NSFontCollectionUserInfoKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSFontCollectionActionTypeKey;}
 */
typealias NSFontCollectionActionTypeKey = MemorySegment

/**
 * {@snippet lang=c : typedef Long NSWindowLevel;}
 */
typealias NSWindowLevel = Long

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSWindowFrameAutosaveName;}
 */
typealias NSWindowFrameAutosaveName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSWindowPersistableFrameDescriptor;}
 */
typealias NSWindowPersistableFrameDescriptor = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSWindowTabbingIdentifier;}
 */
typealias NSWindowTabbingIdentifier = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSImageHintKey;}
 */
typealias NSImageHintKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSBitmapImageRepPropertyKey;}
 */
typealias NSBitmapImageRepPropertyKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSBrowserColumnsAutosaveName;}
 */
typealias NSBrowserColumnsAutosaveName = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong CVOptionFlags;}
 */
typealias CVOptionFlags = Long

/**
 * {@snippet lang=c : typedef Int CVReturn;}
 */
typealias CVReturn = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int GLbitfield;}
 */
typealias GLbitfield = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Char GLboolean;}
 */
typealias GLboolean = Byte

/**
 * {@snippet lang=c : typedef SIGNED = Char GLbyte;}
 */
typealias GLbyte = Byte

/**
 * {@snippet lang=c : typedef Float GLclampf;}
 */
typealias GLclampf = Float

/**
 * {@snippet lang=c : typedef UNSIGNED = Int GLenum;}
 */
typealias GLenum = Int

/**
 * {@snippet lang=c : typedef Float GLfloat;}
 */
typealias GLfloat = Float

/**
 * {@snippet lang=c : typedef Int GLint;}
 */
typealias GLint = Int

/**
 * {@snippet lang=c : typedef Short GLshort;}
 */
typealias GLshort = Short

/**
 * {@snippet lang=c : typedef Int GLsizei;}
 */
typealias GLsizei = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Char GLubyte;}
 */
typealias GLubyte = Byte

/**
 * {@snippet lang=c : typedef UNSIGNED = Int GLuint;}
 */
typealias GLuint = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short GLushort;}
 */
typealias GLushort = Short

/**
 * {@snippet lang=c : typedef Char GLchar;}
 */
typealias GLchar = Byte

/**
 * {@snippet lang=c : typedef Char GLcharARB;}
 */
typealias GLcharARB = Byte

/**
 * {@snippet lang=c : typedef Double GLdouble;}
 */
typealias GLdouble = Double

/**
 * {@snippet lang=c : typedef Double GLclampd;}
 */
typealias GLclampd = Double

/**
 * {@snippet lang=c : typedef Int GLfixed;}
 */
typealias GLfixed = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Short GLhalf;}
 */
typealias GLhalf = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short GLhalfARB;}
 */
typealias GLhalfARB = Short

/**
 * {@snippet lang=c : typedef LongLong GLint64;}
 */
typealias GLint64 = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong GLuint64;}
 */
typealias GLuint64 = Long

/**
 * {@snippet lang=c : typedef LongLong GLint64EXT;}
 */
typealias GLint64EXT = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = LongLong GLuint64EXT;}
 */
typealias GLuint64EXT = Long

/**
 * {@snippet lang=c : typedef Long GLintptr;}
 */
typealias GLintptr = Long

/**
 * {@snippet lang=c : typedef Long GLsizeiptr;}
 */
typealias GLsizeiptr = Long

/**
 * {@snippet lang=c : typedef Long GLintptrARB;}
 */
typealias GLintptrARB = Long

/**
 * {@snippet lang=c : typedef Long GLsizeiptrARB;}
 */
typealias GLsizeiptrARB = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int IOSurfaceID;}
 */
typealias IOSurfaceID = Int

/**
 * {@snippet lang=c : typedef Int CIFormat;}
 */
typealias CIFormat = Int

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* CIImageOption;}
 */
typealias CIImageOption = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* CIImageAutoAdjustmentOption;}
 */
typealias CIImageAutoAdjustmentOption = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSDraggingImageComponentKey;}
 */
typealias NSDraggingImageComponentKey = MemorySegment

/**
 * {@snippet lang=c : typedef Float NSLayoutPriority;}
 */
typealias NSLayoutPriority = Float

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSImageName;}
 */
typealias NSImageName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSSharingServiceName;}
 */
typealias NSSharingServiceName = MemorySegment

/**
 * {@snippet lang=c : typedef Double NSSliderAccessoryWidth;}
 */
typealias NSSliderAccessoryWidth = Double

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSSpeechSynthesizerVoiceName;}
 */
typealias NSSpeechSynthesizerVoiceName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSVoiceAttributeKey;}
 */
typealias NSVoiceAttributeKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSSpeechDictionaryKey;}
 */
typealias NSSpeechDictionaryKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSVoiceGenderName;}
 */
typealias NSVoiceGenderName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSSpeechPropertyKey;}
 */
typealias NSSpeechPropertyKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSSpeechMode;}
 */
typealias NSSpeechMode = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSSpeechStatusKey;}
 */
typealias NSSpeechStatusKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSSpeechErrorKey;}
 */
typealias NSSpeechErrorKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSSpeechSynthesizerInfoKey;}
 */
typealias NSSpeechSynthesizerInfoKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSSpeechPhonemeInfoKey;}
 */
typealias NSSpeechPhonemeInfoKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSSpeechCommandDelimiterKey;}
 */
typealias NSSpeechCommandDelimiterKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSTextCheckingOptionKey;}
 */
typealias NSTextCheckingOptionKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSSplitViewAutosaveName;}
 */
typealias NSSplitViewAutosaveName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSPrintPanelJobStyleHint;}
 */
typealias NSPrintPanelJobStyleHint = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSPrintPanelAccessorySummaryKey;}
 */
typealias NSPrintPanelAccessorySummaryKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSPasteboardTypeTextFinderOptionKey;}
 */
typealias NSPasteboardTypeTextFinderOptionKey = MemorySegment

/**
 * {@snippet lang=c : typedef Float NSStackViewVisibilityPriority;}
 */
typealias NSStackViewVisibilityPriority = Float

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSTextContentType;}
 */
typealias NSTextContentType = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSTextEffectStyle;}
 */
typealias NSTextEffectStyle = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSTextHighlightStyle;}
 */
typealias NSTextHighlightStyle = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSTextHighlightColorScheme;}
 */
typealias NSTextHighlightColorScheme = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSAttributedStringDocumentType;}
 */
typealias NSAttributedStringDocumentType = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSTextLayoutSectionKey;}
 */
typealias NSTextLayoutSectionKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSAttributedStringDocumentAttributeKey;}
 */
typealias NSAttributedStringDocumentAttributeKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSAttributedStringDocumentReadingOptionKey;}
 */
typealias NSAttributedStringDocumentReadingOptionKey = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Long NSTextStorageEditedOptions;}
 */
typealias NSTextStorageEditedOptions = Long

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSToolbarIdentifier;}
 */
typealias NSToolbarIdentifier = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSToolbarItemIdentifier;}
 */
typealias NSToolbarItemIdentifier = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSToolbarUserInfoKey;}
 */
typealias NSToolbarUserInfoKey = MemorySegment

/**
 * {@snippet lang=c : typedef Long NSToolbarItemVisibilityPriority;}
 */
typealias NSToolbarItemVisibilityPriority = Long

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSPasteboardTypeFindPanelSearchOptionKey;}
 */
typealias NSPasteboardTypeFindPanelSearchOptionKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSTableViewAutosaveName;}
 */
typealias NSTableViewAutosaveName = MemorySegment

/**
 * {@snippet lang=c : typedef (Void)* NSTableViewDiffableDataSourceCellProvider;}
 */
typealias NSTableViewDiffableDataSourceCellProvider = MemorySegment

/**
 * {@snippet lang=c : typedef (Void)* NSTableViewDiffableDataSourceRowProvider;}
 */
typealias NSTableViewDiffableDataSourceRowProvider = MemorySegment

/**
 * {@snippet lang=c : typedef (Void)* NSTableViewDiffableDataSourceSectionHeaderViewProvider;}
 */
typealias NSTableViewDiffableDataSourceSectionHeaderViewProvider = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSRulerViewUnitName;}
 */
typealias NSRulerViewUnitName = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Long NSInterfaceStyle;}
 */
typealias NSInterfaceStyle = Long

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSStatusItemAutosaveName;}
 */
typealias NSStatusItemAutosaveName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSSoundName;}
 */
typealias NSSoundName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSSoundPlaybackDeviceIdentifier;}
 */
typealias NSSoundPlaybackDeviceIdentifier = MemorySegment

/**
 * {@snippet lang=c : typedef UNSIGNED = Int NSOpenGLPixelFormatAttribute;}
 */
typealias NSOpenGLPixelFormatAttribute = Int

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* CAMediaTimingFillMode;}
 */
typealias CAMediaTimingFillMode = MemorySegment

/**
 * {@snippet lang=c : STRUCT CATransform3D
 */
class CATransform3D {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_DOUBLE.withName("m11"),
            ValueLayout.JAVA_DOUBLE.withName("m12"),
            ValueLayout.JAVA_DOUBLE.withName("m13"),
            ValueLayout.JAVA_DOUBLE.withName("m14"),
            ValueLayout.JAVA_DOUBLE.withName("m21"),
            ValueLayout.JAVA_DOUBLE.withName("m22"),
            ValueLayout.JAVA_DOUBLE.withName("m23"),
            ValueLayout.JAVA_DOUBLE.withName("m24"),
            ValueLayout.JAVA_DOUBLE.withName("m31"),
            ValueLayout.JAVA_DOUBLE.withName("m32"),
            ValueLayout.JAVA_DOUBLE.withName("m33"),
            ValueLayout.JAVA_DOUBLE.withName("m34"),
            ValueLayout.JAVA_DOUBLE.withName("m41"),
            ValueLayout.JAVA_DOUBLE.withName("m42"),
            ValueLayout.JAVA_DOUBLE.withName("m43"),
            ValueLayout.JAVA_DOUBLE.withName("m44")
        ).withName("CATransform3D")
        
        val byteSize: Long
            get() = layout.byteSize()
        
        fun allocate(allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(layout)
        
        fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =
            allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))
        
        fun asSlice(array: MemorySegment, index: Long): MemorySegment =
            array.asSlice(byteSize * index)
        
        fun reinterpret(addr: MemorySegment): MemorySegment =
            addr.reinterpret(byteSize)
        
        fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =
            addr.reinterpret(byteSize * elementCount)
        
    } // End companion object
    
    val m11_VH: VarHandle = layout.varHandle(groupElement("m11"))
    
    @Suppress("UNCHECKED_CAST")
    fun m11(segment: MemorySegment): Double =
        m11_VH.get(segment, 0L) as Double
    
    fun m11(segment: MemorySegment, value: Double) =
        m11_VH.set(segment, 0L, value)
    
    val m12_VH: VarHandle = layout.varHandle(groupElement("m12"))
    
    @Suppress("UNCHECKED_CAST")
    fun m12(segment: MemorySegment): Double =
        m12_VH.get(segment, 0L) as Double
    
    fun m12(segment: MemorySegment, value: Double) =
        m12_VH.set(segment, 0L, value)
    
    val m13_VH: VarHandle = layout.varHandle(groupElement("m13"))
    
    @Suppress("UNCHECKED_CAST")
    fun m13(segment: MemorySegment): Double =
        m13_VH.get(segment, 0L) as Double
    
    fun m13(segment: MemorySegment, value: Double) =
        m13_VH.set(segment, 0L, value)
    
    val m14_VH: VarHandle = layout.varHandle(groupElement("m14"))
    
    @Suppress("UNCHECKED_CAST")
    fun m14(segment: MemorySegment): Double =
        m14_VH.get(segment, 0L) as Double
    
    fun m14(segment: MemorySegment, value: Double) =
        m14_VH.set(segment, 0L, value)
    
    val m21_VH: VarHandle = layout.varHandle(groupElement("m21"))
    
    @Suppress("UNCHECKED_CAST")
    fun m21(segment: MemorySegment): Double =
        m21_VH.get(segment, 0L) as Double
    
    fun m21(segment: MemorySegment, value: Double) =
        m21_VH.set(segment, 0L, value)
    
    val m22_VH: VarHandle = layout.varHandle(groupElement("m22"))
    
    @Suppress("UNCHECKED_CAST")
    fun m22(segment: MemorySegment): Double =
        m22_VH.get(segment, 0L) as Double
    
    fun m22(segment: MemorySegment, value: Double) =
        m22_VH.set(segment, 0L, value)
    
    val m23_VH: VarHandle = layout.varHandle(groupElement("m23"))
    
    @Suppress("UNCHECKED_CAST")
    fun m23(segment: MemorySegment): Double =
        m23_VH.get(segment, 0L) as Double
    
    fun m23(segment: MemorySegment, value: Double) =
        m23_VH.set(segment, 0L, value)
    
    val m24_VH: VarHandle = layout.varHandle(groupElement("m24"))
    
    @Suppress("UNCHECKED_CAST")
    fun m24(segment: MemorySegment): Double =
        m24_VH.get(segment, 0L) as Double
    
    fun m24(segment: MemorySegment, value: Double) =
        m24_VH.set(segment, 0L, value)
    
    val m31_VH: VarHandle = layout.varHandle(groupElement("m31"))
    
    @Suppress("UNCHECKED_CAST")
    fun m31(segment: MemorySegment): Double =
        m31_VH.get(segment, 0L) as Double
    
    fun m31(segment: MemorySegment, value: Double) =
        m31_VH.set(segment, 0L, value)
    
    val m32_VH: VarHandle = layout.varHandle(groupElement("m32"))
    
    @Suppress("UNCHECKED_CAST")
    fun m32(segment: MemorySegment): Double =
        m32_VH.get(segment, 0L) as Double
    
    fun m32(segment: MemorySegment, value: Double) =
        m32_VH.set(segment, 0L, value)
    
    val m33_VH: VarHandle = layout.varHandle(groupElement("m33"))
    
    @Suppress("UNCHECKED_CAST")
    fun m33(segment: MemorySegment): Double =
        m33_VH.get(segment, 0L) as Double
    
    fun m33(segment: MemorySegment, value: Double) =
        m33_VH.set(segment, 0L, value)
    
    val m34_VH: VarHandle = layout.varHandle(groupElement("m34"))
    
    @Suppress("UNCHECKED_CAST")
    fun m34(segment: MemorySegment): Double =
        m34_VH.get(segment, 0L) as Double
    
    fun m34(segment: MemorySegment, value: Double) =
        m34_VH.set(segment, 0L, value)
    
    val m41_VH: VarHandle = layout.varHandle(groupElement("m41"))
    
    @Suppress("UNCHECKED_CAST")
    fun m41(segment: MemorySegment): Double =
        m41_VH.get(segment, 0L) as Double
    
    fun m41(segment: MemorySegment, value: Double) =
        m41_VH.set(segment, 0L, value)
    
    val m42_VH: VarHandle = layout.varHandle(groupElement("m42"))
    
    @Suppress("UNCHECKED_CAST")
    fun m42(segment: MemorySegment): Double =
        m42_VH.get(segment, 0L) as Double
    
    fun m42(segment: MemorySegment, value: Double) =
        m42_VH.set(segment, 0L, value)
    
    val m43_VH: VarHandle = layout.varHandle(groupElement("m43"))
    
    @Suppress("UNCHECKED_CAST")
    fun m43(segment: MemorySegment): Double =
        m43_VH.get(segment, 0L) as Double
    
    fun m43(segment: MemorySegment, value: Double) =
        m43_VH.set(segment, 0L, value)
    
    val m44_VH: VarHandle = layout.varHandle(groupElement("m44"))
    
    @Suppress("UNCHECKED_CAST")
    fun m44(segment: MemorySegment): Double =
        m44_VH.get(segment, 0L) as Double
    
    fun m44(segment: MemorySegment, value: Double) =
        m44_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* CALayerContentsGravity;}
 */
typealias CALayerContentsGravity = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* CALayerContentsFormat;}
 */
typealias CALayerContentsFormat = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* CALayerContentsFilter;}
 */
typealias CALayerContentsFilter = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* CALayerCornerCurve;}
 */
typealias CALayerCornerCurve = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* CAToneMapMode;}
 */
typealias CAToneMapMode = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* CADynamicRange;}
 */
typealias CADynamicRange = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSSearchFieldRecentsAutosaveName;}
 */
typealias NSSearchFieldRecentsAutosaveName = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSTextListMarkerFormat;}
 */
typealias NSTextListMarkerFormat = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSRuleEditorPredicatePartKey;}
 */
typealias NSRuleEditorPredicatePartKey = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSPageControllerObjectIdentifier;}
 */
typealias NSPageControllerObjectIdentifier = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSTextInputSourceIdentifier;}
 */
typealias NSTextInputSourceIdentifier = MemorySegment

/**
 * {@snippet lang=c : typedef typedef NSString = (Void)* NSDataAssetName;}
 */
typealias NSDataAssetName = MemorySegment

