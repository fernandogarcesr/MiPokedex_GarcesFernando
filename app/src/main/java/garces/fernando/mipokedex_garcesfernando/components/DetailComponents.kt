package garces.fernando.mipokedex_garcesfernando.components

import android.R.attr.text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import garces.fernando.mipokedex_garcesfernando.R

//piezas chicas, una debajo de otra

//encabezado con el nombre y el numero abajo
@Composable
fun PokemonHeader(name: String, number: String,modifier: Modifier = Modifier) {
    //columna sin fillmazwidth mide lo que mide su hijo mas ancho que es el nombre y
    //horizontalAlignment end pone el numero en el borde derecho de la columna
    Column(modifier = modifier, horizontalAlignment = Alignment.End) {
        Text(text = name, color = Color.White, fontSize = 36.sp, fontWeight = FontWeight.Bold)
        Text(text = number, color = colorResource(R.color.pokedex_text), fontSize = 20.sp)

    }
}

// tipo "Electrico" texto con fondo en forma de píldora
@Composable
fun TypeBadge(type: String, modifier: Modifier = Modifier) {
    Text(
        text = type,
        color = Color.Black,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .background(colorResource(R.color.pokedex_badge), RoundedCornerShape(20.dp))
            .padding(horizontal = 20.dp, vertical = 6.dp)
    )
}

// label rojo en negritas y valor normal
@Composable
fun StatLabel(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = colorResource(R.color.pokedex_red),
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
fun StatValue(text: String, modifier: Modifier = Modifier) {
    Text(text = text, color = colorResource(R.color.pokedex_text), fontSize = 22.sp, modifier = modifier)
}

// pokemon anterior o siguiente, imagen arriba y debajo una fila
// Un solo componente para los dos
@Composable
fun NeighborPokemon(
    imageRes: Int,
    imageDescription: String,
    label: String,
    isPrevious: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val arrowIcon = if (isPrevious) R.drawable.ic_arrow_left else R.drawable.ic_arrow_right
    val arrowDescription = stringResource(if (isPrevious) R.string.cd_previous else R.string.cd_next)

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = imageDescription,
            modifier = Modifier.size(80.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (isPrevious) NavArrowButton(arrowIcon, arrowDescription, onClick)   // flecha ANTES del texto
            Text(text = label, color = colorResource(R.color.pokedex_text), fontSize = 14.sp)
            if (!isPrevious) NavArrowButton(arrowIcon, arrowDescription, onClick)  // flecha DESPUÉS del texto
        }
    }
}

// private,solo se puede usar dentro de este archivo (pieza auxiliar de NeighborPokemon)
@Composable
private fun NavArrowButton(iconRes: Int, description: String, onClick: () -> Unit){
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(colorResource(R.color.pokedex_dark))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = description,
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )
    }

}