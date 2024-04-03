import './App.css';
import {ArtistPage} from "./pages/ArtistPage";
import {ApolloClient, InMemoryCache, ApolloProvider, HttpLink, from} from "@apollo/client";
import {onError} from "@apollo/client/link/error";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";

const errorLink = onError(({graphqlErrors, networkError}) => {
    if(graphqlErrors) {
        graphqlErrors.map(({message, location, path}) => {
            console.log(`Graphql error ${message}`);
        })
    }
});
const link = from([
    errorLink,
    new HttpLink({uri: "http://localhost:8080/graphql"}),
]);
const client = new ApolloClient({
    cache: new InMemoryCache(),
    link: link,
});
function App() {
  return (
      <ApolloProvider client={client}>
          <div className="App">
              <Router>
                  <Routes>
                      <Route path="/artists/:artistName" element={<ArtistPage />} /> {/* Use the 'element' prop to specify the component */}
                      {/*<Route path="/artists">*/}
                      {/*    <ArtistPage/>*/}
                      {/*</Route>*/}
                  </Routes>

              </Router>

          </div>
      </ApolloProvider>

  );
}

export default App;
