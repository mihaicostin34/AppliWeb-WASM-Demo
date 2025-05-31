import LoginForm from './pages/login';
import useToken from './pages/useToken';
import Create from './pages/admin/admin_create';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from './pages/layout';
import Dashboard from './pages/dashboard';

function App(){

  const { token, setToken } = useToken();

  if(!token){
    return < LoginForm setToken = { setToken }/>
  }

  return (
    <>
    <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout />}>
                    <Route index element={<Dashboard />} />
                    <Route path="/" element={<Dashboard />} />
                    <Route path="/new" element={<Create />} />
                    {/* <Route path="*" element={<NoPage />} /> */}
                </Route>
            </Routes>
        </BrowserRouter>
    </>
  );
}

export default App