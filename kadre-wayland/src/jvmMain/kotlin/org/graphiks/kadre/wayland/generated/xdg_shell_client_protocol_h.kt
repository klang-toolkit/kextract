package org.graphiks.kadre.wayland.generated

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
}

/**
 * {@snippet lang=c : typedef Long int64_t;}
 */
typealias int64_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long uint64_t;}
 */
typealias uint64_t = Any

/**
 * {@snippet lang=c : typedef Long int_least64_t;}
 */
typealias int_least64_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long uint_least64_t;}
 */
typealias uint_least64_t = Any

/**
 * {@snippet lang=c : typedef Long int_fast64_t;}
 */
typealias int_fast64_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long uint_fast64_t;}
 */
typealias uint_fast64_t = Any

/**
 * {@snippet lang=c : typedef Int int32_t;}
 */
typealias int32_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int uint32_t;}
 */
typealias uint32_t = Any

/**
 * {@snippet lang=c : typedef Int int_least32_t;}
 */
typealias int_least32_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int uint_least32_t;}
 */
typealias uint_least32_t = Any

/**
 * {@snippet lang=c : typedef Int int_fast32_t;}
 */
typealias int_fast32_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int uint_fast32_t;}
 */
typealias uint_fast32_t = Any

/**
 * {@snippet lang=c : typedef Short int16_t;}
 */
typealias int16_t = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short uint16_t;}
 */
typealias uint16_t = Any

/**
 * {@snippet lang=c : typedef Short int_least16_t;}
 */
typealias int_least16_t = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short uint_least16_t;}
 */
typealias uint_least16_t = Any

/**
 * {@snippet lang=c : typedef Short int_fast16_t;}
 */
typealias int_fast16_t = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short uint_fast16_t;}
 */
typealias uint_fast16_t = Any

/**
 * {@snippet lang=c : typedef SIGNED = Char int8_t;}
 */
typealias int8_t = Any

/**
 * {@snippet lang=c : typedef UNSIGNED = Char uint8_t;}
 */
typealias uint8_t = Any

/**
 * {@snippet lang=c : typedef SIGNED = Char int_least8_t;}
 */
typealias int_least8_t = Any

/**
 * {@snippet lang=c : typedef UNSIGNED = Char uint_least8_t;}
 */
typealias uint_least8_t = Any

/**
 * {@snippet lang=c : typedef SIGNED = Char int_fast8_t;}
 */
typealias int_fast8_t = Any

/**
 * {@snippet lang=c : typedef UNSIGNED = Char uint_fast8_t;}
 */
typealias uint_fast8_t = Any

/**
 * {@snippet lang=c : typedef Long intptr_t;}
 */
typealias intptr_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long uintptr_t;}
 */
typealias uintptr_t = Any

/**
 * {@snippet lang=c : typedef Long intmax_t;}
 */
typealias intmax_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long uintmax_t;}
 */
typealias uintmax_t = Any

/**
 * {@snippet lang=c : typedef Long ptrdiff_t;}
 */
typealias ptrdiff_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long size_t;}
 */
typealias size_t = Any

/**
 * {@snippet lang=c : typedef UNSIGNED = Int wchar_t;}
 */
typealias wchar_t = Any

/**
 * {@snippet lang=c : typedef UNSIGNED = Char __u_char;}
 */
typealias _u_char = Any

/**
 * {@snippet lang=c : typedef UNSIGNED = Short __u_short;}
 */
typealias _u_short = Any

/**
 * {@snippet lang=c : typedef UNSIGNED = Int __u_int;}
 */
typealias _u_int = Any

/**
 * {@snippet lang=c : typedef UNSIGNED = Long __u_long;}
 */
typealias _u_long = Any

/**
 * {@snippet lang=c : typedef SIGNED = Char __int8_t;}
 */
typealias _int8_t = Any

/**
 * {@snippet lang=c : typedef UNSIGNED = Char __uint8_t;}
 */
typealias _uint8_t = Any

/**
 * {@snippet lang=c : typedef Short __int16_t;}
 */
typealias _int16_t = Short

/**
 * {@snippet lang=c : typedef UNSIGNED = Short __uint16_t;}
 */
typealias _uint16_t = Any

/**
 * {@snippet lang=c : typedef Int __int32_t;}
 */
typealias _int32_t = Int

/**
 * {@snippet lang=c : typedef UNSIGNED = Int __uint32_t;}
 */
typealias _uint32_t = Any

/**
 * {@snippet lang=c : typedef Long __int64_t;}
 */
typealias _int64_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long __uint64_t;}
 */
typealias _uint64_t = Any

/**
 * {@snippet lang=c : typedef Long __quad_t;}
 */
typealias _quad_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long __u_quad_t;}
 */
typealias _u_quad_t = Any

/**
 * {@snippet lang=c : typedef Long __intmax_t;}
 */
typealias _intmax_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long __uintmax_t;}
 */
typealias _uintmax_t = Any

/**
 * {@snippet lang=c : typedef UNSIGNED = Long __dev_t;}
 */
typealias _dev_t = Any

/**
 * {@snippet lang=c : typedef UNSIGNED = Int __uid_t;}
 */
typealias _uid_t = Any

/**
 * {@snippet lang=c : typedef UNSIGNED = Int __gid_t;}
 */
typealias _gid_t = Any

/**
 * {@snippet lang=c : typedef UNSIGNED = Long __ino_t;}
 */
typealias _ino_t = Any

/**
 * {@snippet lang=c : typedef UNSIGNED = Long __ino64_t;}
 */
