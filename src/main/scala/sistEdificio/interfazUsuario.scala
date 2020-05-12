package sistEdificio

import ocupacion._
import salon._
import usuario._
import scala.util._
import scala.io._

object interfazUsuario extends App
{
    var sistemaCerrado : Boolean = false
    var lagos : sistema = new sistema()
    while(sistemaCerrado == false)
    {
        bienvenida()
        println("Cerrado")
        sistemaCerrado = true
    }

    def bienvenida() : Unit =
    {
        println("|| Hora: " + lagos._hora + " : "  + lagos._min + " ||")
        println("Bievenido al edificio inteligente de la Pontificia Universidad Javeriana")
        println("Por favor inicie sesion o registrese para continuar")
        println("1-> Registrarse \n2-> Iniciar sesión")
        var opcion : Int = StdIn.readInt()
        opcion match
        {
            case 1 => registrar()
            case 2 => login()
        } 
    }

    def registrar() : Unit = 
    {
        println("|| Hora: " + lagos._hora + " : "  + lagos._min + " ||")
        println("Ingrese username")
        var us : String = StdIn.readLine()
        println("Ingrese contraseña")
        var pa : String = StdIn.readLine()
        println("Ingrese rango al que pertenece \n0 -> usuario normal \n1 -> administrador")
        var ra : Int = StdIn.readInt()
        lagos.registrar(us,pa,ra)
        ra match
        {
            case 0 => inicioNormal()
            case 1 => inicioAdmin()
            case _ => registrar()
        }
        println("Usuario registrado correctamente")
        lagos.pasarHora()
    }

    def login() : Unit =
    {
        println("|| Hora: " + lagos._hora + " : "  + lagos._min + " ||")
        println("Ingrese username")
        var us : String = StdIn.readLine()
        println("Ingrese contraseña")
        var pa : String = StdIn.readLine()
        if(lagos._listaUsuario.isEmpty != true)
        {
            for(i <- lagos._listaUsuario)
            {
                if(i._user == us)
                {
                    i._tipoUsu match
                    {
                        case 0 => inicioNormal()
                        case 1 => inicioAdmin()
                    }
                }
            }
            println("No se encontró su usuario, vuelvalo a ingresar")
            login()
            lagos.pasarHora()
        }
        else
        {
            registrar()
            lagos.pasarHora()
        }
    }

    def inicioNormal() : Unit =
    {
        println("|| Hora: " + lagos._hora + " : "  + lagos._min + " ||")
        println("Bienvenido usuario ¿Qué desea hacer? \n1 -> Reservar un salon\n2 -> Mostrar reservas\n3 -> Cerrar sesion")
        var op : Int = StdIn.readInt()
        if(op == 1)
        {
            println("Introduzca salon a reservar")
            var sal : String = StdIn.readLine
            println("Introduzca la hora de comienzo de su reserva") 
            var tim : Int = StdIn.readInt
            if ( lagos.reservar(sal,tim) )
            {
                println("Su reserva fue realizada con exito")
            }
            else
            {
                println("Su reserva no fue completada con exito, intentelo otra vez")
            }
            inicioNormal()
            lagos.pasarHora()

        }
        if(op == 2)
        {
            lagos.primReservas()
            lagos.pasarHora()
        }
        // if(op == 3)
        // {
        //     println("Introduzca salon a eliminar reserva")
        //     var sal : String = StdIn.readLine
        //     println("Introduzca la hora de su reserva") 
        //     var tim : Int = StdIn.readInt
        //     if ( lagos.eliminarReserva(sal,tim) )
        //     {
        //         println("Su reserva fue eliminada con exito")
        //     }
        //     else
        //     {
        //         println("Su reserva no fue eliminada con exito, intentelo otra vez")
        //     }
        //     inicioNormal()
        //     lagos.pasarHora()
        // }
        if(op == 3)
        {
            bienvenida()
        }
    }

    def inicioAdmin() : Unit =
    {
        println("|| Hora: " + lagos._hora + " : "  + lagos._min + " ||")
        println("Bienvenido usuario ¿Qué desea hacer? \n1 -> Reservar un salon\n2 -> Mostrar reservas\n3 -> agendar clase")
        println("4 -> poner en mantenimiento salon\n5 -> Quitar mantenimiento salon \n6 -> Agregar salon")
        var op : Int = StdIn.readInt()
        if(op == 1)
        {
            println("Introduzca salon a reservar")
            var sal : String = StdIn.readLine
            println("Introduzca la hora de comienzo de su reserva") 
            var tim : Int = StdIn.readInt
            if ( lagos.reservar(sal,tim) )
            {
                println("Su reserva fue realizada con exito")
            }
            else
            {
                println("Su reserva no fue completada con exito, intentelo otra vez")
            }
            inicioNormal()
            lagos.pasarHora()

        }
        if(op == 2)
        {
            lagos.primReservas()
            lagos.pasarHora()
            inicioAdmin()
        }

        if(op == 3)
        {
            println("Introduzca salon a agendar clase")
            var sal : String = StdIn.readLine
            println("Introduzca la hora de comienzo de la clase") 
            var tim : Int = StdIn.readInt
            if ( lagos.clasear(sal,tim) )
            {
                println("Su reserva fue realizada con exito")
            }
            else
            {
                println("Su reserva no fue completada con exito, intentelo otra vez")
            }
            inicioAdmin()
            lagos.pasarHora()

        }
        if(op == 4)
        {
            println("Introduzca salon al cual desea poner en mantenimiento")
            var sal : String = StdIn.readLine
            if(lagos.ponerMantenimiento(sal))
            {
                println("La asignacion se hizo correctamente")
                inicioAdmin()
                lagos.pasarHora()
            }
            else
            {
                println("La asignacion no fue exitosa")
                inicioAdmin()
                lagos.pasarHora()
            }
        }

        if(op == 5)
        {
            println("Introduzca salon al cual desea quitar del mantenimiento")
            var sal : String = StdIn.readLine
            if(lagos.ponerMantenimiento(sal))
            {
                println("El salon se quito correctamente")
                inicioAdmin()
                lagos.pasarHora()
            }
            else
            {
                println("Hubo un fallo al intentar quitar el mantenimiento")
                inicioAdmin()
                lagos.pasarHora()
            }
        }

    }
}

