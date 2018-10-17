/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.adssched.di

import android.content.Context
import com.google.samples.apps.adssched.shared.data.login.StagingAuthenticatedUser
import com.google.samples.apps.adssched.shared.data.login.StagingSignInHandler
import com.google.samples.apps.adssched.shared.data.login.datasources.StagingAuthStateUserDataSource
import com.google.samples.apps.adssched.shared.data.signin.datasources.AuthIdDataSource
import com.google.samples.apps.adssched.shared.data.signin.datasources.AuthStateUserDataSource
import com.google.samples.apps.adssched.shared.domain.sessions.NotificationAlarmUpdater
import com.google.samples.apps.adssched.util.signin.SignInHandler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class SignInModule {
    @Provides
    fun provideSignInHandler(context: Context): SignInHandler {
        return StagingSignInHandler(StagingAuthenticatedUser(context))
    }

    @Singleton
    @Provides
    fun provideAuthStateUserDataSource(
        context: Context,
        notificationAlarmUpdater: NotificationAlarmUpdater
    ): AuthStateUserDataSource {
        return StagingAuthStateUserDataSource(
            isRegistered = true,
            isSignedIn = true,
            context = context,
            userId = "StagingTest",
            notificationAlarmUpdater = notificationAlarmUpdater
        )
    }

    @Singleton
    @Provides
    fun providesAuthIdDataSource(
    ): AuthIdDataSource {
        return object: AuthIdDataSource {
            override fun getUserId() = "StagingTest"
        }
    }
}
