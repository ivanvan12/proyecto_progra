import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;
import java.util.*;
 
public class Programa{
 
    public static String convertirUnicode(String letra,String cadena){

        StringBuilder str = new StringBuilder(cadena);
        int indice = -1;
        char caracter = 0;
        indice = str.lastIndexOf(letra);

        if(indice>=0 )
        {
            switch(letra){
                case "Á": caracter = '\u00C1';
                          break;
                case "á": caracter = '\u00E1';
                          break;
                case "é": caracter = '\u00E9';
                          break;
                case "í": caracter = '\u00ED';
                          break;
                case "ó": caracter = '\u00F3';
                          break;
                case "ú": caracter = '\u00FA';
                          break;
                case "ñ": caracter = '\u00F1';
                          break;
            }
            // System.out.println("\\u" + Integer.toHexString('÷' | 0x10000).substring(1));
            str.replace(indice,indice+1,""+caracter);
        }
 
        return str.toString();
    }
 
    public static void imprimir(String cadena)
    {
        String str; 
        str = convertirUnicode("Á",cadena);
        str = convertirUnicode("á",str);
        str = convertirUnicode("é",str);
        str = convertirUnicode("í",str);
        str = convertirUnicode("ó",str);
        str = convertirUnicode("ú",str);
        str = convertirUnicode("ñ",str);
 
        System.out.println(str);
    }
 
    public static StringBuilder obtenerLetraCancion(int inicio,int fin, String[]data)
    {
        StringBuilder str = new StringBuilder();
        StringTokenizer temp;
 
        for(int i = inicio; i<=fin; i++)
        {
            //System.out.println("@  "+data[i]);
 
            temp = new StringTokenizer(data[i],";");
 
            while(temp.hasMoreTokens())
            {
                System.out.print(temp.nextToken()+" ");
            }
            System.out.println();
            //str.append(data[i]+"\n");
        }
 
        return str;
    }

public static void menu(){
		  System.out.println("_____________________________________________________________________________ ");
          System.out.println("|             _  __               _                        _                 |");
          System.out.println("|           | |/ /              | |                      | |                 |"); 
          System.out.println("|           | ' / __ _ _ __ ___ | | _____  __ _ _ __   __| | ___             |"); 
          System.out.println("|           |  < / _` | '__/ _ \\| |/ / _ \\/ _` | '_ \\ / _` |/ _ \\        |"); 
          System.out.println("|           | . \\ (_| | | | (_) |   <  __/ (_| | | | | (_| | (_) |           |"); 
          System.out.println("|           |_|\\_\\__,_|_|  \\___/|_|\\_\\___|\\__,_|_| |_|\\__,_|\\___/    |"); 
          System.out.println("|                                                                             |");
          System.out.println("|                                                                             |"); 

        System.out.println("|                                 /|                                           |");
        System.out.println("|                   =  =  =      / |                                           |");
        System.out.println("|              ____| || || |____/  | -_-_-_-_-_-_                             |");
        System.out.println("|            |)----| || || |____   |     AH                                    |");
        System.out.println("|              ((  | || || |  ))\\  | _-_-_-_-_-_-                             |");
        System.out.println("|               \\\\_|_||_||_|_//  \\ |                                        |");
        System.out.println("|                \\___________/    \\|                                         |");

 

		imprimir("    |                                                                            |");
		imprimir("    |                    Ingrese una opción así:                                 |");         
		imprimir("    |                    1. Buscar canción                                       |");
		imprimir("    |                    2. Reproducir canción                                   |");
		imprimir("    |                    3. Mostrar Letra                                        |");
		imprimir("    |                    4. Detener Canción                                      |");
		imprimir("    |                    5. Imprimir lista de Canciones                          |");
		imprimir("    |                    6. Crear lista de reproduccion                          |");
		imprimir("    |                    7. Salir                                                |");
		imprimir("    |____________________________________________________________________________|");
	}

