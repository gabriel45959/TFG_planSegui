SET FOREIGN_KEY_CHECKS=0
;

CREATE TABLE `Compra_materia_prima`
(
	`co_id` BIGINT NOT NULL AUTO_INCREMENT,
	`co_nro_factura` VARCHAR(50),
	`co_fecha_de_llegada` DATE,
	`co_detalle_pedido` BIGINT,
	CONSTRAINT `PK_Compra_materia_prima` PRIMARY KEY (`co_id` ASC)
)
;

CREATE TABLE `detalle_compra_materia_prima`
(
	`dcmp_id` BIGINT NOT NULL AUTO_INCREMENT,
	`dcmp_materia_prima` BIGINT,
	`dcmp_co_materia_prima` BIGINT,
	`dcmp_cantidad` INT,
	CONSTRAINT `PK_detalle_compra_materia_prima` PRIMARY KEY (`dcmp_id` ASC)
)
;

CREATE TABLE `detalle_pedido`
(
	`de_id` BIGINT NOT NULL AUTO_INCREMENT,
	`de_pedido` BIGINT,
	`de_producto` BIGINT,
	`de_cantidad` INT,
	`de_estado` BIGINT,
	CONSTRAINT `PK_detalle_pedido` PRIMARY KEY (`de_id` ASC)
)
;

CREATE TABLE `empresa`
(
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`nombre` VARCHAR(50),
	`cuil` VARCHAR(50),
	CONSTRAINT `PK_empresa` PRIMARY KEY (`id` ASC)
)
;

CREATE TABLE `estado_pedido`
(
	`es_id` BIGINT NOT NULL AUTO_INCREMENT,
	`es_nombre` VARCHAR(50),
	`es_descripcion` VARCHAR(200),
	`es_valor_visual` VARCHAR(50),
	CONSTRAINT `PK_estado_pedido` PRIMARY KEY (`es_id` ASC)
)
;

CREATE TABLE `Ingrediente`
(
	`in_id` BIGINT NOT NULL AUTO_INCREMENT,
	`in_producto` BIGINT,
	`in_materia_prima` BIGINT,
	`in_porcentaje` DECIMAL(10,0),
	CONSTRAINT `PK_componente` PRIMARY KEY (`in_id` ASC)
)
;

CREATE TABLE `inventario`
(
	`in_id` BIGINT NOT NULL AUTO_INCREMENT,
	`in_id_materia_prima` BIGINT,
	`in_cantidad` INT,
	`in_fecha_salida` DATE,
	`in_fecha_ingreso` DATE,
	`in_nro_factura` VARCHAR(50),
	CONSTRAINT `PK_inventario` PRIMARY KEY (`in_id` ASC)
)
;

CREATE TABLE `maquinaria`
(
	`ma_id` BIGINT NOT NULL AUTO_INCREMENT,
	`ma_nombre` VARCHAR(50),
	`ma_kg_produccion_xHs` DECIMAL(10,2),
	`ma_id_producto` BIGINT,
	CONSTRAINT `PK_maquinaria` PRIMARY KEY (`ma_id` ASC)
)
;

CREATE TABLE `Materia_prima`
(
	`ma_id` BIGINT NOT NULL AUTO_INCREMENT,
	`ma_nombre` VARCHAR(50),
	`ma_stock_maximo` DECIMAL(10,0),
	`ma_stock_minimo` DECIMAL(10,0),
	`ma_kg_o_cantidad` TINYINT,
	CONSTRAINT `PK_Materia_prima` PRIMARY KEY (`ma_id` ASC)
)
;

CREATE TABLE `parametros`
(
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`nombre` VARCHAR(50),
	`valor` VARCHAR(200),
	CONSTRAINT `PK_parametros` PRIMARY KEY (`id` ASC)
)
;

CREATE TABLE `Pedido`
(
	`pe_id` BIGINT NOT NULL AUTO_INCREMENT,
	`pe_id_empresa` BIGINT,
	`pe_nro_factura` VARCHAR(50),
	`pe_fecha_entrega` DATE,
	`pe_fecha_creacion` DATE,
	`pe_observaciones` VARCHAR(200),
	CONSTRAINT `PK_Pedidos` PRIMARY KEY (`pe_id` ASC)
)
;

