package com.crisolapp.DB;

/**
 * Created by jesusesmipastor on 27/01/2015.
 */
public interface EsquemaDB {

    /*****************************************
     Base para la creacion de la tabla usuarios
     ******************************************/
    public static final  String TABLA_USUARIOS_NOMBRE       = "usuarios";
    public static final  String COLUMNA_USUARIOS_ID         = "usua_id";
    public static final  String COLUMNA_USUARIOS_CODIGO     = "usua_codigo";
    public static final  String COLUMNA_USUARIOS_NOMBRE     = "usua_nombre";
    public static final  String COLUMNA_USUARIOS_CLAVE      = "usua_clave";
    public static final  String COLUMNA_USUARIOS_ENVIARGPS  = "usua_enviargps";

    public static final String CREAR_TABLA_USUARIOS = "create table "+ TABLA_USUARIOS_NOMBRE+"("
            + COLUMNA_USUARIOS_ID + " integer primary key autoincrement, "
            + COLUMNA_USUARIOS_CODIGO    + " text not null, "
            + COLUMNA_USUARIOS_NOMBRE    + " text, "
            + COLUMNA_USUARIOS_CLAVE     + " text,"
            + COLUMNA_USUARIOS_ENVIARGPS + " text"
            + " );" ;



    /******************************************
     Base para la creacion de la tabla insumos
     *******************************************/
    public static final  String TABLA_INSUMOS_NOMBRE         = "insumos";
    public static final  String COLUMNA_INSUMOS_ID           = "insu_id";
    public static final  String COLUMNA_INSUMOS_CODIGO       = "insu_codigo";
    public static final  String COLUMNA_INSUMOS_NOMBRE       = "insu_nombre";
    public static final  String COLUMNA_INSUMOS_PRECIO       = "insu_precio";
    public static final  String COLUMNA_INSUMOS_IVA          = "insu_iva";
    public static final  String COLUMNA_INSUMOS_MARCA        = "insu_marca";
    public static final  String COLUMNA_INSUMOS_INVENTARIO   = "insu_inventario";

    public static final String CREAR_TABLA_INSUMOS = "create table "+ TABLA_INSUMOS_NOMBRE+"("
            + COLUMNA_INSUMOS_ID + " integer primary key autoincrement, "
            + COLUMNA_INSUMOS_CODIGO      + " text not null, "
            + COLUMNA_INSUMOS_NOMBRE      + " text, "
            + COLUMNA_INSUMOS_PRECIO      + " double, "
            + COLUMNA_INSUMOS_IVA         + " double, "
            + COLUMNA_INSUMOS_MARCA       + " text, "
            + COLUMNA_INSUMOS_INVENTARIO  + " text"
            + " );" ;

    /******************************************
     Base para la creacion de la tabla ciudades
     *******************************************/
    public static final  String TABLA_CIUDADES_NOMBRE   = "ciudades";
    public static final  String COLUMNA_CIUDADES_ID      = "ciud_id";
    public static final  String COLUMNA_CIUDADES_CODIGO  = "ciud_codigo";
    public static final  String COLUMNA_CIUDADES_NOMBRE  = "ciud_nombre";

    public static final String CREAR_TABLA_CIUDADES = "create table "+ TABLA_CIUDADES_NOMBRE+"("
            + COLUMNA_CIUDADES_ID + " integer primary key autoincrement, "
            + COLUMNA_CIUDADES_CODIGO + " text not null, "
            + COLUMNA_CIUDADES_NOMBRE + " text "
            + " );" ;


    /******************************************
     Base para la creacion de la tabla clientes
     *******************************************/
    public static final  String TABLA_CLIENTES_NOMBRE      = "clientes";
    public static final  String COLUMNA_CLIENTES_ID        = "clie_id";
    public static final  String COLUMNA_CLIENTES_CODIGO    = "clie_codigo";
    public static final  String COLUMNA_CLIENTES_NOMBRE    = "clie_nombre";
    public static final  String COLUMNA_CLIENTES_NEGOCIO   = "clie_negocio";
    public static final  String COLUMNA_CLIENTES_DIRECCION = "clie_direccion";
    public static final  String COLUMNA_CLIENTES_BARRIO    = "clie_barrio";
    public static final  String COLUMNA_CLIENTES_CIUDAD    = "clie_ciudad";
    public static final  String COLUMNA_CLIENTES_TELEFONO  = "clie_telefono";
    public static final  String COLUMNA_CLIENTES_RUTA      = "clie_ruta";
    public static final  String COLUMNA_CLIENTES_VISITADO  = "clie_visitado";
    public static final  String COLUMNA_CLIENTES_CEDULA    = "clie_cedula";

