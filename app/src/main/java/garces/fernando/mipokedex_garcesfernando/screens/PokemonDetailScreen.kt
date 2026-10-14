package garces.fernando.mipokedex_garcesfernando.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import garces.fernando.mipokedex_garcesfernando.R
import garces.fernando.mipokedex_garcesfernando.components.*
import garces.fernando.mipokedex_garcesfernando.ui.theme.MiPokedex_GarcesFernandoTheme // revisa TU nombre en ui/theme/Theme.kt


@Composable
fun PokemonDetailScreen(modifier: Modifier = Modifier) {

    // estado, lo único que cambia en esta pantalla es si la estrella está activada
    // rememberSaveable la conserva aunque gire el teléfono
    var isFavorite by rememberSaveable { mutableStateOf(false) }

    //Box apila, fondo amarillo en TODA la pantalla
    Box(modifier = modifier.fillMaxSize().background(colorResource(R.color.pokedex_yellow))) {

        // pokebola, queda al fondo. offset la empuja a la derecha y el borde de la pantalla la corta
        Image(
            painter = painterResource(R.drawable.ic_pokeball),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 50.dp, y = 40.dp)
                .size(250.dp)
        )

        // toda la pantalla dividida en 30% (arriba) y 70% parte blanca
        Column(modifier = Modifier.fillMaxSize()) {

            // parte arriba, encabezado, estrella y Pikachu
            Box(
                modifier = Modifier
                    .weight(0.30f)
                    .fillMaxWidth()
                    .zIndex(1f)   // se dibuja arriba de la tarjeta, así el pikachu se ve encima
            ) {
                PokemonHeader(
                    name = stringResource(R.string.pokemon_name),
                    number = stringResource(R.string.pokemon_number),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .statusBarsPadding()
                        .padding(start = 24.dp, top = 24.dp)
                )

                Icon(
                    painter = painterResource(R.drawable.ic_star),
                    contentDescription = stringResource(R.string.cd_favorite),
                    tint = if (isFavorite) colorResource(R.color.pokedex_red) else Color.White,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .statusBarsPadding()
                        .padding(end = 8.dp, top = 8.dp)
                        .clickable { isFavorite = !isFavorite }// al tocar, cambio el estado
                        .padding(8.dp)
                        .size(32.dp)
                )

                Image(
                    painter = painterResource(R.drawable.pikachu),
                    contentDescription = stringResource(R.string.cd_pikachu),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = 30.dp)
                        .size(160.dp)
                )
            }

            // parte inferior, la tarjeta blanca
            Column(
                modifier = Modifier
                    .weight(0.70f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))  // 1) solo esquinas de arriba
                    .background(Color.White)
                    .navigationBarsPadding()
                    .padding(horizontal = 24.dp, vertical = 8.dp)
            ) {
                Spacer(modifier = Modifier.height(32.dp))   // espacio para los pies de Pikachu

                TypeBadge(
                    type = stringResource(R.string.pokemon_type),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // izquierda (Altura y Peso) y derecha (Habilidad), mitad y mitad
                Row(modifier = Modifier.fillMaxWidth()) {

                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // columna de labels, alineados a la derecha entre sí
                        Column(
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            StatLabel(stringResource(R.string.label_height))
                            StatLabel(stringResource(R.string.label_weight))
                        }
                        // columna de valores
                        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                            StatValue(stringResource(R.string.value_height))
                            StatValue(stringResource(R.string.value_weight))
                        }
                    }

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        StatLabel(stringResource(R.string.label_ability))
                        StatValue(stringResource(R.string.value_ability))
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = stringResource(R.string.pokemon_description),
                    color = colorResource(R.color.pokedex_text),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                )

                // Spacer con weight(1f)
                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,   // uno en cada extremo
                    verticalAlignment = Alignment.Bottom
                ) {
                    // mismo componente, dos veces
                    NeighborPokemon(
                        imageRes = R.drawable.arbok,
                        imageDescription = stringResource(R.string.cd_arbok),
                        label = stringResource(R.string.previous_pokemon),
                        isPrevious = true,
                        onClick = { }
                    )
                    NeighborPokemon(
                        imageRes = R.drawable.raichu,
                        imageDescription = stringResource(R.string.cd_raichu),
                        label = stringResource(R.string.next_pokemon),
                        isPrevious = false,
                        onClick = { }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PokemonDetailScreenPreview() {
    MiPokedex_GarcesFernandoTheme {
        PokemonDetailScreen()
    }
}
