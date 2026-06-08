CREATE DATABASE IF NOT EXISTS db_clientes;
CREATE DATABASE IF NOT EXISTS db_productos;
CREATE DATABASE IF NOT EXISTS db_ventas;
CREATE DATABASE IF NOT EXISTS db_login;
CREATE DATABASE IF NOT EXISTS db_inventario;
CREATE DATABASE IF NOT EXISTS db_consolidado;

GRANT ALL PRIVILEGES ON db_clientes.* TO 'user'@'%';
GRANT ALL PRIVILEGES ON db_productos.* TO 'user'@'%';
GRANT ALL PRIVILEGES ON db_ventas.* TO 'user'@'%';
GRANT ALL PRIVILEGES ON db_login.* TO 'user'@'%';
GRANT ALL PRIVILEGES ON db_inventario.* TO 'user'@'%';
GRANT ALL PRIVILEGES ON db_consolidado.* TO 'user'@'%';

FLUSH PRIVILEGES;