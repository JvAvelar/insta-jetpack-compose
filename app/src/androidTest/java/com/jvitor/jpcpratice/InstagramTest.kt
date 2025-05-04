package com.jvitor.jpcpratice

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.jvitor.jpcpratice.ui.view.components.ItemFeatured
import com.jvitor.jpcpratice.ui.view.components.ItemPost
import com.jvitor.jpcpratice.ui.view.screen.Home
import com.jvitor.jpcpratice.ui.view.screen.PrimaryScreen
import com.jvitor.jpcpratice.ui.view.screen.SecondScreen
import com.jvitor.jpcpratice.viewmodel.ScreenViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InstagramTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController
    private lateinit var viewModel: ScreenViewModel

    @Before
    fun setUp() {
        // Inicializa a viewModel
        viewModel = ScreenViewModel().apply {
            getFeatured()
            getPost()
        }

        // Inicializa o NavController
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        navController.navigatorProvider.addNavigator(ComposeNavigator())
    }

    @Composable
    private fun SetUpNavigation() {
        NavHost(navController, startDestination = "home") {
            composable("home") {
                Home(viewModel, navController)
                viewModel.getFeatured()
                viewModel.getPost()
            }
            composable("primary") { PrimaryScreen(navController) }
            composable("second") { SecondScreen(navController) }
        }
    }

    @Test
    fun testItemFeatured() {
        // Configuração das rotas de navegação
        composeTestRule.setContent {
            ItemFeatured(viewModel, navController)
        }

        // Tags dos componentes da tela Featured
        val tagLazy = "lazy_row_featured"
        val tagColumn = "column_featured_items"
        val tagImage = "img_profile_photo_featured"
        val tagName = "txt_user_name_featured"

        // Scroll do LazyRow (selecionado o componente bem específico)
        composeTestRule.onNode(
            hasTestTag(tagLazy) and hasScrollAction()
        ).apply {
            assertExists("O componente LazyRow não existe na tela ItemFeatured")
            assertIsDisplayed()
            performScrollToIndex(0)
        }

        // Foto do usuario
        val valueRender = 6 // quantidade de itens renderizado na tela

        // Verifica se está renderizando os 6 destaques na tela
        composeTestRule.onAllNodesWithTag(tagImage)
            .apply { assertCountEquals(valueRender) }

        // Verifica se está mostrando as fotos
        composeTestRule.onAllNodes(
            hasTestTag(tagImage) and hasParent(hasTestTag(tagColumn))
        )
            .apply { assertCountEquals(valueRender) }

        // Verifica se está mostrando os nomes
        composeTestRule.onAllNodes(
            hasTestTag(tagName) and hasParent(hasTestTag(tagColumn))
        ).apply { assertCountEquals(valueRender) }

    }

    @Test
    fun testItemPost() {

        composeTestRule.setContent {
            ItemPost(viewModel, navController)
        }

        // number of items


        // profile photo


        // Rolar através de todos os itens

        // Click no botão
        composeTestRule.onNodeWithTag("btn_like").performClick()


    }


}