typealias _ino64_t = Any

/**
 * {@snippet lang=c : typedef UNSIGNED = Int __mode_t;}
 */
typealias _mode_t = Any

/**
 * {@snippet lang=c : typedef UNSIGNED = Int __nlink_t;}
 */
typealias _nlink_t = Any

/**
 * {@snippet lang=c : typedef Long __off_t;}
 */
typealias _off_t = Long

/**
 * {@snippet lang=c : typedef Long __off64_t;}
 */
typealias _off64_t = Long

/**
 * {@snippet lang=c : typedef Int __pid_t;}
 */
typealias _pid_t = Int

/**
 * {@snippet lang=c : typedef Long __clock_t;}
 */
typealias _clock_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long __rlim_t;}
 */
typealias _rlim_t = Any

/**
 * {@snippet lang=c : typedef UNSIGNED = Long __rlim64_t;}
 */
typealias _rlim64_t = Any

/**
 * {@snippet lang=c : typedef UNSIGNED = Int __id_t;}
 */
typealias _id_t = Any

/**
 * {@snippet lang=c : typedef Long __time_t;}
 */
typealias _time_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int __useconds_t;}
 */
typealias _useconds_t = Any

/**
 * {@snippet lang=c : typedef Long __suseconds_t;}
 */
typealias _suseconds_t = Long

/**
 * {@snippet lang=c : typedef Long __suseconds64_t;}
 */
typealias _suseconds64_t = Long

/**
 * {@snippet lang=c : typedef Int __daddr_t;}
 */
typealias _daddr_t = Int

/**
 * {@snippet lang=c : typedef Int __key_t;}
 */
typealias _key_t = Int

/**
 * {@snippet lang=c : typedef Int __clockid_t;}
 */
typealias _clockid_t = Int

/**
 * {@snippet lang=c : typedef Int __blksize_t;}
 */
typealias _blksize_t = Int

/**
 * {@snippet lang=c : typedef Long __blkcnt_t;}
 */
typealias _blkcnt_t = Long

/**
 * {@snippet lang=c : typedef Long __blkcnt64_t;}
 */
typealias _blkcnt64_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long __fsblkcnt_t;}
 */
typealias _fsblkcnt_t = Any

/**
 * {@snippet lang=c : typedef UNSIGNED = Long __fsblkcnt64_t;}
 */
typealias _fsblkcnt64_t = Any

/**
 * {@snippet lang=c : typedef UNSIGNED = Long __fsfilcnt_t;}
 */
typealias _fsfilcnt_t = Any

/**
 * {@snippet lang=c : typedef UNSIGNED = Long __fsfilcnt64_t;}
 */
typealias _fsfilcnt64_t = Any

/**
 * {@snippet lang=c : typedef Long __fsword_t;}
 */
typealias _fsword_t = Long

/**
 * {@snippet lang=c : typedef Long __ssize_t;}
 */
typealias _ssize_t = Long

/**
 * {@snippet lang=c : typedef Long __syscall_slong_t;}
 */
typealias _syscall_slong_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Long __syscall_ulong_t;}
 */
typealias _syscall_ulong_t = Any

/**
 * {@snippet lang=c : typedef Long __loff_t;}
 */
typealias _loff_t = Long

/**
 * {@snippet lang=c : typedef Long __intptr_t;}
 */
typealias _intptr_t = Long

/**
 * {@snippet lang=c : typedef UNSIGNED = Int __socklen_t;}
 */
typealias _socklen_t = Any

/**
 * {@snippet lang=c : typedef Int __sig_atomic_t;}
 */
typealias _sig_atomic_t = Int

/**
 * {@snippet lang=c : typedef Float _Float32;}
 */
typealias _Float32 = Float

/**
 * {@snippet lang=c : typedef Double _Float64;}
 */
typealias _Float64 = Double

/**
 * {@snippet lang=c : typedef Double _Float32x;}
 */
typealias _Float32x = Double

/**
 * {@snippet lang=c : typedef Float float_t;}
 */
typealias float_t = Float

/**
 * {@snippet lang=c : typedef Double double_t;}
 */
typealias double_t = Double

/**
 * NS_ENUM: {@snippet lang=c : enum enum (unnamed at /usr/include/math.h:934:1)}
 */
enum class enum_unnamed_at_usr_include_math_h_934_1_(val value: Long) {
    FP_NAN(0L), FP_INFINITE(1L), FP_ZERO(2L), FP_SUBNORMAL(3L), FP_NORMAL(4L);
    
    companion object {
        fun fromValue(v: Long): enum_unnamed_at_usr_include_math_h_934_1_ = entries.firstOrNull { it.value == v }
            ?: error("Unknown enum_unnamed_at_usr_include_math_h_934_1_ value: $v")
    }
}

/**
 * {@snippet lang=c : typedef UNSIGNED = Int __gwchar_t;}
 */
typealias _gwchar_t = Any

/**
 * {@snippet lang=c : STRUCT wl_message
 */
