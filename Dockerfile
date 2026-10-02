FROM tomcat:10.1-jdk21-temurin-jammy

# Remove default Tomcat application files to avoid home page clashes
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your local compiled WebContent files straight into Tomcat's root directory
COPY WebContent/ /usr/local/tomcat/webapps/ROOT/

EXPOSE 8080
CMD ["catalina.sh", "run"]
