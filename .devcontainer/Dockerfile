FROM ubuntu:22.04

# Update packages
RUN apt-get update

# Add sudo
RUN apt-get install -y sudo

RUN sudo apt-get install -y \
    build-essential \
    curl \
    git \
    pkg-config \
    libssl-dev \
    openjdk-17-jdk \
    npm

# Set environment variables
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:${PATH}"

# Install Rust using rustup (default stable toolchain)
RUN curl https://sh.rustup.rs -sSf | sh -s -- -y
ENV PATH="/root/.cargo/bin:${PATH}"

# Copy your Spring Boot and Rust code (assumes both are in the same context)
# Adjust according to your project structure
COPY . .
