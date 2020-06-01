CREATE TABLE positions (
  position VARCHAR(255) NOT NULL PRIMARY KEY,
  company_rank int NOT NULL
);

CREATE TABLE pension_providers (
  pension_provider_name VARCHAR(255) NOT NULL PRIMARY KEY
);

CREATE TABLE employees (
  id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  position VARCHAR(255) NOT NULL,
  age int NOT NULL,
  salary int NOT NULL,
  FOREIGN KEY (position) REFERENCES positions(position)
);

CREATE TABLE pension_funds (
  id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
  employee_id int NOT NULL,
  amount_contributed int NOT NULL DEFAULT 0,
  pension_provider VARCHAR(255) NOT NULL DEFAULT 'RetireRich',
  FOREIGN KEY (employee_id) REFERENCES employees(id),
  FOREIGN KEY (pension_provider) REFERENCES pension_providers(pension_provider_name)
);