class wl_message {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("name"),
            ValueLayout.ADDRESS.withName("signature"),
            ValueLayout.ADDRESS.withName("types")
        ).withName("wl_message")
        
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
        
    } // End companion object
    
    val name_VH: VarHandle = layout.varHandle(groupElement("name"))
    
    @Suppress("UNCHECKED_CAST")
    fun name(segment: MemorySegment): MemorySegment =
        name_VH.get(segment, 0L) as MemorySegment
    
    fun name(segment: MemorySegment, value: MemorySegment) =
        name_VH.set(segment, 0L, value)
    
    val signature_VH: VarHandle = layout.varHandle(groupElement("signature"))
    
    @Suppress("UNCHECKED_CAST")
    fun signature(segment: MemorySegment): MemorySegment =
        signature_VH.get(segment, 0L) as MemorySegment
    
    fun signature(segment: MemorySegment, value: MemorySegment) =
        signature_VH.set(segment, 0L, value)
    
    val types_VH: VarHandle = layout.varHandle(groupElement("types"))
    
    @Suppress("UNCHECKED_CAST")
    fun types(segment: MemorySegment): MemorySegment =
        types_VH.get(segment, 0L) as MemorySegment
    
    fun types(segment: MemorySegment, value: MemorySegment) =
        types_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : STRUCT wl_interface
 */
class wl_interface {
    companion object {
        val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.ADDRESS.withName("name"),
            ValueLayout.JAVA_INT.withName("version"),
            ValueLayout.JAVA_INT.withName("method_count"),
            ValueLayout.ADDRESS.withName("methods"),
            ValueLayout.JAVA_INT.withName("event_count"),
            ValueLayout.ADDRESS.withName("events")
        ).withName("wl_interface")
        
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
        
    } // End companion object
    
    val name_VH: VarHandle = layout.varHandle(groupElement("name"))
    
    @Suppress("UNCHECKED_CAST")
    fun name(segment: MemorySegment): MemorySegment =
        name_VH.get(segment, 0L) as MemorySegment
    
    fun name(segment: MemorySegment, value: MemorySegment) =
        name_VH.set(segment, 0L, value)
    
    val version_VH: VarHandle = layout.varHandle(groupElement("version"))
    
    @Suppress("UNCHECKED_CAST")
    fun version(segment: MemorySegment): Int =
        version_VH.get(segment, 0L) as Int
    
    fun version(segment: MemorySegment, value: Int) =
        version_VH.set(segment, 0L, value)
    
    val method_count_VH: VarHandle = layout.varHandle(groupElement("method_count"))
    
    @Suppress("UNCHECKED_CAST")
    fun method_count(segment: MemorySegment): Int =
        method_count_VH.get(segment, 0L) as Int
    
    fun method_count(segment: MemorySegment, value: Int) =
        method_count_VH.set(segment, 0L, value)
    
    val methods_VH: VarHandle = layout.varHandle(groupElement("methods"))
    
    @Suppress("UNCHECKED_CAST")
    fun methods(segment: MemorySegment): MemorySegment =
        methods_VH.get(segment, 0L) as MemorySegment
    
    fun methods(segment: MemorySegment, value: MemorySegment) =
        methods_VH.set(segment, 0L, value)
    
    val event_count_VH: VarHandle = layout.varHandle(groupElement("event_count"))
    
    @Suppress("UNCHECKED_CAST")
    fun event_count(segment: MemorySegment): Int =
        event_count_VH.get(segment, 0L) as Int
    
    fun event_count(segment: MemorySegment, value: Int) =
        event_count_VH.set(segment, 0L, value)
    
    val events_VH: VarHandle = layout.varHandle(groupElement("events"))
    
    @Suppress("UNCHECKED_CAST")
    fun events(segment: MemorySegment): MemorySegment =
        events_VH.get(segment, 0L) as MemorySegment
    
    fun events(segment: MemorySegment, value: MemorySegment) =
        events_VH.set(segment, 0L, value)
} // End class

/**
 * {@snippet lang=c : typedef Int wl_fixed_t;}
 */
typealias wl_fixed_t = Int

/**
 * NS_ENUM: {@snippet lang=c : enum wl_iterator_result}
 */
enum class wl_iterator_result(val value: Long) {
    WL_ITERATOR_STOP(0L), WL_ITERATOR_CONTINUE(1L);
    
    companion object {
        fun fromValue(v: Long): wl_iterator_result = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_iterator_result value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_display_error}
 */
enum class wl_display_error(val value: Long) {
    WL_DISPLAY_ERROR_INVALID_OBJECT(0L), WL_DISPLAY_ERROR_INVALID_METHOD(1L), WL_DISPLAY_ERROR_NO_MEMORY(2L), WL_DISPLAY_ERROR_IMPLEMENTATION(3L);
    
    companion object {
        fun fromValue(v: Long): wl_display_error = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_display_error value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_shm_error}
 */
enum class wl_shm_error(val value: Long) {
    WL_SHM_ERROR_INVALID_FORMAT(0L), WL_SHM_ERROR_INVALID_STRIDE(1L), WL_SHM_ERROR_INVALID_FD(2L);
    
