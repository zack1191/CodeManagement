package com.hha.codemanagement.utils

data class DataWrapper<out T>(val data : T?, val errorMessage : String?)
{

}