# ==============================
# ETAPA 1: Construcción del proyecto
# ==============================

# Usa una imagen con JDK 23 para compilar
FROM eclipse-temurin:21-jdk AS builder

# Define el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia todo el proyecto al contenedor
COPY . .

# Dar permisos al wrapper de Gradle
RUN chmod +x gradlew

# Compilar el proyecto sin pruebas
RUN ./gradlew build -x test



# ==============================
# ETAPA 2: Imagen final para producción
# ==============================

# Usa una imagen más ligera con solo JRE 23
FROM eclipse-temurin:21-jre

# Directorio de trabajo
WORKDIR /app

# Copia el archivo .jar generado desde la etapa builder
COPY --from=builder /app/build/libs/*.jar app.jar

# Expone el puerto donde correrá Spring Boot
EXPOSE 8090

# Comando que ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]