    companion object {
        fun fromValue(v: Long): wl_shm_error = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_shm_error value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_shm_format}
 */
enum class wl_shm_format(val value: Long) {
    WL_SHM_FORMAT_ARGB8888(0L), WL_SHM_FORMAT_XRGB8888(1L), WL_SHM_FORMAT_C8(538982467L), WL_SHM_FORMAT_RGB332(943867730L), WL_SHM_FORMAT_BGR233(944916290L), WL_SHM_FORMAT_XRGB4444(842093144L), WL_SHM_FORMAT_XBGR4444(842089048L), WL_SHM_FORMAT_RGBX4444(842094674L), WL_SHM_FORMAT_BGRX4444(842094658L), WL_SHM_FORMAT_ARGB4444(842093121L), WL_SHM_FORMAT_ABGR4444(842089025L), WL_SHM_FORMAT_RGBA4444(842088786L), WL_SHM_FORMAT_BGRA4444(842088770L), WL_SHM_FORMAT_XRGB1555(892424792L), WL_SHM_FORMAT_XBGR1555(892420696L), WL_SHM_FORMAT_RGBX5551(892426322L), WL_SHM_FORMAT_BGRX5551(892426306L), WL_SHM_FORMAT_ARGB1555(892424769L), WL_SHM_FORMAT_ABGR1555(892420673L), WL_SHM_FORMAT_RGBA5551(892420434L), WL_SHM_FORMAT_BGRA5551(892420418L), WL_SHM_FORMAT_RGB565(909199186L), WL_SHM_FORMAT_BGR565(909199170L), WL_SHM_FORMAT_RGB888(875710290L), WL_SHM_FORMAT_BGR888(875710274L), WL_SHM_FORMAT_XBGR8888(875709016L), WL_SHM_FORMAT_RGBX8888(875714642L), WL_SHM_FORMAT_BGRX8888(875714626L), WL_SHM_FORMAT_ABGR8888(875708993L), WL_SHM_FORMAT_RGBA8888(875708754L), WL_SHM_FORMAT_BGRA8888(875708738L), WL_SHM_FORMAT_XRGB2101010(808669784L), WL_SHM_FORMAT_XBGR2101010(808665688L), WL_SHM_FORMAT_RGBX1010102(808671314L), WL_SHM_FORMAT_BGRX1010102(808671298L), WL_SHM_FORMAT_ARGB2101010(808669761L), WL_SHM_FORMAT_ABGR2101010(808665665L), WL_SHM_FORMAT_RGBA1010102(808665426L), WL_SHM_FORMAT_BGRA1010102(808665410L), WL_SHM_FORMAT_YUYV(1448695129L), WL_SHM_FORMAT_YVYU(1431918169L), WL_SHM_FORMAT_UYVY(1498831189L), WL_SHM_FORMAT_VYUY(1498765654L), WL_SHM_FORMAT_AYUV(1448433985L), WL_SHM_FORMAT_NV12(842094158L), WL_SHM_FORMAT_NV21(825382478L), WL_SHM_FORMAT_NV16(909203022L), WL_SHM_FORMAT_NV61(825644622L), WL_SHM_FORMAT_YUV410(961959257L), WL_SHM_FORMAT_YVU410(961893977L), WL_SHM_FORMAT_YUV411(825316697L), WL_SHM_FORMAT_YVU411(825316953L), WL_SHM_FORMAT_YUV420(842093913L), WL_SHM_FORMAT_YVU420(842094169L), WL_SHM_FORMAT_YUV422(909202777L), WL_SHM_FORMAT_YVU422(909203033L), WL_SHM_FORMAT_YUV444(875713881L), WL_SHM_FORMAT_YVU444(875714137L), WL_SHM_FORMAT_R8(538982482L), WL_SHM_FORMAT_R16(540422482L), WL_SHM_FORMAT_RG88(943212370L), WL_SHM_FORMAT_GR88(943215175L), WL_SHM_FORMAT_RG1616(842221394L), WL_SHM_FORMAT_GR1616(842224199L), WL_SHM_FORMAT_XRGB16161616F(1211388504L), WL_SHM_FORMAT_XBGR16161616F(1211384408L), WL_SHM_FORMAT_ARGB16161616F(1211388481L), WL_SHM_FORMAT_ABGR16161616F(1211384385L), WL_SHM_FORMAT_XYUV8888(1448434008L), WL_SHM_FORMAT_VUY888(875713878L), WL_SHM_FORMAT_VUY101010(808670550L), WL_SHM_FORMAT_Y210(808530521L), WL_SHM_FORMAT_Y212(842084953L), WL_SHM_FORMAT_Y216(909193817L), WL_SHM_FORMAT_Y410(808531033L), WL_SHM_FORMAT_Y412(842085465L), WL_SHM_FORMAT_Y416(909194329L), WL_SHM_FORMAT_XVYU2101010(808670808L), WL_SHM_FORMAT_XVYU12_16161616(909334104L), WL_SHM_FORMAT_XVYU16161616(942954072L), WL_SHM_FORMAT_Y0L0(810299481L), WL_SHM_FORMAT_X0L0(810299480L), WL_SHM_FORMAT_Y0L2(843853913L), WL_SHM_FORMAT_X0L2(843853912L), WL_SHM_FORMAT_YUV420_8BIT(942691673L), WL_SHM_FORMAT_YUV420_10BIT(808539481L), WL_SHM_FORMAT_XRGB8888_A8(943805016L), WL_SHM_FORMAT_XBGR8888_A8(943800920L), WL_SHM_FORMAT_RGBX8888_A8(943806546L), WL_SHM_FORMAT_BGRX8888_A8(943806530L), WL_SHM_FORMAT_RGB888_A8(943798354L), WL_SHM_FORMAT_BGR888_A8(943798338L), WL_SHM_FORMAT_RGB565_A8(943797586L), WL_SHM_FORMAT_BGR565_A8(943797570L), WL_SHM_FORMAT_NV24(875714126L), WL_SHM_FORMAT_NV42(842290766L), WL_SHM_FORMAT_P210(808530512L), WL_SHM_FORMAT_P010(808530000L), WL_SHM_FORMAT_P012(842084432L), WL_SHM_FORMAT_P016(909193296L), WL_SHM_FORMAT_AXBXGXRX106106106106(808534593L), WL_SHM_FORMAT_NV15(892425806L), WL_SHM_FORMAT_Q410(808531025L), WL_SHM_FORMAT_Q401(825242705L), WL_SHM_FORMAT_XRGB16161616(942953048L), WL_SHM_FORMAT_XBGR16161616(942948952L), WL_SHM_FORMAT_ARGB16161616(942953025L), WL_SHM_FORMAT_ABGR16161616(942948929L);
    
