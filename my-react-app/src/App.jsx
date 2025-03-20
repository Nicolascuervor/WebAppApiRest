import { useState, useEffect } from "react";
import './App.css';

function App() {
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch("http://127.0.0.2:8081/Postulaciones")
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Error al obtener los datos");
                }
                return response.json();
            })
            .then((data) => {
                setData(data);
                setLoading(false);
            })
            .catch((error) => {
                setError(error.message);
                setLoading(false);
            });
    }, []);

    return (
        <div className="min-h-screen bg-gray-100 flex flex-col items-center p-6">
            <h1 className="text-3xl font-bold text-gray-800 mb-6">Lista de Postulaciones</h1>

            {loading && <p className="text-blue-500">Cargando...</p>}
            {error && <p className="text-red-500">{error}</p>}

            {!loading && !error && data?.length > 0 ? (
                <div className="w-full max-w-5xl overflow-x-auto shadow-lg rounded-lg">
                    <table className="w-full border-collapse bg-white shadow-md rounded-lg">
                        <thead className="bg-blue-600 text-white">
                        <tr>
                            <th className="py-3 px-6 text-left">ID</th>
                            <th className="py-3 px-6 text-left">Nombre</th>
                            <th className="py-3 px-6 text-left">Apellido</th>
                            <th className="py-3 px-6 text-left">Email</th>
                            <th className="py-3 px-6 text-left">Telefono</th>
                        </tr>
                        </thead>
                        <tbody>
                        {data.map((post, index) => (
                            <tr key={index} className="border-b hover:bg-gray-50">
                                <td className="py-3 px-6">{post.id}</td>
                                <td className="py-3 px-6">{post.candidato.nombre}</td>
                                <td className="py-3 px-6">{post.candidato.apellido}</td>
                                <td className="py-3 px-6">{post.candidato.correo}</td>
                                <td className="py-3 px-6">{post.candidato.telefono || "No especificado"}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            ) : (
                !loading && <p className="text-gray-600">No hay postulaciones disponibles.</p>
            )}
        </div>
    );
}

export default App;
