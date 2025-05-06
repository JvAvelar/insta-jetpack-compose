package com.jvitor.jpcpratice

import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.jvitor.jpcpratice.ui.view.components.Featured
import com.jvitor.jpcpratice.ui.view.components.Post
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

    @Test
    fun testFeatured() {
        // Configuração das rotas de navegação
        composeTestRule.setContent {
            Featured(viewModel)
        }
        // quantidade de itens renderizado na tela
        val quantityRenderized = 6

        // Tags dos componentes da tela Featured
        val tagLazy = "lazy_row_featured"
        val tagColumn = "column_featured_items"
        val tagImage = "img_profile_photo_featured"
        val tagName = "txt_user_name_featured"

        // Scroll do LazyRow
        composeTestRule.onNode(
            hasTestTag(tagLazy) and hasScrollAction()
        ).apply {
            assertExists("O componente LazyRow não existe na tela ItemFeatured")
            assertIsDisplayed()
            performScrollToIndex(quantityRenderized)
        }

        // Esperar a animação
        composeTestRule.waitForIdle()

        // Verifica se está renderizando os 6 destaques na tela
        composeTestRule.onAllNodesWithTag(tagImage)
            .assertCountEquals(quantityRenderized)

        // Verifica se está mostrando as fotos de perfil dos destaques
        composeTestRule.onAllNodes(
            hasTestTag(tagImage) and hasParent(hasTestTag(tagColumn))
        )
            .assertCountEquals(quantityRenderized)

        // Verifica se está mostrando os nomes nos destaques
        composeTestRule.onAllNodes(
            hasTestTag(tagName) and hasParent(hasTestTag(tagColumn))
        ).assertCountEquals(quantityRenderized)
    }

    @Test
    fun testProfilePost() {
        composeTestRule.setContent {
            Post(viewModel, navController)
        }
        // quantidade de itens renderizado na tela
        val quantityRenderized = 2

        // Tags dos componentes da tela Post
        val tagLazyColumnPost = "lazy_column_post"
        val tagRowProfile = "row_main_profile_post"
        val tagImgProfile = "img_profile_photo_post"
        val tagTextProfile = "txt_profile_name_post"
        val tagBtnProfile = "btn_profile_menu_post"

        // Esperar renderizar normalmente
        composeTestRule.waitForIdle()

        // Scroll do LazyColumn
        composeTestRule.onNode(
            hasTestTag(tagLazyColumnPost) and
                    hasScrollAction()
        ).apply {
            assertExists("O componente $tagLazyColumnPost não existe na tela")
            assertIsDisplayed()
            performScrollToIndex(quantityRenderized)
        }

        // Verifica se está mostrando a foto de perfil
        composeTestRule.onAllNodesWithTag(tagImgProfile)
            .onFirst()
            .assertExists()
            .assertIsDisplayed()

        // Verifica se está mostrando o nome do usuário
        composeTestRule.onAllNodesWithTag(tagTextProfile)
            .onFirst()
            .assertExists()
            .assertIsDisplayed()

        // Verifica se está mostrando o icone do perfil
        composeTestRule.onAllNodesWithTag(tagBtnProfile)
            .onFirst()
            .assertExists("O component $tagBtnProfile não existe na tela")
            .assertIsDisplayed()
    }

    @Test
    fun testPostedPhotoPost() {
        composeTestRule.setContent {
            Post(viewModel, navController)
        }
        // Tags foto postada
        val tagBox = "box_main_photo_post"
        val tagImg = "img_posted_photo_post"

        // Verifica se mostra a foto que foi postada
        composeTestRule.onAllNodes(
            hasTestTag(tagImg) and
                    hasParent(hasTestTag(tagBox))
        )
            .onFirst()
            .apply {
                assertExists("O componente $tagImg não está mostrado na tela")
                assertIsDisplayed()
                assertContentDescriptionEquals("post photo")
            }
    }

    @Test
    fun testIconsInteractionPost() {
        composeTestRule.setContent {
            Post(viewModel, navController)
        }
        // Tags icones de interação
        val tagRow = "row_main_icons_post"
        val tagBtnLike = "btn_like_post"
        val tagBtnSave = "btn_save_post"

        // Do something
        // Clica no botão de like
        composeTestRule.onAllNodes(
            hasTestTag(tagBtnLike) and
            hasParent(hasTestTag(tagRow))
        )
            .onFirst()
            .performClick()

        // Clica no botão de salvar
        composeTestRule.onAllNodes(
            hasTestTag(tagBtnSave) and
                    hasParent(hasTestTag(tagRow))
        )
            .onFirst()
            .performClick()

        // Check something
        // Verifica se os botões de like e save mudou a cor e a descrição de acordo com o click
        composeTestRule.onNodeWithContentDescription("liked")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("saved")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testDescriptionAndTimePost() {
        composeTestRule.setContent {
            Post(viewModel, navController)
        }
        // Tags descrição, nome e tempo
        val tagColumn = "column_main_description_post"
        val tagRow = "row_name_and_description_post"
        val tagTxtName = "txt_name_description_post"
        val tagTxtDescription = "txt_description_post"
        val tagTxtTime = "txt_time_post"

        // Verifica se o nome está sendo mostrado
        composeTestRule.onAllNodes(
            hasTestTag(tagTxtName) and
            hasParent(hasTestTag(tagRow))
        )
            .onFirst()
            .assertExists("O componente $tagTxtName não existe na tela")
            .assertIsDisplayed()

        // Verifica se a descrição está sendo mostrada
        composeTestRule.onAllNodes(
            hasTestTag(tagTxtDescription) and
            hasParent(hasTestTag(tagRow))
        )
            .onFirst()
            .assertExists("O componente $tagTxtDescription não existe na tela")
            .assertIsDisplayed()

        // Verifica se o time está sendo mostrado
        composeTestRule.onAllNodes(
            hasTestTag(tagTxtTime) and
            hasParent(hasTestTag(tagColumn))
        )
            .onFirst()
            .assertExists("O componente $tagTxtTime não existe na tela")
            .assertIsDisplayed()
    }
}