    companion object {
        fun fromValue(v: Long): wl_shm_format = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_shm_format value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_data_offer_error}
 */
enum class wl_data_offer_error(val value: Long) {
    WL_DATA_OFFER_ERROR_INVALID_FINISH(0L), WL_DATA_OFFER_ERROR_INVALID_ACTION_MASK(1L), WL_DATA_OFFER_ERROR_INVALID_ACTION(2L), WL_DATA_OFFER_ERROR_INVALID_OFFER(3L);
    
    companion object {
        fun fromValue(v: Long): wl_data_offer_error = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_data_offer_error value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_data_source_error}
 */
enum class wl_data_source_error(val value: Long) {
    WL_DATA_SOURCE_ERROR_INVALID_ACTION_MASK(0L), WL_DATA_SOURCE_ERROR_INVALID_SOURCE(1L);
    
    companion object {
        fun fromValue(v: Long): wl_data_source_error = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_data_source_error value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_data_device_error}
 */
enum class wl_data_device_error(val value: Long) {
    WL_DATA_DEVICE_ERROR_ROLE(0L);
    
    companion object {
        fun fromValue(v: Long): wl_data_device_error = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_data_device_error value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_data_device_manager_dnd_action}
 */
enum class wl_data_device_manager_dnd_action(val value: Long) {
    WL_DATA_DEVICE_MANAGER_DND_ACTION_NONE(0L), WL_DATA_DEVICE_MANAGER_DND_ACTION_COPY(1L), WL_DATA_DEVICE_MANAGER_DND_ACTION_MOVE(2L), WL_DATA_DEVICE_MANAGER_DND_ACTION_ASK(4L);
    
    companion object {
        fun fromValue(v: Long): wl_data_device_manager_dnd_action = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_data_device_manager_dnd_action value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_shell_error}
 */
enum class wl_shell_error(val value: Long) {
    WL_SHELL_ERROR_ROLE(0L);
    
    companion object {
        fun fromValue(v: Long): wl_shell_error = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_shell_error value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_shell_surface_resize}
 */
enum class wl_shell_surface_resize(val value: Long) {
    WL_SHELL_SURFACE_RESIZE_NONE(0L), WL_SHELL_SURFACE_RESIZE_TOP(1L), WL_SHELL_SURFACE_RESIZE_BOTTOM(2L), WL_SHELL_SURFACE_RESIZE_LEFT(4L), WL_SHELL_SURFACE_RESIZE_TOP_LEFT(5L), WL_SHELL_SURFACE_RESIZE_BOTTOM_LEFT(6L), WL_SHELL_SURFACE_RESIZE_RIGHT(8L), WL_SHELL_SURFACE_RESIZE_TOP_RIGHT(9L), WL_SHELL_SURFACE_RESIZE_BOTTOM_RIGHT(10L);
    
    companion object {
        fun fromValue(v: Long): wl_shell_surface_resize = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_shell_surface_resize value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_shell_surface_transient}
 */
enum class wl_shell_surface_transient(val value: Long) {
    WL_SHELL_SURFACE_TRANSIENT_INACTIVE(1L);
    
    companion object {
        fun fromValue(v: Long): wl_shell_surface_transient = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_shell_surface_transient value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_shell_surface_fullscreen_method}
 */
enum class wl_shell_surface_fullscreen_method(val value: Long) {
    WL_SHELL_SURFACE_FULLSCREEN_METHOD_DEFAULT(0L), WL_SHELL_SURFACE_FULLSCREEN_METHOD_SCALE(1L), WL_SHELL_SURFACE_FULLSCREEN_METHOD_DRIVER(2L), WL_SHELL_SURFACE_FULLSCREEN_METHOD_FILL(3L);
    
    companion object {
        fun fromValue(v: Long): wl_shell_surface_fullscreen_method = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_shell_surface_fullscreen_method value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_surface_error}
 */
enum class wl_surface_error(val value: Long) {
    WL_SURFACE_ERROR_INVALID_SCALE(0L), WL_SURFACE_ERROR_INVALID_TRANSFORM(1L), WL_SURFACE_ERROR_INVALID_SIZE(2L), WL_SURFACE_ERROR_INVALID_OFFSET(3L), WL_SURFACE_ERROR_DEFUNCT_ROLE_OBJECT(4L);
    
    companion object {
        fun fromValue(v: Long): wl_surface_error = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_surface_error value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_seat_capability}
 */
enum class wl_seat_capability(val value: Long) {
    WL_SEAT_CAPABILITY_POINTER(1L), WL_SEAT_CAPABILITY_KEYBOARD(2L), WL_SEAT_CAPABILITY_TOUCH(4L);
    
    companion object {
        fun fromValue(v: Long): wl_seat_capability = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_seat_capability value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_seat_error}
 */
enum class wl_seat_error(val value: Long) {
    WL_SEAT_ERROR_MISSING_CAPABILITY(0L);
    
    companion object {
        fun fromValue(v: Long): wl_seat_error = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_seat_error value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_pointer_error}
 */
enum class wl_pointer_error(val value: Long) {
    WL_POINTER_ERROR_ROLE(0L);
    
