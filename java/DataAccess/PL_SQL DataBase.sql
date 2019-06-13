/*
  CREACION DE LOS OBJETOS.
*/
CREATE OR REPLACE TYPE TIP_RESERVA AS OBJECT (
	ID_RESERVA NUMBER,
	NOMBRE VARCHAR2(30),
	APELLIDOS VARCHAR2(50),
	TOTAL_RESERVA NUMBER,
	FECHA_ENTRADA DATE,
	FECHA_FIN DATE
);

CREATE OR REPLACE TYPE TIP_HABITACION AS OBJECT (
	ID_HABITACION NUMBER,
	PRECIO NUMBER,
	EXTRAS_HABITACION VARCHAR2(100),
	RESENIAS VARCHAR2(200),
	RESERVA REF TIP_RESERVA
);

CREATE OR REPLACE TYPE TIP_HABITACIONES AS TABLE OF TIP_HABITACION;

CREATE OR REPLACE TYPE TIP_ALOJAMIENTO AS OBJECT (
	ID_ALOJAMIENTO NUMBER,
	NOMBRE VARCHAR2(50),
	RAZON_SOCIAL VARCHAR2(100),
	HABITACIONES TIP_HABITACIONES
);

/*
  CREACION DE LAS TABLAS.
*/
DROP table tabla_reserva;
CREATE TABLE TABLA_RESERVA OF TIP_RESERVA (
	ID_RESERVA PRIMARY KEY
);

DROP TABLE TABLA_ALOJAMIENTO;
CREATE TABLE TABLA_ALOJAMIENTO OF TIP_ALOJAMIENTO (
	ID_ALOJAMIENTO PRIMARY KEY
) NESTED TABLE HABITACIONES STORE AS TABLA_RESERVAS;

/*
  INSERCION DE DATOS.
*/
INSERT INTO TABLA_RESERVA VALUES(1, 'Mario', 'Codes', 200, '20-12-2016', '24-12-2016');
INSERT INTO TABLA_RESERVA VALUES(2, 'Juan', 'De la Fuente', 300, '25-01-2017', '27-01-2017');
INSERT INTO TABLA_RESERVA VALUES(3, 'Sara', 'Cazo', 400, '05-04-2016', '12-04-2016');
INSERT INTO TABLA_RESERVA VALUES(4, 'Rey', 'San Juan', 500, '10-06-2017', '15-06-2017');

INSERT INTO TABLA_ALOJAMIENTO VALUES(
	1,
	'Las Rosas',
	'COMPLEJO TURISTICO LAS ROSAS S.A.',
	TIP_HABITACIONES(
		TIP_HABITACION(1, 200, 'Ba�era grande', 'Muy luminosa', (SELECT REF(a) FROM TABLA_RESERVA a WHERE a.ID_RESERVA = 1)),
		TIP_HABITACION(2, 300, 'Horno pirolitico', 'Muy buenas vistas', (SELECT REF(a) FROM TABLA_RESERVA a WHERE a.ID_RESERVA = 3))
	)
);

INSERT INTO TABLA_ALOJAMIENTO VALUES(
  2,
  'Los Cipreses',
  'Complejo Lasaosa',
  TIP_HABITACIONES(
	TIP_HABITACION(3, 100, 'Microondas', 'Puerta Blindada', (SELECT REF(a) FROM TABLA_RESERVA a WHERE a.ID_RESERVA = 1)),
	TIP_HABITACION(4, 200, 'Bombillas de bajo consumo', 'Ubicacion agradable', (SELECT REF(a) FROM TABLA_RESERVA a WHERE a.ID_RESERVA = 2))
  )
);

INSERT INTO TABLA_ALOJAMIENTO VALUES(
  3,
  'El Oso',
  'Complejo Turistico Alquezar',
  TIP_HABITACIONES(
	TIP_HABITACION(2, 400, 'Alfombrilla', 'Muy luminosa', (SELECT REF(a) FROM TABLA_RESERVA a WHERE a.ID_RESERVA = 2)),
	TIP_HABITACION(3, 500, 'Ventanas reforzadas', 'Agradable', (SELECT REF(a) FROM TABLA_RESERVA a WHERE a.ID_RESERVA = 4))
  )
);

