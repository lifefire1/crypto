# Diffie-Hellman Protocol Implementation

The Diffie-Hellman (DH) Protocol Implementation in this repository demonstrates the secure exchange of a shared secret key between two entities (Alice and Bob). The protocol allows them to establish a shared secret key over an insecure communication channel, which can then be used for encrypted communication.

## Components

The DH Protocol Implementation consists of the following components:

1. **DH Client**: The DH Client initiates the protocol by generating a public key and sending it to the DH Server. It then calculates the shared secret key based on the received public key from the server.

2. **DH Server**: The DH Server receives the public key from the client, generates its own public key, and sends it back to the client. It then calculates the shared secret key using its private key.

## How It Works

1. The DH protocol relies on the mathematical properties of modular exponentiation and the difficulty of the discrete logarithm problem to achieve secure key exchange.

2. Both the client and the server generate a public-private key pair and exchange public keys.

3. The shared secret key is calculated independently by both parties using the received public key and their own private key.

## Usage

1. Compile and run the DH Server and DH Client components.

2. The DH Client will generate its public key and send it to the DH Server.

3. The DH Server will generate its own public key, calculate the shared secret key, and send its public key to the DH Client.

4. Both the client and the server calculate the shared secret key independently based on the received public key and their private key.

## Getting Started

To get started with the DH Protocol Implementation, navigate to the [DH folder](./DH) and refer to the respective README files for the DH Client and DH Server.

## Contribution

This project is open for contributions. If you have ideas for enhancements or bug fixes, feel free to contribute by opening an issue or a pull request on [GitHub](https://github.com/lifefire1/crypto).

## License

The code in this repository is distributed under the [MIT License](./LICENSE), which means you can use, modify, and distribute it for both personal and commercial purposes.

Start establishing secure communication using the DH Protocol today!

---
*Note: This README provides an overview of the DH Protocol Implementation. Please refer to the individual README files within the DH subdirectories for specific details and instructions.*
