package com.zack.socketdemo.base

class BaseContract{
    interface BasePresenter{

    }

    interface BaseView{
        fun getActivity(): BaseActivity
    }
}