INSERT INTO TABLA_ALOJAMIENTO VALUES(
  4,
  'Madrigal',
  'Albergue Monta�a',
  TIP_HABITACIONES(
	TIP_HABITACION(4, 200, 'Ba�era peque�a', 'Poco luminosa', (SELECT REF(a) FROM TABLA_RESERVA a WHERE a.ID_RESERVA = 4)),
	TIP_HABITACION(2, 300, 'No tiene horno', 'Muy buenas vistas', (SELECT REF(a) FROM TABLA_RESERVA a WHERE a.ID_RESERVA = 2))
  )
);

/*
	UPDATES
*/
UPDATE TABLA_RESERVA
	SET NOMBRE = 'MANOLO'
	WHERE ID_RESERVA = 1;
	
UPDATE TABLA_ALOJAMIENTO
	SET ID_ALOJAMIENTO = 8,
	NOMBRE = 'Mara�a',
	RAZON_SOCIAL = 'La nada de nada'
	WHERE ID_ALOJAMIENTO = 1;
	
INSERT INTO TABLE (SELECT HABITACIONES FROM TABLA_ALOJAMIENTO WHERE ID_ALOJAMIENTO = 1)
	VALUES (TIP_HABITACION(5, 300, 'No tiene horno', 'Muy buenas vistas', (SELECT REF(a) FROM TABLA_RESERVA a WHERE a.ID_RESERVA = 2)));
	
UPDATE TABLE (SELECT HABITACIONES FROM TABLA_ALOJAMIENTO WHERE ID_ALOJAMIENTO = 2) HABITACIONES
	SET VALUE(HABITACIONES) = TIP_HABITACION(10, 200, 'Con horno', 'vistas preciosas', (SELECT REF(r) FROM TABLA_RESERVA r WHERE r.ID_RESERVA = 2))
WHERE HABITACIONES.ID_HABITACION = 5;
	
/*
	DELETES
*/
DELETE FROM TABLA_RESERVA
	WHERE ID_RESERVA = 1;
	
DELETE FROM TABLE (SELECT HABITACIONES FROM TABLA_ALOJAMIENTO WHERE ID_ALOJAMIENTO = 2) HABITACIONES
	WHERE HABITACIONES.ID_HABITACION = 5;
	
DELETE FROM TABLA_ALOJAMIENTO
	WHERE ID_ALOJAMIENTO = 2;
	
/*
	CONSULTAS
*/
SELECT * FROM TABLA_RESERVA;

/*
	PROCEDIMIENTO
*/
CREATE OR REPLACE PROCEDURE MOSTRAR_TODO AS
	CURSOR CRS IS
		SELECT TA.ID_ALOJAMIENTO, TA.NOMBRE, TA.RAZON_SOCIAL
			FROM TABLA_ALOJAMIENTO TA;
BEGIN
	FOR A IN CRS LOOP
		DBMS_OUTPUT.PUT_LINE('ID del Alojamiento: ' || A.ID_ALOJAMIENTO);
		DBMS_OUTPUT.PUT_LINE('Nombre: ' || A.NOMBRE);
		DBMS_OUTPUT.PUT_LINE('Razon Social: ' || A.RAZON_SOCIAL);
		
		FOR C IN
		(
			SELECT TIP_HABITACION.ID_HABITACION, TIP_HABITACION.PRECIO, TIP_HABITACION.EXTRAS_HABITACION, TIP_HABITACION.RESENIAS, DEREF(TIP_HABITACION.RESERVA).NOMBRE AS NOMBRE
			FROM TABLA_ALOJAMIENTO TA,
				TABLE(HABITACIONES) TIP_HABITACION
			WHERE TA.ID_ALOJAMIENTO = A.ID_ALOJAMIENTO
		)
		LOOP
			DBMS_OUTPUT.PUT_LINE('Nombre de la reserva: ' || C.NOMBRE);
			DBMS_OUTPUT.PUT_LINE('Habitacion numero: ' || C.ID_HABITACION);
			DBMS_OUTPUT.PUT_LINE('Precio de la habitacion: ' || C.PRECIO);
			DBMS_OUTPUT.PUT_LINE('Extras de la Habitacion: ' || C.EXTRAS_HABITACION);
			DBMS_OUTPUT.PUT_LINE('Rese�as: ' || C.RESENIAS || chr(10));
		END LOOP;
	END LOOP;
END MOSTRAR_TODO;

/*
	EJECUCION DEL PROCEDIMIENTO.
*/
SET SERVEROUTPUT ON
BEGIN
	MOSTRAR_TODO();
END;
	