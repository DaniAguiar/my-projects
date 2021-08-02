import React from "react";
import { render, waitForElement } from "react-dom";
import Todo from "./Todo";

describe("Tests for Todo component", () => {
  it("should add new task when form has been submitted", async () => {
    const { getByTestId } = render(<Todo />);
    const fieldNode = await waitForElement(() => getByTestId("form-field"));
    console.log(fieldNode);
  });
});
