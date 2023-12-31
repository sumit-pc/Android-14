package me.globant.android14.activities

import android.app.GrammaticalInflectionManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import me.globant.android14.R
import me.globant.android14.nav.Destination
import me.globant.android14.screens.grammar.GrammarScreen
import me.globant.android14.ui.composables.SetupM3Scaffold
import me.globant.android14.ui.theme.Android14Theme
import me.globant.android14.viewmodels.GrammaticalInflectionViewModel

class GrammaticalInflectionActivity : AppCompatActivity() {

    private val viewModel: GrammaticalInflectionViewModel by viewModels()

    private val grammaticalInflectionManager by lazy {
        getSystemService(GrammaticalInflectionManager::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android14Theme {
                SetupM3Scaffold(Destination.GrammaticalInflection) { paddingValues ->
                    GrammarScreen(
                        paddingValues,
                        viewModel,
                        word = stringResource(id = R.string.word),
                        sentence = stringResource(id = R.string.sentence),
                        onLanguageChange = {
                            AppCompatDelegate.setApplicationLocales(viewModel.currentLanguage.toLocaleList())
                            grammaticalInflectionManager.setRequestedApplicationGrammaticalGender(
                                viewModel.currentGender.inflection
                            )
                        },
                        onGenderChange = {
                            grammaticalInflectionManager.setRequestedApplicationGrammaticalGender(
                                viewModel.currentGender.inflection
                            )
                        }
                    )
                }
            }
        }
    }

    companion object {
        fun start(activity: ComponentActivity) {
            val intent = Intent(activity, GrammaticalInflectionActivity::class.java)
            activity.startActivity(intent)
        }
    }
}

@Preview
@Composable
fun GrammarLightPreview() {
    Android14Theme {
        SetupM3Scaffold(Destination.GrammaticalInflection) { paddingValues ->
            GrammarScreen(paddingValues)
        }
    }
}


@Preview
@Composable
fun GrammarDarkPreview() {
    Android14Theme(useDarkTheme = true) {
        SetupM3Scaffold(Destination.GrammaticalInflection) { paddingValues ->
            GrammarScreen(paddingValues)
        }
    }
}