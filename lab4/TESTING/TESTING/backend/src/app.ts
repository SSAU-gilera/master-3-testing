import express from "express";
import CalculatorFactory from "./calculator/calculator";
import { ENumberSystemType } from "../../contracts/number-system-type.enum";
import { EOperation } from "../../contracts/operation.enum";
import cors from "cors";

const app = express();
const port = 3000;

const calculatorFactory = new CalculatorFactory();

app.use(cors());

app.get("/:system/:operation/:a/:b", (req, res) => {
  const system = req.params.system as ENumberSystemType;
  const operation = req.params.operation as EOperation;
  const calculator = calculatorFactory.createCalculator(system, operation);

  if (calculator) {
    const result = calculator.getResult(req.params.a, req.params.b);
    res.json({ result });

    return;
  }

  res.statusCode = 400;
  res.send(`Wrong input`);
});

app.listen(port, () => {
  return console.log(`Express is listening at http://localhost:${port}`);
});