    public static final String CREAR_TABLA_CLIENTES = "create table "+ TABLA_CLIENTES_NOMBRE+"("
            + COLUMNA_CLIENTES_ID + " integer primary key autoincrement, "
            + COLUMNA_CLIENTES_CODIGO    + " text not null, "
            + COLUMNA_CLIENTES_NOMBRE    + " text, "
            + COLUMNA_CLIENTES_NEGOCIO   + " text, "
            + COLUMNA_CLIENTES_DIRECCION + " text, "
            + COLUMNA_CLIENTES_BARRIO    + " text, "
            + COLUMNA_CLIENTES_CIUDAD    + " text, "
            + COLUMNA_CLIENTES_TELEFONO  + " text, "
            + COLUMNA_CLIENTES_RUTA      + " text, "
            + COLUMNA_CLIENTES_VISITADO  + " text, "
            + COLUMNA_CLIENTES_CEDULA    + " text "
            + " );" ;

    /*****************************************
     Base para la creacion de la tabla mensajes
     ******************************************/
    public static final  String TABLA_MENSAJES_NOMBRE   = "mensajes";
    public static final  String COLUMNA_MENSAJES_ID     = "mens_id";
    public static final  String COLUMNA_MENSAJES_IDD    = "mens_idd";
    public static final  String COLUMNA_MENSAJES_TEXTO  = "mens_texto";
    public static final  String COLUMNA_MENSAJES_TEXTO2 = "mens_texto2";
    public static final  String COLUMNA_MENSAJES_TEXTO3 = "mens_texto3";
    public static final  String COLUMNA_MENSAJES_TEXTO4 = "mens_texto4";
    public static final  String COLUMNA_MENSAJES_TEXTO5 = "mens_texto5";
    public static final  String COLUMNA_MENSAJES_FECHA  = "mens_fecha";
    public static final  String COLUMNA_MENSAJES_ESTADO = "mens_estado";

    public static final String CREAR_TABLA_MENSAJES = "create table "+ TABLA_MENSAJES_NOMBRE+"("
            + COLUMNA_MENSAJES_ID + " integer primary key autoincrement, "
            + COLUMNA_MENSAJES_IDD    + " text , "
            + COLUMNA_MENSAJES_TEXTO  + " text , "
            + COLUMNA_MENSAJES_TEXTO2 + " text, "
            + COLUMNA_MENSAJES_TEXTO3 + " text, "
            + COLUMNA_MENSAJES_TEXTO4 + " text, "
            + COLUMNA_MENSAJES_TEXTO5 + " text, "
            + COLUMNA_MENSAJES_FECHA  + " text, "
            + COLUMNA_MENSAJES_ESTADO + " text"
            + " );" ;


    /******************************************
     Base para la creacion de la tabla novedades
     *******************************************/
    public static final  String TABLA_NOVEDADES_NOMBRE        = "novedades";
    public static final  String COLUMNA_NOVEDADES_ID          = "nove_id";
    public static final  String COLUMNA_NOVEDADES_TIPONOVEDAD = "nove_tiponovedad";
    public static final  String COLUMNA_NOVEDADES_CLIENTE     = "nove_cliente";
    public static final  String COLUMNA_NOVEDADES_ESTADO      = "nove_estado";

    public static final String CREAR_TABLA_NOVEDADES = "create table "+ TABLA_NOVEDADES_NOMBRE+"("
            + COLUMNA_NOVEDADES_ID + " integer primary key autoincrement, "
            + COLUMNA_NOVEDADES_TIPONOVEDAD + " text, "
            + COLUMNA_NOVEDADES_CLIENTE + " text, "
            + COLUMNA_NOVEDADES_ESTADO + " text "
            + " );" ;