    companion object {
        fun fromValue(v: Long): wl_pointer_error = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_pointer_error value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_pointer_button_state}
 */
enum class wl_pointer_button_state(val value: Long) {
    WL_POINTER_BUTTON_STATE_RELEASED(0L), WL_POINTER_BUTTON_STATE_PRESSED(1L);
    
    companion object {
        fun fromValue(v: Long): wl_pointer_button_state = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_pointer_button_state value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_pointer_axis}
 */
enum class wl_pointer_axis(val value: Long) {
    WL_POINTER_AXIS_VERTICAL_SCROLL(0L), WL_POINTER_AXIS_HORIZONTAL_SCROLL(1L);
    
    companion object {
        fun fromValue(v: Long): wl_pointer_axis = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_pointer_axis value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_pointer_axis_source}
 */
enum class wl_pointer_axis_source(val value: Long) {
    WL_POINTER_AXIS_SOURCE_WHEEL(0L), WL_POINTER_AXIS_SOURCE_FINGER(1L), WL_POINTER_AXIS_SOURCE_CONTINUOUS(2L), WL_POINTER_AXIS_SOURCE_WHEEL_TILT(3L);
    
    companion object {
        fun fromValue(v: Long): wl_pointer_axis_source = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_pointer_axis_source value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_pointer_axis_relative_direction}
 */
enum class wl_pointer_axis_relative_direction(val value: Long) {
    WL_POINTER_AXIS_RELATIVE_DIRECTION_IDENTICAL(0L), WL_POINTER_AXIS_RELATIVE_DIRECTION_INVERTED(1L);
    
    companion object {
        fun fromValue(v: Long): wl_pointer_axis_relative_direction = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_pointer_axis_relative_direction value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_keyboard_keymap_format}
 */
enum class wl_keyboard_keymap_format(val value: Long) {
    WL_KEYBOARD_KEYMAP_FORMAT_NO_KEYMAP(0L), WL_KEYBOARD_KEYMAP_FORMAT_XKB_V1(1L);
    
    companion object {
        fun fromValue(v: Long): wl_keyboard_keymap_format = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_keyboard_keymap_format value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_keyboard_key_state}
 */
enum class wl_keyboard_key_state(val value: Long) {
    WL_KEYBOARD_KEY_STATE_RELEASED(0L), WL_KEYBOARD_KEY_STATE_PRESSED(1L);
    
    companion object {
        fun fromValue(v: Long): wl_keyboard_key_state = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_keyboard_key_state value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_output_subpixel}
 */
enum class wl_output_subpixel(val value: Long) {
    WL_OUTPUT_SUBPIXEL_UNKNOWN(0L), WL_OUTPUT_SUBPIXEL_NONE(1L), WL_OUTPUT_SUBPIXEL_HORIZONTAL_RGB(2L), WL_OUTPUT_SUBPIXEL_HORIZONTAL_BGR(3L), WL_OUTPUT_SUBPIXEL_VERTICAL_RGB(4L), WL_OUTPUT_SUBPIXEL_VERTICAL_BGR(5L);
    
    companion object {
        fun fromValue(v: Long): wl_output_subpixel = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_output_subpixel value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_output_transform}
 */
enum class wl_output_transform(val value: Long) {
    WL_OUTPUT_TRANSFORM_NORMAL(0L), WL_OUTPUT_TRANSFORM_90(1L), WL_OUTPUT_TRANSFORM_180(2L), WL_OUTPUT_TRANSFORM_270(3L), WL_OUTPUT_TRANSFORM_FLIPPED(4L), WL_OUTPUT_TRANSFORM_FLIPPED_90(5L), WL_OUTPUT_TRANSFORM_FLIPPED_180(6L), WL_OUTPUT_TRANSFORM_FLIPPED_270(7L);
    
    companion object {
        fun fromValue(v: Long): wl_output_transform = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_output_transform value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_output_mode}
 */
enum class wl_output_mode(val value: Long) {
    WL_OUTPUT_MODE_CURRENT(1L), WL_OUTPUT_MODE_PREFERRED(2L);
    
    companion object {
        fun fromValue(v: Long): wl_output_mode = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_output_mode value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_subcompositor_error}
 */
enum class wl_subcompositor_error(val value: Long) {
    WL_SUBCOMPOSITOR_ERROR_BAD_SURFACE(0L), WL_SUBCOMPOSITOR_ERROR_BAD_PARENT(1L);
    
    companion object {
        fun fromValue(v: Long): wl_subcompositor_error = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_subcompositor_error value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum wl_subsurface_error}
 */
enum class wl_subsurface_error(val value: Long) {
    WL_SUBSURFACE_ERROR_BAD_SURFACE(0L);
    
    companion object {
        fun fromValue(v: Long): wl_subsurface_error = entries.firstOrNull { it.value == v }
            ?: error("Unknown wl_subsurface_error value: $v")
    }
}

/**
 * {@snippet lang=c : xdg_wm_base_interface Declared(wl_interface)
 */
val xdg_wm_base_interface: MemorySegment = SymbolLookup.loaderLookup().find("xdg_wm_base_interface").orElseThrow()

/**
 * {@snippet lang=c : xdg_surface_interface Declared(wl_interface)
 */
val xdg_surface_interface: MemorySegment = SymbolLookup.loaderLookup().find("xdg_surface_interface").orElseThrow()

/**
 * {@snippet lang=c : xdg_toplevel_interface Declared(wl_interface)
 */
val xdg_toplevel_interface: MemorySegment = SymbolLookup.loaderLookup().find("xdg_toplevel_interface").orElseThrow()

/**
 * NS_ENUM: {@snippet lang=c : enum xdg_wm_base_error}
 */
enum class xdg_wm_base_error(val value: Long) {
    XDG_WM_BASE_ERROR_ROLE(0L), XDG_WM_BASE_ERROR_DEFUNCT_SURFACES(1L), XDG_WM_BASE_ERROR_NOT_THE_TOPMOST_POPUP(2L), XDG_WM_BASE_ERROR_INVALID_POPUP_PARENT(3L), XDG_WM_BASE_ERROR_INVALID_SURFACE_STATE(4L), XDG_WM_BASE_ERROR_INVALID_POSITIONER(5L), XDG_WM_BASE_ERROR_UNRESPONSIVE(6L);
    
