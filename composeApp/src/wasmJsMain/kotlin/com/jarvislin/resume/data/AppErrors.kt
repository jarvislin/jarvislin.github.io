package com.jarvislin.resume.data

open class AppError(message: String) : Throwable(message)

data object EmailFormatError : AppError("Invalid email format")
data object EmptyNameError : AppError("Name cannot be empty")
data object EmptyMessageError : AppError("Message cannot be empty")
data object UnknownError : AppError("Unknown error occurred")