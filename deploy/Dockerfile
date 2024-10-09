# Use the official MongoDB image from Docker Hub
FROM mongo:latest

# Set environment variables (optional)
# These can also be passed in at runtime for flexibility
ENV MONGO_INITDB_ROOT_USERNAME=admin \
    MONGO_INITDB_ROOT_PASSWORD=secret \
    MONGO_INITDB_DATABASE=mydb

# Expose the default MongoDB port
EXPOSE 27017

# Optionally, copy a script to initialize the database
# You can place any initialization scripts in the /docker-entrypoint-initdb.d directory
# COPY ./init-mongo.js /docker-entrypoint-initdb.d/

# Default command to run MongoDB
CMD ["mongod"]
