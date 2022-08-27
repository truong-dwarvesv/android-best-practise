package com.df.android.utils

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import java.io.Serializable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T : Any> argument(key: String? = null, default: T? = null): ReadWriteProperty<Fragment, T> = FragmentArgumentDelegate(key, default)
fun <T : Any> argumentNullable(key: String? = null, default: T? = null): ReadWriteProperty<Fragment, T?> = FragmentNullableArgumentDelegate(key, default)

class FragmentArgumentDelegate<T : Any>(private val customKey: String?, val default: T?) : ReadWriteProperty<Fragment, T> {

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val key = customKey ?: property.name
        val value = thisRef.arguments?.get(key)?.let { it as? T } ?: default
        return value ?: error("Argument $key could not be read")
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        val key = customKey ?: property.name
        val args = thisRef.arguments ?: Bundle().also(thisRef::setArguments)
        args.put(key, value)
    }
}

class FragmentNullableArgumentDelegate<T : Any?>(private val customKey: String?, val default: T?) : ReadWriteProperty<Fragment, T?> {

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T? {
        val key = customKey ?: property.name
        return thisRef.arguments?.get(key)?.let { it as? T } ?: default
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T?) {
        val key = customKey ?: property.name
        val args = thisRef.arguments ?: Bundle().also(thisRef::setArguments)
        value?.let { args.put(key, it) } ?: args.remove(key)
    }
}

fun <T> Bundle.put(key: String, value: T) {
    when (value) {
        is Boolean -> putBoolean(key, value)
        is String -> putString(key, value)
        is Int -> putInt(key, value)
        is Short -> putShort(key, value)
        is Long -> putLong(key, value)
        is Byte -> putByte(key, value)
        is ByteArray -> putByteArray(key, value)
        is Char -> putChar(key, value)
        is CharArray -> putCharArray(key, value)
        is CharSequence -> putCharSequence(key, value)
        is Float -> putFloat(key, value)
        is Bundle -> putBundle(key, value)
        is Parcelable -> putParcelable(key, value)
        is Serializable -> putSerializable(key, value)
        else -> error("Type of property $key is not supported")
    }
}