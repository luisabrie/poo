package poo.grafos.busca;

import poo.grafos.Ciudad;
import poo.grafos.MapaCarreteras;

import java.util.*;

public class BreadthFirst implements Buscador {

  @Override
  public List<Ciudad> ruta(Ciudad origen, Ciudad destino, MapaCarreteras mapa) {
    // Guardando aquí una ciudad, la marcamos como visitada
    Set<Ciudad> visitadas = new HashSet<>();

    // Cola de las ciudades que nos quedan por visitar
    List<Ciudad> colaPorVisitar = new LinkedList<>();

    // Vamos guardando los distintos caminos que se van recorriendo
    List<List<Ciudad>> colaCaminos = new LinkedList<>();

    // Todo camino contendrá al menos la ciudad de origen
    List<Ciudad> camino = new ArrayList<>();
    camino.add(origen);

    // Añadir a la cola la ciudad desde la que se empieza
    colaPorVisitar.add(origen);
    // Añadir a la cola de caminos que se van trazando el camino con sólo la ciudad origen
    colaCaminos.add(camino);

    while (!colaPorVisitar.isEmpty()) {
      // Sacamos una ciudad de la cola
      Ciudad actual = colaPorVisitar.remove(0);
      // Sacamos el camino hasta llegar hasta la ciudad actual
      camino = colaCaminos.remove(0);

      // Si ya está visitada, vamos a la siguiente
      if (visitadas.contains(actual)) {
        continue;
      }
      visitadas.add(actual);

      // Si hemos llegado al destino, retornamos el camino
      if (actual.equals(destino)) {
        return camino;
      }

      // Si no, buscamos todos los nodos adyacentes y los añadimos a la cola.
      // También añadimos nuevos caminos a la cola de caminos
      for (Ciudad adyacente : mapa.getConexiones().get(actual)) {
        colaPorVisitar.add(adyacente);
        // clonamos una nueva lista, igual al camino actual, y añadimos la ciudad adyacente
        List<Ciudad> clonCamino = new ArrayList<>(camino);
        clonCamino.add(adyacente);
        colaCaminos.add(clonCamino);
      }
    }

    // Si hemos llegado aquí, se han agotado todas las ciudades por visitar --> no hay ruta posible
    return null;
  }


}