    companion object {
        fun fromValue(v: Long): xdg_wm_base_error = entries.firstOrNull { it.value == v }
            ?: error("Unknown xdg_wm_base_error value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum xdg_positioner_error}
 */
enum class xdg_positioner_error(val value: Long) {
    XDG_POSITIONER_ERROR_INVALID_INPUT(0L);
    
    companion object {
        fun fromValue(v: Long): xdg_positioner_error = entries.firstOrNull { it.value == v }
            ?: error("Unknown xdg_positioner_error value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum xdg_positioner_anchor}
 */
enum class xdg_positioner_anchor(val value: Long) {
    XDG_POSITIONER_ANCHOR_NONE(0L), XDG_POSITIONER_ANCHOR_TOP(1L), XDG_POSITIONER_ANCHOR_BOTTOM(2L), XDG_POSITIONER_ANCHOR_LEFT(3L), XDG_POSITIONER_ANCHOR_RIGHT(4L), XDG_POSITIONER_ANCHOR_TOP_LEFT(5L), XDG_POSITIONER_ANCHOR_BOTTOM_LEFT(6L), XDG_POSITIONER_ANCHOR_TOP_RIGHT(7L), XDG_POSITIONER_ANCHOR_BOTTOM_RIGHT(8L);
    
    companion object {
        fun fromValue(v: Long): xdg_positioner_anchor = entries.firstOrNull { it.value == v }
            ?: error("Unknown xdg_positioner_anchor value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum xdg_positioner_gravity}
 */
enum class xdg_positioner_gravity(val value: Long) {
    XDG_POSITIONER_GRAVITY_NONE(0L), XDG_POSITIONER_GRAVITY_TOP(1L), XDG_POSITIONER_GRAVITY_BOTTOM(2L), XDG_POSITIONER_GRAVITY_LEFT(3L), XDG_POSITIONER_GRAVITY_RIGHT(4L), XDG_POSITIONER_GRAVITY_TOP_LEFT(5L), XDG_POSITIONER_GRAVITY_BOTTOM_LEFT(6L), XDG_POSITIONER_GRAVITY_TOP_RIGHT(7L), XDG_POSITIONER_GRAVITY_BOTTOM_RIGHT(8L);
    
    companion object {
        fun fromValue(v: Long): xdg_positioner_gravity = entries.firstOrNull { it.value == v }
            ?: error("Unknown xdg_positioner_gravity value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum xdg_positioner_constraint_adjustment}
 */
enum class xdg_positioner_constraint_adjustment(val value: Long) {
    XDG_POSITIONER_CONSTRAINT_ADJUSTMENT_NONE(0L), XDG_POSITIONER_CONSTRAINT_ADJUSTMENT_SLIDE_X(1L), XDG_POSITIONER_CONSTRAINT_ADJUSTMENT_SLIDE_Y(2L), XDG_POSITIONER_CONSTRAINT_ADJUSTMENT_FLIP_X(4L), XDG_POSITIONER_CONSTRAINT_ADJUSTMENT_FLIP_Y(8L), XDG_POSITIONER_CONSTRAINT_ADJUSTMENT_RESIZE_X(16L), XDG_POSITIONER_CONSTRAINT_ADJUSTMENT_RESIZE_Y(32L);
    
    companion object {
        fun fromValue(v: Long): xdg_positioner_constraint_adjustment = entries.firstOrNull { it.value == v }
            ?: error("Unknown xdg_positioner_constraint_adjustment value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum xdg_surface_error}
 */
enum class xdg_surface_error(val value: Long) {
    XDG_SURFACE_ERROR_NOT_CONSTRUCTED(1L), XDG_SURFACE_ERROR_ALREADY_CONSTRUCTED(2L), XDG_SURFACE_ERROR_UNCONFIGURED_BUFFER(3L), XDG_SURFACE_ERROR_INVALID_SERIAL(4L), XDG_SURFACE_ERROR_INVALID_SIZE(5L), XDG_SURFACE_ERROR_DEFUNCT_ROLE_OBJECT(6L);
    
    companion object {
        fun fromValue(v: Long): xdg_surface_error = entries.firstOrNull { it.value == v }
            ?: error("Unknown xdg_surface_error value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum xdg_toplevel_error}
 */
enum class xdg_toplevel_error(val value: Long) {
    XDG_TOPLEVEL_ERROR_INVALID_RESIZE_EDGE(0L), XDG_TOPLEVEL_ERROR_INVALID_PARENT(1L), XDG_TOPLEVEL_ERROR_INVALID_SIZE(2L);
    
    companion object {
        fun fromValue(v: Long): xdg_toplevel_error = entries.firstOrNull { it.value == v }
            ?: error("Unknown xdg_toplevel_error value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum xdg_toplevel_resize_edge}
 */
enum class xdg_toplevel_resize_edge(val value: Long) {
    XDG_TOPLEVEL_RESIZE_EDGE_NONE(0L), XDG_TOPLEVEL_RESIZE_EDGE_TOP(1L), XDG_TOPLEVEL_RESIZE_EDGE_BOTTOM(2L), XDG_TOPLEVEL_RESIZE_EDGE_LEFT(4L), XDG_TOPLEVEL_RESIZE_EDGE_TOP_LEFT(5L), XDG_TOPLEVEL_RESIZE_EDGE_BOTTOM_LEFT(6L), XDG_TOPLEVEL_RESIZE_EDGE_RIGHT(8L), XDG_TOPLEVEL_RESIZE_EDGE_TOP_RIGHT(9L), XDG_TOPLEVEL_RESIZE_EDGE_BOTTOM_RIGHT(10L);
    
    companion object {
        fun fromValue(v: Long): xdg_toplevel_resize_edge = entries.firstOrNull { it.value == v }
            ?: error("Unknown xdg_toplevel_resize_edge value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum xdg_toplevel_state}
 */
enum class xdg_toplevel_state(val value: Long) {
    XDG_TOPLEVEL_STATE_MAXIMIZED(1L), XDG_TOPLEVEL_STATE_FULLSCREEN(2L), XDG_TOPLEVEL_STATE_RESIZING(3L), XDG_TOPLEVEL_STATE_ACTIVATED(4L), XDG_TOPLEVEL_STATE_TILED_LEFT(5L), XDG_TOPLEVEL_STATE_TILED_RIGHT(6L), XDG_TOPLEVEL_STATE_TILED_TOP(7L), XDG_TOPLEVEL_STATE_TILED_BOTTOM(8L), XDG_TOPLEVEL_STATE_SUSPENDED(9L), XDG_TOPLEVEL_STATE_CONSTRAINED_LEFT(10L), XDG_TOPLEVEL_STATE_CONSTRAINED_RIGHT(11L), XDG_TOPLEVEL_STATE_CONSTRAINED_TOP(12L), XDG_TOPLEVEL_STATE_CONSTRAINED_BOTTOM(13L);
    
    companion object {
        fun fromValue(v: Long): xdg_toplevel_state = entries.firstOrNull { it.value == v }
            ?: error("Unknown xdg_toplevel_state value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum xdg_toplevel_wm_capabilities}
 */
enum class xdg_toplevel_wm_capabilities(val value: Long) {
    XDG_TOPLEVEL_WM_CAPABILITIES_WINDOW_MENU(1L), XDG_TOPLEVEL_WM_CAPABILITIES_MAXIMIZE(2L), XDG_TOPLEVEL_WM_CAPABILITIES_FULLSCREEN(3L), XDG_TOPLEVEL_WM_CAPABILITIES_MINIMIZE(4L);
    
    companion object {
        fun fromValue(v: Long): xdg_toplevel_wm_capabilities = entries.firstOrNull { it.value == v }
            ?: error("Unknown xdg_toplevel_wm_capabilities value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum xdg_popup_error}
 */
enum class xdg_popup_error(val value: Long) {
    XDG_POPUP_ERROR_INVALID_GRAB(0L);
    
    companion object {
        fun fromValue(v: Long): xdg_popup_error = entries.firstOrNull { it.value == v }
            ?: error("Unknown xdg_popup_error value: $v")
    }
}

/**
 * {@snippet lang=c : zxdg_decoration_manager_v1_interface Declared(wl_interface)
 */
val zxdg_decoration_manager_v1_interface: MemorySegment = SymbolLookup.loaderLookup().find("zxdg_decoration_manager_v1_interface").orElseThrow()

/**
 * {@snippet lang=c : zxdg_toplevel_decoration_v1_interface Declared(wl_interface)
 */
val zxdg_toplevel_decoration_v1_interface: MemorySegment = SymbolLookup.loaderLookup().find("zxdg_toplevel_decoration_v1_interface").orElseThrow()

/**
 * NS_ENUM: {@snippet lang=c : enum zxdg_toplevel_decoration_v1_error}
 */
enum class zxdg_toplevel_decoration_v1_error(val value: Long) {
    ZXDG_TOPLEVEL_DECORATION_V1_ERROR_UNCONFIGURED_BUFFER(0L), ZXDG_TOPLEVEL_DECORATION_V1_ERROR_ALREADY_CONSTRUCTED(1L), ZXDG_TOPLEVEL_DECORATION_V1_ERROR_ORPHANED(2L), ZXDG_TOPLEVEL_DECORATION_V1_ERROR_INVALID_MODE(3L);
    
    companion object {
        fun fromValue(v: Long): zxdg_toplevel_decoration_v1_error = entries.firstOrNull { it.value == v }
            ?: error("Unknown zxdg_toplevel_decoration_v1_error value: $v")
    }
}

/**
 * NS_ENUM: {@snippet lang=c : enum zxdg_toplevel_decoration_v1_mode}
 */
enum class zxdg_toplevel_decoration_v1_mode(val value: Long) {
    ZXDG_TOPLEVEL_DECORATION_V1_MODE_CLIENT_SIDE(1L), ZXDG_TOPLEVEL_DECORATION_V1_MODE_SERVER_SIDE(2L);
    
    companion object {
        fun fromValue(v: Long): zxdg_toplevel_decoration_v1_mode = entries.firstOrNull { it.value == v }
            ?: error("Unknown zxdg_toplevel_decoration_v1_mode value: $v")
    }
}

