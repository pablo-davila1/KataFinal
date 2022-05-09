import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import routes from "./config/routes"
const App = () => {
  return (
    <div className="App">
      <Router>
        <Routes>
          {routes.map((route, index) => (
            <Route
              key={index}
              path={route.patch}
              element={<route.component />}
            />
          ))}
        </Routes>
      </Router>
    </div>
  );
};

export default App;