	public static void main(String[] args) {
		
		Audio audio = new Audio();
		int centinela = 0;	
		int indice_cancion = 0;
		int inicio_letra = 0, fin_letra = 0;
		String [] canciones;
		String [][] info_canciones;
		StringBuilder letra_cancion;

		canciones = ConsoleFile.readBigFile("recursos/letras.csv");
		info_canciones = ConsoleData.dataList(canciones);

		try{
			
			do{

				System.out.println();
				menu();

				centinela = ConsoleInput.getInt();

				if(centinela == 1)
				{
					// Para cada archivo de cada cancion existe un numero que lo reproduce
					
					imprimir("Ingrese indice de la cancion, entre 0 y "+(info_canciones.length-1));
					indice_cancion = ConsoleInput.getInt();
					audio.seleccionarCancion(info_canciones[indice_cancion][ConsoleData.RUTA_CANCION]);

					System.out.println();
					imprimir("Inicio letra "+inicio_letra);
					imprimir("Fin letra "+fin_letra);
					imprimir("Nombre "+info_canciones[indice_cancion][ConsoleData.NOMBRE_CANCION]);
					imprimir("Autor "+info_canciones[indice_cancion][ConsoleData.AUTOR_CANCION]);
					imprimir("Archivo "+info_canciones[indice_cancion][ConsoleData.RUTA_CANCION]);
					

					imprimir("Primera estrofa: "+canciones[inicio_letra]);
					imprimir("Última estrofa: "+canciones[fin_letra]);
					
				}

				// Centinela 2 reproduce la cancion que se le dice segun el indice con la funcion de audio.reproducir
				if(centinela == 2)
				{					
					imprimir("Ingrese indice de la cancion, entre 0 y "+(info_canciones.length-1));
					indice_cancion = ConsoleInput.getInt();
					audio.seleccionarCancion(info_canciones[indice_cancion][ConsoleData.RUTA_CANCION]);
					audio.reproducir();
				}

				if(centinela == 3)
				{					

					imprimir("Ingrese indice de la cancion, entre 0 y "+(info_canciones.length-1));
					indice_cancion = ConsoleInput.getInt();

					inicio_letra = ConsoleInput.string(info_canciones[indice_cancion][ConsoleData.INICIO_CANCION]);
					fin_letra = ConsoleInput.string(info_canciones[indice_cancion][ConsoleData.FIN_CANCION]);
					
					imprimir(letra_cancion.toString());
				}

				if(centinela == 4)
				{
					audio.detener();
				}

				if(centinela==5)
				{

					//Se valida el valor ingresado y se da por correcta la relacion
					imprimir("Ingrese indice de la cancion, entre 0 y "+(info_canciones.length-1));
					indice_cancion = ConsoleInput.getInt();

					inicio_letra = ConsoleInput.stringToInt(info_canciones[indice_cancion][ConsoleData.INICIO_CANCION]);
					fin_letra = ConsoleInput.stringToInt(info_canciones[indice_cancion][ConsoleData.FIN_CANCION]);

					System.out.println();
					imprimir("Inicio letra "+inicio_letra);
					imprimir("Fin letra "+fin_letra);
					imprimir("Nombre "+info_canciones[indice_cancion][ConsoleData.NOMBRE_CANCION]);
					imprimir("Autor "+info_canciones[indice_cancion][ConsoleData.AUTOR_CANCION]);
					imprimir("Archivo "+info_canciones[indice_cancion][ConsoleData.RUTA_CANCION]);

					imprimir("Primera estrofa: "+canciones[inicio_letra]);
					imprimir("Última estrofa: "+canciones[fin_letra]);
					//Cada linea es impresa desde la funcion imprimir, que contiene el String de las canciones 					
					
				}

				if(centinela==6)
				{

				do
					{	
					System.out.println("¿Cuantas canciones desea agregar?");
					int numero_canciones= ConsoleInput.getInt();

					int r = 1;
					
				    imprimir("Ingrese indice de la cancion, entre 0 y "+(info_canciones.length-1));
					indice_cancion = ConsoleInput.getInt();
					audio.seleccionarCancion(info_canciones[indice_cancion][ConsoleData.RUTA_CANCION]);

					System.out.println();
					imprimir("Inicio letra "+inicio_letra);
					imprimir("Fin letra "+fin_letra);
					imprimir("Nombre "+info_canciones[indice_cancion][ConsoleData.NOMBRE_CANCION]);
					imprimir("Autor "+info_canciones[indice_cancion][ConsoleData.AUTOR_CANCION]);
					imprimir("Archivo "+info_canciones[indice_cancion][ConsoleData.RUTA_CANCION]);

				r++;

				} while(numero_canciones > r); 

		    }


			}while(centinela!=7);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally{
			audio.detener();
		}
	}
}