### Hexlet tests and linter status:
[![Actions Status](https://github.com/miskaris-wq/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/miskaris-wq/java-project-78/actions)

[![Java CI](https://github.com/miskaris-wq/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/miskaris-wq/java-project-78/actions/workflows/main.yml)

[![Maintainability](https://api.codeclimate.com/v1/badges/c0c76e34c73bb9b720ce/maintainability)](https://codeclimate.com/github/miskaris-wq/java-project-78/maintainability)

[![Test Coverage](https://api.codeclimate.com/v1/badges/c0c76e34c73bb9b720ce/test_coverage)](https://codeclimate.com/github/miskaris-wq/java-project-78/test_coverage)

# java-project-78: Hexlet Project - Data Validator

## Description

The Data Validator is a library for validating various types of data. 
Similar libraries exist in many programming languages since most applications handle external data that requires validation—especially user-submitted form data. 
This project is inspired by the yup validation library.

## Features

### Supported Validations
- **Strings**:
    - `required()` - Non-null and non-empty
    - `minLength(int)` - Minimum length
    - `contains(String)` - Substring check
- **Numbers**:
    - `required()` - Non-null
    - `positive()` - > 0
    - `range(int, int)` - Value range
- **Maps**:
    - `sizeof(int)` - Exact size
    - `shape(Map)` - Nested validation

### Architecture
- Fluent interface design
- Extensible schema system
- SOLID principles compliant
- Zero external dependencies

## Installation

1.  Clone the repository:

    ```
    git clone https://github.com/miskaris-wq/java-project-78.git
    cd java-project-78
    ```

2.  Build the project (using Make):

    ```
    make build
    ```

## Requirements

*   Java 11 or higher.
*   Make installed (for building and running with `make build` and `make run` commands).

## Dependencies

The project has no external dependencies other than the standard Java library.

## Authors

*   miskaris-wq

## Acknowledgments

Thanks to Hexlet for the educational program and the provided platform for developing this project.