CREATE TABLE `Planificacion`
(
	`pl_id` BIGINT NOT NULL AUTO_INCREMENT,
	`pl_id_detalle_pedido` BIGINT,
	`pl_fecha_inicio_estimada` DATE,
	`pl_fecha_entrega_estimada` DATE,
	CONSTRAINT `PK_Planificacion` PRIMARY KEY (`pl_id` ASC)
)
;

CREATE TABLE `Problemas_reportados`
(
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`pr_tipo_problema` BIGINT,
	`pr_fecha_resolucion` DATE,
	`pr_observaciones` VARCHAR(200),
	`pr_estado` TINYINT,
	`pr_detalle_pedido` BIGINT,
	CONSTRAINT `PK_Problemas_reportados` PRIMARY KEY (`id` ASC)
)
;

CREATE TABLE `Producto`
(
	`pr_id` BIGINT NOT NULL AUTO_INCREMENT,
	`pr_nombre` VARCHAR(50),
	CONSTRAINT `PK_Producto` PRIMARY KEY (`pr_id` ASC)
)
;

CREATE TABLE `proveedor`
(
	`prov_id` BIGINT NOT NULL,
	`prov_nombre` VARCHAR(50),
	`prov_direccion` VARCHAR(200),
	`porv_telefono` VARCHAR(50),
	`prov_materia_prima` BIGINT,
	CONSTRAINT `PK_proveedor` PRIMARY KEY (`prov_id` ASC)
)
;

CREATE TABLE `reserva_materia_prima`
(
	`re_id` BIGINT NOT NULL AUTO_INCREMENT,
	`re_id_detalle_pedido` BIGINT,
	`re_id_materia_prima` BIGINT,
	`re_cantidad` INT,
	`re_fecha_ingreso` DATE,
	`re_fecha_egreso` DATE,
	`re_nro_factura` VARCHAR(50),
	CONSTRAINT `PK_reserva_materia_prima` PRIMARY KEY (`re_id` ASC)
)
;

CREATE TABLE `role`
(
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`nombre_usuario` VARCHAR(50),
	`id_rol` VARCHAR(50),
	CONSTRAINT `PK_role` PRIMARY KEY (`id` ASC)
)
;

CREATE TABLE `tipo_problema`
(
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`tp_nombre` VARCHAR(50),
	`tp_descripcion` VARCHAR(100),
	CONSTRAINT `PK_tipo_problema` PRIMARY KEY (`id` ASC)
)
;

CREATE TABLE `usuario`
(
	`nombre_usuario` VARCHAR(50) NOT NULL,
	`nombre` VARCHAR(50),
	`apellido` VARCHAR(50),
	`clave` VARCHAR(200),
	`habilitado` TINYINT,
	CONSTRAINT `PK_usuario` PRIMARY KEY (`nombre_usuario` ASC)
)
;