    /******************************************
     Base para la creacion de la tabla pedidos
     *******************************************/
    public static final  String TABLA_PEDIDOS_NOMBRE          = "pedidos";
    public static final  String COLUMNA_PEDIDOS_ID            = "pedi_id";
    public static final  String COLUMNA_PEDIDOS_INSUMO        = "pedi_insumo";
    public static final  String COLUMNA_PEDIDOS_CANTIDAD      = "pedi_cantidad";
    public static final  String COLUMNA_PEDIDOS_PRECIO        = "pedi_precio";
    public static final  String COLUMNA_PEDIDOS_VENDEDOR      = "pedi_vendedor";
    public static final  String COLUMNA_PEDIDOS_CLIENTE       = "pedi_cliente";
    public static final  String COLUMNA_PEDIDOS_ESTADO        = "pedi_estado";
    public static final  String COLUMNA_PEDIDOS_COMENTARIO    = "pedi_comentario";
    public static final  String COLUMNA_PEDIDOS_PRECIOIVA     = "pedi_precioiva";
    public static final  String COLUMNA_PEDIDOS_MARCA         = "pedi_marca";
    public static final  String COLUMNA_PEDIDOS_NOMBREINSUMO  = "pedi_nombreinsumo";
    public static final  String COLUMNA_PEDIDOS_INSUMOIVA     = "pedi_insu_iva";

    public static final String CREAR_TABLA_PEDIDOS = "create table "+ TABLA_PEDIDOS_NOMBRE+"("
            + COLUMNA_PEDIDOS_ID           + " integer primary key autoincrement, "
            + COLUMNA_PEDIDOS_INSUMO       + " text, "
            + COLUMNA_PEDIDOS_CANTIDAD     + " int, "
            + COLUMNA_PEDIDOS_PRECIO       + " double, "
            + COLUMNA_PEDIDOS_VENDEDOR     + " text, "
            + COLUMNA_PEDIDOS_CLIENTE      + " text, "
            + COLUMNA_PEDIDOS_ESTADO       + " text, "
            + COLUMNA_PEDIDOS_COMENTARIO   + " text, "
            + COLUMNA_PEDIDOS_PRECIOIVA    + " double, "
            + COLUMNA_PEDIDOS_MARCA        + " text, "
            + COLUMNA_PEDIDOS_NOMBREINSUMO + " text, "
            + COLUMNA_PEDIDOS_INSUMOIVA    + " int "
            + " );" ;

    /******************************************
     Base para la creacion de la tabla rutas
     *******************************************/
    public static final  String TABLA_RUTAS_NOMBRE       = "rutas";
    public static final  String COLUMNA_RUTAS_ID         = "ruta_id";
    public static final  String COLUMNA_RUTAS_CODIGO     = "ruta_codigo";
    public static final  String COLUMNA_RUTAS_NOMBRE     = "ruta_nombre";
    public static final  String COLUMNA_RUTAS_DIA        = "ruta_dia";

    public static final String CREAR_TABLA_RUTAS = "create table "+ TABLA_RUTAS_NOMBRE+"("
            + COLUMNA_RUTAS_ID + " integer primary key autoincrement, "
            + COLUMNA_RUTAS_CODIGO + " text not null, "
            + COLUMNA_RUTAS_NOMBRE + " text, "
            + COLUMNA_RUTAS_DIA + " text "
            + " );" ;

    /******************************************
     Base para la creacion de la tabla tipoclientes
     *******************************************/
    public static final  String TABLA_TIPOCLIENTES_NOMBRE       = "tipoclientes";
    public static final  String COLUMNA_TIPOCLIENTES_ID         = "tcli_id";
    public static final  String COLUMNA_TIPOCLIENTES_CODIGO     = "tcli_codigo";
    public static final  String COLUMNA_TIPOCLIENTES_NOMBRE     = "tcli_nombre";

    public static final String CREAR_TABLA_TIPOCLIENTES = "create table "+ TABLA_TIPOCLIENTES_NOMBRE+"("
            + COLUMNA_TIPOCLIENTES_ID + " integer primary key autoincrement, "
            + COLUMNA_TIPOCLIENTES_CODIGO + " text not null, "
            + COLUMNA_TIPOCLIENTES_NOMBRE + " text "
            + " );" ;

    /******************************************
     Base para la creacion de la tabla tiponovedades
     *******************************************/
    public static final  String TABLA_TIPONOVEDADES_NOMBRE       = "tiponovedades";
    public static final  String COLUMNA_TIPONOVEDADES_ID         = "tnov_id";
    public static final  String COLUMNA_TIPONOVEDADES_CODIGO     = "tnov_codigo";
    public static final  String COLUMNA_TIPONOVEDADES_NOMBRE     = "tnov_nombre";

    public static final String CREAR_TABLA_TIPONOVEDADES = "create table "+ TABLA_TIPONOVEDADES_NOMBRE+"("
            + COLUMNA_TIPONOVEDADES_ID + " integer primary key autoincrement, "
            + COLUMNA_TIPONOVEDADES_CODIGO + " text not null, "
            + COLUMNA_TIPONOVEDADES_NOMBRE + " text "
            + " );" ;


}
