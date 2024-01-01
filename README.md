## Repositorio de Proyecto de Microservicios para una Tienda de Electrodomésticos

Este repositorio contiene la implementación de una tienda de electrodomésticos, compuesta por tres microservicios interconectados. El objetivo principal es facilitar la comunicación y colaboración entre los servicios de carritos de compras y registros de ventas. Para lograr esto, se han aplicado diversos patrones de diseño de microservicios que enfatizan la eficiencia, seguridad y resiliencia de cada componente.

### Microservicios:

1. **Productos:** Gestiona la información de electrodomésticos, proporcionando detalles como código, nombre, marca y precio individual.
2. **Carrito de Compras:** Maneja los carritos de compras, permitiendo a los usuarios agregar y quitar artículos con identificadores únicos y un campo para almacenar el precio total. Se comunica/consume al servicio **Productos** para obtener los productos cargados al carrito de compras.
3. **Ventas:** Registra cada venta asociada a un carrito de compras, permitiendo conocer el monto total y la lista de productos involucrados. Se comunica/consume al servicio **Carrito de compras** para obtener el carrito de compras con los productos cargados del cliente.

### Requerimientos implementados:
1. **Arquitectura de Microservicios:** Se presenta un diagrama representativo de la arquitectura, detallando la comunicación entre los servicios.
2. **Desarrollo Individual:** Cada microservicio implementa operaciones CRUD y configuración adecuada del archivo application.properties.
3. **Registro con Eureka:** Se configura un servidor Eureka para registrar los microservicios, asignando nombres representativos.
4. **Balanceo de Carga:** Se implementa Spring Cloud Load Balancer, con múltiples instancias de algunos microservicios para prueba de carga mediante Postman.
5. **Circuit Breaker:** Se utiliza Resilience4J para implementar un Circuit Breaker, con fallback method y posibilidad de reintento en caso de errores de comunicación.
6. **API Gateway:** Se implementa una API Gateway como punto de entrada para clientes externos.
7. **Docker:**
7.1 Se crean Dockerfiles para cada microservicio.
7.2 Se proporciona un archivo docker-compose.yml para facilitar el despliegue.
7.3 Se realiza el proceso de deploy utilizando docker-compose build y docker-compose up.
7.3 Se valida la comunicación correcta entre los servicios dentro del entorno Docker mediante pruebas con Postman.

### Configuración y Despliegue:

1. **Modificar Configuración de Conexión a la Base de Datos:**
   - Modifica la URL de conexión a la base de datos cambiando el anfitrión de localhost a la IP del equipo/servidor en los archivos application.properties de cada jar de los servicio products_service, shoppingcart_service y sales_service.
   - Estos tres son los que requieren una BASE DE DATOS para su lógica de negocio.

     # Antes
     - spring.datasource.url=jdbc:mysql://localhost:3306/store_elect_products?useSSL=false&allowPublicKeyRetrieval=true
     # Después
     - spring.datasource.url=jdbc:mysql://192.168.10.17:3306/store_elect_products?useSSL=false&allowPublicKeyRetrieval=true

2. **Habilitar Reglas de Entrada en el Firewall:**
   - Habilitar las reglas de entrada del firewall para el puerto 3306.

3. **Configurar IP de Anfitrión para Cada Base de Datos:**
   - Ejecutar los comandos SQL para habilitar el acceso desde la IP del anfitrión a cada base de datos que usarán los servicios.
       1. GRANT ALL PRIVILEGES ON store_elect_products.* TO 'tu_usuario_mysql'@'tu_ip_equipo' IDENTIFIED BY 'tu_contraseña_mysql';
       2. GRANT ALL PRIVILEGES ON store_elect_shoppingcart.* TO 'tu_usuario_mysql'@'tu_ip_equipo' IDENTIFIED BY 'tu_contraseña_mysql';
       3. GRANT ALL PRIVILEGES ON store_elect_sales.* TO 'tu_usuario_mysql'@'tu_ip_equipo' IDENTIFIED BY 'tu_contraseña_mysql';
       4. FLUSH PRIVILEGES;

4. **Configuración del Archivo Docker-Compose:**
   - Editar el archivo `docker-compose.yml` para mapear los servicios con las bases de datos y configurar la IP (de tu equipo), el usuario y la contraseña de la BASE DE DATOS.
  
5. **Limpiar Caché (Opcional):**
   - En caso de haber eliminado imágenes y contenedores docker o incluso luego si por alguna razó se hiciese es recomendable limpiar la caché con el siguiente comando: `docker builder prune -a` para evitar problemas.

6. **Crear Imágenes Docker:**
   - Ejecutar el siguiente comando para crear las imágenes Docker de los servicios: `docker-compose build`.

7. **Subir Contenedores:**
   - Iniciar los contenedores Docker utilizando el siguiente comando: `docker-compose up`.
  
   
