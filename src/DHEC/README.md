# Elliptic Curve Diffie-Hellman (ECDH) Protocol Implementation

The Elliptic Curve Diffie-Hellman (ECDH) Protocol Implementation in this repository showcases the secure exchange of keys using elliptic curve cryptography. This protocol enables two entities (Alice and Bob) to establish a shared secret key over an insecure communication channel.

## Components

The ECDH Protocol Implementation consists of the following components:

1. **DHEC Client**: The DHEC Client generates a private key, calculates the public key, sends it to the DHEC Server, and receives the server's public key. It then calculates the shared secret key using elliptic curve operations.

2. **DHEC Server**: The DHEC Server receives the client's public key, generates its own private and public keys, and sends the public key to the client. It then calculates the shared secret key using elliptic curve operations.

## How It Works

1. The ECDH protocol utilizes elliptic curve mathematics to generate public-private key pairs for secure key exchange.

2. Both the client and the server generate their respective public-private key pairs and exchange public keys.

3. The shared secret key is calculated independently by both parties using their private key and the received public key.

## Usage

1. Compile and run the DHEC Server and DHEC Client components.

2. The DHEC Client will generate its private key, calculate the public key, and send it to the DHEC Server.

3. The DHEC Server will generate its own private and public keys, calculate the shared secret key, and send its public key to the DHEC Client.

4. Both the client and the server calculate the shared secret key independently based on their private key and the received public key.

## Getting Started

To get started with the DHEC Protocol Implementation, navigate to the [DHEC folder](./src/DHEC) and refer to the respective README files for the DHEC Client and DHEC Server.

## Contribution

This project is open for contributions. If you have ideas for enhancements or bug fixes, feel free to contribute by opening an issue or a pull request on [GitHub](https://github.com/lifefire1/crypto/).

## License

The code in this repository is distributed under the [MIT License](./LICENSE), which means you can use, modify, and distribute it for both personal and commercial purposes.

Start establishing secure key exchange using the ECDH Protocol today!

---
*Note: This README provides an overview of the DHEC Protocol Implementation. Please refer to the individual README files within the DHEC subdirectories for specific details and instructions.*
