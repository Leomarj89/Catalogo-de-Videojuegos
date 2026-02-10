-- 1. Crear un login SQL (modo SQL Server, no Windows)
CREATE LOGIN videojuegos_app WITH PASSWORD = 'Pai3642314*-';

-- 2. Darle acceso a la base Videojuegos
USE Videojuegos;
GO
CREATE USER videojuegos_app FOR LOGIN videojuegos_app;
ALTER ROLE db_owner ADD MEMBER videojuegos_app;
-- (o un rol más limitado si quieres, pero db_owner para desarrollo es simple)
