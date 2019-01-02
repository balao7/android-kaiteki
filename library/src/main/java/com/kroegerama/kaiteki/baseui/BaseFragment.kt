package com.kroegerama.kaiteki.baseui

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

abstract class BaseFragment : Fragment() {

    protected val preferences: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepare()
        arguments?.let {
            handleArguments(it)
        }
        loadPreferences(preferences)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (container == null) {
            return null
        }
        return inflater.inflate(layoutResource, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupGUI()

        savedInstanceState?.let {
            loadState(it)
        }
    }

    protected fun <T : BaseActivity> getBaseActivity(): T {
        return activity as T
    }

    override fun onStart() {
        super.onStart()
        run()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        saveState(outState)
    }

    override fun onPause() {
        super.onPause()
        savePreferences(preferences)
    }

    protected abstract val layoutResource: Int

    protected open fun prepare() {}

    protected open fun setupGUI() {}

    protected open fun run() {}

    protected open fun handleArguments(args: Bundle) {}

    protected open fun loadPreferences(prefs: SharedPreferences) {}

    protected open fun loadState(state: Bundle) {}

    protected open fun saveState(outState: Bundle) {}

    protected open fun savePreferences(outPrefs: SharedPreferences) {}

    @get:StringRes
    open val title: Int = android.R.string.unknownName

    open fun handleBackPress(): Boolean {
        return false
    }

    open fun decorateTransaction(transaction: FragmentTransaction) {}
}