ALTER TABLE `Compra_materia_prima` 
 ADD CONSTRAINT `FK_Compra_materia_prima_detalle_pedido`
	FOREIGN KEY (`co_detalle_pedido`) REFERENCES `detalle_pedido` (`de_id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `detalle_compra_materia_prima` 
 ADD CONSTRAINT `FK_detalle_compra_materia_prima_Compra_materia_prima`
	FOREIGN KEY (`dcmp_co_materia_prima`) REFERENCES `Compra_materia_prima` (`co_id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `detalle_compra_materia_prima` 
 ADD CONSTRAINT `FK_detalle_compra_materia_prima_Materia_prima`
	FOREIGN KEY (`dcmp_materia_prima`) REFERENCES `Materia_prima` (`ma_id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `detalle_pedido` 
 ADD CONSTRAINT `FK_detalle_pedido_estado_pedido`
	FOREIGN KEY (`de_estado`) REFERENCES `estado_pedido` (`es_id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `detalle_pedido` 
 ADD CONSTRAINT `FK_detalle_pedido_Producto`
	FOREIGN KEY (`de_producto`) REFERENCES `Producto` (`pr_id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `Ingrediente` 
 ADD CONSTRAINT `FK_componente_Materia_prima`
	FOREIGN KEY (`in_materia_prima`) REFERENCES `Materia_prima` (`ma_id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `Ingrediente` 
 ADD CONSTRAINT `FK_Ingrediente_Producto`
	FOREIGN KEY (`in_producto`) REFERENCES `Producto` (`pr_id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `inventario` 
 ADD CONSTRAINT `FK_inventario_Materia_prima`
	FOREIGN KEY (`in_id_materia_prima`) REFERENCES `Materia_prima` (`ma_id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `maquinaria` 
 ADD CONSTRAINT `FK_maquinaria_Producto`
	FOREIGN KEY (`ma_id_producto`) REFERENCES `Producto` (`pr_id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `Pedido` 
 ADD CONSTRAINT `FK_Pedido_empresa`
	FOREIGN KEY (`pe_id_empresa`) REFERENCES `empresa` (`id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `Planificacion` 
 ADD CONSTRAINT `FK_Planificacion_detalle_pedido`
	FOREIGN KEY (`pl_id_detalle_pedido`) REFERENCES `detalle_pedido` (`de_id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `Problemas_reportados` 
 ADD CONSTRAINT `FK_Problemas_reportados_detalle_pedido`
	FOREIGN KEY (`pr_detalle_pedido`) REFERENCES `detalle_pedido` (`de_id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `Problemas_reportados` 
 ADD CONSTRAINT `FK_Problemas_reportados_tipo_problema`
	FOREIGN KEY (`pr_tipo_problema`) REFERENCES `tipo_problema` (`id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `proveedor` 
 ADD CONSTRAINT `FK_proveedor_Materia_prima`
	FOREIGN KEY (`prov_materia_prima`) REFERENCES `Materia_prima` (`ma_id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `reserva_materia_prima` 
 ADD CONSTRAINT `FK_reserva_materia_prima_detalle_pedido`
	FOREIGN KEY (`re_id_detalle_pedido`) REFERENCES `detalle_pedido` (`de_id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `reserva_materia_prima` 
 ADD CONSTRAINT `FK_reserva_materia_prima_Materia_prima`
	FOREIGN KEY (`re_id_materia_prima`) REFERENCES `Materia_prima` (`ma_id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `role` 
 ADD CONSTRAINT `FK_role_usuario`
	FOREIGN KEY (`nombre_usuario`) REFERENCES `usuario` (`nombre_usuario`) ON DELETE Restrict ON UPDATE Restrict
;

SET FOREIGN_KEY_CHECKS=1
;

INSERT INTO `usuario`(`nombre_usuario`, `nombre`, `apellido`, `clave`, `habilitado`) VALUES ('admin','admin','admin','$2a$10$hbxecwitQQ.dDT4JOFzQAulNySFwEpaFLw38jda6Td.Y/cOiRzDFu',1);
INSERT INTO `role`(`nombre_usuario`, `id_rol`) VALUES ('admin','ADMIN');


INSERT INTO `estado_pedido` (`es_id`, `es_nombre`, `es_descripcion`, `es_valor_visual`) VALUES
(1, 'Pendiente de planificar', 'Sin planifiación cargada', 'light text-dark'),
(2, 'Pendiente compra materia prima', 'No se realizo la gestion de compra', 'secondary text-white'),
(3, 'Materia prima solicitada', 'Se realizo la gestion de compra', 'secondary text-white'),
(4, 'Materia prima en stock', 'La materia prima llego y esta en el almacen', 'primary text-white'),
(5, 'Listo para fabricar', 'Se esta fabricando', 'success text-white'),
(6, 'Problemas detectados', 'Se encontraron problemas al realizar la fabricación', 'warning text-dark');
(7, 'Finalizado', 'El pedido fue terminado', 'light text-dark');

INSERT INTO `tipo_problema` (`id`, `tp_nombre`, `tp_descripcion`) VALUES
(13, 'Otros', 'detallar el problema'),
(12, 'Problemas electricos', 'falta de luz'),
(11, 'Maquina averiada', 'desperfecto mecanico');


commit;